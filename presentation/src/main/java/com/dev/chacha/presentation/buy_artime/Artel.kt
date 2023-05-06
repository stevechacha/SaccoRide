package com.dev.chacha.presentation.buy_artime

import android.provider.ContactsContract
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.components.ContinueButton
import com.dev.chacha.presentation.common.components.RideOutlinedTextField
import com.dev.chacha.presentation.common.theme.SaccoRideTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Artel() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        val (mobileNumber, setMobileNumber) = rememberSaveable { mutableStateOf("") }
        val (amount, setAmount) = rememberSaveable { mutableStateOf("") }

        val context = LocalContext.current

        RideOutlinedTextField(
            value = mobileNumber,
            onValueChange = {
                setMobileNumber(it)
            },
            keyboardType = KeyboardType.Phone,
            hint = stringResource(id = R.string.phoneNumber),
            trailingIcon = {
                IconButton(onClick = {

                }) {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = null
                    )
                }
            }

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

        Spacer(modifier = Modifier.height(24.dp))
        ContinueButton(
            text = stringResource(id = R.string.continuee),
            onClick = {},
            enable = mobileNumber.isNotEmpty() && amount.isNotEmpty()
        )

    }

}


@Composable
@Preview
fun ArtelPreview() {
        Artel()

}