package com.example.rps.presentation.main_business_navigation.consoles

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rps.R
import com.example.rps.databinding.FragmentConsolesBinding
import com.example.rps.domain.model.Console
import com.example.rps.presentation.FirebaseViewModel
import com.example.rps.presentation.main_business_navigation.adapters.ChangeConsoleStateClickListener
import com.example.rps.presentation.main_business_navigation.adapters.ConsolesAdapter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ConsolesFragment : Fragment(R.layout.fragment_consoles), ChangeConsoleStateClickListener {
    private lateinit var binding: FragmentConsolesBinding
    private val viewModel: FirebaseViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConsolesBinding.inflate(inflater, container, false)

        binding.addConsoleButton.setOnClickListener {
            startActivity(Intent(requireActivity(), AddConsoleActivity::class.java))
        }

        val currentUser = Firebase.auth.currentUser
        viewModel.databaseReference.child(currentUser!!.uid).get().addOnCompleteListener { task ->
            if (task.isSuccessful && task.result.exists()) {
                val consoles = arrayListOf<Console>()
                for (console in task.result.children) {
                    try {
                        console.getValue(Console::class.java)?.let {
                            val currentConsole = it
                            currentConsole.key = console.key
                            consoles.add(currentConsole)
                        }
                    } catch (e: Exception) {
                    }
                }
                binding.consolesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                binding.consolesRecyclerView.adapter =
                    ConsolesAdapter(requireContext(), consoles, this)
                binding.progressCircular.visibility = View.GONE
            }
        }

        return binding.root
    }

    override fun onStateClick(console: Console) {
        val currentUser = Firebase.auth.currentUser
        if (console.consoleState == true) {
            viewModel.databaseReference.child(currentUser!!.uid).child(console.key.toString())
                .child("consoleState").setValue(false)
        } else {
            viewModel.databaseReference.child(currentUser!!.uid).child(console.key.toString())
                .child("consoleState").setValue(true)
        }
    }
}