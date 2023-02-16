package com.dev.chacha.presentation.transaction.buy_goods

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Edit
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

                }

            )


        }

    }


}

@Composable
@Preview
fun PreviewBuyGoods() {
}