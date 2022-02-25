package com.example.rps.presentation.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.rps.databinding.ActivityLoginBinding
import com.example.rps.domain.model.User
import com.example.rps.presentation.FirebaseViewModel
import com.example.rps.presentation.main_business_navigation.MainBusinessNavigationActivity
import com.example.rps.presentation.main_user_navigation.MainUserNavigationActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: FirebaseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backButton.setOnClickListener { onBackPressed() }

        binding.recoveryButton.setOnClickListener {
            startActivity(Intent(this, AccountRecoveryActivity::class.java))
        }

        binding.loginButton.setOnClickListener {
            try {
                Firebase.auth.signInWithEmailAndPassword(
                    binding.emailEditText.text.toString(),
                    binding.passwordEditText.text.toString()
                ).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val currentUser = Firebase.auth.currentUser
                        viewModel.databaseReference.child(currentUser!!.uid).get().addOnCompleteListener {
                            if (it.isSuccessful && it.result.exists()) {
                                val user = it.result.getValue(User::class.java)!!
                                if (user.AccountStatus == true) {
                                    startActivity(Intent(this, MainBusinessNavigationActivity::class.java))
                                    finish()
                                } else {
                                    startActivity(Intent(this, MainUserNavigationActivity::class.java))
                                    finish()
                                }
                            }
                        }
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