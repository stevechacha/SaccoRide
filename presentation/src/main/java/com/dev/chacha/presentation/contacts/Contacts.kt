package com.dev.chacha.presentation.contacts

import android.Manifest
import android.content.pm.PackageManager
import android.os.Parcelable
import android.provider.ContactsContract
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(val name: String, val phoneNumber: String): Parcelable

@Composable
fun ContactListItem(contact: Contact) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = contact.name)
        Text(text = contact.phoneNumber)
    }

}

@Composable
fun ContactList() {
    val context = LocalContext.current
    val hasReadContactsPermission = remember {
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.READ_CONTACTS
        ) == PackageManager.PERMISSION_GRANTED
    }
    val contacts = remember {
        if (hasReadContactsPermission) {
            // Retrieve all contacts from the device
            val cursor2 = context.contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                null,
                null,
                null
            )
            val contacts = mutableListOf<Contact>()

            /*while (cursor2 != null && cursor2.moveToNext()) {
                val userName =
                    cursor2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                val userPhoneNumber =
                    cursor2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)

                val name = cursor2.getString(userName)
                val phoneNumber = cursor2.getString(userPhoneNumber)

                contacts.add(Contact(name, phoneNumber))
            }
            cursor2?.close()*/
            val cursor = context.contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                null,
                null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
            )
            while (cursor?.moveToNext() == true) {
                val name =
                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                val phoneNumber =
                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                contacts.add(Contact(name = name.toString(), phoneNumber = phoneNumber.toString()))
            }
            cursor?.close()
            contacts
        } else {
            emptyList<Contact>()
        }
    }

    val pickContact = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickContact()
    ) { uri ->
        val cursor = uri?.let { context.contentResolver.query(it, null, null, null, null) }
        if (cursor?.moveToFirst() == true) {
            val name =
                cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                    .let { cursor.getString(it) }
            val phoneNumber =
                cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                    .let { cursor.getString(it) }
            // Do something with the contact name and phone number
            // Here we're just showing a Toast message
            Toast.makeText(context, "Selected contact: $name ($phoneNumber)", Toast.LENGTH_SHORT)
                .show()
        }
        cursor?.close()
    }


    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Contacts")
            })
        },
        floatingActionButton =
        {
            FloatingActionButton(
                onClick = {
                    // Launch the contact picker
                    pickContact.launch(null)
                },
                content = {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = null
                    )
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            items(items = contacts) { contact ->
                ContactListItem(contact = contact)
            }
        }

    }
}
