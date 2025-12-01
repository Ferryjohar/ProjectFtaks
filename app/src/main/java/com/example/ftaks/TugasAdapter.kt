package com.example.ftaks

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class TugasAdapter(private val context: Context, private val listTugas: List<Tugas>) :
    RecyclerView.Adapter<TugasAdapter.TugasViewHolder>() {

    // 1. ViewHolder: Menampung view dari item_tugas.xml
    inner class TugasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvMatakuliah: TextView = itemView.findViewById(R.id.tv_matakuliah)
        val tvDeskripsi: TextView = itemView.findViewById(R.id.tv_deskripsi)
        val tvTanggal: TextView = itemView.findViewById(R.id.tv_tanggal)
        val tvPrioritasTag: TextView = itemView.findViewById(R.id.tv_prioritas_tag)
    }

    // 2. onCreateViewHolder: Membuat dan meng-inflate layout item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TugasViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_tugas, parent, false)
        return TugasViewHolder(view)
    }

    // 3. getItemCount: Mengetahui berapa banyak item dalam list
    override fun getItemCount(): Int {
        return listTugas.size
    }

    // 4. onBindViewHolder: Menghubungkan data ke view
    override fun onBindViewHolder(holder: TugasViewHolder, position: Int) {
        val tugas = listTugas[position]

        // Set data ke TextViews
        holder.tvMatakuliah.text = tugas.matakuliah
        holder.tvDeskripsi.text = tugas.deskripsi
        holder.tvTanggal.text = tugas.tanggal

        // Logika untuk mengatur tag prioritas
        when (tugas.prioritas) {
            Prioritas.TINGGI -> {
                holder.tvPrioritasTag.text = "Tinggi"
                holder.tvPrioritasTag.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_tag_tinggi)
            }
            Prioritas.SEDANG -> {
                holder.tvPrioritasTag.text = "Sedang"
                holder.tvPrioritasTag.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_tag_sedang)
            }
            Prioritas.RENDAH -> {
                holder.tvPrioritasTag.text = "Rendah"
                holder.tvPrioritasTag.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_tag_rendah)
            }
        }
    }
}