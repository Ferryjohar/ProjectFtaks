package com.example.ftaks

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class JadwalFragment : Fragment() {

    private lateinit var jadwalAdapter: JadwalAdapter
    // Menggunakan ArrayList agar data bisa ditambah/dihapus/diurutkan
    private val listJadwal: ArrayList<Jadwal> = ArrayList()

    // --- 1. LAUNCHER (Menerima Data Balik) ---
    private val tambahJadwalLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // Ambil data jadwal baru
            val jadwalBaru = result.data?.getParcelableExtra<Jadwal>("JADWAL_BARU")

            if (jadwalBaru != null) {
                listJadwal.add(jadwalBaru)

                // PENTING: Urutkan list agar tanggal yang sama berkumpul
                urutkanJadwal()

                // PENTING: Pakai notifyDataSetChanged() agar logika header di Adapter dihitung ulang
                jadwalAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_jadwal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvJadwal: RecyclerView = view.findViewById(R.id.rv_jadwal)
        val btnTambah: Button = view.findViewById(R.id.btn_tambah_jadwal)

        // --- 2. DATA DUMMY ---
        if (listJadwal.isEmpty()) {
            // Masukkan data acak
            listJadwal.add(Jadwal("Senin, 15 Des 2025", "Tata Kelola IT", "R. 313", "08.40 - 10.40"))
            listJadwal.add(Jadwal("Selasa, 16 Des 2025", "Kripto", "Lab 301", "07.30 - 10.00"))
            listJadwal.add(Jadwal("Senin, 15 Des 2025", "Pancasila", "R. 311", "17.30 - 19.00"))

            // Urutkan data dummy agar tampilan awal langsung rapi
            urutkanJadwal()
        }

        // --- 3. SETUP ADAPTER ---
        jadwalAdapter = JadwalAdapter(requireContext(), listJadwal) { position ->
            konfirmasiHapus(position) // Aksi Hapus
        }

        rvJadwal.layoutManager = LinearLayoutManager(requireContext())
        rvJadwal.adapter = jadwalAdapter

        // --- 4. KLIK TOMBOL TAMBAH ---
        btnTambah.setOnClickListener {
            val intent = Intent(requireContext(), TambahJadwalActivity::class.java)
            tambahJadwalLauncher.launch(intent)
        }
    }

    // --- 5. FUNGSI URUTKAN (SORTING) ---
    private fun urutkanJadwal() {
        // Mengurutkan list berdasarkan teks hari/tanggal
        // Ini membuat item dengan tanggal yang sama menjadi berdekatan
        listJadwal.sortBy { it.hariTanggal }
    }

    // --- 6. FUNGSI HAPUS ---
    private fun konfirmasiHapus(position: Int) {
        AlertDialog.Builder(requireContext())
            .setTitle("Hapus Jadwal")
            .setMessage("Yakin ingin menghapus jadwal mata kuliah ini?")
            .setPositiveButton("Hapus") { _, _ ->
                listJadwal.removeAt(position)

                // Kita gunakan notifyDataSetChanged() karena penghapusan bisa mengubah
                // status header item di bawahnya (misal item ke-2 naik jadi ke-1)
                jadwalAdapter.notifyDataSetChanged()

                Toast.makeText(requireContext(), "Jadwal dihapus", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Batal", null)
            .show()
    }
}