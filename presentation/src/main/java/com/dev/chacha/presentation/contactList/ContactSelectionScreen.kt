package com.dev.chacha.presentation.contactList


import android.Manifest
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.components.AppTopBar
import com.dev.chacha.presentation.common.components.RetryButton
import com.dev.chacha.presentation.contactList.component.ContactItem
import com.dev.chacha.presentation.contactList.component.NoMatchFound
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalPermissionsApi::class, ExperimentalCoroutinesApi::class)
@Composable
fun ContactSelectionScreen(
    viewModel: ContactListViewModel,
    navController: NavController,
    onContactSelected: (Contact) -> Unit,
) {
    val context = LocalContext.current
    val contactState by viewModel.state.collectAsState()
    val lazyState = rememberLazyListState()

    // Check for permission to read contacts
    val permissionState = rememberPermissionState(Manifest.permission.READ_CONTACTS)

    LaunchedEffect(permissionState) {
        if (permissionState.hasPermission) {
            viewModel.send(ContactUiEvent.GetContacts, context)
        } else {
            permissionState.launchPermissionRequest()
        }
    }

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Search Contact",
                initialValue = contactState.searchParams,
                onSearchParamChange = { searchParams ->
                    viewModel.send(ContactUiEvent.SearchContact(searchParams), context)
                },
                showBackArrow = true,
                showSearchBar = true,
            )
        },
    ) { paddingValues ->

        if (contactState.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } else if (contactState.contact.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 12.dp)
                    .fillMaxSize(),
                state = lazyState,
            ) {
                val contacts = contactState.contact
                items(contacts) { contact ->
                    ContactItem(contact = contact) {
                        // Call the onContactSelected function to update the phone number
                        // viewModel.send(ContactUiEvent.SearchContact(""),context) // clear search field
                        viewModel.send(ContactUiEvent.GetContacts, context) // refresh the contact list
                        navController.previousBackStackEntry?.savedStateHandle?.set("selectedContact", contact)
                        onContactSelected(contact)
                        navController.popBackStack()
                    }
                }
            }
        } else if (contactState.error.isNotEmpty()) {
            RetryButton(
                error = contactState.error,
                onRetryEvent = {
                    viewModel.send(ContactUiEvent.GetContacts, context)
                }
            )
        } else {
            NoMatchFound(lottie = R.raw.no_match_found_lottie)
        }

    }
}


/*@OptIn(ExperimentalPermissionsApi::class, ExperimentalCoroutinesApi::class)
@Composable
fun ContactSelectionScreen(
    viewModel: ContactListViewModel,
    navController: NavController,
    onContactSelected: (Contact) -> Unit,
) {
    val context = LocalContext.current
    val contactState by viewModel.state.collectAsState()
    val lazyState = rememberLazyListState()


    // Check for permission to read contacts
    val permissionState = rememberPermissionState(Manifest.permission.READ_CONTACTS)

    LaunchedEffect(permissionState) {
        if (permissionState.hasPermission) {
            viewModel.send(ContactUiEvent.GetContacts, context)
        } else {
            permissionState.launchPermissionRequest()
        }
    }

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Search Contact",
                initialValue = contactState.searchParams,
                onSearchParamChange = { searchParams ->
                    viewModel.send(ContactUiEvent.SearchContact(searchParams), context)
                },
                showBackArrow = true,
                showSearchBar = true,

                )
        },
        ) { paddingValues ->

        if (contactState.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } else if (contactState.contact.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 12.dp)
                    .fillMaxSize(),
                state = lazyState,
            ) {
                val contacts = contactState.contact

                items(contacts) { contact ->
                    ContactItem(
                        contact = contact
                    ) {
                        // Call the onContactSelected function to update the phone number
                        //                                    viewModel.send(ContactUiEvent.SearchContact(""),context) // clear search field
                        viewModel.send(
                            ContactUiEvent.GetContacts,
                            context
                        ) // refresh the contact list
                        navController.previousBackStackEntry?.savedStateHandle?.set(
                            "selectedContact",
                            contact
                        )
                        onContactSelected(contact)
                        navController.popBackStack()
                    }
                }
            }
        } else if (contactState.error.isNotEmpty()) {
            RetryButton(
                error = contactState.error,
                onRetryEvent = {
                    viewModel.send(ContactUiEvent.GetContacts, context)
                }
            )
        } else {

            NoMatchFound(lottie = R.raw.no_match_found_lottie)
        }


        *//*  Box(
              modifier = Modifier
                  .fillMaxSize()
                  .padding(horizontal = 16.dp),
              contentAlignment = Alignment.TopCenter
          ) {

              when {
                  contactState.isLoading -> {
                      CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                  }

                  contactState.error.isNotEmpty() -> {
                      RetryButton(
                          error = contactState.error,
                          onRetryEvent = {
                              viewModel.send(ContactUiEvent.GetContacts, context)
                          }
                      )
                  }

                  else -> {
                      val contacts = contactState.contact
                      LazyColumn(
                          modifier = Modifier
                              .fillMaxWidth()
                              .fillMaxHeight(),
                          contentPadding = paddingValues
                      ) {
                          items(contacts) { contact ->
                              ContactItem(
                                  contact = contact
                              ) {
                                  // Call the onContactSelected function to update the phone number
  //                                    viewModel.send(ContactUiEvent.SearchContact(""),context) // clear search field
                                  viewModel.send(
                                      ContactUiEvent.GetContacts,
                                      context
                                  ) // refresh the contact list
                                  navController.previousBackStackEntry?.savedStateHandle?.set(
                                      "selectedContact",
                                      contact
                                  )
                                  onContactSelected(contact)
                                  navController.popBackStack()
                              }
                          }
                      }
                  }
              }
          }*//*
    }
}*/
