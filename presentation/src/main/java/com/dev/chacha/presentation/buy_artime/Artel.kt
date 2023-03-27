package com.dev.chacha.presentation.buy_artime

import android.Manifest
import android.content.Context
import android.provider.ContactsContract
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.components.ContinueButton
import com.dev.chacha.presentation.common.components.RideOutlinedTextField
import com.dev.chacha.presentation.common.theme.SaccoRideTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Artel() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val (mobileNumber, setMobileNumber) = rememberSaveable { mutableStateOf("") }
        val (amount, setAmount) = rememberSaveable { mutableStateOf("") }


        /*val context = LocalContext.current
        val permissionState = rememberPermissionState(android.Manifest.permission.READ_CONTACTS)

        val contactLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickContact()
        ) { contactUri ->
            // Get the contact details from the contactUri
            val cursor = contactUri?.let { context.contentResolver.query(it, null, null, null, null) }
            if (cursor != null && cursor.moveToFirst()) {
                val name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                val number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                // Use the contact name and number as the new text value
                onValueChange("$name ($number)")
            }
            cursor?.close()
        }*/


        RideOutlinedTextField(
            value = mobileNumber,
            onValueChange = {
                setMobileNumber(it)
            },
            keyboardType = KeyboardType.Phone,
            hint = stringResource(id = R.string.phoneNumber),
            trailingIcon = {
                IconButton(onClick = {}) {
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

            )

        Spacer(modifier = Modifier.height(16.dp))
        ContinueButton(
            text = stringResource(id = R.string.continuee),
            onClick = {}
        )







    }

}





@Composable
@Preview
fun ArtelPreview() {
    SaccoRideTheme {
        Artel()
    }
}