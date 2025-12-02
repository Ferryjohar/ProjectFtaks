package com.example.ftaks

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailBeritaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_berita)
        val berita = intent.getParcelableExtra<Berita>("DATA_BERITA")
        val ivGambar: ImageView = findViewById(R.id.iv_detail_gambar)
        val tvJudul: TextView = findViewById(R.id.tv_detail_judul)
        val tvTanggal: TextView = findViewById(R.id.tv_detail_tanggal)
        val tvIsi: TextView = findViewById(R.id.tv_detail_isi)

        if (berita != null) {
            ivGambar.setImageResource(berita.gambar)
            tvJudul.text = berita.judul
            tvTanggal.text = berita.tanggal
            tvIsi.text = berita.isiLengkap
        }
    }
}