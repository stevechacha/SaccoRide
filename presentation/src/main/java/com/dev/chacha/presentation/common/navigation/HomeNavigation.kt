package com.dev.chacha.presentation.common.navigation

import PayBillScreen
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.rememberBottomSheetState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dev.chacha.presentation.bank_transfer.BankTransferScreen
import com.dev.chacha.presentation.baybill.BillScreen
import com.dev.chacha.presentation.baybill.components.BillConfirmationScreen
import com.dev.chacha.presentation.buy_artime.BuyAirtimeScreen
import com.dev.chacha.presentation.buy_goods.BuyGoods
import com.dev.chacha.presentation.buy_goods.components.BuyGoodsConfirms
import com.dev.chacha.presentation.common.navigation.DestinationGraph.HOME_SCREEN_ROUTE
import com.dev.chacha.presentation.common.navigation.DestinationGraph.LOANS_SCREEN_ROUTE
import com.dev.chacha.presentation.common.navigation.DestinationGraph.MARKET_SCREEN_ROUTE
import com.dev.chacha.presentation.deposit.DepositScreen
import com.dev.chacha.presentation.home.HomeScreen
import com.dev.chacha.presentation.pay_with_sacco.PayWithSacco
import com.dev.chacha.presentation.paybill.PayBill
import com.dev.chacha.presentation.savings.SavingsScreen
import com.dev.chacha.presentation.send_money.SendMoneyScreen
import com.dev.chacha.presentation.statement.StatementScreen
import com.dev.chacha.presentation.transactions_all.AllTransactionsScreen
import com.dev.chacha.presentation.withdraw.WithdrawScreen
import timber.log.Timber

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.homeNavGraph(navController: NavHostController) {
    composable(route = HOME_SCREEN_ROUTE) {
        HomeScreen(
            onSendMoneyClicked = { navController.navigate(HomeAction.SendMoney.route) },
            onBuyAirtimeClicked = { navController.navigate(HomeAction.BuyAirtime.route) },
            onBankTransferClicked = { navController.navigate(HomeAction.BankTransfer.route) },
            onPayBillClicked = { navController.navigate(HomeAction.PayWithSacco.route) },
            onWithdrawClicked = { navController.navigate(HomeAction.Withdraw.route) },
            onDepositClicked = { navController.navigate(HomeAction.Deposit.route) },
            onLoanClicked = { navController.navigate(LOANS_SCREEN_ROUTE) },
            onMarketClicked = { navController.navigate(MARKET_SCREEN_ROUTE) },
            onSavingsClicked = { navController.navigate(HomeAction.Savings.route) },
            navigateToAllTransactions = { navController.navigate(HomeAction.AllTransaction.route) },
            navigateBack = { navController.navigateUp() }
        )

    }

    composable(HomeAction.BankTransfer.route) {
        BankTransferScreen(
            navigateBack = { navController.navigateUp() }
        )

    }

    composable(HomeAction.SendMoney.route) {
        SendMoneyScreen(
            navigateBack = { navController.navigateUp() }
        )

    }
    composable(HomeAction.BuyAirtime.route) {
        BuyAirtimeScreen(
            navigateBack = { navController.navigateUp() },
        )
    }

    composable(HomeAction.Withdraw.route) {
        WithdrawScreen(
            ignoredNavigateBack = { navController.navigateUp() }

        )
    }
    composable(HomeAction.Deposit.route) {
        DepositScreen(
            navigateBack = { navController.navigateUp() }

        )

    }

    composable(HomeAction.BillScreen.route) {
        BillScreen(
            navigateBack = { navController.navigateUp() },
            navController
        )
    }

    composable(HomeAction.AllTransaction.route) {
        AllTransactionsScreen(
            navigateBack = { navController.navigateUp() },

            )
    }


    composable(HomeAction.PayBill.route) {
        PayBillScreen(
            onPayBillClick = {},
            sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed),
            navController = navController

        )
    }

    composable(
        HomeAction.BillConfirm.route,
        arguments = listOf(
            navArgument("accountName") {
                type = NavType.StringType
                defaultValue = " "
            },
            navArgument("businessNumber") { type = NavType.StringType },
            navArgument("accountNumber") { type = NavType.StringType },
            navArgument("amount") { type = NavType.StringType },
            navArgument("date") {
                type = NavType.StringType
                defaultValue = ""
            },
        )
    ) { backStackEntry ->
        val payBill = PayBill(
            name = backStackEntry.arguments?.getString("accountName") ?: "",
            businessNumber = backStackEntry.arguments?.getString("businessNumber") ?: "",
            accountNumber = backStackEntry.arguments?.getString("accountNumber") ?: "",
            amount = backStackEntry.arguments?.getString("amount")?.toDoubleOrNull() ?: 0.0,
            date = backStackEntry.arguments?.getString("date") ?: "",
        )
        Timber.tag("BillConfirm").d(payBill.toString())
        BillConfirmationScreen(
            payBill = payBill,
        )
    }



    composable(
        route = "TillConfirm?tillName={tillName}&tillNumber={tillNumber}&amount={amount}&date={date}",
        arguments = listOf(
            navArgument("tillName") {
                type = NavType.StringType
                defaultValue = " "
            },
            navArgument("tillNumber") { type = NavType.StringType },
            navArgument("amount") { type = NavType.StringType },
            navArgument("date") {
                type = NavType.StringType
                defaultValue = ""
            }
        )
    ) { entry ->
        val buyGoods = BuyGoods(
            tillName = entry.arguments?.getString("tillName") ?: "",
            tillNumber = entry.arguments?.getString("tillNumber") ?: "",
            amount = entry.arguments?.getString("amount")?.toDoubleOrNull() ?: 0.0,
            date = entry.arguments?.getString("date") ?: ""
        )
        BuyGoodsConfirms(buyGoods = buyGoods)
    }



    composable(HomeAction.PayWithSacco.route) {
        PayWithSacco(
            navigateBack = { navController.navigateUp() },
            navController
        )
    }

    composable(HomeAction.Savings.route) {
        SavingsScreen(
            navigateBack = { navController.navigateUp() }
        )
    }
    composable(HomeAction.Statements.route) {
        StatementScreen(
            navigateBack = { navController.navigateUp() }

        )
    }


}


