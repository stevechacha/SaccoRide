package com.dev.chacha.presentation.buy_artime.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.components.AppToolbar
import com.dev.chacha.presentation.common.components.ContinueButton
import com.dev.chacha.presentation.common.components.RideOutlinedTextField
import com.dev.chacha.presentation.contactList.ContactListViewModel
import com.dev.chacha.presentation.contactList.ContactSelectionScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalCoroutinesApi::class)
@Composable
fun Telkom() {
    val coroutineScope = rememberCoroutineScope()

    val viewModel = viewModel<ContactListViewModel>()
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    var phoneNumber by rememberSaveable { mutableStateOf("") }
    val (amount, setAmount) = rememberSaveable { mutableStateOf("") }
    val (mobileNumber, setMobileNumber) = rememberSaveable { mutableStateOf("") }
    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val navController = rememberNavController()
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)

    val scope = rememberCoroutineScope()
    var targetRadio by rememberSaveable { mutableStateOf("myself") }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            ContactSelectionScreen(
                onContactSelected = { contact ->
                    phoneNumber = contact.phoneNumber
                    coroutineScope.launch {
                        phoneNumber = contact.phoneNumber
                        sheetState.collapse()
                    }
                }, navController = navController,
                viewModel = ContactListViewModel()
            )
        },
        sheetPeekHeight = 0.dp,
        sheetBackgroundColor = Color.Unspecified.copy(alpha = 0F),
        backgroundColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()

    ) {
        Scaffold(
            topBar = {
                AppToolbar(
                    title = stringResource(id = R.string.buy_airtime),
                    showBackArrow = true,
                    navigateBack = {}
                )
            },
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Buy Airtime For:")
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = targetRadio == "myself",
                            onClick = { targetRadio = "myself" }
                        )
                        Text(text = "Myself")
                        RadioButton(
                            selected = targetRadio == "someone_else",
                            onClick = { targetRadio = "someone_else" }
                        )
                        Text(text = "Someone Else")
                    }
                }

                if (targetRadio == "someone_else") {
                    RideOutlinedTextField(
                        value = phoneNumber,
                        onValueChange = {
                            phoneNumber = it
                        },
                        keyboardType = KeyboardType.Phone,
                        hint = stringResource(id = R.string.phoneNumber),
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Search,
                                contentDescription = "Call",
                                modifier = Modifier
                                    .padding(8.dp)
                                    .size(24.dp)
                                    .clickable {
                                        coroutineScope.launch {
                                            scope.launch {
                                                if (sheetState.isCollapsed) {
//                                            viewModel.send(ContactUiEvent.GetContacts,context)
                                                    sheetState.expand()
                                                } else {
                                                    sheetState.collapse()
                                                }
                                            }
                                        }
                                    },
                                tint = LocalContentColor.current.copy(alpha = ContentAlpha.medium)
                            )
                        }
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                RideOutlinedTextField(
                    value = amount,
                    onValueChange = {
                        setAmount(it)
                    },
                    keyboardType = KeyboardType.Phone,
                    hint = stringResource(id = R.string.amount),
                    supportText = stringResource(id = R.string.amount_support_text)
                )

                Spacer(modifier = Modifier.height(24.dp))

                ContinueButton(
                    text = stringResource(id = R.string.continuee),
                    onClick = {},
                    enable = mobileNumber.isNotEmpty() && amount.isNotEmpty()
                )
            }


        }
      /*  Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            RideOutlinedTextField(
                value = phoneNumber,
                onValueChange = {
                    phoneNumber = it
                },
                keyboardType = KeyboardType.Phone,
                hint = stringResource(id = R.string.phoneNumber),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = "Call",
                        modifier = Modifier
                            .padding(8.dp)
                            .size(24.dp)
                            .clickable {
                                coroutineScope.launch {
                                    scope.launch {
                                        if (sheetState.isCollapsed) {
//                                            viewModel.send(ContactUiEvent.GetContacts,context)
                                            sheetState.expand()
                                        } else {
                                            sheetState.collapse()
                                        }
                                    }
                                }
                            },
                        tint = LocalContentColor.current.copy(alpha = ContentAlpha.medium)
                    )
                }

            )
            Spacer(modifier = Modifier.height(12.dp))


            RideOutlinedTextField(
                value = amount,
                onValueChange = {
                    setAmount(it)
                },
                keyboardType = KeyboardType.Phone,
                hint = stringResource(id = R.string.amount),
                supportText = stringResource(id = R.string.amount_support_text),

                )

            Spacer(modifier = Modifier.height(24.dp))

            ContinueButton(
                text = stringResource(id = R.string.continuee),
                onClick = {},
                enable = mobileNumber.isNotEmpty() && amount.isNotEmpty()

            )


        }*/

    }


}
