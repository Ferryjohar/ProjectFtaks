package com.example.ftaks

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val username = intent.getStringExtra(LoginActivity.KEY_USERNAME)
        val tvUsername: TextView = findViewById(R.id.tv_username)
        tvUsername.text = if (!username.isNullOrEmpty()) username else "Pengguna"

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)

        bottomNav.setupWithNavController(navController)

        val ivAvatar: ImageView = findViewById(R.id.iv_avatar)
        val profileClickListener = View.OnClickListener {
            val username = intent.getStringExtra(LoginActivity.KEY_USERNAME) ?: ""
            val password = intent.getStringExtra(LoginActivity.KEY_PASSWORD) ?: ""

            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("username", username)
            intent.putExtra("password", password)
            startActivity(intent)
        }

        ivAvatar.setOnClickListener(profileClickListener)
        tvUsername.setOnClickListener(profileClickListener)
    }
}
