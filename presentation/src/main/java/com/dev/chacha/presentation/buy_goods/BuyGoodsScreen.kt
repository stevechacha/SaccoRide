package com.dev.chacha.presentation.buy_goods

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Size
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import coil.compose.AsyncImage
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.components.ContinueButton
import com.dev.chacha.presentation.common.components.RideOutlinedTextField
import com.dev.chacha.presentation.common.theme.SaccoRideTheme

@Composable
fun BuyGoodsScreen() {
    Scaffold(
        topBar = {

        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            var tillNumber by remember { mutableStateOf("") }
            val (amount, setAmount) = rememberSaveable { mutableStateOf("") }

            var textfieldSize by remember { mutableStateOf(Size.Zero)}

            var expanded by remember { mutableStateOf(false) }

            val buyItems = listOf(
                BuyGoodItem(
                    name = "John Doe",
                    contact = "0712345678",
                    image = R.drawable.home_icon,
                ),
                BuyGoodItem(
                    name = "John Doe",
                    contact = "0712345678",
                    image = null,
                ),
            )

            val icon = if (expanded)
                Icons.Filled.KeyboardArrowUp
            else
                Icons.Filled.KeyboardArrowDown

            Column(modifier = Modifier.fillMaxWidth()) {
                RideOutlinedTextField(
                    value = tillNumber,
                    onValueChange = {
                        tillNumber =  it
                    },
                    hint = stringResource(id = R.string.tillNumber),
                    keyboardType = KeyboardType.Phone,
                    trailingIcon = {
                        Icon(icon, "contentDescription",
                            Modifier.clickable { expanded = !expanded })
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            textfieldSize = coordinates.size.toSize()
                        }
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current){textfieldSize.width.toDp()}),
                ) {
                    buyItems.forEach { item ->
                        DropdownMenuItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .align(Alignment.CenterHorizontally),
                            text = { Text(text = item.name) },
                            onClick = {
                                tillNumber = item.contact
                                expanded = false
                            },
                            leadingIcon = {
                                if (item.image != null) {
                                    AsyncImage(
                                        model = item.image,
                                        contentDescription = "profile_image",
                                        modifier = Modifier
                                            .size(40.dp)
                                            .clip(shape = CircleShape),
                                        placeholder = null
                                    )

                                } else {
                                    val names = item.name.split(" ")
                                    val initials = names[0].first().toString() + names[1].first().toString()
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

                            },
                            trailingIcon = {
                                Text("F11",
                                    textAlign = TextAlign.Center)
                            },
                        )
                    }

                }

            }
            Spacer(modifier = Modifier.height(12.dp))

            RideOutlinedTextField(
                value = amount,
                onValueChange = {
                    setAmount(it)
                },
                keyboardType = KeyboardType.Phone,
                hint = stringResource(id = R.string.amount),
                )

            Spacer(modifier = Modifier.height(24.dp))

            ContinueButton(
                text = stringResource(id = R.string.continuee),
                onClick = {}
            )



        }

    }


}

@Composable
@Preview
fun PreviewBuyGoods() {
    SaccoRideTheme {
        BuyGoodsScreen()
    }
}