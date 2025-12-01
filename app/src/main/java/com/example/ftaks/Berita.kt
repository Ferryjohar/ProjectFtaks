package com.example.ftaks

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Berita(
    val judul: String,
    val ringkasan: String,
    val isiLengkap: String,
    val tanggal: String,
    val gambar: Int
) : Parcelable