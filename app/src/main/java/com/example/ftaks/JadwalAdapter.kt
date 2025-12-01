package com.example.ftaks

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class JadwalAdapter(
    private val context: Context,
    private val listJadwal: List<Jadwal>,
    private val onDeleteClick: (Int) -> Unit
) : RecyclerView.Adapter<JadwalAdapter.JadwalViewHolder>() {

    inner class JadwalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvMatkul: TextView = itemView.findViewById(R.id.tv_matkul)
        val tvWaktu: TextView = itemView.findViewById(R.id.tv_waktu)
        val tvRuang: TextView = itemView.findViewById(R.id.tv_ruang)
        val tvHari: TextView = itemView.findViewById(R.id.tv_hari_tanggal)
        val btnHapus: ImageView = itemView.findViewById(R.id.btn_hapus_jadwal)
        val container: ConstraintLayout = itemView.findViewById(R.id.container_jadwal)
        val divider: View = itemView.findViewById(R.id.divider_line)

        init {
            btnHapus.setOnClickListener { onDeleteClick(adapterPosition) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JadwalViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_jadwal, parent, false)
        return JadwalViewHolder(view)
    }

    override fun getItemCount(): Int = listJadwal.size

    override fun onBindViewHolder(holder: JadwalViewHolder, position: Int) {
        val jadwal = listJadwal[position]

        // Set Data Teks
        holder.tvMatkul.text = jadwal.mataKuliah
        holder.tvWaktu.text = jadwal.waktu
        holder.tvRuang.text = jadwal.ruangKelas
        holder.tvHari.text = jadwal.hariTanggal

        // --- LOGIKA GROUPING & MENYATU ---

        // Cek data sebelumnya dan sesudahnya
        val prevJadwal = if (position > 0) listJadwal[position - 1] else null
        val nextJadwal = if (position < listJadwal.size - 1) listJadwal[position + 1] else null

        val isSameDayAsPrev = prevJadwal != null && prevJadwal.hariTanggal == jadwal.hariTanggal
        val isSameDayAsNext = nextJadwal != null && nextJadwal.hariTanggal == jadwal.hariTanggal

        // 1. ATUR VISIBILITY HEADER TANGGAL
        if (isSameDayAsPrev) {
            holder.tvHari.visibility = View.GONE // Sembunyikan jika sama dengan atasnya
        } else {
            holder.tvHari.visibility = View.VISIBLE // Tampilkan jika beda (atau item pertama)
        }

        // 2. ATUR BENTUK BACKGROUND (BULAT/KOTAK)
        if (!isSameDayAsPrev && !isSameDayAsNext) {
            // Sendirian: Bulat Semua
            holder.container.background = ContextCompat.getDrawable(context, R.drawable.bg_item_single)
            holder.divider.visibility = View.GONE
        }
        else if (!isSameDayAsPrev && isSameDayAsNext) {
            // Paling Atas: Bulat Atas, Kotak Bawah
            holder.container.background = ContextCompat.getDrawable(context, R.drawable.bg_item_top)
            holder.divider.visibility = View.VISIBLE // Tampilkan garis pemisah di bawahnya
        }
        else if (isSameDayAsPrev && isSameDayAsNext) {
            // Tengah-tengah: Kotak Semua
            holder.container.background = ContextCompat.getDrawable(context, R.drawable.bg_item_top)
            holder.divider.visibility = View.VISIBLE
        }
        else if (isSameDayAsPrev && !isSameDayAsNext) {
            // Paling Bawah: Kotak Atas, Bulat Bawah
            holder.container.background = ContextCompat.getDrawable(context, R.drawable.bg_item_bottom)
            holder.divider.visibility = View.GONE
        }
    }
}