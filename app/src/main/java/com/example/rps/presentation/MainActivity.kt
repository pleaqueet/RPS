package com.example.rps.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.rps.databinding.ActivityMainBinding
import com.example.rps.presentation.login.LoginActivity
import com.example.rps.presentation.main_navigation.MainNavigationActivity
import com.example.rps.presentation.registration.ChooseStatusRegistrationActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user = Firebase.auth.currentUser
        if (user != null ) {
            startActivity(Intent(this, MainNavigationActivity::class.java))
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.registerButton.setOnClickListener {
            startActivity(Intent(this, ChooseStatusRegistrationActivity::class.java))
        }

    }
}