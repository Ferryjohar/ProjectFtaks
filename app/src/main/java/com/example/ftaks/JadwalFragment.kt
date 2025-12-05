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

    val tambahJadwalLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val jadwalBaru = result.data?.getParcelableExtra<Jadwal>("JADWAL_BARU")

            if (jadwalBaru != null) {
                DataPenyimpanan.listJadwal.add(jadwalBaru)
                urutkanJadwal()

                val rvJadwal = view?.findViewById<RecyclerView>(R.id.rv_jadwal)
                rvJadwal?.adapter?.notifyDataSetChanged()
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

        val adapter = JadwalAdapter(requireContext(), DataPenyimpanan.listJadwal) { position ->
            konfirmasiHapus(position)
        }

        rvJadwal.layoutManager = LinearLayoutManager(requireContext())
        rvJadwal.adapter = adapter

        btnTambah.setOnClickListener {
            context?.let { ctx ->
                val intent = Intent(ctx, TambahJadwalActivity::class.java)

                try {
                    tambahJadwalLauncher.launch(intent)
                } catch (e: Exception) {
                    Toast.makeText(ctx, "Gagal membuka halaman: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun urutkanJadwal() {
        DataPenyimpanan.listJadwal.sortBy { it.hariTanggal }
    }

    fun konfirmasiHapus(position: Int) {
        AlertDialog.Builder(requireContext())
            .setTitle("Hapus Jadwal")
            .setMessage("Yakin ingin menghapus jadwal mata kuliah ini?")
            .setPositiveButton("Hapus") { _, _ ->
                DataPenyimpanan.listJadwal.removeAt(position)

                val rvJadwal = view?.findViewById<RecyclerView>(R.id.rv_jadwal)
                rvJadwal?.adapter?.notifyDataSetChanged()

                Toast.makeText(requireContext(), "Jadwal dihapus", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Batal", null)
            .show()
    }
}