package com.example.rps.presentation.main_business_navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.rps.R
import com.example.rps.databinding.ActivityMainBusinessNavigationBinding
import com.example.rps.presentation.main_business_navigation.consoles.ConsolesFragment

class MainBusinessNavigationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBusinessNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBusinessNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(ConsolesFragment())

        binding.navigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.action_console -> replaceFragment(ConsolesFragment())
                R.id.action_search -> replaceFragment(SearchFragment())
                R.id.action_profile -> replaceFragment(ProfileFragment())
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