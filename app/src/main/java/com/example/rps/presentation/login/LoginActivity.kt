package com.example.rps.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.rps.databinding.ActivityLoginBinding
import com.example.rps.presentation.FirebaseViewModel
import com.example.rps.presentation.main_business_navigation.MainBusinessNavigationActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: FirebaseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            try {
                Firebase.auth.signInWithEmailAndPassword(
                    binding.emailEditText.text.toString(),
                    binding.passwordEditText.text.toString()
                ).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        startActivity(Intent(this, MainBusinessNavigationActivity::class.java))
                    } else {
                        Toast.makeText(this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(this, "Введите email и пароль", Toast.LENGTH_SHORT).show()
            }
        }
    }
}