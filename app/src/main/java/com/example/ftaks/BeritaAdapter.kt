package com.example.ftaks
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView

class BeritaAdapter(
    val context: Context,
    val listBerita: List<Berita>,
    val onItemClick: (Berita) -> Unit
) : RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder>() {
    inner class BeritaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgThumbnail: ImageView = itemView.findViewById(R.id.iv_thumbnail)
        val tvJudul: TextView = itemView.findViewById(R.id.tv_judul_berita)
        val tvDeskripsiBeritaSingkat: TextView = itemView.findViewById(R.id.tv_deskripsiSingkat)
        val tvKategori: TextView = itemView.findViewById(R.id.tv_kategori)
        init {
            itemView.setOnClickListener {
                onItemClick(listBerita[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeritaViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_berita, parent, false)
        return BeritaViewHolder(view)
    }

    override fun getItemCount(): Int = listBerita.size

    override fun onBindViewHolder(holder: BeritaViewHolder, position: Int) {
        val berita = listBerita[position]
        holder.tvJudul.text = berita.judul
        holder.imgThumbnail.setImageResource(berita.gambar)
        holder.tvDeskripsiBeritaSingkat.text = potongDeskripsi(berita.deskripsiberita)
        holder.tvKategori.text = "${berita.tanggal} ${berita.kategori.displayName}"
    }
    fun potongDeskripsi(text: String, maxChar: Int = 60): CharSequence {
        return if (text.length > maxChar) {
            text.substring(0, maxChar) + "... lihat lainnya"
        } else {
            text
        }
    }
}