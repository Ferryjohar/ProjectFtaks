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

    // Siapkan Fragment
    private val fragmentTugas = TugasFragment()
    private val fragmentJadwal = JadwalFragment()
    private val fragmentBerita = BeritaFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setup Edge-to-Edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(0, systemBars.top, 0, 0)
            insets
        }

        // 1. Set Username (Header tetap di Activity)
        val username = intent.getStringExtra(LoginActivity.KEY_USERNAME)
        val tvUsername: TextView = findViewById(R.id.tv_username)
        tvUsername.text = if (!username.isNullOrEmpty()) username else "Pengguna"

        // 2. Inisialisasi Tombol Tab
        val tabTugas: TextView = findViewById(R.id.tab_tugas)
        val tabJadwal: TextView = findViewById(R.id.tab_jadwal)
        val tabBerita: TextView = findViewById(R.id.tab_berita)

        // 3. Tampilkan Fragment Awal (Tugas)
        gantiFragment(fragmentTugas)
        aturWarnaTab(tabTugas, tabJadwal, tabBerita)

        // 4. Listener Tombol TUGAS
        tabTugas.setOnClickListener {
            gantiFragment(fragmentTugas)
            aturWarnaTab(tabTugas, tabJadwal, tabBerita)
        }

        // 5. Listener Tombol JADWAL
        tabJadwal.setOnClickListener {
            // Jika Anda ingin "Tab Jadwal" langsung membuka halaman tambah jadwal (Activity baru):
            // val intent = Intent(this, TambahJadwalActivity::class.java)
            // startActivity(intent)

            // TAPI, jika ingin pindah Fragment Jadwal:
            gantiFragment(fragmentJadwal)
            aturWarnaTab(tabJadwal, tabTugas, tabBerita)
        }

        // 6. Listener Tombol BERITA
        tabBerita.setOnClickListener {
            gantiFragment(fragmentBerita)
            aturWarnaTab(tabBerita, tabTugas, tabJadwal)
        }
    }

    // Fungsi Ganti Fragment
    private fun gantiFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            commit()
        }
    }

    // Fungsi Atur Warna Tombol
    private fun aturWarnaTab(aktif: TextView, pasif1: TextView, pasif2: TextView) {
        // Tombol Aktif: Background Biru, Teks Putih
        aktif.background = ContextCompat.getDrawable(this, R.drawable.bg_tab_selected)
        aktif.setTextColor(Color.WHITE)

        // Tombol Pasif: Background Kosong, Teks Abu
        pasif1.background = null
        pasif1.setTextColor(ContextCompat.getColor(this, R.color.home_text_secondary))

        pasif2.background = null
        pasif2.setTextColor(ContextCompat.getColor(this, R.color.home_text_secondary))
    }
}