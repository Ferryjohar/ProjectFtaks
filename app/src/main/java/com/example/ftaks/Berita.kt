package com.example.ftaks

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Berita(
    val judul: String,
    val ringkasan: String, // Teks pendek untuk di list
    val isiLengkap: String, // Teks panjang untuk di detail
    val tanggal: String,
    val gambar: Int // ID dari drawable (contoh: R.drawable.img_kampus)
) : Parcelable