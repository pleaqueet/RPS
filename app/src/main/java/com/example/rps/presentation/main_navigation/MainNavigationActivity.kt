package com.example.rps.presentation.main_navigation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.rps.databinding.ActivityMainNavigationBinding
import com.example.rps.presentation.FirebaseViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainNavigationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainNavigationBinding
    private val viewModel: FirebaseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = Firebase.auth.currentUser
        val userReference = user?.let { viewModel.databaseReference.child(it.uid) }
        userReference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Toast.makeText(
                    this@MainNavigationActivity,
                    snapshot.value.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }

    private fun replaceFragment(fragment: Fragment) {

    }

    override fun onBackPressed() {
        finishAffinity()
    }
}