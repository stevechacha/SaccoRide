package com.dev.chacha.presentation.contacts

import android.annotation.SuppressLint
import android.content.Context
import android.provider.ContactsContract
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.dev.chacha.domain.repo.ThemeRepository

class ContactsViewModel(
) : ViewModel() {
    var state by mutableStateOf(ContactState())



}