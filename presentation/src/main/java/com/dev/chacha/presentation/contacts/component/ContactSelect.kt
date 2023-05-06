package com.dev.chacha.presentation.contacts.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dev.chacha.presentation.common.navigation.SendMon
import com.dev.chacha.presentation.contactList.ContactListViewModel
import com.dev.chacha.presentation.contactList.ContactSelectionScreen

/*
@Composable
fun ContactSelect(
    navController: NavController
) {
    var phoneNumber by remember { mutableStateOf("") }
    // Call the ContactSelectionScreen composable outside of the Column
    ContactSelectionScreen(onContactSelected = { contact ->
        phoneNumber = contact.phoneNumber
    }, navController = navController)
}*/

@Composable
fun ContactSelect(navController: NavController) {
    var phoneNumber by remember { mutableStateOf("") }
    ContactSelectionScreen(
        onContactSelected = { contact ->
            phoneNumber = contact.phoneNumber
        },
        navController = navController,
        viewModel = ContactListViewModel()
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Contact",
            modifier = Modifier.padding(bottom = 16.dp)
        )
        var name by remember { mutableStateOf("") }
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Phone Number") },
            trailingIcon = {
                IconButton(onClick = {
                    // Navigate to the screen to select a contact to import
                    navController.navigate(SendMon.ContactSelection.route) {
                        // Set the behavior of the back button in the contact selection screen
                        popUpTo(navController.currentDestination!!.id) {
                            inclusive = true
                        }
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = { /* Save contact information */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text("Save")
        }
    }
}
