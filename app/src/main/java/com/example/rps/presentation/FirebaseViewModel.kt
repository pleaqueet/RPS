package com.example.rps.presentation

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


const val firebaseChild = "Users"

@HiltViewModel
class FirebaseViewModel
@Inject constructor() : ViewModel() {
    var auth: FirebaseAuth = Firebase.auth
    var currentUser = auth.currentUser
    private var database = FirebaseDatabase.getInstance()
    var databaseReference = database.reference.child(firebaseChild)
}