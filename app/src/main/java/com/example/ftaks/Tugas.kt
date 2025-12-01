package com.example.ftaks

// Enum untuk mempermudah pengelolaan prioritas
enum class Prioritas {
    TINGGI,
    SEDANG,
    RENDAH
}

// Data class untuk menyimpan semua info dari satu tugas
data class Tugas(
    val matakuliah: String,
    val deskripsi: String,
    val tanggal: String,
    val prioritas: Prioritas
)