package com.example.ftaks

import android.app.Activity
import android.app.DatePickerDialog // Import ini
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat // Import untuk format tanggal
import java.util.Calendar // Import untuk kalender
import java.util.Locale // Import untuk Bahasa Indonesia

class TambahTugasActivity : AppCompatActivity() {

    // Variabel Calendar untuk menampung tanggal yang dipilih
    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_tugas)

        val etMatkul = findViewById<EditText>(R.id.et_matkul_tugas)
        val etDeskripsi = findViewById<EditText>(R.id.et_deskripsi_tugas)
        val etTanggal = findViewById<EditText>(R.id.et_tanggal_tugas) // EditText Tanggal
        val rgPrioritas = findViewById<RadioGroup>(R.id.rg_prioritas)
        val btnSimpan = findViewById<Button>(R.id.btn_simpan_tugas)

        // 1. EVENT KLIK PADA KOLOM TANGGAL
        etTanggal.setOnClickListener {
            showDatePicker(etTanggal)
        }

        btnSimpan.setOnClickListener {
            val matkul = etMatkul.text.toString()
            val deskripsi = etDeskripsi.text.toString()
            val tanggal = etTanggal.text.toString()

            val prioritas = when (rgPrioritas.checkedRadioButtonId) {
                R.id.rb_tinggi -> Prioritas.TINGGI
                R.id.rb_sedang -> Prioritas.SEDANG
                R.id.rb_rendah -> Prioritas.RENDAH
                else -> Prioritas.RENDAH
            }

            if (matkul.isEmpty() || deskripsi.isEmpty() || tanggal.isEmpty()) {
                Toast.makeText(this, "Mohon lengkapi semua data", Toast.LENGTH_SHORT).show()
            } else {
                val tugasBaru = Tugas(matkul, deskripsi, tanggal, prioritas)
                val resultIntent = Intent()
                resultIntent.putExtra("TUGAS_BARU", tugasBaru)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        }
    }

    // 2. FUNGSI UNTUK MEMUNCULKAN KALENDER
    private fun showDatePicker(editText: EditText) {
        // Buat DatePickerDialog
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                // Saat user memilih tanggal:
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                // Format tanggal ke teks (Contoh: "Kamis, 12 Juni 2025")
                // "EEEE" = Nama Hari, "dd" = Tanggal, "MMMM" = Bulan, "yyyy" = Tahun
                val formatTanggal = "EEEE, dd MMMM yyyy"
                val sdf = SimpleDateFormat(formatTanggal, Locale("id", "ID")) // Pakai Bahasa Indonesia

                // Set hasil format ke EditText
                editText.setText(sdf.format(calendar.time))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        // Tampilkan dialog
        datePickerDialog.show()
    }
}