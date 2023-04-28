package com.dev.chacha.presentation.markets


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.markets.components.MarketTopBar
import com.dev.chacha.presentation.markets.components.ShopCard
import kotlin.random.Random


@Composable
fun MarketScreen() {
    Scaffold(
        topBar = {
           MarketTopBar(title = "Market")
        },
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            LazyColumn {
                item {
                    Text(
                        text = stringResource(id = R.string.shop_header_text),
                        modifier = Modifier
                            .fillMaxWidth(),
                        fontSize = 26.sp,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2
                    )
                }

            }
            Spacer(modifier = Modifier.height(16.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                userScrollEnabled = true
            ) {

                item {
                    ShopCard(
                        drawable = R.drawable.home_icon,
                        title = R.string.electronics,
                        subTitle = R.string.electronics,
                        onClickCard = {}
                    )
                }
                item {
                    ShopCard(
                        drawable = R.drawable.home_icon,
                        title = R.string.electronics,
                        subTitle = R.string.electronics,
                        onClickCard = {}
                    )
                }

                item {
                    ShopCard(
                        drawable = R.drawable.home_icon,
                        title = R.string.electronics,
                        subTitle = R.string.electronics,
                        onClickCard = {}
                    )
                }

                item {
                    ShopCard(
                        drawable = R.drawable.home_icon,
                        title = R.string.electronics,
                        subTitle = R.string.electronics,
                        onClickCard = {}
                    )
                }
                item {
                    ShopCard(
                        drawable = R.drawable.home_icon,
                        title = R.string.electronics,
                        subTitle = R.string.electronics,
                        onClickCard = {}
                    )
                }

                item {
                    ShopCard(
                        drawable = R.drawable.home_icon,
                        title = R.string.electronics,
                        subTitle = R.string.electronics,
                        onClickCard = {}
                    )
                }

            }

        }
    }
}

fun randommColor(): Color {
    val red = Random.nextInt(256)
    val green = Random.nextInt(256)
    val blue = Random.nextInt(256)
    return Color(red, green, blue)
}


@Composable
@Preview
fun MarketPreview() {
    Surface(modifier = Modifier.fillMaxSize()) {
        MarketScreen()
    }
}