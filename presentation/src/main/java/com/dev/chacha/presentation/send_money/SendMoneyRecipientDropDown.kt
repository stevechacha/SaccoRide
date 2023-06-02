package com.dev.chacha.presentation.send_money

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.util.Constants

@Composable
fun SendMoneyRecipientDropDown(
    sendMoneyState: SendMoneyState,
    onRecipientProvider: (RecipientProvider)->Unit
) {
    val sendMoneyViewModel: SendMoneyViewModel = viewModel()
    var textfieldSize by remember { mutableStateOf(Size.Zero) }
    var expanded by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    if (interactionSource.collectIsPressedAsState().value)
        expanded = !expanded
    val icon = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown

    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = sendMoneyState.selectedRecipient.name,
            onValueChange = {newValue->
                val newProvider = recipientProvider.find { it.name == newValue }
                if (newProvider != null) {
                    onRecipientProvider(newProvider)
                }
            },
            trailingIcon = {
                Icon(icon, "contentDescription",
                    Modifier.clickable { expanded = !expanded })
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(id = R.dimen.margin_10))
                .clickable {
                    expanded = !expanded
                }
                .onGloballyPositioned { coordinates ->
                    textfieldSize = coordinates.size.toSize()
                },
            readOnly = true,
            leadingIcon = {
                Icon(
                    sendMoneyState.selectedRecipient.icon,
                    "contentDescription",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { expanded = !expanded }
                )
            },
            interactionSource = interactionSource
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = androidx.compose.ui.Modifier
                .width(with(LocalDensity.current) { textfieldSize.width.toDp() }),
        ) {
            recipientProvider.forEach { item ->
                DropdownMenuItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally),
                    text = { Text(text = item.name) },
                    onClick = {
                        sendMoneyViewModel.onSendMoneyEvent(SendMoneyEvent.RecipientProviderSelected(item))
                        expanded = false
                    },
                    leadingIcon = {
                        Icon(
                            item.icon,
                            contentDescription = item.name,
                            modifier = Modifier.padding(end = 8.dp)
                        )

                    }
                )
            }

        }
    }

}

fun getRecipientProviderString(recipientProvider: String, context: Context): String {
    return if (recipientProvider.isEmpty() || recipientProvider == Constants.RECIPIENT_PROVIDER) {
        context.getString(R.string.all_recpient_with_emoji)
    } else {
        val recipientName = getRecipientProviderName(recipientProvider)
        val recipientIcon = getRecipientProviderIcon(recipientProvider)
        context.getString(R.string.recipient_with_emoji, recipientIcon, recipientName)
    }
}

fun getRecipientProviderName(recipientProvider: String): String {
    val provider = RecipientProvider.valueOf(recipientProvider)
    return when (provider) {
        RecipientProvider.Mpesa -> "Mpesa"
        RecipientProvider.AirtelMoney -> "Airtel Money"
        RecipientProvider.Tkash -> "Tkash"
    }
}

fun getRecipientProviderIcon(recipientProvider: String): String {
    val provider = RecipientProvider.valueOf(recipientProvider)
    return when (provider) {
        RecipientProvider.Mpesa -> Icons.Default.Phone.toString()
        RecipientProvider.AirtelMoney -> Icons.Default.AttachMoney.toString()
        RecipientProvider.Tkash -> Icons.Default.AccountBalanceWallet.toString()
    }
}

