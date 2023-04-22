package com.dev.chacha.presentation.markets

import android.widget.Space
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.chacha.presentation.R

@Composable
fun MarketScreen() {
    Scaffold(
        topBar = {
            MarketTopBar()
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
        ) {
            Text(
                text = stringResource(id = R.string.shop_header_text),
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxWidth(),
                fontSize = 26.sp,
            )
            Card(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {


                }

            }
            LazyVerticalGrid(
                columns = GridCells.Adaptive(160.dp),
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 16.dp),
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ){
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

            Text(
                text = "Account",
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxWidth(),
                fontSize = 14.sp,
            )
            LazyVerticalGrid(
                columns = GridCells.Adaptive(160.dp),
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 16.dp),
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ){
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

@Composable
fun MarketTopBar() {
    TopAppBar(
        title = {
            Text(
                text = "Market",
                style = MaterialTheme.typography.labelSmall
            )
        },
        modifier = Modifier.fillMaxWidth(),
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = null
                )
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = null
                )
            }
        },
        backgroundColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
    )
}

@Composable
fun ShopContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text =   "Shop for",
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = "See All",
                style = MaterialTheme.typography.labelSmall

            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ShopTextImage(
                text = R.string.electronics,
                drawable = R.drawable.home_icon,
                onItemClick = {}
            )
            ShopTextImage(
                text = R.string.electronics,
                drawable = R.drawable.home_icon,
                onItemClick = {}
            )
            ShopTextImage(
                text = R.string.electronics,
                drawable = R.drawable.home_icon,
                onItemClick = {}
            )
            ShopTextImage(
                text = R.string.electronics,
                drawable = R.drawable.home_icon,
                onItemClick = {}
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            ShopTextImage(
                text = R.string.electronics,
                drawable = R.drawable.home_icon,
                onItemClick = {}
            )
            ShopTextImage(
                text = R.string.electronics,
                drawable = R.drawable.home_icon,
                onItemClick = {}
            )
            ShopTextImage(
                text = R.string.electronics,
                drawable = R.drawable.home_icon,
                onItemClick = {}
            )
            ShopTextImage(
                text = R.string.electronics,
                drawable = R.drawable.home_icon,
                onItemClick = {}
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ShopTextImage(
                text = R.string.electronics,
                drawable = R.drawable.home_icon,
                onItemClick = {}
            )
            ShopTextImage(
                text = R.string.electronics,
                drawable = R.drawable.home_icon,
                onItemClick = {}
            )
            ShopTextImage(
                text = R.string.electronics,
                drawable = R.drawable.home_icon,
                onItemClick = {}
            )
            ShopTextImage(
                text = R.string.electronics,
                drawable = R.drawable.home_icon,
                onItemClick = {}
            )
        }
    }

}

@Composable
@Preview
fun MarketPreview() {
    Surface(modifier = Modifier.fillMaxSize()) {
        MarketScreen()
    }
}