package com.dev.chacha.presentation.paybill

import androidx.compose.foundation.Image
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
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.components.AppTopBar
import com.dev.chacha.presentation.common.components.ContinueButton
import com.dev.chacha.presentation.common.components.RideOutlinedTextField
import com.dev.chacha.presentation.paybill.component.PayBillItem
import kotlinx.coroutines.launch


@Composable
fun PayBills() {
    Scaffold(
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
                    IconButton(onClick = { /*TODO*/ }) {
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
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            PayBillScreen(
                onPayBillClick = {}
            )

        }
    }


}

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterialApi::class)
@Composable
fun PayBillScreen(
    onPayBillClick: (PayBill) -> Unit,
) {

    var businessNumber by remember { mutableStateOf("") }
    var businessName by remember { mutableStateOf("") }
    val (accountNumber, setAccountNumber) = rememberSaveable { mutableStateOf("") }
    val (amount, setAmount) = rememberSaveable { mutableStateOf("") }

    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )

    val scope = rememberCoroutineScope()



    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            Scaffold(
                topBar = {
                    AppTopBar(
                        title = "",
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
                    LazyColumn() {
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
        backgroundColor = MaterialTheme.colorScheme.background

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
                    setAccountNumber(it)
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
                onClick = {},
                enable = businessNumber.isNotEmpty() && accountNumber.isNotEmpty()&& accountNumber.length >6 && amount.isNotEmpty() && amount.toInt() > 0
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