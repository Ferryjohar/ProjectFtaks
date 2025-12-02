package com.example.ftaks

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TugasFragment : Fragment() {

    private lateinit var adapterAktif: TugasAdapter
    private lateinit var adapterSelesai: TugasAdapter

    private val listTugasAktif: ArrayList<Tugas> = ArrayList()
    private val listTugasSelesai: ArrayList<Tugas> = ArrayList()

    private lateinit var tvLabelTugasAktif: TextView
    private lateinit var tvLabelSelesai: TextView

    private val tambahTugasLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val tugasBaru = result.data?.getParcelableExtra<Tugas>("TUGAS_BARU")
            if (tugasBaru != null) {
                listTugasAktif.add(0, tugasBaru)
                adapterAktif.notifyItemInserted(0)
                updateLabels()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tugas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvTugasAktif: RecyclerView = view.findViewById(R.id.rv_tugas_aktif)
        val rvTugasSelesai: RecyclerView = view.findViewById(R.id.rv_tugas_selesai)
        tvLabelTugasAktif = view.findViewById(R.id.tv_label_tugas_aktif)
        tvLabelSelesai = view.findViewById(R.id.tv_label_selesai)
        val btnTambah: Button = view.findViewById(R.id.btn_tambah_tugas)

        // Data Dummy (Hanya muncul jika list kosong)
        if (listTugasAktif.isEmpty() && listTugasSelesai.isEmpty()) {
            listTugasAktif.addAll(buatDataTugasAktif())
        }

        // CATATAN: Kode "bersihkanTugasLama()" SUDAH DIHAPUS DI SINI.
        // Jadi tugas selesai akan tetap ada selamanya.

        // 1. ADAPTER TUGAS AKTIF
        adapterAktif = TugasAdapter(
            requireContext(),
            listTugasAktif,
            onDeleteClick = { pos -> konfirmasiHapus(listTugasAktif, adapterAktif, pos) },
            onDoneClick = { pos -> pindahkanKeSelesai(pos) }
        )
        rvTugasAktif.layoutManager = LinearLayoutManager(requireContext())
        rvTugasAktif.adapter = adapterAktif

        // 2. ADAPTER TUGAS SELESAI
        adapterSelesai = TugasAdapter(
            requireContext(),
            listTugasSelesai,
            onDeleteClick = { pos -> konfirmasiHapus(listTugasSelesai, adapterSelesai, pos) },
            onDoneClick = { } // Tidak melakukan apa-apa (sudah selesai)
        )
        rvTugasSelesai.layoutManager = LinearLayoutManager(requireContext())
        rvTugasSelesai.adapter = adapterSelesai

        updateLabels()

        btnTambah.setOnClickListener {
            val intent = Intent(requireContext(), TambahTugasActivity::class.java)
            tambahTugasLauncher.launch(intent)
        }
    }

    private fun pindahkanKeSelesai(position: Int) {
        val tugas = listTugasAktif[position]

        tugas.isSelesai = true
        // Kita tidak perlu mencatat waktuSelesai lagi karena tidak ada penghapusan otomatis

        listTugasAktif.removeAt(position)
        listTugasSelesai.add(0, tugas) // Masuk ke paling atas list selesai

        adapterAktif.notifyItemRemoved(position)
        adapterAktif.notifyItemRangeChanged(position, listTugasAktif.size)

        adapterSelesai.notifyItemInserted(0)

        updateLabels()
        Toast.makeText(requireContext(), "Tugas Selesai!", Toast.LENGTH_SHORT).show()
    }

    private fun konfirmasiHapus(list: ArrayList<Tugas>, adapter: TugasAdapter, position: Int) {
        AlertDialog.Builder(requireContext())
            .setTitle("Hapus Tugas")
            .setMessage("Yakin ingin menghapus tugas ini?")
            .setPositiveButton("Hapus") { _, _ ->
                list.removeAt(position)
                adapter.notifyItemRemoved(position)
                adapter.notifyItemRangeChanged(position, list.size)
                updateLabels()
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    private fun updateLabels() {
        tvLabelTugasAktif.text = "Tugas Aktif (${listTugasAktif.size})"
        tvLabelSelesai.text = "Selesai (${listTugasSelesai.size})"
    }

    private fun buatDataTugasAktif(): List<Tugas> {
        return listOf(
            Tugas("Tugas Matakuliah Manajemen", "membuat SCRUM", "Kamis, 12 Juni", Prioritas.TINGGI),
            Tugas("Tugas UI/UX", "Desain Prototype", "Jumat, 13 Juni", Prioritas.SEDANG)
        )
    }
}