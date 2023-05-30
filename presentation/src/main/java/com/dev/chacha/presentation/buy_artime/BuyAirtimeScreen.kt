package com.dev.chacha.presentation.buy_artime

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.components.AppToolbar
import com.dev.chacha.presentation.common.components.ContinueButton
import com.dev.chacha.presentation.common.components.RideOutlinedTextField
import com.dev.chacha.presentation.common.theme.PrimaryColor
import com.dev.chacha.presentation.contactList.ContactListViewModel
import com.dev.chacha.presentation.contactList.ContactSelectionScreen
import com.dev.chacha.presentation.contactList.ContactUiEvent
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalPagerApi::class, ExperimentalMaterialApi::class,
    ExperimentalFoundationApi::class, ExperimentalCoroutinesApi::class
)

@Composable
fun BuyAirtimeScreen(
    navigateBack: () -> Unit
) {
    val buyAirtimeViewModel: BuyAirtimeViewModel = viewModel()
    val coroutineScope = rememberCoroutineScope()
    var targetRadio by rememberSaveable { mutableStateOf(buyAirtimeViewModel.targetRadio) }
    var selectedAccount by rememberSaveable { mutableStateOf(AccountType.AccountA) }
    val buyAirtimeState by buyAirtimeViewModel.buyAirtimeState.collectAsState()
    val viewModel = viewModel<ContactListViewModel>()
    val context = LocalContext.current
    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val navController = rememberNavController()
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
    var textfieldSize by remember { mutableStateOf(Size.Zero) }
    var expanded by remember { mutableStateOf(false) }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            ContactSelectionScreen(
                onContactSelected = { contact ->
                    coroutineScope.launch {
                        buyAirtimeViewModel.handleBuyAirtimeEvent(BuyAirtimeEvent.PhoneNumberChanged(contact.phoneNumber))
                        sheetState.collapse()
                    }

                }, navController = navController,
                viewModel = viewModel
            )
        },
        sheetPeekHeight = 0.dp,
        sheetBackgroundColor = Color.Unspecified.copy(alpha = 0F),
        backgroundColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize(),

        ) {
        Scaffold(
            topBar = {
                AppToolbar(
                    title = stringResource(id = R.string.buy_airtime),
                    showBackArrow = true,
                    navigateBack = {navigateBack()}
                )
            },
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                ) {
                    Text(text = "Buy Airtime For:")
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = targetRadio == "myself",
                            onClick = {
                                targetRadio = "myself"
                                buyAirtimeViewModel.updateTargetRadio("myself")
                            },
                            colors = RadioButtonDefaults.colors(selectedColor = PrimaryColor)
                        )
                        Text(text = "Myself")
                        RadioButton(
                            selected = targetRadio == "someone_else",
                            onClick = {
                                targetRadio = "someone_else"
                                buyAirtimeViewModel.updateTargetRadio("someone_else")
                            },
                            colors = RadioButtonDefaults.colors(selectedColor = PrimaryColor)
                        )
                        Text(text = "Someone Else")
                    }
                }

                if (targetRadio == "someone_else") {
                    RideOutlinedTextField(
                        value = buyAirtimeState.phoneNumber,
                        onValueChange = { phoneNumber->
                            buyAirtimeViewModel.handleBuyAirtimeEvent(
                                BuyAirtimeEvent.PhoneNumberChanged(phoneNumber)
                            )
                        },
                        keyboardType = KeyboardType.Phone,
                        hint = stringResource(id = R.string.phoneNumber),
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Search,
                                tint = LocalContentColor.current.copy(alpha = ContentAlpha.medium),
                                contentDescription = "Call",
                                modifier = Modifier.padding(8.dp).size(24.dp)
                                    .clickable {
                                        coroutineScope.launch {
                                            if (sheetState.isCollapsed) {
                                                viewModel.send(ContactUiEvent.GetContacts, context)
                                                sheetState.expand()
                                            } else {
                                                sheetState.collapse()
                                            }
                                        }
                                    },
                            )
                        }
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Column(modifier = Modifier.fillMaxWidth()) {
                    RideOutlinedTextField(
                        value = buyAirtimeState.amount,
                        onValueChange = { amount ->
                            buyAirtimeViewModel.handleBuyAirtimeEvent(
                                BuyAirtimeEvent.AmountChanged(amount)
                            )
                        },
                        maxLength = 5,
                        keyboardType = KeyboardType.Phone,
                        hint = stringResource(id = R.string.amount),
                        supportText = stringResource(id = R.string.amount_support_text),
                        modifier = Modifier
                            .fillMaxWidth()
                            .onGloballyPositioned { coordinates ->
                                textfieldSize = coordinates.size.toSize()
                            },
                        trailingIcon = {
                            Row(
                                modifier = Modifier
                                    .clickable { expanded = !expanded }
                                    .padding(end = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    selectedAccount.icon,
                                    modifier = Modifier.size(24.dp),
                                   contentDescription =  "contentDescription",
                                )
                                Text(text = selectedAccount.name)
                                Icon(Icons.Filled.KeyboardArrowDown, "contentDescription")
                            }

                        }
                    )
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier
                            .width(with(LocalDensity.current) { textfieldSize.width.toDp() }),
                    ) {
                        accountType.forEach { item ->
                            DropdownMenuItem(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .align(Alignment.CenterHorizontally),
                                text = { Text(text = item.name) },
                                onClick = {
                                    selectedAccount = item
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

                Spacer(modifier = Modifier.height(24.dp))

                ContinueButton(
                    text = stringResource(id = R.string.continuee),
                    onClick = {},
                    enable = buyAirtimeState.isBuyAirtimeEnabled
                )
            }


        }

    }

}


val accountType = listOf(
    AccountType.AccountA,
    AccountType.AccountB,
)


@Composable
@Preview
fun BuyAirtimePreview() {
    BuyAirtimeContentScreen(
        buyAirtimeState = BuyAirtimeState(),
        onPhoneNumberChanged = {},
        onAmountChanged = {},
        onBuyAirtimeClicked = {},
    )

}