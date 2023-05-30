package com.dev.chacha.presentation.common.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dev.chacha.presentation.common.navigation.DestinationGraph.LOANS_SCREEN_ROUTE
import com.dev.chacha.presentation.common.navigation.DestinationGraph.MARKET_SCREEN_ROUTE
import com.dev.chacha.presentation.common.navigation.DestinationGraph.TRANSACTION_SCREEN_ROUTE
import com.dev.chacha.presentation.transaction.TransactionScreen

fun NavGraphBuilder.transactionNavGraph(navController: NavController){
    composable(route = TRANSACTION_SCREEN_ROUTE) {
        TransactionScreen(
            onSendMoneyClicked = { navController.navigate(HomeAction.SendMoney.route) },
            onBuyAirtimeClicked = { navController.navigate(HomeAction.BuyAirtime.route) },
            onBankTransferClicked = { navController.navigate(HomeAction.BankTransfer.route) },
            onPayBillClicked = { navController.navigate(HomeAction.PayWithSacco.route) },
            onWithdrawClicked = { navController.navigate(HomeAction.Withdraw.route) },
            onDepositClicked = { navController.navigate(HomeAction.Deposit.route) },
            onLoanClicked = { navController.navigate(LOANS_SCREEN_ROUTE) },
            onMarketClicked = { navController.navigate(MARKET_SCREEN_ROUTE) },
            onSavingsClicked = { navController.navigate(HomeAction.Savings.route) },
        )

    }

}