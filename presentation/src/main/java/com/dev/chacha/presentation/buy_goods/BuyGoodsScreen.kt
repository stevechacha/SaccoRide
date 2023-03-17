package com.dev.chacha.presentation.buy_goods

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.auth.login.LoginViewModel
import com.dev.chacha.presentation.common.components.AppOutlinedTextField
import com.dev.chacha.presentation.common.components.RideOutlinedTextField

@Composable
fun BuyGoodsScreen() {
    Scaffold(
        topBar = {}
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            val (tillNumber, setTillNumber) = rememberSaveable { mutableStateOf("") }
            val (amount, setAmount) = rememberSaveable { mutableStateOf("") }
            val (idNumber, setIdNumber) = rememberSaveable { mutableStateOf("") }

            var expanded by remember { mutableStateOf(false) }

            val icon = if (expanded)
                Icons.Filled.KeyboardArrowUp
            else
                Icons.Filled.KeyboardArrowDown


            RideOutlinedTextField(
                value = tillNumber,
                onValueChange = {
                    setTillNumber(it)
                },
                hint = stringResource(id = R.string.amount),
                keyboardType = KeyboardType.Phone,
                trailingIcon = {
                    androidx.compose.material.Icon(icon, "contentDescription",
                        Modifier.clickable { expanded = !expanded })
                }


            )


            RideOutlinedTextField(
                value = amount,
                onValueChange = {
                    setAmount(it)
                },
                keyboardType = KeyboardType.Phone,
                hint = stringResource(id = R.string.amount),


                )


        }

    }


}

@Composable
@Preview
fun PreviewBuyGoods() {
}