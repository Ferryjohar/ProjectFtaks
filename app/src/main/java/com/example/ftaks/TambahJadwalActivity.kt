package com.example.ftaks

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class TambahJadwalActivity : AppCompatActivity() {

    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_jadwal)

        val etHariTanggal = findViewById<EditText>(R.id.et_hari_tanggal)
        val etMataKuliah = findViewById<EditText>(R.id.et_mata_kuliah)
        val etRuangKelas = findViewById<EditText>(R.id.et_ruang_kelas)
        val etWaktu = findViewById<EditText>(R.id.et_waktu_perkuliahan)
        val btnSimpan = findViewById<Button>(R.id.btn_simpan_jadwal)

        // 1. Event Klik untuk Kalender
        etHariTanggal.setOnClickListener {
            showDatePicker(etHariTanggal)
        }

        // 2. Event Klik Simpan
        btnSimpan.setOnClickListener {
            val hari = etHariTanggal.text.toString()
            val matkul = etMataKuliah.text.toString()
            val ruang = etRuangKelas.text.toString()
            val waktu = etWaktu.text.toString()

            if (hari.isEmpty() || matkul.isEmpty() || ruang.isEmpty() || waktu.isEmpty()) {
                Toast.makeText(this, "mohon melengkapi semua data", Toast.LENGTH_SHORT).show()
            } else {
                val jadwalBaru = Jadwal(hari, matkul, ruang, waktu)

                val resultIntent = Intent()
                resultIntent.putExtra("Jadwal Baru", jadwalBaru)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        }
    }

    private fun showDatePicker(editText: EditText) {
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val formatTanggal = "EEEE, dd MMMM yyyy"
                val sdf = SimpleDateFormat(formatTanggal, Locale("id", "ID"))
                editText.setText(sdf.format(calendar.time))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }
}