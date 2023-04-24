package com.dev.chacha.presentation.send_money.artel_money

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.components.ContinueButton
import com.dev.chacha.presentation.common.components.RideOutlinedTextField

@Composable
fun ArtelMoneyScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val (mobileNumber, setMobileNumber) = rememberSaveable { mutableStateOf("") }
        val (amount, setAmount) = rememberSaveable { mutableStateOf("") }


        RideOutlinedTextField(
            value = mobileNumber,
            onValueChange = {
                setMobileNumber(it)
            },
            keyboardType = KeyboardType.Phone,
            hint = stringResource(id = R.string.phoneNumber),

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

            )

        Spacer(modifier = Modifier.height(20.dp))

        ContinueButton(
            text = stringResource(id = R.string.continuee),
            onClick = {},
            enable = mobileNumber.isNotEmpty() && amount.isNotEmpty()

        )


    }

}