package com.dev.chacha.presentation.contactList

data class ContactState(
    val isLoading: Boolean = false,
    val contact: List<Contact> = emptyList(),
    val error: String = "",
    val searchQuery: String = "",
    var searchParams: String = ""

)
