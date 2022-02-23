package com.example.rps.domain.model

import com.google.firebase.database.IgnoreExtraProperties


@IgnoreExtraProperties
data class User(
    val AccountStatus: Boolean? = null,
    val City: String? = null,
    val Email: String? = null,
    val Login: String? = null,
    val Name: String? = null,
    val PhoneNumber: String? = null
)

@IgnoreExtraProperties
data class Console(
    var userCity: String? = null,
    var userName: String? = null,
    val consoleName: String? = null,
    val consoleState: Boolean? = null,
    val gamesInfo: String? = null,
    val joypadCount: String? = null,
    val serialNumber: String? = null,
    val weekdayTextField: String? = null,
    val weekendTextField: String? = null
)
