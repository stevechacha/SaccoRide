package com.dev.chacha.presentation.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dev.chacha.presentation.activity.MainScreen


@Composable
fun RootNavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ){
        authNavGraph(navController = navController)

        composable(route = Graph.HOME){
            MainScreen()
        }
    }

}

object Graph{
    const val HOME = "home_graph"
    const val AUTHENTICATION = "auth_graph"
    const val ROOT = "root_graph"
    const val DETAILS = "detail_route"
}