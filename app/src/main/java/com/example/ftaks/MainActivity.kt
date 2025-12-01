package com.example.ftaks

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    val tambahJadwalLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val jadwalBaru = data?.getParcelableExtra<Jadwal>("JADWAL_BARU")

            if (jadwalBaru != null) {
                Toast.makeText(
                    this,
                    "Jadwal ${jadwalBaru.mataKuliah} berhasil ditambahkan!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(0, systemBars.top, 0, 0)
            insets
        }

        val username = intent.getStringExtra(LoginActivity.KEY_USERNAME)
        val tvUsername: TextView = findViewById(R.id.tv_username)

        if (username != null && username.isNotEmpty()) {
            tvUsername.text = username
        } else {
            tvUsername.text = "Pengguna"
        }
        val tabJadwal: TextView = findViewById(R.id.tab_jadwal)
        tabJadwal.setOnClickListener {
            val intent = Intent(this, TambahJadwalActivity::class.java)
            tambahJadwalLauncher.launch(intent)
        }
        val rvTugasAktif: RecyclerView = findViewById(R.id.rv_tugas_aktif)
        val rvTugasSelesai: RecyclerView = findViewById(R.id.rv_tugas_selesai)
        val tvLabelTugasAktif: TextView = findViewById(R.id.tv_label_tugas_aktif)
        val tvLabelSelesai: TextView = findViewById(R.id.tv_label_selesai)

        val listDataTugasAktif = buatDataTugasAktif()
        val listDataTugasSelesai = buatDataTugasSelesai()

        tvLabelTugasAktif.text = "Tugas Aktif (${listDataTugasAktif.size})"
        tvLabelSelesai.text = "Selesai (${listDataTugasSelesai.size})"

        val adapterAktif = TugasAdapter(this, listDataTugasAktif)
        rvTugasAktif.layoutManager = LinearLayoutManager(this)
        rvTugasAktif.adapter = adapterAktif

        val adapterSelesai = TugasAdapter(this, listDataTugasSelesai)
        rvTugasSelesai.layoutManager = LinearLayoutManager(this)
        rvTugasSelesai.adapter = adapterSelesai
    }

    fun buatDataTugasAktif(): List<Tugas> {
        return listOf(
            Tugas("Tugas Matakuliah Manajemen proyek", "membuat SCRUM", "Kamis, 12 Juni 2025", Prioritas.TINGGI),
            Tugas("Tugas Matakuliah Manajemen proyek", "membuat SCRUM", "Kamis, 12 Juni 2025", Prioritas.SEDANG),
            Tugas("Tugas UI & UX", "Project UAS portofolio Desain", "Minggu, 15 Juni 2025", Prioritas.RENDAH)
        )
    }

    fun buatDataTugasSelesai(): List<Tugas> {
        return listOf(
            Tugas("Tugas UI & UX", "Project UAS portofolio Desain", "Minggu, 15 Juni 2025", Prioritas.RENDAH)
        )
    }
}