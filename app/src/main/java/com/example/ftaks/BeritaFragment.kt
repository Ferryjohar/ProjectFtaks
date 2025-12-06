package com.example.ftaks

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BeritaFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_berita, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvBerita: RecyclerView = view.findViewById(R.id.rv_berita)
        val listBerita = listOf(
            Berita(
                judul = "Kisah Shareent, Lolos ke FEB UGM Tanpa Tes",
                deskripsiberita = "Shareent mencuri perhatian publik setelah berhasil diterima di Fakultas Ekonomika dan Bisnis UGM tanpa mengikuti tes seleksi apa pun. Prestasinya selama di sekolah menengah—mulai dari kejuaraan kompetisi hingga konsistensi akademik—menjadi faktor utama yang membuatnya lolos melalui jalur prestasi. Kisahnya menginspirasi banyak pelajar untuk lebih fokus mengembangkan portofolio dan rekam jejak kompetisi.",
                tanggal = "Senin, 12 Juni 2025",
                kategori = BeritaKategori.Umum,
                gambar = R.drawable.img_berita_1
            ),
            Berita(
                judul = "15 PTN Masih Buka Jalur Mandiri 2025",
                deskripsiberita = "Bagi peserta yang tidak berhasil pada SNBT, masih ada peluang karena setidaknya 15 perguruan tinggi negeri di Indonesia membuka pendaftaran jalur mandiri dengan kuota bervariasi. Beberapa kampus besar seperti UI, UGM, ITS, dan Unair menawarkan jalur dengan mekanisme tes maupun portofolio. Informasi detail mengenai syarat, jadwal, dan sistem seleksi penting diperhatikan karena tiap kampus menerapkan ketentuan berbeda.",
                tanggal = "Selasa, 13 Juni 2025",
                kategori = BeritaKategori.Umum,
                gambar = R.drawable.img_berita_2
            ),
            Berita(
                judul = "Seminar Teknologi Informasi",
                deskripsiberita = "Seminar nasional dengan tema “Inovasi Teknologi untuk Masa Depan Industri” akan diselenggarakan di Aula Utama Kampus. Kegiatan ini menghadirkan pembicara dari berbagai perusahaan teknologi terkemuka yang akan membahas perkembangan AI, keamanan siber, dan otomatisasi industri. Mahasiswa yang mengikuti seminar ini akan mendapatkan sertifikat resmi dan dapat mengajukan SKPI sebagai tambahan portofolio. Kegiatan ini terbuka untuk seluruh mahasiswa, namun tempat terbatas sehingga pendaftaran dilakukan berdasarkan urutan peserta yang mendaftar.",
                tanggal = "Rabu, 14 Juni 2025",
                kategori = BeritaKategori.Akademik,
                gambar = R.drawable.img_berita_2
            ),
            Berita(
                judul = "Kegiatan Praktikum Jaringan Komputer Dipindah Jadwal",
                deskripsiberita = "Kegiatan praktikum Jaringan Komputer untuk seluruh kelas semester awal mengalami perubahan jadwal akibat penyesuaian ruangan laboratorium. Praktikum yang semula dilaksanakan pada hari Selasa secara resmi dipindah ke hari Jumat. Perubahan ini bertujuan memberikan waktu persiapan lebih bagi teknisi lab untuk memastikan seluruh perangkat, termasuk router, switch, dan server simulasi, berfungsi optimal.\n" +
                        "\n" +
                        "Selain itu, perpindahan jadwal ini dilakukan untuk mencegah bentrokan dengan jadwal mata kuliah lain yang sebelumnya dikeluhkan beberapa mahasiswa. Dengan penjadwalan yang baru, diharapkan kegiatan praktikum dapat berjalan lebih efektif dan mahasiswa dapat mengikuti sesi tanpa hambatan. Informasi lengkap mengenai pembagian kelompok akan diumumkan oleh dosen pengampu melalui sistem akademik kampus.",
                tanggal = "Senin, 18 November 2025",
                kategori = BeritaKategori.Akademik,
                gambar = R.drawable.img_berita_2
            ),
            Berita(
                judul = "Lomba UI/UX Nasional Dibuka untuk Mahasiswa TI",
                deskripsiberita = "Program Studi Teknologi Informasi secara resmi membuka pendaftaran untuk kompetisi UI/UX tingkat nasional yang akan diselenggarakan bulan depan. Kompetisi ini menantang mahasiswa untuk merancang solusi digital kreatif yang dapat membantu meningkatkan produktivitas pelajar Indonesia. Para peserta akan mengikuti workshop desain, mentoring intensif, serta sesi presentasi di hadapan juri profesional dari berbagai industri.\n" +
                        "\n" +
                        "Kompetisi ini menjadi peluang besar bagi mahasiswa yang ingin mengembangkan portofolio dan mendapatkan pengalaman nyata dalam dunia desain produk digital. Selain sertifikat, para pemenang juga berkesempatan memperoleh inkubasi proyek dan beasiswa pelatihan lanjutan. Pendaftaran dilakukan melalui laman resmi kampus dan akan ditutup jika kuota peserta terpenuhi.",
                tanggal = "Selasa, 2 desember 2025",
                kategori = BeritaKategori.Event,
                gambar = R.drawable.img_berita_2
            ),
            Berita(
                judul = "Perpustakaan Kampus Hadirkan Sistem Peminjaman Digital",
                deskripsiberita = "Perpustakaan kampus meluncurkan sistem peminjaman buku berbasis digital yang dapat diakses melalui aplikasi resmi kampus. Melalui sistem ini, mahasiswa dapat mengecek ketersediaan buku, melakukan peminjaman, memperpanjang masa pinjam, hingga mendapatkan rekomendasi bacaan berdasarkan riwayat pencarian. Sistem baru ini diharapkan mampu mengurangi antrean panjang yang sering terjadi pada jam sibuk.\n" +
                        "\n" +
                        "Selain fitur peminjaman, perpustakaan juga menyediakan ruang baca digital yang memungkinkan mahasiswa mengakses jurnal internasional dan e-book tanpa batas. Dengan adanya layanan ini, mahasiswa tidak perlu lagi datang ke perpustakaan untuk sekadar mengecek ketersediaan buku. Implementasi sistem digital ini merupakan bagian dari komitmen kampus menuju Smart Campus 2025.",
                tanggal = "Sabtu, 15 November 2025",
                kategori = BeritaKategori.Akademik,
                gambar = R.drawable.img_berita_2
            )
        )

        val adapter = BeritaAdapter(requireContext(), listBerita) { beritaKlik ->
            val intent = Intent(requireContext(), DetailBeritaActivity::class.java)
            intent.putExtra("DATA_BERITA", beritaKlik)
            startActivity(intent)
        }

        rvBerita.layoutManager = LinearLayoutManager(requireContext())
        rvBerita.adapter = adapter
    }
}