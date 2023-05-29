package com.dev.chacha.presentation.common.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.*

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun RootNavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.HOME
    ){
        authNavGraph(navController = navController)
        composable(route = Graph.HOME){
            MainScreen()
        }
    }

}


object Graph {
    const val HOME = "home_graph"
    const val AUTHENTICATION = "auth_graph"
    const val ROOT = "root_graph"
    const val BIOMETRIC = "biometric"
    const val DETAILS = "detail_route"
    const val SEND_MONEY = "send_money"
    const val RECEIVE_MONEY = "receive_money"
    const val TRANSACTION_HISTORY = "transaction_history"
    const val PIN_LOCK = "pin_lock"
    const val ONBOARD = "onboard"
    const val WELCOME = "welcome"
    const val ACCOUNT = "account"
}