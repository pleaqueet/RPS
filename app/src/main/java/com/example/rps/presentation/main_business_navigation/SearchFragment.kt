package com.example.rps.presentation.main_business_navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rps.R
import com.example.rps.databinding.FragmentSearchBinding
import com.example.rps.domain.model.Console
import com.example.rps.domain.model.User
import com.example.rps.presentation.FirebaseViewModel
import com.example.rps.presentation.main_business_navigation.adapters.SearchConsolesAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {
    private lateinit var binding: FragmentSearchBinding
    private val viewModel by viewModels<FirebaseViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        viewModel.databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val consoles = arrayListOf<Console>()
                var currentUser: User?
                for (user in snapshot.children) {
                    currentUser = user.getValue(User::class.java)
                    for (console in user.children) {
                        try {
                            console.getValue(Console::class.java)?.let {
                                val currentConsole = it
                                currentConsole.userName = currentUser?.Name
                                currentConsole.userCity = currentUser?.City
                                consoles.add(currentConsole)
                            }
                        } catch (e: Exception) { }
                    }
                }
                binding.consolesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                val adapter = SearchConsolesAdapter(requireContext(),consoles)
                binding.consolesRecyclerView.adapter = adapter
                binding.progressCircular.visibility = View.GONE
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

        return binding.root
    }
}