sealed class HomeAction(val route: String) {
    object PayWithSacco : HomeAction("pay_with_sacco")
    object SendMoney : HomeAction(route = "send_money")
    object BuyGoods : HomeAction(route = "buy_goods")
    object BuyAirtime : HomeAction(route = "buy airtime")
    object PayBill : HomeAction(route = "paybill")
    object Savings : HomeAction(route = "Saving")
    object Withdraw : HomeAction(route = "Withdraw")
    object Deposit : HomeAction(route = "deposit")
    object BillScreen : HomeAction("BillScreen")
    object AllTransaction : HomeAction("AllTransaction")
    object Statements : HomeAction("Statement")
    object BankTransfer : HomeAction("bank_transfer")
    object BillConfirm :
        HomeAction("BillConfirm?accountName={accountName}&businessNumber={businessNumber}&accountNumber={accountNumber}&amount={amount}&date={date}") {
        fun sendData(
            accountName: String,
            businessNumber: String,
            accountNumber: String,
            amount: Double,
            date: String
        ): String {

            val encodedAccountName = Uri.encode(accountName)
            val encodedBusinessNumber = Uri.encode(businessNumber)
            val encodedAccountNumber = Uri.encode(accountNumber)
            val encodedAmount = Uri.encode(amount.toString())
            val encodedDate = Uri.encode(date)

            return "BillConfirm?accountName=$encodedAccountName&businessNumber=$encodedBusinessNumber&accountNumber=$encodedAccountNumber&amount=$encodedAmount&date=$encodedDate"
        }
    }

    object TillConfirm :
        HomeAction("TillConfirm?tillName={tillName}&tillNumber={tillNumber}&amount={amount}&date={date}") {
        fun sendData(
            tillName: String = " ",
            tillNumber: String,
            amount: Double,
            date: String = "123"
        ): String {
            val encodedTillName = Uri.encode(tillName)
            val encodedTillNumber = Uri.encode(tillNumber)
            val encodedAmount = Uri.encode(amount.toString())
            val encodedDate = Uri.encode(date)
            return "TillConfirm?tillName=$encodedTillName&tillNumber=$encodedTillNumber&amount=$encodedAmount&date=$encodedDate"
        }
    }

}