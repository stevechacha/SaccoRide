package com.dev.chacha.presentation.common.navigation

import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
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
import com.dev.chacha.presentation.baybill.BillScreen
import com.dev.chacha.presentation.baybill.components.BillConfirmItem
import com.dev.chacha.presentation.buy_artime.BuyAirtimeScreen
import com.dev.chacha.presentation.buy_goods.BayGoods
import com.dev.chacha.presentation.buy_goods.BuyGoods
import com.dev.chacha.presentation.buy_goods.components.BuyGoodConfirm
import com.dev.chacha.presentation.buy_goods.components.bayGoods
import com.dev.chacha.presentation.contactList.ContactScreen
import com.dev.chacha.presentation.contacts.component.ContactSelect
import com.dev.chacha.presentation.home.HomeScreen
import com.dev.chacha.presentation.information.Information
import com.dev.chacha.presentation.loan.LoanScreen
import com.dev.chacha.presentation.markets.MarketScreen
import com.dev.chacha.presentation.notification.NotificationsScreen
import com.dev.chacha.presentation.overview.Overview
import com.dev.chacha.presentation.pay_with_sacco.PayWithSacco
import com.dev.chacha.presentation.paybill.PayBill
import com.dev.chacha.presentation.paybill.PayBills
import com.dev.chacha.presentation.savings.SavingsScreen
import com.dev.chacha.presentation.send_money.SendMoneyScreen
import com.dev.chacha.presentation.setting.SettingsScreen
import com.dev.chacha.presentation.statement.StatementScreen
import com.dev.chacha.presentation.statement.component.StatementDetail
import com.dev.chacha.presentation.theme.ThemeScreen
import com.dev.chacha.presentation.transaction.TransactionScreen
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
                onBuyGoodsClicked = { navController.navigate(HomeAction.BuyGoods.route) },
                onPayBillClicked = { navController.navigate(HomeAction.PayBill.route) },
                onWithdrawClicked = { navController.navigate(HomeAction.Withdraw.route) },
                onDepositClicked = { navController.navigate(HomeAction.Deposit.route) },
                onLoanClicked = { navController.navigate(HomeAction.Loan.route) },
                onMarketClicked = { navController.navigate(BottomBarScreen.OurMarket.route) },
                onSavingsClicked = { navController.navigate(HomeAction.BillScreen.route) },
            )

        }
        composable(BottomBarScreen.Transaction.route) {
            showBottomBar(true)
            TransactionScreen(
                onSendMoneyClicked = { navController.navigate(HomeAction.SendMoney.route) },
                onBuyAirtimeClicked = { navController.navigate(HomeAction.BuyAirtime.route) },
                onBuyGoodsClicked = { navController.navigate(HomeAction.BuyGoods.route) },
                onPayBillClicked = { navController.navigate(HomeAction.PayBill.route) },
                onWithdrawClicked = { navController.navigate(HomeAction.Withdraw.route) },
                onDepositClicked = { navController.navigate(HomeAction.Deposit.route) },
                onLoanClicked = { navController.navigate(HomeAction.Loan.route) },
                onMarketClicked = { navController.navigate(BottomBarScreen.OurMarket.route) },
                onSavingsClicked = { navController.navigate(HomeAction.BillScreen.route) },
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
            MarketScreen()
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

        /*composable(BottomBarScreen.ContactsSelection.route){
            showBottomBar(false)
            ContactSelectionScreen(onContactSelected = {}, navController = navController )
        }*/

        composable(AccountAction.StatementDetail.route) {
            showBottomBar(false)
            StatementDetail(
                onViewStatement = { navController.navigate(HomeAction.Statements.route) }
            )
        }

        composable(AccountAction.Theme.route) {
            ThemeScreen()
        }

        composable(HomeAction.SendMoney.route) {
            showBottomBar(false)
            SendMoneyScreen()

        }
        composable(HomeAction.BuyAirtime.route) {
            showBottomBar(false)
            BuyAirtimeScreen()
        }
        composable(HomeAction.BuyGoods.route) {
            showBottomBar(false)
            val tillNumber = bayGoods.tillNumber
            val tillName = bayGoods.tillName
            val amount = bayGoods.amount
            val date = bayGoods.date
            BuyGoods(
                navigateTo = {
                    navController.navigate(
                        HomeAction.TillPaymentConfirm.route(
                            tillName,
                            tillNumber,
                            amount.toDouble().toString(),
                            date.toString()
                        )
                    )
                }
            )
        }
        composable(HomeAction.Withdraw.route) {
            showBottomBar(false)
            WithdrawScreen()
        }
        composable(HomeAction.Deposit.route) {
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


        composable(HomeAction.TillPaymentConfirm.route) { backStackEntry ->
            showBottomBar(false)
            val tillName = backStackEntry.arguments?.getString("tillName") ?: ""
            val tillNumber = backStackEntry.arguments?.getString("tillNumber") ?: ""
            val amount = backStackEntry.arguments?.getDouble("amount") ?: 0.0
            val date = backStackEntry.arguments?.getString("date") ?: ""

            val bayGoods = BayGoods(
                tillName = tillName,
                tillNumber = tillNumber,
                amount = amount,
                date = date
            )
            BuyGoodConfirm(
                message = "",
                drawableRes = R.drawable.home,
                buttonText = "",
                bayGoods = bayGoods.copy(tillName, tillNumber, amount, date)
            )
        }


        composable(HomeAction.PayBill.route) {
            showBottomBar(false)
            PayBills()
        }
        composable(HomeAction.Savings.route) {
            showBottomBar(false)
            SavingsScreen()
        }
        composable(HomeAction.Statements.route) {
            showBottomBar(false)
            StatementScreen()
        }

        composable(HomeAction.BillScreen.route) {
            showBottomBar(false)
            PayWithSacco()
        }

        composable(AccountAction.Notification.route) {
            showBottomBar(false)
            NotificationsScreen()
        }

        composable(AccountAction.Setting.route) {
            showBottomBar(false)
            SettingsScreen(
                navigateToTheme = { navController.navigate(AccountAction.Theme.route) }
            )
        }

        composable(AccountAction.AboutSaccoRide.route) {
            showBottomBar(false)
            AboutSaccoRide()
        }
        composable(AccountAction.ManagePin.route) {
            showBottomBar(false)

            ResetPinScreen(
                onClickAction = {}
            )
        }

        composable(AccountAction.ManagePassword.route) {
            showBottomBar(false)
            ResetPasswordScreen(
                onClickAction = {}
            )

        }
        sendMoneyNavGraph(navController = navController)




        detailsNavGraph(navController = navController)

    }

}

fun NavGraphBuilder.sendMoneyNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.SEND_MONEY,
        startDestination = SendMon.Contact.route
    ){
        composable(SendMon.Contact.route){
            ContactScreen(navController = navController)
        }

        composable(SendMon.ContactSelection.route){
            ContactSelect(
                navController = navController
            )
        }
    }
}

sealed class SendMon(val route: String){
    object Contact: SendMon("Contact")
    object ContactSelection: SendMon("contact_selection")
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
}

sealed class HomeAction(val route: String) {

    object PinLock : HomeAction("pinlock")
    object SendMoney : HomeAction(route = "send_money")
    object BuyGoods : HomeAction(route = "buy_goods")
    object BuyAirtime : HomeAction(route = "buy airtime")
    object PayBill : HomeAction(route = "paybill")
    object PayWithSacco : HomeAction(route = "pay_with_sacco")
    object Savings : HomeAction(route = "Saving")
    object Withdraw : HomeAction(route = "Withdraw")
    object Deposit : HomeAction(route = "deposit")
    object Loan : HomeAction("Loan")
    object BillScreen : HomeAction("BillScreen")

    object Statements : HomeAction("Statement")

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

    object TillPaymentConfirm :
        HomeAction("TillPaymentConfirm/{tillName}/{tillNumber}/{amount}/{date}") {
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