package com.example.ftaks

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class TambahTugasActivity : AppCompatActivity() {

    val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_tugas)

        val etMatkul = findViewById<EditText>(R.id.et_matkul_tugas)
        val etDeskripsi = findViewById<EditText>(R.id.et_deskripsi_tugas)
        val etTanggal = findViewById<EditText>(R.id.et_tanggal_tugas)
        val rgPrioritas = findViewById<RadioGroup>(R.id.rg_prioritas)
        val btnSimpan = findViewById<Button>(R.id.btn_simpan_tugas)

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

    fun showDatePicker(editText: EditText) {
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