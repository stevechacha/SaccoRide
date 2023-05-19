package com.dev.chacha.presentation.send_money.mpesa

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import coil.compose.AsyncImage
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.components.ContinueButton
import com.dev.chacha.presentation.common.components.RideOutlinedTextField

@Composable
fun MpesaScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val (mobileNumber, setMobileNumber) = rememberSaveable { mutableStateOf("") }
        val (amount, setAmount) = rememberSaveable { mutableStateOf("") }

        var recipientProvider by rememberSaveable { mutableStateOf("") }
        var tillName by rememberSaveable { mutableStateOf("") }

        var textfieldSize by remember { mutableStateOf(Size.Zero) }

        var expanded by remember { mutableStateOf(false) }

        var showDialog by rememberSaveable { mutableStateOf(false) }

        val icon = if (expanded)
            Icons.Filled.KeyboardArrowUp
        else
            Icons.Filled.KeyboardArrowDown

        Column(modifier = Modifier.fillMaxWidth()) {
            RideOutlinedTextField(
                value = recipientProvider,
                onValueChange = {
                    recipientProvider = it
                },
                hint = stringResource(id = R.string.recipientProvider),
                keyboardType = KeyboardType.Phone,
                trailingIcon = {
                    Icon(icon, "contentDescription",
                        Modifier.clickable { expanded = !expanded })
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        textfieldSize = coordinates.size.toSize()
                    },
                supportText = tillName
            )


            RideOutlinedTextField(
                value = mobileNumber,
                onValueChange = {
                    setMobileNumber(it)
                },
                keyboardType = KeyboardType.Phone,
                hint = stringResource(id = R.string.phoneNumber),
                error = if (amount.isNotEmpty() && mobileNumber.isEmpty()) "Please enter phone number" else ""
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
                enable = mobileNumber.isNotBlank() && amount.isNotBlank()
            )


        }
    }

}