package com.dev.chacha.presentation.contacts

data class ContactState(
    val isLoading: Boolean = false,
    val contact: List<Contact> = emptyList(),
    val error: String = "",
    val searchQuery: String = "",
    var searchParams: String = ""

)
