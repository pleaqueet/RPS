package com.example.rps.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.rps.databinding.ActivityLoginBinding
import com.example.rps.presentation.FirebaseViewModel
import com.example.rps.presentation.main_navigation.MainNavigationActivity
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
                viewModel.auth.signInWithEmailAndPassword(
                    binding.emailEditText.text.toString(),
                    binding.passwordEditText.text.toString()
                ).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        viewModel.currentUser = viewModel.auth.currentUser
                        startActivity(Intent(this, MainNavigationActivity::class.java))
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