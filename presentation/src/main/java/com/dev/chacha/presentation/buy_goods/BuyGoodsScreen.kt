package com.dev.chacha.presentation.buy_goods

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
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
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.Locale

@Composable
fun BayGoods() {
    BuyGoodsScreen(
        navController = rememberNavController()
    )
}

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun BuyGoodsScreen(
    viewModel: BuyGoodsViewModel = viewModel(),
    navController: NavController
) {

    val buyGoods = getTillNumber()

    val state by viewModel.state.collectAsState()
    var textfieldSize by remember { mutableStateOf(Size.Zero) }
    var expanded by remember { mutableStateOf(false) }
    val icon = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown


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
                    tillNumber = state.tillNumber,
                    amount = state.amount.toDouble()
                ),
                onClickSend = { bayGoods ->
                    // Navigate to the next screen passing the buyGoods object
                },
                viewModel = viewModel,

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
                    .onGloballyPositioned { coordinates ->
                        textfieldSize = coordinates.size.toSize()
                    },
                supportText = state.tillName
            )

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
                            val names = item.tillName.split(" ")
                            val initials = (if (names.size >= 2) {
                                names[0].trim().first().toString().trim() + names[1].trim().first().toString().trim()
                            } else {
                                names[0].trim().first().toString().trim()
                            }).uppercase()
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = initials,
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


@Composable
@Preview
fun PreviewBuyGoods() {
    BuyGoodsScreen(
        navController = rememberNavController()
    )
}