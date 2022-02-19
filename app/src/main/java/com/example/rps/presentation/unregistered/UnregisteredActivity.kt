package com.example.rps.presentation.unregistered

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rps.databinding.ActivityUnregisteredBinding

class UnregisteredActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUnregisteredBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUnregisteredBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}