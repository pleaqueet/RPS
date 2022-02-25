package com.example.rps.presentation.main_business_navigation.consoles

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.rps.databinding.ActivityAddConsoleBinding
import com.example.rps.domain.model.Console
import com.example.rps.presentation.FirebaseViewModel
import com.example.rps.presentation.main_business_navigation.MainBusinessNavigationActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AddConsoleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddConsoleBinding
    private val viewModel: FirebaseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddConsoleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentUser = Firebase.auth.currentUser

        binding.saveButton.setOnClickListener {
            if (binding.editTextConsoleInfo.text.isNotEmpty() && binding.editTextSerialNumber.text.isNotEmpty() &&
                binding.editTextConsoleName.text.isNotEmpty() && binding.editTextJoypadCount.text.isNotEmpty() &&
                binding.editTextWeekdayPrice.text.isNotEmpty() && binding.editTextWeekendPrice.text.isNotEmpty()
            ) {
                try {
                    val console = Console(
                        consoleName = binding.editTextConsoleName.text.toString(),
                        consoleState = binding.freeCheckBox.isChecked,
                        serialNumber = binding.editTextSerialNumber.text.toString(),
                        joypadCount = binding.editTextJoypadCount.text.toString(),
                        weekdayTextField = binding.editTextWeekdayPrice.text.toString(),
                        weekendTextField = binding.editTextWeekendPrice.text.toString(),
                        gamesInfo = binding.editTextConsoleInfo.text.toString()
                    )
                    viewModel.databaseReference.child(currentUser!!.uid).push().setValue(console)
                    startActivity(Intent(this, MainBusinessNavigationActivity::class.java))
                    finish()
                } catch (e: Exception) {
                }
            } else {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
            }
        }

        binding.closeButton.setOnClickListener {
            onBackPressed()
        }
    }
}