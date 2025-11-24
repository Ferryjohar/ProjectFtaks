package com.example.ftaks

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val editTextUsername: EditText = findViewById<EditText>(R.id.et_username)
        val editTextPassword: EditText = findViewById(R.id.et_password)
        val buttonLogin = findViewById<Button>(R.id.btn_masuk)

        buttonLogin.setOnClickListener {
            val username = editTextUsername.text.toString()
            if (editTextUsername.text.toString()=="ferry" && editTextPassword.text.toString()=="1234"){
                Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra(KEY_USERNAME,username)
                startActivity(intent)

            }else{

                Toast.makeText(this, "Login Gagal", Toast.LENGTH_SHORT).show()
            }
        }
    }
    companion object KEY {
        const val KEY_USERNAME = "username"
    }
}