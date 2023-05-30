package com.dev.chacha.presentation.common.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.dev.chacha.presentation.common.navigation.DestinationGraph.MARKET_SCREEN_ROUTE
import com.dev.chacha.presentation.markets.MarketScreen
import com.google.accompanist.navigation.material.bottomSheet

fun NavGraphBuilder.marketNavGraph(navController: NavHostController){
    composable(route = MARKET_SCREEN_ROUTE) {
        MarketScreen(
            navigateBack = { navController.navigateUp() }
        )
    }
}