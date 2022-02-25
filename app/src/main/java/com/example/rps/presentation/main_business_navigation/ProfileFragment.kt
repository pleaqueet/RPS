package com.example.rps.presentation.main_business_navigation

import android.content.Intent
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.text.color
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.rps.R
import com.example.rps.databinding.FragmentProfileBinding
import com.example.rps.domain.model.User
import com.example.rps.presentation.FirebaseViewModel
import com.example.rps.presentation.MainActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private lateinit var binding: FragmentProfileBinding
    private val viewModel: FirebaseViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        val currentUser = Firebase.auth.currentUser
        binding.logOutButton.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(requireActivity(), MainActivity::class.java))
            requireActivity().finish()
        }

        viewModel.databaseReference.child(currentUser!!.uid).get().addOnCompleteListener { task ->
            if (task.isSuccessful && task.result.exists()) {
                val currentUserInDB = task.result.getValue(User::class.java)!!
                binding.nameTextViewLk.text = currentUserInDB.Name
                binding.loginTextViewLk.text = currentUserInDB.Login
                binding.cityTextViewLk.text = SpannableStringBuilder()
                    .color(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.text_hint_color
                        )
                    ) { append("Город: ") }
                    .append(currentUserInDB.City)
                binding.emailTextViewLk.text = SpannableStringBuilder()
                    .color(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.text_hint_color
                        )
                    ) { append("Email: ") }
                    .append(currentUserInDB.Email)
                binding.telephoneTextViewLk.text = SpannableStringBuilder()
                    .color(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.text_hint_color
                        )
                    ) { append("Телефон: ") }
                    .append(currentUserInDB.PhoneNumber)
                if (currentUserInDB.AccountStatus == true) {
                    binding.accountStatusTextViewLk.text = SpannableStringBuilder()
                        .color(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.text_hint_color
                            )
                        ) { append("Вид аккаунта: ") }
                        .append("Business")
                } else {
                    binding.accountStatusTextViewLk.text = SpannableStringBuilder()
                        .color(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.text_hint_color
                            )
                        ) { append("Вид аккаунта: ") }
                        .append("User")
                }
                binding.progressCircular.visibility = View.GONE
            }
        }

        return binding.root
    }
}