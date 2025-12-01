package com.example.ftaks

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BeritaFragment1 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_berita, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvBerita: RecyclerView = view.findViewById(R.id.rv_berita)

        // 1. Buat Data Dummy (Pastikan Anda punya gambar di drawable)
        // Ganti R.drawable.img_berita_1 dengan nama file gambar Anda
        val listBerita = listOf(
            Berita(
                judul = "Kisah Shareent, Lolos ke FEB UGM Tanpa Tes",
                ringkasan = "Selengkapnya.",
                isiLengkap = "Ini adalah isi lengkap berita tentang Shareent yang sangat inspiratif. Dia berhasil masuk UGM tanpa tes berkat prestasinya yang gemilang selama di sekolah menengah...",
                tanggal = "Senin, 12 Juni 2025",
                gambar = R.drawable.img_berita_1 // Ganti dengan gambar asli Anda
            ),
            Berita(
                judul = "15 PTN Masih Buka Jalur Mandiri 2025",
                ringkasan = "Selengkapnya.",
                isiLengkap = "Bagi yang gagal SNBT jangan khawatir, berikut adalah daftar 15 PTN yang masih membuka jalur mandiri dengan kuota yang cukup banyak...",
                tanggal = "Selasa, 13 Juni 2025",
                gambar = R.drawable.img_berita_2 // Ganti dengan gambar asli Anda
            )
        )

        // 2. Setup Adapter dengan Listener Klik
        val adapter = BeritaAdapter(requireContext(), listBerita) { beritaKlik ->
            // Saat item diklik, pindah ke Detail Activity
            val intent = Intent(requireContext(), DetailBeritaActivity1::class.java)
            intent.putExtra("DATA_BERITA", beritaKlik)
            startActivity(intent)
        }

        rvBerita.layoutManager = LinearLayoutManager(requireContext())
        rvBerita.adapter = adapter
    }
}