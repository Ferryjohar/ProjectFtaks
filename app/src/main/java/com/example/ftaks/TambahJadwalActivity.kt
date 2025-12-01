package com.example.ftaks

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class TambahJadwalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_jadwal)

        val etHariTanggal: EditText = findViewById(R.id.et_hari_tanggal)
        val etMataKuliah: EditText = findViewById(R.id.et_mata_kuliah)
        val etRuangKelas: EditText = findViewById(R.id.et_ruang_kelas)
        val etWaktu: EditText = findViewById(R.id.et_waktu_perkuliahan)
        val btnSimpan: Button = findViewById(R.id.btn_simpan_jadwal)

        btnSimpan.setOnClickListener {
            val hari = etHariTanggal.text.toString()
            val matkul = etMataKuliah.text.toString()
            val ruang = etRuangKelas.text.toString()
            val waktu = etWaktu.text.toString()

            // Validasi sederhana: pastikan tidak ada yang kosong
            if (hari.isEmpty() || matkul.isEmpty() || ruang.isEmpty() || waktu.isEmpty()) {
                Toast.makeText(this, "Semua data harus diisi", Toast.LENGTH_SHORT).show()
            } else {
                // Buat objek Jadwal baru
                val jadwalBaru = Jadwal(hari, matkul, ruang, waktu)

                // Siapkan Intent untuk mengirim data kembali
                val resultIntent = Intent()
                resultIntent.putExtra("JADWAL_BARU", jadwalBaru)

                // Set hasilnya OK dan kirim datanya
                setResult(Activity.RESULT_OK, resultIntent)

                // Tutup activity ini
                finish()
            }
        }
    }
}