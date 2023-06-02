package com.dev.chacha.presentation.bank_transfer


import androidx.compose.animation.core.AnimationSpec
import androidx.compose.foundation.Image
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
import androidx.compose.material.SwipeableDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.components.ContinueButton
import com.dev.chacha.presentation.common.theme.PrimaryColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalCoroutinesApi::class)
@Composable
fun BanksTransfersScreen(
    navigateBack:()->Unit
) {
    val viewModel: BankTransferViewModel = viewModel()
    val bankTransferState by viewModel.bankTransferState.collectAsState()

    var showAccountList by remember { mutableStateOf(false) }
    var showBankList by remember { mutableStateOf(false) }
    val modalBottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    var expanded by remember { mutableStateOf(false) }
    var textfieldSize by remember { mutableStateOf(Size.Zero) }
    val interactionSource = remember { MutableInteractionSource() }
    if (interactionSource.collectIsPressedAsState().value)
        expanded = !expanded



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
                value = bankTransferState.accountName,
                onValueChange = { viewModel.onBankTransferEvent(BankTransferEvent.AccountNameChanged(it)) },
                label = { Text("From Account") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(id = R.dimen.margin_10)),
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
                value = bankTransferState.bankName,
                onValueChange = { viewModel.onBankTransferEvent(BankTransferEvent.BankNameChanged(it)) },
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
                value = bankTransferState.amount,
                onValueChange = { viewModel.onBankTransferEvent(BankTransferEvent.AmountChanged(it)) },
                label = { Text("Amount") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )


            OutlinedTextField(
                value = bankTransferState.accountName,
                onValueChange = { viewModel.onBankTransferEvent(BankTransferEvent.AccountNameChanged(it)) },
                label = { Text("To Account") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = bankTransferState.transferDescription,
                onValueChange = { viewModel.onBankTransferEvent(BankTransferEvent.TransferDescriptionChanged(it)) },
                label = { Text("Transfer Description") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            ContinueButton(
                onClick = { viewModel.onBankTransferEvent(BankTransferEvent.BankTransfer) },
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
                    bankTransferState.accountList.forEach { account ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    viewModel.onBankTransferEvent(BankTransferEvent.AccountNameChanged(account.accountName))
                                    coroutineScope.launch {
                                        showAccountList = false
                                        modalBottomSheetState.hide()
                                    }
                                }
                                .padding(16.dp)
                        ) {
                            Text(
                                text = account.accountName,
                                modifier = Modifier.weight(1f)
                            )
                            if (account.accountName == bankTransferState.accountName) {
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
                    bankTransferState.bankList.forEach { bank ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    viewModel.onBankTransferEvent(BankTransferEvent.BankNameChanged(bank.bankName))
                                    coroutineScope.launch {
                                        showBankList = false
                                        modalBottomSheetState.hide()
                                    }
                                }
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            bank.bankImageUrl?.let { imageUrl ->
                                Image(
                                    painter = rememberImagePainter(data = imageUrl),
                                    contentDescription = "Bank Logo",
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clip(RoundedCornerShape(4.dp))
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                            }

                            Text(
                                text = bank.bankName,
                                modifier = Modifier.weight(1f)
                            )

                            if (bank.bankName == bankTransferState.bankName) {
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
















