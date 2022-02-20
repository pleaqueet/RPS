package com.example.rps.presentation

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

const val firebaseChild = "Users"

class FirebaseViewModel : ViewModel() {
    private var database = FirebaseDatabase.getInstance()
    var databaseReference = database.reference.child(firebaseChild)
}