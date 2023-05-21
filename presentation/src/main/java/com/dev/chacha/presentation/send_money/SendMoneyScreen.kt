package com.dev.chacha.presentation.send_money

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.components.AppToolbar
import com.dev.chacha.presentation.common.components.ContinueButton
import com.dev.chacha.presentation.common.components.RideOutlinedTextField
import com.dev.chacha.presentation.contactList.ContactListViewModel
import com.dev.chacha.presentation.contactList.ContactSelectionScreen
import com.dev.chacha.presentation.contactList.ContactUiEvent
import kotlinx.coroutines.launch
import timber.log.Timber

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SendMoneyScreen(
    navigateBack:()->Unit

) {
    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
    val viewModel = viewModel<ContactListViewModel>()
    val coroutineScope = rememberCoroutineScope()
    val (mobileNumber, setMobileNumber) = rememberSaveable { mutableStateOf("") }
    val (amount, setAmount) = rememberSaveable { mutableStateOf("") }
    var selectedRecipient by rememberSaveable { mutableStateOf(RecipientProvider.Mpesa) }
    var phoneNumber by rememberSaveable { mutableStateOf("") }
    var textfieldSize by remember { mutableStateOf(Size.Zero) }
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val navController = rememberNavController()
    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    val interactionSource = remember { MutableInteractionSource() }

    if (interactionSource.collectIsPressedAsState().value)
        expanded = !expanded


    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            Timber.tag("Error Contact").d(phoneNumber)
            ContactSelectionScreen(
                onContactSelected = { contact ->
                    phoneNumber = contact.phoneNumber
                    coroutineScope.launch {
                        phoneNumber = contact.phoneNumber
                        sheetState.collapse()
                    }
                }, navController = navController,
                viewModel = viewModel
            )
        },
        sheetPeekHeight = 0.dp,
        sheetBackgroundColor = Color.Unspecified.copy(alpha = 0F),
        modifier = Modifier.fillMaxSize()

    ) {
        Scaffold(
            topBar = {
                AppToolbar(
                    title = stringResource(id = R.string.send_money),
                    showBackArrow = true,
                    navigateBack = {navigateBack()}
                )
            },
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(horizontal = 16.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = selectedRecipient.name,
                        onValueChange = {newValue->
                            val newProvider = recipientProvider.find { it.name == newValue }
                            if (newProvider != null) {
                                selectedRecipient = newProvider
                            }
                        },
                        trailingIcon = {
                            Icon(icon, "contentDescription",
                                Modifier.clickable { expanded = !expanded })
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = dimensionResource(id = R.dimen.margin_10))
                            .clickable {
                                expanded = !expanded
                            }
                            .onGloballyPositioned { coordinates ->
                                textfieldSize = coordinates.size.toSize()
                            },
                        readOnly = true,
                        leadingIcon = {
                            Icon(
                                selectedRecipient.icon,
                                "contentDescription",
                                modifier = Modifier.size(24.dp)
                                    .clickable { expanded = !expanded }
                            )
                        },
                        interactionSource = interactionSource
                    )
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier
                            .width(with(LocalDensity.current) { textfieldSize.width.toDp() }),
                    ) {
                        recipientProvider.forEach { item ->
                            DropdownMenuItem(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .align(Alignment.CenterHorizontally),
                                text = { Text(text = item.name) },
                                onClick = {
                                    selectedRecipient = item
                                    expanded = false
                                },
                                leadingIcon = {
                                    Icon(
                                        item.icon,
                                        contentDescription = item.name,
                                        modifier = Modifier.padding(end = 8.dp)
                                    )

                                }
                            )
                        }

                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

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
                                        coroutineScope.launch {
                                            if (sheetState.isCollapsed) {
                                                viewModel.send(ContactUiEvent.GetContacts, context)
                                                sheetState.expand()
                                            } else {
                                                sheetState.collapse()
                                            }
                                        }
                                    }
                                },
                            tint = LocalContentColor.current.copy(alpha = ContentAlpha.medium)
                        )
                    },
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
                    error = if (amount.isEmpty() && phoneNumber.isNotEmpty() && recipientProvider.isEmpty()) "Enter amount" else "",
                    isError = amount.isEmpty() && phoneNumber.isNotEmpty() && recipientProvider.isNotEmpty()
                )

                Spacer(modifier = Modifier.height(20.dp))

                ContinueButton(
                    text = stringResource(id = R.string.continuee),
                    onClick = {},
                    enable = phoneNumber.isNotBlank() && amount.isNotBlank()
                )

            }
        }
    }


}




val recipientProvider = listOf(
    RecipientProvider.Mpesa,
    RecipientProvider.AirtelMoney,
    RecipientProvider.Tkash
)

enum class RecipientProvider(val icon: ImageVector) {
    Mpesa(Icons.Default.Phone),
    AirtelMoney(Icons.Default.AttachMoney),
    Tkash(Icons.Default.AccountBalanceWallet)
}
