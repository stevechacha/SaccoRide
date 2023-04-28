package com.dev.chacha.presentation.buy_goods

import androidx.compose.foundation.Image
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
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ColorFilter
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
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.buy_goods.components.BuyGoodsDialog
import com.dev.chacha.presentation.common.components.ContinueButton
import com.dev.chacha.presentation.common.components.RideOutlinedTextField
import com.dev.chacha.presentation.common.navigation.HomeAction
import com.dev.chacha.presentation.common.theme.SaccoRideTheme

@Composable
fun BuyGoods(
    navigateTo: ()->Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.buy_goods),
                        fontSize = 14.sp
                    )
                },
                backgroundColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground,
                elevation = 0.dp,
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Image(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            colorFilter = ColorFilter.tint(
                                MaterialTheme.colorScheme.onBackground
                            )
                        )

                    }
                }
            )

        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BuyGoodsScreen(
                navigateTo = navigateTo
            )

        }
    }


}

@Composable
fun BuyGoodsScreen(
    navigateTo: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var tillNumber by rememberSaveable { mutableStateOf("") }
        var tillName by rememberSaveable { mutableStateOf("") }
        var date by rememberSaveable { mutableStateOf("") }
        var (amount, setAmount) = rememberSaveable { mutableStateOf("") }

        var textfieldSize by remember { mutableStateOf(Size.Zero) }

        var expanded by remember { mutableStateOf(false) }

        var showDialog by rememberSaveable { mutableStateOf(false) }


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

        val navController = rememberNavController()

        if (showDialog) {
            BuyGoodsDialog(
                onDismiss = {
                    showDialog = false
                },
                bayGoods = BayGoods(
                    tillName = tillName,
                    tillNumber = tillNumber,
                    amount = amount.toDouble()
                ),
                onClickSend = {

                }

            )
        }

        val icon = if (expanded)
            Icons.Filled.KeyboardArrowUp
        else
            Icons.Filled.KeyboardArrowDown

        Column(modifier = Modifier.fillMaxWidth()) {
            RideOutlinedTextField(
                value = tillNumber,
                onValueChange = {
                    tillNumber = it
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
                    },
                supportText = tillName
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { textfieldSize.width.toDp() }),
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
                            tillName = item.name
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
                                val initials =
                                    names[0].first().toString() + names[1].first().toString()
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

                        }
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
            onClick = {
                showDialog = true
            },
            enable = tillNumber.isNotEmpty() && amount.isNotEmpty() && amount.toInt() > 0 && amount.toInt() < 3000000 && tillNumber.length > 5
        )

    }

}

@Composable
@Preview
fun PreviewBuyGoods() {
        BuyGoods(
            navigateTo = {}
        )
}