package com.dev.chacha.presentation.statement.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.components.AppToolbar
import com.dev.chacha.presentation.common.components.ContinueButton
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun StatementDetail(
    onViewStatement: () -> Unit
) {
    Scaffold(
        topBar = {
            AppToolbar(
                title = "Statement",
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(
                    text = "Select Duration",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(listOf("1M", "3M", "6M", "1Y", "2Y")) {
                        DurationChip(
                            onClick = { },
                            text = it
                        )
                    }
                }


                Text(
                    text = "OR",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Text(text = "Transaction Type")
                var textfieldSize by remember { mutableStateOf(Size.Zero) }
                val startDateDialogState = rememberMaterialDialogState()
                val endDateDialogState = rememberMaterialDialogState()
                var endDate by remember {
                    mutableStateOf(LocalDate.now())
                }
                var startDate by remember {
                    mutableStateOf(LocalDate.now())
                }
                val startFormattedDate by remember {
                    derivedStateOf {
                        DateTimeFormatter
                            .ofPattern("dd MMM yyyy")
                            .format(startDate)
                    }
                }
                val endFormattedDate by remember {
                    derivedStateOf {
                        DateTimeFormatter
                            .ofPattern("dd MMM yyyy")
                            .format(endDate)
                    }
                }


                var expanded by remember { mutableStateOf(false) }
                var transactionType by rememberSaveable { mutableStateOf("") }
                val icon = if (expanded)
                    Icons.Filled.KeyboardArrowUp
                else
                    Icons.Filled.KeyboardArrowDown

                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = transactionType,
                            modifier = Modifier
                                .weight(1f)
                                .onGloballyPositioned { coordinates ->
                                    textfieldSize = coordinates.size.toSize()
                                }
                                .clickable { expanded = !expanded }
                        )
                        Icon(icon, "contentDescription",
                            Modifier.clickable { expanded = !expanded })
                    }
                    Divider(
                        thickness = 1.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier
                            .width(with(LocalDensity.current) { textfieldSize.width.toDp() }),
                    ) {
                        transactionTypes.forEach { item ->
                            DropdownMenuItem(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .align(Alignment.CenterHorizontally),
                                text = { Text(text = item.toString()) },
                                onClick = {
                                    transactionType = item.toString()
                                    expanded = false
                                }
                            )
                        }

                    }
                }


                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = startFormattedDate,
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    startDateDialogState.show()
                                }
                                .align(Alignment.CenterVertically)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.calendar_month),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .size(25.dp)
                                .clickable {
                                    startDateDialogState.show()

                                }
                        )
                    }
                    Divider(
                        thickness = 1.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = endFormattedDate,
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    endDateDialogState.show()
                                }
                                .align(Alignment.CenterVertically)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.calendar_month),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .size(25.dp)
                                .clickable {
                                    endDateDialogState.show()

                                }
                        )
                    }
                    Divider(
                        thickness = 1.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }



                MaterialDialog(
                    dialogState = endDateDialogState,
                    buttons = {
                        positiveButton(text = "Ok") {
                            endDateDialogState.hide()
                        }
                        negativeButton(text = "Cancel")
                    }
                ) {
                    datepicker(
                        initialDate = LocalDate.now(),
                        title = "Pick a date",
                        allowedDateValidator = {
                            it.isBefore(LocalDate.now())
                        }
                    ) {
                        endDate = it
                    }
                }

                MaterialDialog(
                    dialogState = startDateDialogState,
                    buttons = {
                        positiveButton(text = "Ok") {
                            startDateDialogState.hide()
                        }
                        negativeButton(text = "Cancel")
                    }
                ) {
                    datepicker(
                        initialDate = LocalDate.now(),
                        title = "Pick a date",
                        allowedDateValidator = {
                            it.isBefore(LocalDate.now())
                        }
                    ) {
                        startDate = it
                    }
                }

            }

        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                ContinueButton(
                    text = "Continue",
                    onClick = {
                        onViewStatement()
                    },
                    enable = true
                )
            }

        }
    )

}


val transactionTypes = listOf(
    TransactionType.All,
    TransactionType.BuyAirtime,
    TransactionType.PayBill,
    TransactionType.BuyGoods,


    )

enum class TransactionType {
    All,
    PayBill,
    BuyGoods,
    BuyAirtime

}

@Composable
fun DurationChip(
    onClick: () -> Unit,
    text: String
) {
    AssistChip(
        onClick = { onClick() },
        label = {
            Text(text = text)
        }
    )
}