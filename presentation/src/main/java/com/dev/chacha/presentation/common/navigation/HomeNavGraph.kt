package com.dev.chacha.presentation.common.navigation

import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.core.os.bundleOf
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.about_sacco.AboutSaccoRide
import com.dev.chacha.presentation.account.AccountScreen
import com.dev.chacha.presentation.auth.reset_password.ResetPasswordScreen
import com.dev.chacha.presentation.auth.reset_pin.ResetPinScreen
import com.dev.chacha.presentation.bank_transfer.BankTransferScreen
import com.dev.chacha.presentation.baybill.BillScreen
import com.dev.chacha.presentation.baybill.components.BillConfirmItem
import com.dev.chacha.presentation.buy_artime.BuyAirtimeScreen
import com.dev.chacha.presentation.buy_artime.BuyAirtimeViewModel
import com.dev.chacha.presentation.buy_goods.BuyGoods
import com.dev.chacha.presentation.buy_goods.BuyGoodsScreen
import com.dev.chacha.presentation.buy_goods.components.BuyGoodConfirm
import com.dev.chacha.presentation.deposit.DepositScreen
import com.dev.chacha.presentation.home.HomeScreen
import com.dev.chacha.presentation.information.Information
import com.dev.chacha.presentation.loan.LoanScreen
import com.dev.chacha.presentation.markets.MarketScreen
import com.dev.chacha.presentation.notification.NotificationsScreen
import com.dev.chacha.presentation.overview.Overview
import com.dev.chacha.presentation.pay_with_sacco.PayWithSacco
import com.dev.chacha.presentation.paybill.PayBill
import com.dev.chacha.presentation.paybill.PayBillScreen
import com.dev.chacha.presentation.savings.SavingsScreen
import com.dev.chacha.presentation.send_money.SendMoneyScreen
import com.dev.chacha.presentation.setting.SettingsScreen
import com.dev.chacha.presentation.setting.biometric_settings.BiometricSettingsScreen
import com.dev.chacha.presentation.statement.StatementScreen
import com.dev.chacha.presentation.statement.component.StatementDetail
import com.dev.chacha.presentation.theme.ThemeScreen
import com.dev.chacha.presentation.transaction.TransactionScreen
import com.dev.chacha.presentation.transactions_all.AllTransactionsScreen
import com.dev.chacha.presentation.withdraw.WithdrawScreen
import timber.log.Timber

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun HomeNavGraph(
    navController: NavHostController,
    showBottomBar: (Boolean) -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
        route = Graph.HOME
    ) {
        /*composable(BottomBarScreen.PinLock.route){
            showBottomBar(false)
            PinLockScreen(
                onClickAction = { *//*TODO*//* },
                navController = navController
            )
        }*/

        composable(BottomBarScreen.Home.route) {
            showBottomBar(true)
            HomeScreen(
                onSendMoneyClicked = { navController.navigate(HomeAction.SendMoney.route) },
                onBuyAirtimeClicked = { navController.navigate(HomeAction.BuyAirtime.route) },
                onBankTransferClicked = { navController.navigate(HomeAction.BankTransfer.route) },
                onPayBillClicked = { navController.navigate(HomeAction.PayWithSacco.route) },
                onWithdrawClicked = { navController.navigate(HomeAction.Withdraw.route) },
                onDepositClicked = { navController.navigate(HomeAction.Deposit.route) },
                onLoanClicked = { navController.navigate(HomeAction.Loan.route) },
                onMarketClicked = { navController.navigate(BottomBarScreen.OurMarket.route) },
                onSavingsClicked = { navController.navigate(HomeAction.Savings.route) },
                navigateToAllTransactions = {navController.navigate(HomeAction.AllTransaction.route)},
                navigateBack = {navController.navigateUp()}
            )

        }
        composable(BottomBarScreen.Transaction.route) {
            showBottomBar(true)
            TransactionScreen(
                onSendMoneyClicked = { navController.navigate(HomeAction.SendMoney.route) },
                onBuyAirtimeClicked = { navController.navigate(HomeAction.BuyAirtime.route) },
                onBankTransferClicked = { navController.navigate(HomeAction.BankTransfer.route) },
                onPayBillClicked = { navController.navigate(HomeAction.PayWithSacco.route) },
                onWithdrawClicked = { navController.navigate(HomeAction.Withdraw.route) },
                onDepositClicked = { navController.navigate(HomeAction.Deposit.route) },
                onLoanClicked = { navController.navigate(HomeAction.Loan.route) },
                onMarketClicked = { navController.navigate(BottomBarScreen.OurMarket.route) },
                onSavingsClicked = { navController.navigate(HomeAction.Savings.route) },
            )

        }

        composable(HomeAction.BankTransfer.route){
            showBottomBar(false)
            BankTransferScreen(
                navigateBack = {navController.navigateUp()}
            )

        }
        composable(BottomBarScreen.Loan.route) {
            showBottomBar(true)
            LoanScreen(
                navController = navController,
                navigateToContact = {navController.navigate(Graph.SEND_MONEY)}
            )

        }
        composable(BottomBarScreen.OurMarket.route) {
            showBottomBar(true)
            MarketScreen(
                navigateBack = {navController.navigateUp()}
            )
        }

        composable(BottomBarScreen.Account.route) {
            showBottomBar(true)
            AccountScreen(
                navigateBack = { navController.navigateUp() },
                onNavigateToStatement = { navController.navigate(AccountAction.StatementDetail.route) },
                navigateToSettings = { navController.navigate(AccountAction.Setting.route) },
                navigateToAboutSaccoRide = { navController.navigate(AccountAction.AboutSaccoRide.route) },
                navigateToNotification = { navController.navigate(AccountAction.Notification.route) },
                navigateToManagePin = { navController.navigate(AccountAction.ManagePin.route) },
                navigateToManagePassword = { navController.navigate(AccountAction.ManagePassword.route) },
                navController = navController,

                )

        }



        composable(AccountAction.StatementDetail.route) {
            showBottomBar(false)
            StatementDetail(
                onViewStatement = { navController.navigate(HomeAction.Statements.route) },
                navigateBack = {navController.navigateUp()}
            )
        }

        composable(AccountAction.Theme.route) {
            ThemeScreen(
                navigateBack = {navController.navigateUp()}
            )
        }

        composable(HomeAction.SendMoney.route) {
            showBottomBar(false)
            SendMoneyScreen(
                navigateBack = {navController.navigateUp()}
            )

        }
        composable(HomeAction.BuyAirtime.route) {
            showBottomBar(false)
            BuyAirtimeScreen(
                navigateBack = {navController.navigateUp()},
                buyAirtimeViewModel = BuyAirtimeViewModel()

            )
        }
        composable(HomeAction.BuyGoods.route) {
            BuyGoodsScreen(
                navController = navController,
            )
        }


        composable(HomeAction.Withdraw.route) {
            showBottomBar(false)
            WithdrawScreen(
                navigateBack = {navController.navigateUp()}

            )
        }
        composable(HomeAction.Deposit.route) {
            showBottomBar(false)
            DepositScreen(
                navigateBack = {navController.navigateUp()}

            )

        }

        composable(HomeAction.BillScreen.route){
            showBottomBar(false)
            BillScreen(
                navigateBack = { navController.navigateUp() },
                navigateToBillConfirm = { payBill ->
                    val bundle = bundleOf(
                        "accountName" to payBill.name,
                        "businessNumber" to payBill.businessNumber,
                        "accountNo" to payBill.accountNumber,
                        "amount" to payBill.amount,
                        "date" to payBill.date
                    )
                    navController.navigate(HomeAction.BillConfirm.route.replace("$bundle","$bundle"))
                }
            )
        }


        composable(HomeAction.BillConfirm.route,
            arguments = listOf(
                navArgument("accountName") { type = NavType.StringType },
                navArgument("businessNumber") { type = NavType.StringType },
                navArgument("accountNo") { type = NavType.StringType },
                navArgument("amount") { type = NavType.StringType },
                navArgument("date") { type = NavType.StringType },
            )
        ) { backStackEntry ->
            showBottomBar(false)
            val payBill = PayBill(
                name = backStackEntry.arguments?.getString("accountName") ?: "",
                businessNumber = backStackEntry.arguments?.getString("businessNumber") ?: "",
                accountNumber = backStackEntry.arguments?.getString("accountNo") ?: "",
                amount = (backStackEntry.arguments?.getDouble("amount") ?: "") as Double,
                date = backStackEntry.arguments?.getString("date") ?: "",
            )
            Timber.tag("BillConfirm").d(payBill.toString())
            BillConfirmItem(
                payBill = payBill,
            )
        }

        composable(HomeAction.AllTransaction.route){
            showBottomBar(false)
            AllTransactionsScreen(
                navigateBack = {navController.navigateUp()}

            )
        }

        composable("TillConfirm/{tillName}/{tillNumber}/{amount}",
            arguments = listOf(
                navArgument("tillName") { type = NavType.StringType },
                navArgument("tillNumber") { type = NavType.StringType },
                navArgument("amount") { type = NavType.StringType },
                navArgument("date"){type = NavType.StringType}
            )
        ) { entry ->
            val tillName = entry.arguments?.getString("tillName") ?: ""
            val tillNumber = entry.arguments?.getString("tillNumber") ?: ""
            val amount = entry.arguments?.getDouble("amount") ?: 0.0
            BuyGoodConfirms(tillName, tillNumber, amount)
        }



        composable(HomeAction.TillPaymentConfirm.route) { backStackEntry ->
            showBottomBar(false)
            val tillName = backStackEntry.arguments?.getString("tillName") ?: ""
            val tillNumber = backStackEntry.arguments?.getString("tillNumber") ?: ""
            val amount = backStackEntry.arguments?.getDouble("amount") ?: 0.0
            val date = backStackEntry.arguments?.getString("date") ?: ""

            val buyGoods = BuyGoods(
                tillName = tillName,
                tillNumber = tillNumber,
                amount = amount,
                date = date
            )
            BuyGoodConfirm(
                message = "",
                drawableRes = R.drawable.home,
                buttonText = "",
                buyGoods = buyGoods.copy(tillName, tillNumber, amount, date)
            )
        }


        composable(HomeAction.PayWithSacco.route) {
            showBottomBar(false)
            PayWithSacco(
                navigateBack = {navController.navigateUp()}

            )
        }

        composable(HomeAction.PayBill.route){
            showBottomBar(false)
            PayBillScreen(
                onPayBillClick = {} ,
                onNavigateToBillConfirm = { payBill ->
                    val bundle = bundleOf(
                        "accountName" to payBill.name,
                        "businessNumber" to payBill.businessNumber,
                        "accountNo" to payBill.accountNumber,
                        "amount" to payBill.amount,
                        "date" to payBill.date
                    )
                    navController.navigate(HomeAction.BillConfirm.route.replace("$bundle","$bundle"))
                }
            )
        }
        composable(HomeAction.Savings.route) {
            showBottomBar(false)
            SavingsScreen(
                navigateBack = {navController.navigateUp()}

            )
        }
        composable(HomeAction.Statements.route) {
            showBottomBar(false)
            StatementScreen(
                navigateBack = {navController.navigateUp()}

            )
        }


        composable(AccountAction.Notification.route) {
            showBottomBar(false)
            NotificationsScreen(
                navigateBack = {navController.navigateUp()}

            )
        }

        composable(AccountAction.Setting.route) {
            showBottomBar(false)
            SettingsScreen(
                navigateToTheme = { navController.navigate(AccountAction.Theme.route) },
                navigateToBiometricSettings = {navController.navigate(AccountAction.BiometricSettings.route)},
                navigateBack = {navController.navigateUp()}

            )
        }

        composable(AccountAction.BiometricSettings.route){
            BiometricSettingsScreen()
        }

        composable(AccountAction.AboutSaccoRide.route) {
            showBottomBar(false)
            AboutSaccoRide(
                navigateBack = {navController.navigateUp()}
            )
        }
        composable(AccountAction.ManagePin.route) {
            showBottomBar(false)

            ResetPinScreen(
                onClickAction = {},
                navigateBack = {navController.navigateUp()}

            )
        }

        composable(AccountAction.ManagePassword.route) {
            showBottomBar(false)
            ResetPasswordScreen(
                onClickAction = {},
                navigateBack = {navController.navigateUp()}

            )

        }
        composable(HomeAction.TillConfirm.route){
            BuyGoodConfirms(
                tillName = "",
                tillNumber = "",
                amount = 0.0
            )
        }


    }

}

