package com.dev.chacha.presentation.permissions

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contacts(
    val name: String,
    val phoneNumber: String,
    val email: String,
    val number: String,
): Parcelable
