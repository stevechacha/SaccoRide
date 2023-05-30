package com.dev.chacha.presentation.bottomnav

import androidx.annotation.DrawableRes
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.navigation.DestinationGraph.HOME_SCREEN_ROUTE
import com.dev.chacha.presentation.common.navigation.DestinationGraph.LOANS_SCREEN_ROUTE
import com.dev.chacha.presentation.common.navigation.DestinationGraph.MARKET_SCREEN_ROUTE
import com.dev.chacha.presentation.common.navigation.DestinationGraph.TRANSACTION_SCREEN_ROUTE
import com.dev.chacha.presentation.common.navigation.DestinationGraph.ACCOUNTS_SCREEN_ROUTE

enum class BottomBarDestination(val route: String, @DrawableRes val icon: Int, val title: String) {
    HOME(HOME_SCREEN_ROUTE, R.drawable.home, "Home"),
    TRANSACTION(TRANSACTION_SCREEN_ROUTE, R.drawable.sync_alt_icon, "Transact"),
    LOANS(LOANS_SCREEN_ROUTE, R.drawable.loan_icon, "Loan"),
    MARKET(MARKET_SCREEN_ROUTE, R.drawable.market_icons, "Market"),
    ACCOUNTS(ACCOUNTS_SCREEN_ROUTE, R.drawable.account_circle, "Account")

}