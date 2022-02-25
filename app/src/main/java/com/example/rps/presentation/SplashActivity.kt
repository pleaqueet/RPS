package com.example.rps.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.rps.R
import com.example.rps.databinding.ActivitySplashBinding
import com.example.rps.domain.model.User
import com.example.rps.presentation.main_business_navigation.MainBusinessNavigationActivity
import com.example.rps.presentation.main_user_navigation.MainUserNavigationActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.lang.Exception

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private val viewModel: FirebaseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        val currentUser = Firebase.auth.currentUser

        try {
            viewModel.databaseReference.child(currentUser!!.uid).get().addOnCompleteListener { task ->
                if (task.isSuccessful && task.result.exists()) {
                    val currentUserInDB = task.result.getValue(User::class.java)!!
                    if (currentUserInDB.AccountStatus == true) {
                        startActivity(Intent(this, MainBusinessNavigationActivity::class.java))
                        finish()
                    } else {
                        startActivity(Intent(this, MainUserNavigationActivity::class.java))
                        finish()
                    }
                }
            }
        } catch (e: Exception) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }


        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}