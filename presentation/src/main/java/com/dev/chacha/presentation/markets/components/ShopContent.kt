package com.dev.chacha.presentation.markets.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.markets.ShopTextImage


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
                text = "Shop for",
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