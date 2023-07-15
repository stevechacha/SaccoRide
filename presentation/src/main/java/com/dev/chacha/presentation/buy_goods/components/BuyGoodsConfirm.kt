package com.dev.chacha.presentation.buy_goods.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.dev.chacha.presentation.buy_goods.BuyGoods
import com.dev.chacha.presentation.common.components.AppToolbar

@Composable
fun BuyGoodsTopBar(title: String, icon: Painter, onIconClick: () -> Unit) {


}

@Composable
fun BuyGoodsConfirms(buyGoods: BuyGoods) {
    Scaffold(
        topBar = {
            AppToolbar(
                title = "Confirm Payment",
                navigateBack = {}
            )
        },

        ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 16.dp)
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
                                    text = buyGoods.tillName,
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
                            Text(text = "Deone")
                        }
                    }


                }

            }

        }

    }
}
