package com.dev.chacha.presentation.contactList

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Contact(
    val name: String,
    val phoneNumber: String,
    val email: String,
    val numberId: String
) : Parcelable