package com.dev.chacha.presentation.bank_transfer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.components.ContinueButton
import com.dev.chacha.presentation.common.theme.PrimaryColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BankTransferScreen(
    navigateBack:()->Unit
) {
    var fromAccount by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var toBank by remember { mutableStateOf("") }
    var toAccount by remember { mutableStateOf("") }
    var transferDescription by remember { mutableStateOf("") }

    var showAccountList by remember { mutableStateOf(false) }
    var showBankList by remember { mutableStateOf(false) }

    val modalBottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    var expanded by remember { mutableStateOf(false) }
    var textfieldSize by remember { mutableStateOf(Size.Zero) }
    val interactionSource = remember { MutableInteractionSource() }
    if (interactionSource.collectIsPressedAsState().value)
        expanded = !expanded

    val accountList = listOf("Account 1", "Account 2", "Account 3")
    val bankList = listOf("Bank 1", "Bank 2", "Bank 3", "Bank 4", "Bank 5", "Bank 6")


    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("SACCO Bank Transfer", style = MaterialTheme.typography.labelSmall) },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                navigationIcon = {
                    IconButton(onClick = { navigateBack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = fromAccount,
                onValueChange = { fromAccount = it },
                label = { Text("From Account") },
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
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search",
                        modifier = Modifier.clickable {
                            expanded = !expanded
                            coroutineScope.launch {
                                if (modalBottomSheetState.isVisible) {
                                    modalBottomSheetState.hide()
                                } else {
                                    showAccountList = true
                                    modalBottomSheetState.show()
                                }
                            }
                        }
                    )
                },
                interactionSource = interactionSource
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = toBank,
                onValueChange = { toBank = it },
                label = { Text("Select Bank") },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search",
                        modifier = Modifier.clickable {
                            coroutineScope.launch {
                                if (modalBottomSheetState.isVisible) {
                                    modalBottomSheetState.hide()
                                } else {
                                    showBankList = true
                                    modalBottomSheetState.show()
                                }
                            }

                        }
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = amount,
                onValueChange = { amount = it },
                label = { Text("Amount") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = toAccount,
                onValueChange = { toAccount = it },
                label = { Text("To Account") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = transferDescription,
                onValueChange = { transferDescription = it },
                label = { Text("Transfer Description") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            ContinueButton(
                onClick = { /* Perform transfer logic here */ },
                text = "Transfer Funds"
            )

            Spacer(modifier = Modifier.height(16.dp))


        }
    }

    if (showAccountList) {
        ModalBottomSheetLayout(
            sheetBackgroundColor = Color.Unspecified.copy(alpha = 0F),
            sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
            sheetState = modalBottomSheetState,
            sheetContent = {
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    verticalArrangement = Arrangement.Center

                ) {
                    accountList.forEach { account ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    fromAccount = account
                                    coroutineScope.launch {
                                        showAccountList = false
                                        modalBottomSheetState.hide()
                                    }
                                }
                                .padding(16.dp)
                        ) {
                            Text(
                                text = account,
                                modifier = Modifier.weight(1f)
                            )
                            if (fromAccount == account) {
                                Icon(
                                    imageVector = Icons.Filled.Check,
                                    contentDescription = "Selected",
                                    tint = PrimaryColor
                                )
                            }
                        }
                    }
                }
            }
        ) {
            // Empty content
        }
    }

    if (showBankList) {
        ModalBottomSheetLayout(
            sheetBackgroundColor = Color.Unspecified.copy(alpha = 0F),
            sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
            sheetState = modalBottomSheetState,
            sheetContent = {
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    verticalArrangement = Arrangement.Center
                ) {
                    bankList.forEach { bank ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    toBank = bank
                                    coroutineScope.launch {
                                        showBankList = false
                                        modalBottomSheetState.hide()
                                    }
                                }
                                .padding(16.dp),
                        ) {
                            Text(
                                text = bank,
                                modifier = Modifier.weight(1f)
                            )
                            if (toBank == bank) {
                                Icon(
                                    imageVector = Icons.Filled.Check,
                                    contentDescription = "Selected",
                                    tint = PrimaryColor
                                )
                            }
                        }
                    }
                }
            }
        ) {
            // Empty content
        }
    }
}















