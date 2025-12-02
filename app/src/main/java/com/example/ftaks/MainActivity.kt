package com.example.ftaks

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    val fragmentTugas = TugasFragment()
    val fragmentJadwal = JadwalFragment()
    val fragmentBerita = BeritaFragment()

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
        tvUsername.text = if (!username.isNullOrEmpty()) username else "Pengguna"

        val tabTugas: TextView = findViewById(R.id.tab_tugas)
        val tabJadwal: TextView = findViewById(R.id.tab_jadwal)
        val tabBerita: TextView = findViewById(R.id.tab_berita)

        gantiFragment(fragmentTugas)
        aturWarnaTab(tabTugas, tabJadwal, tabBerita)

        tabTugas.setOnClickListener {
            gantiFragment(fragmentTugas)
            aturWarnaTab(tabTugas, tabJadwal, tabBerita)
        }

        tabJadwal.setOnClickListener {
            gantiFragment(fragmentJadwal)
            aturWarnaTab(tabJadwal, tabTugas, tabBerita)
        }

        tabBerita.setOnClickListener {
            gantiFragment(fragmentBerita)
            aturWarnaTab(tabBerita, tabTugas, tabJadwal)
        }
    }

    fun gantiFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            commit()
        }
    }

    fun aturWarnaTab(aktif: TextView, pasif1: TextView, pasif2: TextView) {
        aktif.background = ContextCompat.getDrawable(this, R.drawable.bg_tab_selected)
        aktif.setTextColor(Color.WHITE)

        pasif1.background = null
        pasif1.setTextColor(ContextCompat.getColor(this, R.color.home_text_secondary))

        pasif2.background = null
        pasif2.setTextColor(ContextCompat.getColor(this, R.color.home_text_secondary))
    }
}