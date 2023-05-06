package com.dev.chacha.presentation.contacts

sealed interface ContactUiEvent{
    object GetContacts: ContactUiEvent
    data class SearchContact(val searchParams: String): ContactUiEvent

}