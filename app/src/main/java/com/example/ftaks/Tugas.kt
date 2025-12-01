package com.example.ftaks

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class Prioritas : Parcelable {
    TINGGI, SEDANG, RENDAH
}

@Parcelize
data class Tugas(
    val matakuliah: String,
    val deskripsi: String,
    val tanggal: String,
    val prioritas: Prioritas,
    var isSelesai: Boolean = false, // Penanda status
    var waktuSelesai: Long = 0 // Menyimpan waktu (timestamp) saat selesai
) : Parcelable