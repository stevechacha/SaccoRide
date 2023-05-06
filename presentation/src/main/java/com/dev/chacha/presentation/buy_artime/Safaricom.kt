package com.dev.chacha.presentation.buy_artime

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.components.ContinueButton
import com.dev.chacha.presentation.common.components.RideOutlinedTextField
import com.dev.chacha.presentation.contactList.ContactListViewModel
import com.dev.chacha.presentation.contactList.ContactSelectionScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun Safaricom() {
    val navController = rememberNavController()
    val modalBottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    var (mobileNumber, setMobileNumber) = rememberSaveable { mutableStateOf("") }
    val (amount, setAmount) = rememberSaveable { mutableStateOf("") }
    var phoneNumber by rememberSaveable { mutableStateOf("") }


    ModalBottomSheetLayout(
        modifier = Modifier.fillMaxSize(),
        sheetState = modalBottomSheetState,
        sheetBackgroundColor = Color.Unspecified.copy(alpha = 0F),
        sheetContent = {
            ContactSelectionScreen(
                onContactSelected = { contact ->
                    phoneNumber = contact.phoneNumber
                    coroutineScope.launch {
                        phoneNumber = contact.phoneNumber
                        modalBottomSheetState.hide()
                    }
                } , navController = navController,
                viewModel = ContactListViewModel()
            )
        }
    ) {
        CompositionLocalProvider(
            LocalOverscrollConfiguration provides null
        ) {
            Column(
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
                                        if (modalBottomSheetState.isVisible) {
                                            modalBottomSheetState.hide()
                                        } else {
                                            modalBottomSheetState.show()
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


            }
        }


    }



}