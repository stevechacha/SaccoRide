package com.dev.chacha.presentation.contactList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.LaunchedEffect
import com.dev.chacha.presentation.contacts.ContactUiEvent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.dev.chacha.presentation.common.navigation.SendMon
import com.dev.chacha.presentation.contacts.Contact
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun ContactScreen(navController: NavController) {
    val viewModel: ContactListViewModel = viewModel()
    val contactState by viewModel.state.collectAsState()
    var phoneNumber by remember { mutableStateOf("") }
    val context = LocalContext.current


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

    LaunchedEffect(contactState.contact) {
        if (contactState.contact.isNotEmpty()) {
            // Set the phoneNumber state to the first contact's phoneNumber
            phoneNumber = contactState.contact.forEach { _ ->
            }.toString()
        }
    }

    // Pass a lambda function to ContactSelectionScreen to update the phoneNumber state when a contact is selected
    val onContactSelected: (Contact) -> Unit = { contact ->
        phoneNumber = contact.phoneNumber
    }

    // Call the send function in the viewModel to retrieve the list of contacts
    LaunchedEffect(Unit) {
//        viewModel.send(ContactUiEvent.GetContacts,context)
    }

    // Show a loading indicator while contacts are being retrieved
    if (contactState.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5F)),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color.White)
        }
    }

    // Show an error message if there was an error retrieving the contacts
/*
    contactState.error?.let { error ->
        AlertDialog(
            onDismissRequest = {
                viewModel.send(ContactUiEvent.GetContacts,context)
            },
            title = { Text("Error") },
            text = { Text(error) },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.send(ContactUiEvent.GetContacts,context)
                    }
                ) {
                    Text("Retry")
                }
            }
        )
    }
*/

    // Show the contact selection screen if the user navigated to it
    val currentRoute = currentRoute(navController)
    if (currentRoute == SendMon.ContactSelection.route) {
        ContactSelectionScreen(onContactSelected = onContactSelected, navController = navController, viewModel = viewModel)
    }

}
fun currentRoute(navController: NavController): String? {
    val currentBackStackEntry = navController.currentBackStackEntry
    return currentBackStackEntry?.destination?.route
}


