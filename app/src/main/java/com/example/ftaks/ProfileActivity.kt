package com.example.ftaks

import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProfileActivity : AppCompatActivity() {

    var username: String? = null
    var password: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        username = intent.getStringExtra("username")
        password = intent.getStringExtra("password")

        val tvUser = findViewById<TextView>(R.id.tv_profile_username)
        val tvPass = findViewById<TextView>(R.id.tv_profile_password)
        val btnback = findViewById<TextView>(R.id.btn_back_profile)
        btnback.setOnClickListener {
            finish()
        }
        val btnLogout = findViewById<Button>(R.id.btn_logout)
        btnLogout.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        tvUser.text = username
        tvPass.text = password

        tvPass.transformationMethod = PasswordTransformationMethod.getInstance()

        }
}
