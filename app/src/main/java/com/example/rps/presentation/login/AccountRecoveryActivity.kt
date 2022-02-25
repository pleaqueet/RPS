package com.example.rps.presentation.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.rps.databinding.ActivityAccountRecoveryBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AccountRecoveryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAccountRecoveryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountRecoveryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recoveryButton.setOnClickListener {
            Firebase.auth.sendPasswordResetEmail(binding.emailEditText.text.toString()).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Проверьте свою почту и поменяйте пароль", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Введите верный Email", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}