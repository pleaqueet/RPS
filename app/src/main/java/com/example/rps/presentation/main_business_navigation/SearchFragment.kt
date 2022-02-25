package com.example.rps.presentation.main_business_navigation

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rps.R
import com.example.rps.databinding.FragmentSearchBinding
import com.example.rps.domain.model.Console
import com.example.rps.domain.model.User
import com.example.rps.presentation.FirebaseViewModel
import com.example.rps.presentation.main_business_navigation.adapters.ConsoleClickListener
import com.example.rps.presentation.main_business_navigation.adapters.SearchConsolesAdapter
import com.example.rps.presentation.unregistered.ConsoleInfoActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class SearchFragment : Fragment(R.layout.fragment_search), ConsoleClickListener {
    private lateinit var binding: FragmentSearchBinding
    private val viewModel by viewModels<FirebaseViewModel>()
    lateinit var adapter: SearchConsolesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

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
                                    if (currentConsole.consoleState == true) {
                                        consoles.add(currentConsole)
                                    }
                                }
                            } catch (e: Exception) {
                            }
                        }
                    }
                }
                try {
                    binding.consolesRecyclerView.layoutManager =
                        LinearLayoutManager(requireContext())
                    adapter = SearchConsolesAdapter(requireContext(), consoles, this@SearchFragment)
                    binding.consolesRecyclerView.adapter = adapter
                    binding.progressCircular.visibility = View.GONE
                } catch (e: Exception) {
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }

        })
        return binding.root
    }

    override fun onConsoleClick(console: Console) {
        val intent = Intent(requireContext(), ConsoleInfoActivity::class.java)
        intent.putExtra("console", console)
        startActivity(intent)
    }
}