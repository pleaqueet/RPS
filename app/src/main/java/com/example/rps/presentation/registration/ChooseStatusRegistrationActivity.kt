package com.example.rps.presentation.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rps.databinding.ActivityChooseStatusRegistrationBinding

class ChooseStatusRegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChooseStatusRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseStatusRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.forBusinessButton.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            intent.putExtra("register_status", true)
            startActivity(intent)
        }

        binding.forUserButton.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            intent.putExtra("register_status", false)
            startActivity(intent)
        }
    }
}