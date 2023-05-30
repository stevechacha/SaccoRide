package com.dev.chacha.presentation.common.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.*
import com.dev.chacha.presentation.common.navigation.DestinationGraph.HOME_SCREEN_ROUTE
import com.dev.chacha.presentation.common.navigation.DestinationGraph.ROOT_ROUTE

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun RootNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        route = ROOT_ROUTE,
        startDestination = HOME_SCREEN_ROUTE
    ) {
        authNavGraph(navController)
        homeNavGraph(navController)
        transactionNavGraph(navController)
        loansNavGraph(navController)
        marketNavGraph(navController)
        accountNavGraph(navController)

    }

}