@Composable
fun BuyGoodConfirms(tillName: String, tillNumber: String, amount: Double) {
    Text(text = "Till Name: $tillName")
    Text(text = "Till Number: $tillNumber")
    Text(text = "Amount: $amount")
}


fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAILS,
        startDestination = HomeAction.SendMoney.route
    ) {
        composable(route = HomeAction.SendMoney.route) {
            Information {
                navController.navigate(HomeAction.SendMoney.route)
            }
        }
        composable(route = HomeAction.BuyAirtime.route) {
            Overview {
                navController.popBackStack(
                    route = HomeAction.BuyAirtime.route,
                    inclusive = false
                )
            }
        }
    }
}

sealed class AccountAction(val route: String) {
    object StatementDetail : AccountAction("View Statement")
    object Setting : AccountAction("Settings")
    object Notification : AccountAction("Notification")
    object AboutSaccoRide : AccountAction("AboutSacco")

    object ManagePin : AccountAction("manage_pin")

    object ManagePassword : AccountAction("Mange_password")

    object Theme : AccountAction("Theme")

    object BiometricSettings: AccountAction("Biometric_Settings")
}

sealed class HomeAction(val route: String) {

    object PinLock : HomeAction("pinlock")
    object PayWithSacco: HomeAction("pay_with_sacco")
    object SendMoney : HomeAction(route = "send_money")
    object BuyGoods : HomeAction(route = "buy_goods")
    object BuyAirtime : HomeAction(route = "buy airtime")
    object PayBill : HomeAction(route = "paybill")
    object Savings : HomeAction(route = "Saving")
    object Withdraw : HomeAction(route = "Withdraw")
    object Deposit : HomeAction(route = "deposit")
    object Loan : HomeAction("Loan")
    object BillScreen : HomeAction("BillScreen")

