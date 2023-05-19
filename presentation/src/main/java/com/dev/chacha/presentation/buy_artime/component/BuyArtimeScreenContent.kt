package com.dev.chacha.presentation.buy_artime.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
fun BuyAirtimeScreenContent() {
    val navController = rememberNavController()
    val modalBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    var (mobileNumber, setMobileNumber) = rememberSaveable { mutableStateOf("") }
    val (amount, setAmount) = rememberSaveable { mutableStateOf("") }
    var phoneNumber by rememberSaveable { mutableStateOf("") }
    var targetRadio by rememberSaveable { mutableStateOf("myself") }

    ModalBottomSheetLayout(
        modifier = Modifier.fillMaxSize(),
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetElevation = 8.dp,
        sheetBackgroundColor = Color.Unspecified.copy(alpha = 0F),
        sheetContent = {
            ContactSelectionScreen(
                onContactSelected = { contact ->
                    phoneNumber = contact.phoneNumber
                    coroutineScope.launch {
                        phoneNumber = contact.phoneNumber
                        modalBottomSheetState.hide()
                    }
                }, navController = navController,
                viewModel = ContactListViewModel()
            )
        },
    ) {
        CompositionLocalProvider(
            LocalOverscrollConfiguration provides null
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
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
    }


}