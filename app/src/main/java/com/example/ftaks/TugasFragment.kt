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

    val listTugasAktif: ArrayList<Tugas> = ArrayList()
    val listTugasSelesai: ArrayList<Tugas> = ArrayList()

    val tambahTugasLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val tugasBaru = result.data?.getParcelableExtra<Tugas>("TUGAS_BARU")
            if (tugasBaru != null) {
                listTugasAktif.add(0, tugasBaru)

                val rvAktif = view?.findViewById<RecyclerView>(R.id.rv_tugas_aktif)
                rvAktif?.adapter?.notifyItemInserted(0)

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
        val btnTambah: Button = view.findViewById(R.id.btn_tambah_tugas)

        if (listTugasAktif.isEmpty() && listTugasSelesai.isEmpty()) {
            listTugasAktif.addAll(buatDataTugasAktif())
        }

        val adapterAktif = TugasAdapter(
            requireContext(),
            listTugasAktif,
            onDeleteClick = { pos -> konfirmasiHapus(listTugasAktif, pos, true) },
            onDoneClick = { pos -> pindahkanKeSelesai(pos) }
        )
        rvTugasAktif.layoutManager = LinearLayoutManager(requireContext())
        rvTugasAktif.adapter = adapterAktif

        val adapterSelesai = TugasAdapter(
            requireContext(),
            listTugasSelesai,
            onDeleteClick = { pos -> konfirmasiHapus(listTugasSelesai, pos, false) },
            onDoneClick = { }
        )
        rvTugasSelesai.layoutManager = LinearLayoutManager(requireContext())
        rvTugasSelesai.adapter = adapterSelesai

        updateLabels()

        btnTambah.setOnClickListener {
            context?.let { ctx ->
                val intent = Intent(ctx, TambahTugasActivity::class.java)
                try {
                    tambahTugasLauncher.launch(intent)
                } catch (e: Exception) {
                    Toast.makeText(ctx, "Gagal membuka halaman: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun pindahkanKeSelesai(position: Int) {
        val tugas = listTugasAktif[position]
        tugas.isSelesai = true

        listTugasAktif.removeAt(position)
        listTugasSelesai.add(0, tugas)

        val rvAktif = view?.findViewById<RecyclerView>(R.id.rv_tugas_aktif)
        val rvSelesai = view?.findViewById<RecyclerView>(R.id.rv_tugas_selesai)

        rvAktif?.adapter?.notifyItemRemoved(position)
        rvAktif?.adapter?.notifyItemRangeChanged(position, listTugasAktif.size)

        rvSelesai?.adapter?.notifyItemInserted(0)

        updateLabels()
        Toast.makeText(requireContext(), "Tugas Selesai!", Toast.LENGTH_SHORT).show()
    }

    fun konfirmasiHapus(list: ArrayList<Tugas>, position: Int, isAktif: Boolean) {
        AlertDialog.Builder(requireContext())
            .setTitle("Hapus Tugas")
            .setMessage("Yakin ingin menghapus tugas ini?")
            .setPositiveButton("Hapus") { _, _ ->
                list.removeAt(position)

                val rvId = if (isAktif) R.id.rv_tugas_aktif else R.id.rv_tugas_selesai
                val rv = view?.findViewById<RecyclerView>(rvId)

                rv?.adapter?.notifyItemRemoved(position)
                rv?.adapter?.notifyItemRangeChanged(position, list.size)

                updateLabels()
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    fun updateLabels() {
        val tvLabelTugasAktif = view?.findViewById<TextView>(R.id.tv_label_tugas_aktif)
        val tvLabelSelesai = view?.findViewById<TextView>(R.id.tv_label_selesai)

        tvLabelTugasAktif?.text = "Tugas Aktif (${listTugasAktif.size})"
        tvLabelSelesai?.text = "Selesai (${listTugasSelesai.size})"
    }

    fun buatDataTugasAktif(): List<Tugas> {
        return listOf(
            Tugas("Tugas Matakuliah Manajemen", "membuat SCRUM", "Kamis, 12 Juni", Prioritas.TINGGI),
            Tugas("Tugas UI/UX", "Desain Prototype", "Jumat, 13 Juni", Prioritas.SEDANG)
        )
    }
}