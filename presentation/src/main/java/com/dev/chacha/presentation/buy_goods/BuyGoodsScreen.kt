package com.dev.chacha.presentation.buy_goods

import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.buy_goods.components.BuyGoodsDialog
import com.dev.chacha.presentation.common.components.ContinueButton
import com.dev.chacha.presentation.common.components.RideOutlinedTextField
import com.dev.chacha.presentation.common.navigation.HomeAction
import com.dev.chacha.presentation.common.theme.PrimaryColor
import com.dev.chacha.presentation.extensions.getCurrentDateTime
import com.dev.chacha.presentation.extensions.getInitials
import com.dev.chacha.presentation.send_money.recipientProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BayGoods(
    scaffoldState: BottomSheetState,
    navController: NavController
) {
    BuyGoodsScreen(
        navController = navController,
        sheetState = scaffoldState,
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalCoroutinesApi::class, ExperimentalMaterialApi::class)
@Composable
fun BuyGoodsScreen(
    viewModel: BuyGoodsViewModel = viewModel(),
    navController: NavController,
    sheetState: BottomSheetState,

    ) {

    val buyGoods = getTillNumber()
    val state by viewModel.state.collectAsState()
    var textfieldSize by remember { mutableStateOf(Size.Zero) }
    var expanded by remember { mutableStateOf(false) }
    val icon = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
    val scope = rememberCoroutineScope()
    val currentPage by viewModel.settledPage.collectAsState()
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(key1 = currentPage) {
        if (currentPage == 0) {
            focusRequester.requestFocus()
        }
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (viewModel.showDialog.collectAsState().value) {
            BuyGoodsDialog(
                onDismiss = {
                    viewModel.onDialogDismissed()
                },
                buyGoods = BuyGoods(
                    tillName = state.tillName,
                    tillNumber = state.tillNumber.toString(),
                    amount = state.amount.toDouble(),
                    date = state.date
                ),
                onClickSend = { buyGoods ->
                    Timber.tag("BuyGoods").d(buyGoods.toString())
                    val route = HomeAction.TillConfirm.sendData(
                        tillName = buyGoods.tillName,
                        tillNumber = buyGoods.tillNumber,
                        amount = buyGoods.amount.toDouble(),
                        date = getCurrentDateTime()
                    )
                    navController.navigate(route = route)

                },
            )
        }

        Column(modifier = Modifier.fillMaxWidth()) {
            RideOutlinedTextField(
                value = state.tillNumber,
                onValueChange = { viewModel.onTillNumberChanged(it) },
                hint = stringResource(id = R.string.tillNumber),
                keyboardType = KeyboardType.Phone,
                trailingIcon = {
                    Icon(
                        icon,
                        "contentDescription",
                        Modifier.clickable { expanded = !expanded }
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester)
                    .onGloballyPositioned { coordinates ->
                        textfieldSize = coordinates.size.toSize()
                    },
                supportText = state.tillName,
                error = if ( state.tillNumber.isEmpty() ) "Enter PhoneNumber" else "",
                isError =  state.tillNumber.isEmpty()
            )

            Button(onClick = {
                scope.launch {
                    if (sheetState.isCollapsed) {
                        sheetState.expand()
                    } else {
                        sheetState.collapse()
                    }
                }
            }) {
                Text(text = "BottomSheet")

            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { textfieldSize.width.toDp() }),
            ) {
                buyGoods.forEach { item ->
                    DropdownMenuItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .align(Alignment.CenterHorizontally),
                        text = { Text(text = item.tillName) },
                        onClick = {
                            viewModel.onTillNumberChanged(item.tillNumber)
                            item.tillName.let { viewModel.onTillNameChanged(it) }
                            expanded = false
                        },
                        leadingIcon = {
                            val buyGoodsInitials = item.tillName
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = getInitials(buyGoodsInitials),
                                    fontWeight = FontWeight.Normal,
                                    textAlign = TextAlign.Center,
                                    fontSize = 26.sp
                                )

                            }

                        }
                    )
                }

            }

        }
        Spacer(modifier = Modifier.height(5.dp))

        RideOutlinedTextField(
            value = state.amount,
            onValueChange = { viewModel.onAmountChanged(it) },
            keyboardType = KeyboardType.Phone,
            hint = stringResource(id = R.string.amount),
            maxLength = 6
        )

        Spacer(modifier = Modifier.height(24.dp))

        ContinueButton(
            text = stringResource(id = R.string.continuee),
            onClick = {
                viewModel.onContinueButtonClicked()
            },
            enable = viewModel.isInputValid()
        )

    }
}


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class)
@Composable
@Preview
fun PreviewBuyGoods() {
    BuyGoodsScreen(
        navController = rememberNavController(),
        sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed),
    )
}


