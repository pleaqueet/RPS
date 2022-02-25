package com.example.rps.presentation.main_user_navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.rps.R
import com.example.rps.databinding.ActivityMainUserNavigationBinding
import com.example.rps.presentation.main_business_navigation.ProfileFragment
import com.example.rps.presentation.main_business_navigation.SearchFragment

class MainUserNavigationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainUserNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainUserNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(SearchFragment())

        binding.navigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.action_search_user -> replaceFragment(SearchFragment())
                R.id.action_profile_user -> replaceFragment(ProfileFragment())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_view, fragment)
            .commit()
    }

    override fun onBackPressed() {
        finishAffinity()
    }
}