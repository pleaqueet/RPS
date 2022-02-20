package com.example.rps.presentation.registration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.rps.databinding.ActivityRegistrationBinding
import com.example.rps.presentation.FirebaseViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding
    private val viewModel: FirebaseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerButton.setOnClickListener { register() }
    }

    private fun register() {
        if (binding.passwordEditText.text.toString() == binding.repasswordEditText.text.toString()) {
            try {
                Firebase.auth.createUserWithEmailAndPassword(
                    binding.emailEditText.text.toString(),
                    binding.passwordEditText.text.toString()
                ).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val currentUser = Firebase.auth.currentUser
                        val currentUserInDB =
                            viewModel.databaseReference.child((currentUser?.uid!!))
                        currentUserInDB.child("Login")
                            .setValue(binding.loginEditText.text.toString())
                        currentUserInDB.child("Name").setValue(binding.nameEditText.text.toString())
                        currentUserInDB.child("City").setValue(binding.cityEditText.text.toString())
                        currentUserInDB.child("PhoneNumber")
                            .setValue(binding.telephoneEditText.text.toString())
                        currentUserInDB.child("Email")
                            .setValue(binding.emailEditText.text.toString())
                        currentUserInDB.child("AccountStatus")
                            .setValue(intent.getBooleanExtra("register_status", false))
                    } else {
                        Toast.makeText(this, "Введите все данные правильно", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(this, "Введите все данные правильно", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
        }
    }
}