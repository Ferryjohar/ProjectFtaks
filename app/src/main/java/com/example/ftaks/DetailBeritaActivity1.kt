package com.example.ftaks

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class DetailBeritaActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_berita)
        // 1. Ambil Data dari Intent
        val berita = intent.getParcelableExtra<Berita>("DATA_BERITA")

        // 2. Hubungkan View
        val ivGambar: ImageView = findViewById(R.id.iv_detail_gambar)
        val tvJudul: TextView = findViewById(R.id.tv_detail_judul)
        val tvTanggal: TextView = findViewById(R.id.tv_detail_tanggal)
        val tvIsi: TextView = findViewById(R.id.tv_detail_isi)

        // 3. Tampilkan Data
        if (berita != null) {
            ivGambar.setImageResource(berita.gambar)
            tvJudul.text = berita.judul
            tvTanggal.text = berita.tanggal
            tvIsi.text = berita.isiLengkap
        }
    }
}