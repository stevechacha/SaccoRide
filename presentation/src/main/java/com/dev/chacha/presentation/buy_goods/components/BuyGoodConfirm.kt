package com.dev.chacha.presentation.buy_goods.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.buy_goods.BuyGoods

@Composable
fun BuyGoodConfirm(
    message: String?,
    @DrawableRes drawableRes: Int,
    buttonText: String,
    buyGoods: BuyGoods
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, top = 16.dp, bottom = 10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = drawableRes),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = message.toString()
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.width(100.dp)
                    ) {
                        Text(
                            text = "Pay to:",
                            maxLines = 1,
                            textAlign = TextAlign.Start,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "Till No",
                            maxLines = 1,
                            textAlign = TextAlign.Start,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "Amount",
                            maxLines = 1,
                            textAlign = TextAlign.Start,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "Date/Time",
                            maxLines = 1,
                            textAlign = TextAlign.Start,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Column(
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.Center
                    ) {
                            Text(
                                text = buyGoods.tillNumber,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )

                        Text(
                            text = buyGoods.tillNumber,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = buyGoods.amount.toString(),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = buyGoods.date.toString(),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                }

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = buttonText)
                }
            }


        }

    }
}

val buyGoods = BuyGoods(
    tillName = "",
    tillNumber = "",
    amount = 1000.0,
    date = "122/24/2"
)

@Composable
@Preview
fun BuyGoodsConfirmPreview() {
    BuyGoodConfirm(
        message  = "Your Transaction was successful",
        drawableRes = R.drawable.main_icon ,
        buttonText = "Done" ,
        buyGoods = buyGoods
    )
}