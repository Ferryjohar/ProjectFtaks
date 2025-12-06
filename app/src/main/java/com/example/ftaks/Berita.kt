package com.example.ftaks

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Berita(
    val judul: String,
    val deskripsiberita: String,
    val tanggal: String,
    val gambar: Int,
    val kategori: BeritaKategori
) : Parcelable
enum class BeritaKategori(val displayName: String){
    Akademik("Akademik"),
    Event("Event Kampus"),
    Umum("Informasi Umum")
}