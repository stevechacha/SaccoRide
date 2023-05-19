package com.dev.chacha.presentation.deposit

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.components.ContinueButton
import com.dev.chacha.presentation.common.components.RideOutlinedTextField


@Composable
fun DepositScreen(
    navigateBack: ()->Unit
) {
    Scaffold { paddingValues ->
        var amount by remember { mutableStateOf("") }
        val accountOptions = listOf("Savings Account", "Checking Account", "Investment Account")
        var selectedAccount by remember { mutableStateOf(accountOptions[0]) }
        var textfieldSize by remember { mutableStateOf(Size.Zero) }
        var expanded by remember { mutableStateOf(false) }
        val icon = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Spacer(modifier = Modifier.height(32.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                RideOutlinedTextField(
                    value = selectedAccount,
                    onValueChange = { selectedAccount = it },
                    hint = stringResource(id = R.string.accountType),
                    keyboardType = KeyboardType.Text,
                    trailingIcon = {
                        Icon(
                            icon,
                            "contentDescription",
                            Modifier.clickable { expanded = !expanded }
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            textfieldSize = coordinates.size.toSize()
                        },
                    readOnly = true
                )

                DropdownMenu(
                    expanded = false,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current) { textfieldSize.width.toDp() }),
                    ) {
                    accountOptions.forEach { account ->
                        DropdownMenuItem(
                            onClick = { selectedAccount = account },
                            text = { Text(text = account) }
                        )
                    }
                }

            }

            RideOutlinedTextField(
                value = amount,
                onValueChange = { amount = it },
                hint = stringResource(id = R.string.amount),
                keyboardType = KeyboardType.Number,

                )



            ContinueButton(
                onClick = { /* Perform deposit */ },
                modifier = Modifier.align(Alignment.End),
                text = "Deposit"
            )
        }

    }


}

@Composable
@Preview
fun PreviewText() {
    Surface(modifier = Modifier.fillMaxSize()) {
        DepositScreen(
            navigateBack = {}
        )
    }
}