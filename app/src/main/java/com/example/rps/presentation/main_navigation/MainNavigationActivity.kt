package com.example.rps.presentation.main_navigation

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.rps.R
import com.example.rps.databinding.ActivityMainNavigationBinding
import com.example.rps.domain.model.Console
import com.example.rps.domain.model.User
import com.example.rps.presentation.FirebaseViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

@AndroidEntryPoint
class MainNavigationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainNavigationBinding
    private val viewModel: FirebaseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView.movementMethod = ScrollingMovementMethod()

        val users = arrayListOf<User>()
        val consoles = arrayListOf<Console>()

        viewModel.databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (user in dataSnapshot.children) {
                    for (console in user.children) {
                        try {
                            console.getValue(Console::class.java)?.let { consoles.add(it) }
                        } catch (e: Exception) {
                        }

                    }
                    user.getValue(User::class.java)?.let { users.add(it) }
                }
                binding.textView.text = consoles.toString()
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

    }

    private fun replaceFragment(fragment: Fragment) {
        if (fragment != null) {
            supportFragmentManager.beginTransaction().replace(R.)
        }
    }

    override fun onBackPressed() {
        finishAffinity()
    }
}