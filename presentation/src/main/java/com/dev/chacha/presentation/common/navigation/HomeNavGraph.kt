package com.dev.chacha.presentation.common.navigation

import android.app.Activity
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.dev.chacha.presentation.account.AccountScreen
import com.dev.chacha.presentation.loan.LoanScreen
import com.dev.chacha.presentation.transaction.TransactionScreen
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.home.HomeScreen
import com.dev.chacha.presentation.information.Information
import com.dev.chacha.presentation.overview.Overview
import com.dev.chacha.presentation.savings.SavingsScreen

@Composable
fun HomeNavGraph(
    navController: NavHostController,
    showBottomBar: (Boolean) -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route ,
        route = Graph.HOME
    ){
        composable(BottomBarScreen.Home.route){
            showBottomBar(true)
            HomeScreen(
                onClickAction = {
                    navController.navigate(Graph.DETAILS)
                }
            )

        }
        composable(BottomBarScreen.Transaction.route){
            showBottomBar(true)
            TransactionScreen(
                onClickAction = {}
            )

        }
        composable(BottomBarScreen.Loan.route){
            showBottomBar(true)
            LoanScreen ()

        }
        composable(BottomBarScreen.Savings.route){
            showBottomBar(true)
            SavingsScreen()

        }
        composable(BottomBarScreen.Account.route){
            showBottomBar(true)
            AccountScreen()

        }

        detailsNavGraph(navController = navController)

    }

}

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAILS,
        startDestination = DetailsScreen.Information.route
    ) {
        composable(route = DetailsScreen.Information.route) {
            Information() {
                navController.navigate(DetailsScreen.Overview.route)
            }
        }
        composable(route = DetailsScreen.Overview.route) {
            Overview() {
                navController.popBackStack(
                    route = DetailsScreen.Information.route,
                    inclusive = false
                )
            }
        }
    }
}

sealed class DetailsScreen(val route: String) {
    object Information : DetailsScreen(route = "INFORMATION")
    object Overview : DetailsScreen(route = "OVERVIEW")
}


sealed class BottomBarScreen(val route: String, @DrawableRes val icon: Int, val title: String){
    object Home: BottomBarScreen("home", R.drawable.ic_home, "Home")
    object Transaction: BottomBarScreen("transaction",R.drawable.sessions_icon,"Transaction")
    object Savings: BottomBarScreen("savings",R.drawable.about_icon,"Savings")
    object Loan: BottomBarScreen("loan",R.drawable.sessions_icon,"Loan")
    object Account: BottomBarScreen("account",R.drawable.about_icon,"Account")
}

val bottomNavigationItems = listOf(
    BottomBarScreen.Home,
    BottomBarScreen.Transaction,
    BottomBarScreen.Loan,
    BottomBarScreen.Savings,
    BottomBarScreen.Account
)