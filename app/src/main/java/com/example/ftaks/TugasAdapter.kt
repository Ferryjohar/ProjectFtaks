package com.example.ftaks

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class TugasAdapter(
    val context: Context,
    val listTugas: List<Tugas>,
    val onDeleteClick: (Int) -> Unit,
    val onDoneClick: (Int) -> Unit
) : RecyclerView.Adapter<TugasAdapter.TugasViewHolder>() {

    inner class TugasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvMatakuliah: TextView = itemView.findViewById(R.id.tv_matakuliah)
        val tvDeskripsi: TextView = itemView.findViewById(R.id.tv_deskripsi)
        val tvTanggal: TextView = itemView.findViewById(R.id.tv_tanggal)
        val tvPrioritasTag: TextView = itemView.findViewById(R.id.tv_prioritas_tag)

        val btnHapus: ImageView = itemView.findViewById(R.id.btn_hapus)
        val btnSelesai: ImageView = itemView.findViewById(R.id.btn_selesai)

        init {
            btnHapus.setOnClickListener { onDeleteClick(adapterPosition) }
            btnSelesai.setOnClickListener { onDoneClick(adapterPosition) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TugasViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_tugas, parent, false)
        return TugasViewHolder(view)
    }

    override fun getItemCount(): Int = listTugas.size

    override fun onBindViewHolder(holder: TugasViewHolder, position: Int) {
        val tugas = listTugas[position]

        holder.tvMatakuliah.text = tugas.matakuliah
        holder.tvDeskripsi.text = tugas.deskripsi
        holder.tvTanggal.text = tugas.tanggal

        if (tugas.isSelesai) {
            holder.btnSelesai.visibility = View.GONE
        } else {
            holder.btnSelesai.visibility = View.VISIBLE
        }

        val (bgDrawable, label) = when (tugas.prioritas) {
            Prioritas.TINGGI -> R.drawable.bg_tag_tinggi to "Tinggi"
            Prioritas.SEDANG -> R.drawable.bg_tag_sedang to "Sedang"
            Prioritas.RENDAH -> R.drawable.bg_tag_rendah to "Rendah"
        }
        holder.tvPrioritasTag.text = label
        holder.tvPrioritasTag.background = ContextCompat.getDrawable(context, bgDrawable)
    }
}