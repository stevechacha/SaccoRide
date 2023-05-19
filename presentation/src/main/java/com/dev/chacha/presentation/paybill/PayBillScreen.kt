package com.dev.chacha.presentation.paybill

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.baybill.components.BillDialog
import com.dev.chacha.presentation.common.components.AppTopBar
import com.dev.chacha.presentation.common.components.ContinueButton
import com.dev.chacha.presentation.common.components.RideOutlinedTextField
import com.dev.chacha.presentation.paybill.component.PayBillItem
import kotlinx.coroutines.launch


@Composable
fun PayBills() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        PayBillScreen(
            onPayBillClick = {},
            onNavigateToBillConfirm = {}
        )

    }


}

@OptIn(
    ExperimentalMaterialApi::class, ExperimentalMaterialApi::class,
    ExperimentalFoundationApi::class
)

@Composable
fun PayBillScreen(
    onPayBillClick: (PayBill) -> Unit,
    onNavigateToBillConfirm: (PayBill) -> Unit,
) {

    var businessNumber by rememberSaveable { mutableStateOf("") }
    var businessName by rememberSaveable { mutableStateOf("") }
    var accountNumber by rememberSaveable { mutableStateOf("") }
    var date by rememberSaveable { mutableStateOf("") }
    val (amount, setAmount) = rememberSaveable { mutableStateOf("") }

    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )

    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)

    val scope = rememberCoroutineScope()

    var showDialog by rememberSaveable { mutableStateOf(false) }


    if (showDialog) {
        BillDialog(
            onDismiss = {
                showDialog = false
            },
            onClickSend = {
                onNavigateToBillConfirm.invoke(
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

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            Scaffold(
                topBar = {
                    AppTopBar(
                        title = "Search PayBill",
                        initialValue = "",
                        onSearchParamChange = {},
                        showSearchBar = true
                    )
                }
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(paddingValues)
                        .padding(horizontal = 12.dp)
                ) {
                    LazyColumn(
                    ) {
                        payBillItems.forEachIndexed { index, payBill ->
                            item {
                                PayBillItem(
                                    payBill = payBill,
                                    onPayBillClick = {
                                        businessNumber = payBill.businessNumber
                                        businessName = payBill.name
                                        scope.launch {
                                            sheetState.collapse()
                                        }
                                    }
                                )

                            }

                        }
                    }
                }

            }

        },
        sheetPeekHeight = 0.dp,
        sheetBackgroundColor = Color.Unspecified.copy(alpha = 0F),
        backgroundColor = MaterialTheme.colorScheme.background,
        sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),

        ) {
        CompositionLocalProvider(
            LocalOverscrollConfiguration provides null
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .background(MaterialTheme.colorScheme.background)

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
                            contentDescription = "Search",
                            modifier = Modifier.padding(8.dp).size(24.dp)
                                .clickable {
                                    scope.launch {
                                        if (sheetState.isCollapsed) {
                                            sheetState.expand()
                                        } else {
                                            sheetState.collapse()
                                        }
                                    }
                                },
                            tint = LocalContentColor.current.copy(alpha = ContentAlpha.medium)

                        )
                    },
                    supportText = businessName

                )
                Spacer(modifier = Modifier.height(10.dp))
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
                Spacer(modifier = Modifier.height(10.dp))

                RideOutlinedTextField(
                    value = amount,
                    onValueChange = {
                        setAmount(it)
                    },
                    keyboardType = KeyboardType.Number,
                    hint = stringResource(id = R.string.amount),

                    )

                Spacer(modifier = Modifier.height(16.dp))

                ContinueButton(
                    text = stringResource(id = R.string.continuee),
                    onClick = {
                        showDialog = true
                    },
                    enable = businessNumber.isNotEmpty() && accountNumber.isNotEmpty() && amount.isNotEmpty()
                )

                Spacer(modifier = Modifier.height(25.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Frequent",
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    LazyColumn {
                        payBillItems.forEachIndexed { index, payBill ->
                            item {
                                PayBillItem(
                                    payBill = payBill,
                                    onPayBillClick = {
                                        businessNumber = payBill.businessNumber
                                        businessName = payBill.name
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

@Composable
@Preview
fun PayBillScreenPreview() {
    Column {
        LazyColumn {
            items(20) {
                PayBillItem(
                    payBill = PayBill(
                        name = "John Doe",
                        businessNumber = "1234567890",
                        image = null
                    )
                ) {

                }
            }
        }
    }

}