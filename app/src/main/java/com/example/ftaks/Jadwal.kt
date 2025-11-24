package com.example.ftaks

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Jadwal(
    val hariTanggal: String,
    val mataKuliah: String,
    val ruangKelas: String,
    val waktu: String
) : Parcelable