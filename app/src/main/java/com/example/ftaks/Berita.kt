package com.example.ftaks

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Berita(
    val judul: String,
    val deskripsiberita: String,
    val tanggal: String,
    val gambar: Int,
    val kategori: BeritaCategory
) : Parcelable
enum class BeritaCategory(val displayName: String){
    Akademik("Akademik"),
    Event("Event Kampus"),
    Umum("Informasi Umum")
}