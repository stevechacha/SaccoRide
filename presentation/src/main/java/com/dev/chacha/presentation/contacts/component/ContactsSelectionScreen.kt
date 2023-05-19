package com.dev.chacha.presentation.contacts.component

import android.Manifest
import android.content.Context
import android.provider.ContactsContract
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dev.chacha.presentation.common.components.AppTopBar
import com.dev.chacha.presentation.contactList.ContactListViewModel
import com.dev.chacha.presentation.contactList.component.ContactItem
import com.dev.chacha.presentation.contactList.Contact
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ContactsSelectionScreen(
    onContactSelected: (Contact) -> Unit,
    navController: NavController,
    viewModel: ContactListViewModel,

    ) {
    val context = LocalContext.current
    // Check for permission to read contacts
    val permissionState = rememberPermissionState(Manifest.permission.READ_CONTACTS)
    val contacts = remember { mutableStateListOf<Contact>() }
    val listState = rememberLazyListState()
    val selectedContactIndex by remember { mutableStateOf(-1) }

    LaunchedEffect(permissionState) {
        if (permissionState.hasPermission) {
            val rawContacts = getContactsList(context)
            val uniqueContacts = rawContacts
                .distinctBy { it.phoneNumber }
                .sortedBy { it.name }
            contacts.addAll(uniqueContacts)
            // Scroll to the previously selected contact, if any
            if (selectedContactIndex != -1) {
                listState.animateScrollToItem(selectedContactIndex)
            }
        } else {
            permissionState.launchPermissionRequest()
        }
    }


    Scaffold(
        topBar = {
            AppTopBar(
                title = "Search Contact",
                initialValue = "",
                onSearchParamChange = {},
                showBackArrow = true,
                showSearchBar = true
            )
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.TopCenter
        ) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                state = listState,
                contentPadding = paddingValues
            ) {
                items(contacts) { contact ->
                    ContactItem(
                        contact = contact,
                        onItemClick = {
                            // Call the onContactSelected function to update the phone number
                            onContactSelected(contact)
                            // Navigate back to the previous screen
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}

private fun getContactsList(context: Context): List<Contact> {
    val contacts = mutableListOf<Contact>()
    val cursor = context.contentResolver.query(
        ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null
    )

    cursor?.use {
        while (it.moveToNext()) {
            val contactName = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
            val contactPhoneNumber = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
            val contactEmail = it.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Email.ADDRESS)
            val contactId = it.getColumnIndex(ContactsContract.Contacts._ID)

            val name = it.getString(contactName)
            val phoneNumber = it.getString(contactPhoneNumber)
            val email = it.getString(contactEmail)
            val numberId = it.getString(contactId)
            contacts.add(Contact(name, phoneNumber, email, numberId))
        }
    }
    cursor?.close()

    // Remove duplicates and sort by name
    return contacts
        .distinctBy { it.phoneNumber }
        .sortedBy { it.name }
}




