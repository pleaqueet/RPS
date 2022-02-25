package com.example.rps.domain.model

import android.os.Parcelable
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

@Parcelize
@IgnoreExtraProperties
data class User(
    val AccountStatus: Boolean? = null,
    val City: String? = null,
    val Email: String? = null,
    val Login: String? = null,
    val Name: String? = null,
    val PhoneNumber: String? = null
) : Parcelable

@Parcelize
@IgnoreExtraProperties
data class Console(
    var consoleName: String? = null,
    var key: String? = null,
    var consoleState: Boolean? = null,
    val gamesInfo: String? = null,
    val joypadCount: String? = null,
    val serialNumber: String? = null,
    val weekdayTextField: String? = null,
    val weekendTextField: String? = null,
    var userCity: String? = null,
    var userName: String? = null,
    var userTelephone: String? = null,
): Parcelable

