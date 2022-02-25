package com.example.rps.presentation.registration

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.rps.databinding.ActivityRegistrationBinding
import com.example.rps.presentation.FirebaseViewModel
import com.example.rps.presentation.main_business_navigation.MainBusinessNavigationActivity
import com.example.rps.presentation.main_user_navigation.MainUserNavigationActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding
    private val viewModel: FirebaseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerButton.setOnClickListener { register() }
        binding.backButton.setOnClickListener { onBackPressed() }
    }

    private fun register() {
        if (binding.passwordEditText.text.toString() == binding.repasswordEditText.text.toString() &&
                Patterns.EMAIL_ADDRESS.matcher(binding.emailEditText.text.toString()).matches() &&
                binding.loginEditText.text.length > 3 && binding.nameEditText.text.length > 3 &&
                binding.cityEditText.text.length > 3 && binding.telephoneEditText.text.isNotEmpty() &&
                binding.passwordEditText.text.length > 5) {
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
                        if (intent.getBooleanExtra("register_status", false)) {
                            startActivity(Intent(this, MainBusinessNavigationActivity::class.java))
                        } else {
                            startActivity(Intent(this, MainUserNavigationActivity::class.java))
                        }
                        finish()
                    } else {
                        Toast.makeText(this, "Введите все данные правильно", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(this, "Введите все данные правильно", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Введите все данные правильно", Toast.LENGTH_SHORT).show()
        }
    }
}