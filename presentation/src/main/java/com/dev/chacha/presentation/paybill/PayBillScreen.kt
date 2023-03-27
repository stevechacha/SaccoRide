package com.dev.chacha.presentation.paybill

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.components.ContinueButton
import com.dev.chacha.presentation.common.components.RideOutlinedTextField
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterialApi::class)
@Composable
fun PayBillScreen(
    payBill: PayBill,
    onPayBillClick: (PayBill) -> Unit,
    darkTheme: Boolean = isSystemInDarkTheme(),

    ) {
    Scaffold(
        topBar = {}
    ) { paddingValues ->

        var businessNumber by remember { mutableStateOf("") }
        val (accountNumber, setAccountNumber) = rememberSaveable { mutableStateOf("") }
        val (amount, setAmount) = rememberSaveable { mutableStateOf("") }

        val sheetState = rememberBottomSheetState(
            initialValue = BottomSheetValue.Collapsed
        )

        val scaffoldState = rememberBottomSheetScaffoldState(
            bottomSheetState = sheetState
        )

        val scope = rememberCoroutineScope()

        val buyItems = listOf(
            PayBill(
                name = "John Doe",
                businessNumber = "071234567",
                image = R.drawable.home_icon,
            ),
            PayBill(
                name = "John Doe",
                businessNumber = "071235678",
                image = null,
            )
        )

        BottomSheetScaffold(
            scaffoldState = scaffoldState,
            sheetContent = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.7f)
                        .background(if (darkTheme) Color.DarkGray else Color.White)
                ) {
                    RideOutlinedTextField(
                        value = accountNumber,
                        onValueChange = {
                            setAccountNumber(it)
                        },
                        keyboardType = KeyboardType.Phone,
                        hint = stringResource(id = R.string.search),
                        trailingIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
                            }
                        },
                        modifier = Modifier.padding(16.dp)

                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(if (darkTheme) Color.DarkGray else Color.White)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        ) {
                            buyItems.forEach { buyItem ->
                                PayBillItem(
                                    payBill = buyItem,
                                    onPayBillClick = {
                                        businessNumber = buyItem.businessNumber
                                    }
                                )
                            }
                        }
                    }
                }

            },
            sheetPeekHeight = 0.dp,
            backgroundColor = MaterialTheme.colorScheme.background

        ) {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp, vertical = 16.dp)
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
                            contentDescription = "Call",
                            modifier = Modifier
                                .padding(8.dp)
                                .size(24.dp)
                                .clickable {
                                    scope.launch {
                                        if (sheetState.isCollapsed) {
                                            sheetState.expand()
                                        } else {
                                            sheetState.collapse()
                                        }
                                    }
                                }
                        )
                    }

                )
                Spacer(modifier = Modifier.height(16.dp))

                RideOutlinedTextField(
                    value = accountNumber,
                    onValueChange = {
                        setAccountNumber(it)
                    },
                    keyboardType = KeyboardType.Phone,
                    hint = stringResource(id = R.string.accountNumber),


                    )
                Spacer(modifier = Modifier.height(16.dp))

                RideOutlinedTextField(
                    value = amount,
                    onValueChange = {
                        setAmount(it)
                    },
                    keyboardType = KeyboardType.Phone,
                    hint = stringResource(id = R.string.amount),

                    )

                Spacer(modifier = Modifier.height(16.dp))

                ContinueButton(
                    text = stringResource(id = R.string.continuee),
                    onClick = {}
                )

                Spacer(modifier = Modifier.height(25.dp))

                Column {
                    Text(
                        text = "Frequent",
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    buyItems.forEach { buyItem ->
                        PayBillItem(
                            payBill = buyItem,
                            onPayBillClick = {
                                businessNumber = buyItem.businessNumber
                            }
                        )
                    }
                }

            }


        }
    }


}

@Composable
fun PayBillBottomSheetContent() {

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