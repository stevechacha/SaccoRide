package com.dev.chacha.presentation.baybill

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.navigation.compose.rememberNavController
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.baybill.components.BillDialog
import com.dev.chacha.presentation.common.components.AppTopBar
import com.dev.chacha.presentation.common.components.ContinueButton
import com.dev.chacha.presentation.common.components.RideOutlinedTextField
import com.dev.chacha.presentation.common.navigation.HomeAction
import com.dev.chacha.presentation.paybill.PayBill
import com.dev.chacha.presentation.paybill.component.PayBillItem
import com.dev.chacha.presentation.paybill.payBillItems
import kotlinx.coroutines.launch

/*
@Composable
fun BillScreen(
    onBack: () -> Unit,
    onPay: () -> Unit,
    onPayWithSacco: () -> Unit,
    onPayWithWallet: () -> Unit,
    onPayWithCard: () -> Unit,
    onPayWithMpesa: () -> Unit,
    onPayWithAirtel: () -> Unit,
    onPayWithEquitel: () -> Unit,
    onPayWithTigopesa: () -> Unit,
    onPayWithBank: () -> Unit,
) {

}*/

@Composable
fun BillBayScreen() {
    BillScreen(
        navigateBack = { /*TODO*/ },
        navigateToBillConfirm = {}
    )

}

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun BillScreen(
    navigateBack: () -> Unit,
    navigateToBillConfirm: (PayBill) -> Unit,
) {
//    var bottomSheetContentState by remember { mutableStateOf(BottomSheetContentState()) }
    val modalBottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

    var businessNumber by rememberSaveable { mutableStateOf("") }
    var businessName by rememberSaveable { mutableStateOf("") }
    var accountNumber by rememberSaveable { mutableStateOf("") }
    var date by rememberSaveable { mutableStateOf("") }
    val (amount, setAmount) = rememberSaveable { mutableStateOf("") }

    val viewModel = BillViewModel()

    /*val shouldShowDialog = viewModel.shouldShowDialog.value
    if (shouldShowDialog) {
        BillDialog(
            onDismiss = {
                viewModel.setShowDialogState(!viewModel.shouldShowDialog.value)
            },
            onClickSend = {
                viewModel.onTransactionConfirm()
                // Perform the payment here using the payBill details...
            },
            currentFeedbackString = "",
            payBill = PayBill(
                businessNumber = businessNumber,
                name = businessName,
                accountNumber = accountNumber,
                amount = amount.toDouble()
            ),
            onFeedbackChange = {},
            isError = false,
            error = null
        )
    }*/

    val navController = rememberNavController()

    var showDialog by rememberSaveable { mutableStateOf(false) }


    if (showDialog) {
        BillDialog(
            onDismiss = {
                showDialog = false
            },
            onClickSend = {
                navigateToBillConfirm.invoke(
                    PayBill(
                        name = businessName,
                        businessNumber = businessNumber,
                        accountNumber = accountNumber,
                        amount = amount.toDouble(),
                        date = System.currentTimeMillis().toString()
                    ),
                )
            },
            payBill = PayBill(
                name = businessName,
                businessNumber = businessNumber,
                accountNumber = accountNumber,
                amount = amount.toDouble(),
                date = System.currentTimeMillis().toString()
            )
        )
    }


    val uriHandler = LocalUriHandler.current
    BackHandler(enabled = true) {
        coroutineScope.launch {
            if (modalBottomSheetState.isVisible)
                modalBottomSheetState.hide()
            else navigateBack()
        }
    }

    ModalBottomSheetLayout(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxSize(),
        sheetState = modalBottomSheetState,
        sheetBackgroundColor = Color.Unspecified.copy(alpha = 0F),
        sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        sheetContent = {
            Scaffold(
                topBar = {
                    AppTopBar(
                        title = "",
                        initialValue = "",
                        onSearchParamChange = {},
                        showSearchBar = true
                    )
                },
                modifier = Modifier.fillMaxSize()
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .padding(top = 10.dp, start = 12.dp, end = 12.dp)
                ) {

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            LazyColumn {
                                payBillItems.forEachIndexed { index, payBill ->
                                    item {
                                        PayBillItem(
                                            payBill = payBill,
                                            onPayBillClick = {
                                                businessNumber = payBill.businessNumber
                                                businessName = payBill.name
                                                coroutineScope.launch {
                                                    modalBottomSheetState.hide()
                                                }
                                            }
                                        )

                                    }
                                }
                            }

                        }
                    }
                }

            }

        }
    ) {
        CompositionLocalProvider(
            LocalOverscrollConfiguration provides null
        ) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = stringResource(id = R.string.paybill),
                                fontSize = 14.sp
                            )
                        },
                        backgroundColor = MaterialTheme.colorScheme.background,
                        contentColor = MaterialTheme.colorScheme.onBackground,
                        elevation = 0.dp,
                        navigationIcon = {
                            IconButton(onClick = { navigateBack() }) {
                                Image(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "Back",
                                    colorFilter = ColorFilter.tint(
                                        MaterialTheme.colorScheme.onBackground
                                    )
                                )

                            }
                        }
                    )

                }
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                        .background(MaterialTheme.colorScheme.background),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    RideOutlinedTextField(
                        value = businessNumber,
                        onValueChange = {
                            businessNumber = it
                        },
                        keyboardType = KeyboardType.Phone,
                        hint = stringResource(id = R.string.businessNumber),
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
                        },
                        supportText = businessName

                    )

                    val accountNumberLimit = 20

                    RideOutlinedTextField(
                        value = accountNumber,
                        onValueChange = {
                            accountNumber = it
                        },
                        keyboardType = KeyboardType.Text,
                        hint = stringResource(id = R.string.accountNumber),
                        accountNumberLength = "${accountNumber.length}/${accountNumberLimit}"
                    )

                    RideOutlinedTextField(
                        value = amount,
                        onValueChange = {
                            setAmount(it)
                        },
                        keyboardType = KeyboardType.Number,
                        hint = stringResource(id = R.string.amount),

                        )


                    ContinueButton(
                        text = stringResource(id = R.string.continuee),
                        onClick = {
                            showDialog = true
                        },
                        enable = businessNumber.isNotEmpty() && accountNumber.isNotEmpty() && amount.isNotEmpty()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {
                        Text(
                            text = "Frequent",
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        payBillItems.forEach { buyItem ->
                            PayBillItem(
                                payBill = buyItem,
                                onPayBillClick = {
                                    businessNumber = buyItem.businessNumber
                                    businessName = buyItem.name
                                }
                            )
                        }
                    }

                }
            }

        }
    }

}

@Composable
@Preview
fun BillPreview() {
    BillScreen(
        navigateBack = {},
        navigateToBillConfirm = {},
    )
}
