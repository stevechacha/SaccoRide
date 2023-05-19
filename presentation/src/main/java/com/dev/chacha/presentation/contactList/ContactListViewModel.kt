package com.dev.chacha.presentation.contactList

import android.content.Context
import android.provider.ContactsContract
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
class ContactListViewModel : ViewModel() {

    private val _state = MutableStateFlow(ContactState())
    val state: StateFlow<ContactState> = _state

    private var initialContact = listOf<Contact>()

    private var searchJob: Job? = null

    fun send(event: ContactUiEvent, context: Context) {
        when (event) {
            is ContactUiEvent.GetContacts -> {
                _state.value = ContactState(isLoading = true)
                // Perform asynchronous operation to retrieve contacts and update state accordingly
                viewModelScope.launch {
                    try {
                        val contacts = withContext(Dispatchers.IO) {
                            getContactsList(context)
                        }
                        initialContact = contacts
                        _state.value = ContactState(contact = initialContact.take(1000))
                    } catch (e: Exception) {
                        _state.value = ContactState(error = e.message ?: "Unknown error")
                    } finally {
                        _state.value = _state.value.copy(isLoading = false)
                    }
                }
            }

            is ContactUiEvent.SearchContact -> {
                if (event.searchParams.isNotEmpty()){
                    _state.value.searchParams = event.searchParams.trim()
                    searchJob?.cancel() // Cancel previous search job if exists
                    searchJob = viewModelScope.launch {
                        _state.value = state.value.copy(isLoading = true)
                        // Delay for 500ms to avoid frequent searches and showing loading icon
                        delay(500L)
                        val filteredContacts = initialContact.filter {
                            it.name.contains(state.value.searchParams, ignoreCase = true) ||
                                    it.phoneNumber.contains(state.value.searchParams)
                        }
                        _state.value = state.value.copy(contact = filteredContacts, isLoading = false)
                    }
                } else {

                    _state.value.searchParams = ""
                    _state.value = ContactState(contact = initialContact.take(1000))
                }
            }
        }
    }


    private fun getContactsList(context: Context): List<Contact> {
        val contacts = mutableStateListOf<Contact>()
        val cursor = context.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null
        )

        cursor?.use {
            while (it.moveToNext()) {
                val contactName =
                    it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                val contactPhoneNumber =
                    it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                val contactEmail =
                    it.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Email.ADDRESS)
                val contactId = it.getColumnIndex(ContactsContract.Contacts._ID)

                val name = it.getString(contactName)
                val phoneNumber = it.getString(contactPhoneNumber)
                val email = it.getString(contactEmail)
                val numberId = it.getString(contactId)
                // Remove spaces, hyphens, and parentheses from the phone number
                val cleanedPhoneNumber = phoneNumber.replace("[\\s-()]".toRegex(), "")

                contacts.add(Contact(name, cleanedPhoneNumber, email, numberId))
            }
        }
        cursor?.close()

        // Remove duplicates and sort by name
        return contacts.sortedBy { it. name}

    }
}

