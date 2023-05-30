package com.dev.chacha.presentation.common.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.dev.chacha.presentation.common.navigation.DestinationGraph.LOANS_SCREEN_ROUTE
import com.dev.chacha.presentation.loan.LoanScreen
import com.dev.chacha.presentation.pin.LoanPinScreen

@RequiresApi(Build.VERSION_CODES.P)
fun NavGraphBuilder.loansNavGraph(navController: NavHostController){
    navigation(
        route = LOANS_SCREEN_ROUTE,
        startDestination = LoanScreenRoute.LoanPinLock.route
    ) {
        composable(LoanScreenRoute.LoanPinLock.route) {
            LoanPinScreen(
                onClickAction = {
                    navController.navigate(LoanScreenRoute.LoanScreen.route) {
                        popUpTo(LoanScreenRoute.LoanPinLock.route) {
                            inclusive = true
                        }
                    }
                },
                navController = navController
            )
        }

        composable(LoanScreenRoute.LoanScreen.route) {
            LoanScreen(
                navController = navController,
                )

        }

    }

}




sealed class LoanScreenRoute(val route: String) {
    object LoanPinLock : LoanScreenRoute("loan_pin")
    object LoanScreen : LoanScreenRoute("loan_route")
    object Welcome : LoanScreenRoute("welcome")
    object Login : LoanScreenRoute(route = "LOGIN")



}