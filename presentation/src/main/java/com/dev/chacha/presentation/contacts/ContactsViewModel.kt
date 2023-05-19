package com.dev.chacha.presentation.contacts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.dev.chacha.presentation.contactList.ContactState

class ContactsViewModel(
) : ViewModel() {
    var state by mutableStateOf(ContactState())



}