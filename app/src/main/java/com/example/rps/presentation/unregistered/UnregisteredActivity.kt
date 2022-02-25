package com.example.rps.presentation.unregistered

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rps.databinding.ActivityUnregisteredBinding
import com.example.rps.domain.model.Console
import com.example.rps.domain.model.User
import com.example.rps.presentation.FirebaseViewModel
import com.example.rps.presentation.main_business_navigation.adapters.ConsoleClickListener
import com.example.rps.presentation.main_business_navigation.adapters.SearchConsolesAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class UnregisteredActivity : AppCompatActivity(), ConsoleClickListener {
    private lateinit var binding: ActivityUnregisteredBinding
    private val viewModel: FirebaseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUnregisteredBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener { onBackPressed() }

        val consoles = arrayListOf<Console>()
        viewModel.databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var currentUser: User?
                for (user in snapshot.children) {
                    if (user.exists()) {
                        currentUser = user.getValue(User::class.java)
                        for (console in user.children) {
                            try {
                                console.getValue(Console::class.java)?.let {
                                    val currentConsole = it
                                    currentConsole.userName = currentUser?.Name
                                    currentConsole.userCity = currentUser?.City
                                    currentConsole.userTelephone = currentUser?.PhoneNumber
                                    if (currentConsole.consoleState == true && currentConsole.userCity == intent.getStringExtra("city")) {
                                        consoles.add(currentConsole)
                                    }
                                }
                            } catch (e: Exception) {
                            }
                        }
                    }
                }
                binding.consolesRecyclerView.layoutManager = LinearLayoutManager(this@UnregisteredActivity)
                val adapter = SearchConsolesAdapter(this@UnregisteredActivity, consoles, this@UnregisteredActivity)
                binding.consolesRecyclerView.adapter = adapter
                binding.progressCircular.visibility = View.GONE
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    override fun onConsoleClick(console: Console) {
        val intent = Intent(this, ConsoleInfoActivity::class.java)
        intent.putExtra("console", console)
        startActivity(intent)
    }
}