package com.example.rps.presentation.main_business_navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rps.R
import com.example.rps.databinding.FragmentConsoleBinding

class ConsoleFragment : Fragment(R.layout.fragment_console) {
    private lateinit var binding: FragmentConsoleBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConsoleBinding.inflate(inflater, container, false)



        return binding.root
    }
}