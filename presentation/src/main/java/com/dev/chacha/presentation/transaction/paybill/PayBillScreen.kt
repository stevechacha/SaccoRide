package com.dev.chacha.presentation.transaction.paybill

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.components.RideOutlinedTextField

@Composable
fun PayBillScreen() {
    Scaffold(
        topBar = {}
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            val (businessNumber, setBusinessNumber) = rememberSaveable { mutableStateOf("") }
            val (accountNumber, setAccountNumber) = rememberSaveable { mutableStateOf("") }
            val (amount, setAmount) = rememberSaveable { mutableStateOf("") }


            RideOutlinedTextField(
                value = businessNumber,
                onValueChange = {
                    setBusinessNumber(it)
                },
                keyboardType = KeyboardType.Phone,
                label = {
                    Text(
                        text = stringResource(id = R.string.amount)
                    )
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.amount)
                    )
                },
                supportingText = {
                    Text(
                        text = stringResource(id = R.string.min_amount)
                    )

                },
                trailingIcon = {

                }

            )

            RideOutlinedTextField(
                value = accountNumber,
                onValueChange = {
                    setAccountNumber(it)
                },
                keyboardType = KeyboardType.Phone,
                label = {
                    Text(
                        text = stringResource(id = R.string.amount)
                    )
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.amount)
                    )
                },
                supportingText = {
                    Text(
                        text = stringResource(id = R.string.min_amount)
                    )

                },
                trailingIcon = {

                }

            )

            RideOutlinedTextField(
                value = amount,
                onValueChange = {
                    setAmount(it)
                },
                keyboardType = KeyboardType.Phone,
                label = {
                    Text(
                        text = stringResource(id = R.string.amount)
                    )
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.amount)
                    )
                },
                supportingText = {
                    Text(
                        text = stringResource(id = R.string.min_amount)
                    )

                },

            )


        }

    }

}