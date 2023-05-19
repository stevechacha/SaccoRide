package com.dev.chacha.presentation.contactList

sealed interface ContactUiEvent{
    object GetContacts: ContactUiEvent
    data class SearchContact(val searchParams: String): ContactUiEvent

}