package com.dev.chacha.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dev.chacha.presentation.R


@Composable
fun HomeServiceCard(
    onLoanClicked: () -> Unit,
    onMarketClicked: () -> Unit,
    onSavingsClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        HomeServiceItem(
            onItemClick = onLoanClicked,
            drawable = R.drawable.loan_icon,
            stringRes = R.string.loan
        )

        HomeServiceItem(
            onItemClick = onMarketClicked,
            drawable = R.drawable.market_icons,
            stringRes = R.string.our_market
        )

        HomeServiceItem(
            onItemClick = onSavingsClicked,
            drawable = R.drawable.savings_icons,
            stringRes = R.string.savings
        )

    }

}