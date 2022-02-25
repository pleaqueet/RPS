package com.example.rps.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rps.databinding.ActivityMainBinding
import com.example.rps.presentation.login.LoginActivity
import com.example.rps.presentation.main_business_navigation.MainBusinessNavigationActivity
import com.example.rps.presentation.registration.ChooseStatusRegistrationActivity
import com.example.rps.presentation.unregistered.ChooseCityActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rentButton.setOnClickListener {
            startActivity(Intent(this, ChooseCityActivity::class.java))
        }

        binding.loginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.registerButton.setOnClickListener {
            startActivity(Intent(this, ChooseStatusRegistrationActivity::class.java))
        }
    }
}