    object AllTransaction:HomeAction("AllTransaction")

    object Statements : HomeAction("Statement")

    object BankTransfer: HomeAction("bank_transfer")

    object BillConfirm :
        HomeAction("BillConfirm/{accountName}/{businessNumber}/{accountNo}/{amount}/{date}") {
        fun route(
            amount: Double,
            businessNo: String,
            accountNo: String,
            accountName: String,
            date: String
        ): String {
            return "BillConfirm/$accountName/$businessNo/$accountNo/$amount/$date"
        }

    }

    object TillConfirm: HomeAction("TillConfirm/{tillName}/{tillNumber}/{amount}/{date}") {
        fun route(tillNumber: String, tillName: String, date: String, amount: String): String {
            return "TillConfirm/$tillName/$tillNumber/$amount/$date"
        }

    }

    object TillPaymentConfirm : HomeAction("TillPaymentConfirm/{tillName}/{tillNumber}/{amount}/{date}") {
        fun route(tillNumber: String, tillName: String, date: String, amount: String): String {
            return "TillPaymentConfirm/$tillName/$tillNumber/$amount/$date"
        }

    }
}


sealed class BottomBarScreen(val route: String, @DrawableRes val icon: Int, val title: String) {

    object PinLock : BottomBarScreen("pinlock", R.drawable.about_icon, "PInlock")
    object Home : BottomBarScreen("home", R.drawable.home, "Home")
    object Transaction : BottomBarScreen("transact", R.drawable.sync_alt_icon, "Transact")
    object OurMarket : BottomBarScreen("market", R.drawable.market_icons, "Market")
    object Loan : BottomBarScreen("loan", R.drawable.loan_icon, "Loan")
    object Account : BottomBarScreen("account", R.drawable.account_circle, "Account")

}

val bottomNavigationItems = listOf(
    BottomBarScreen.Home,
    BottomBarScreen.Transaction,
    BottomBarScreen.Loan,
    BottomBarScreen.OurMarket,
    BottomBarScreen.Account
)