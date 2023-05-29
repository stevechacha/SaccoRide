package com.dev.chacha.presentation.common.navigation

import PayBillScreen
import android.net.Uri
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetState
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
import com.dev.chacha.presentation.buy_goods.BuyGoods
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
import com.dev.chacha.presentation.pin.AccountPinScreen
import com.dev.chacha.presentation.pin.LoanPinScreen
import com.dev.chacha.presentation.pin.PinPromptScreen
import com.dev.chacha.presentation.savings.SavingsScreen
import com.dev.chacha.presentation.send_money.SendMoneyScreen
import com.dev.chacha.presentation.settings.SettingsScreen
import com.dev.chacha.presentation.settings.biometric_settings.BiometricSettingsScreen
import com.dev.chacha.presentation.statement.StatementScreen
import com.dev.chacha.presentation.statement.component.StatementDetail
import com.dev.chacha.presentation.theme.ThemeScreen
import com.dev.chacha.presentation.transaction.TransactionScreen
import com.dev.chacha.presentation.transactions_all.AllTransactionsScreen
import com.dev.chacha.presentation.withdraw.WithdrawScreen
import timber.log.Timber

@OptIn(ExperimentalMaterialApi::class)
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun HomeNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
        route = Graph.HOME
    ) {

        composable(BottomBarScreen.Home.route) {
            HomeScreen(
                onSendMoneyClicked = { navController.navigate(HomeAction.SendMoney.route) },
                onBuyAirtimeClicked = { navController.navigate(HomeAction.BuyAirtime.route) },
                onBankTransferClicked = { navController.navigate(HomeAction.BankTransfer.route) },
                onPayBillClicked = { navController.navigate(HomeAction.PayWithSacco.route) },
                onWithdrawClicked = { navController.navigate(HomeAction.Withdraw.route) },
                onDepositClicked = { navController.navigate(HomeAction.Deposit.route) },
                onLoanClicked = { navController.navigate(BottomBarScreen.LoanPinScreen.route) },
                onMarketClicked = { navController.navigate(BottomBarScreen.OurMarket.route) },
                onSavingsClicked = { navController.navigate(HomeAction.Savings.route) },
                navigateToAllTransactions = { navController.navigate(HomeAction.AllTransaction.route) },
                navigateBack = { navController.navigateUp() }
            )

        }
        composable(BottomBarScreen.Transaction.route) {
            TransactionScreen(
                onSendMoneyClicked = { navController.navigate(HomeAction.SendMoney.route) },
                onBuyAirtimeClicked = { navController.navigate(HomeAction.BuyAirtime.route) },
                onBankTransferClicked = { navController.navigate(HomeAction.BankTransfer.route) },
                onPayBillClicked = { navController.navigate(HomeAction.PayWithSacco.route) },
                onWithdrawClicked = { navController.navigate(HomeAction.Withdraw.route) },
                onDepositClicked = { navController.navigate(HomeAction.Deposit.route) },
                onLoanClicked = { navController.navigate(BottomBarScreen.LoanPinScreen.route) },
                onMarketClicked = { navController.navigate(BottomBarScreen.OurMarket.route) },
                onSavingsClicked = { navController.navigate(HomeAction.Savings.route) },
            )

        }

        composable(BottomBarScreen.LoanPinScreen.route) {
            LoanPinScreen(
                onClickAction = {
                    navController.navigate(BottomBarScreen.Loan.route) {
                        popUpTo(BottomBarScreen.LoanPinScreen.route) {
                            inclusive = true
                        }
                    }
                },
                navController = navController
            )

        }
        composable(BottomBarScreen.Loan.route) {
            LoanScreen(
                navController = navController,
                navigateToContact = { navController.navigate(Graph.SEND_MONEY) },

                )

        }
        composable(BottomBarScreen.AccountPinScreen.route) {
            AccountPinScreen(
                onClickAction = {
                    navController.navigate(BottomBarScreen.Account.route) {
                        popUpTo(
                            BottomBarScreen.AccountPinScreen.route
                        ) { inclusive = true }
                    }
                },
                navController = navController
            )
        }


        composable(BottomBarScreen.OurMarket.route) {
            MarketScreen(
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
                navigateToBillConfirm = { payBill ->
                    val bundle = bundleOf(
                        "accountName" to payBill.name,
                        "businessNumber" to payBill.businessNumber,
                        "accountNo" to payBill.accountNumber,
                        "amount" to payBill.amount,
                        "date" to payBill.date
                    )
                    navController.navigate(
                        HomeAction.BillConfirm.route.replace(
                            "$bundle",
                            "$bundle"
                        )
                    )
                }
            )
        }

        composable(HomeAction.PayBill.route) {
            PayBillScreen(
                onPayBillClick = {},
                onNavigateToBillConfirm = { payBill ->
                    navController.navigate(
                        HomeAction.BillConfirm.sendData(
                            accountName = payBill.name,
                            businessNumber = payBill.businessNumber,
                            accountNo = payBill.accountNumber.toString(),
                            amount = 0.0,
                            date = payBill.date.toString()
                        )
                    )
                },
                sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)

            )
        }

        composable(
            HomeAction.BillConfirm.route,
            arguments = listOf(
                navArgument("accountName") { type = NavType.StringType },
                navArgument("businessNumber") { type = NavType.StringType },
                navArgument("accountNo") { type = NavType.StringType },
                navArgument("amount") { type = NavType.StringType },
                navArgument("date") { type = NavType.StringType },
            )
        ) { backStackEntry ->
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




        composable(HomeAction.AllTransaction.route) {
            AllTransactionsScreen(
                navigateBack = { navController.navigateUp() },

                )
        }

//        composable(HomeAction.BuyGoods.route) {
//            BuyGoodsScreen(
//                navController = navController,
//                sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed),
//                /*navigateWithData = { buyGoods->
//                    navController.navigate(
//                        HomeAction.TillConfirm.sendData(
//                            tillName = buyGoods.tillName,
//                            tillNumber = buyGoods.tillNumber,
//                            amount = buyGoods.amount.toDouble(),
//                            date = "12/12/2022"
//                        )
//                    )
//
//                }*/
//
//            )
//        }

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
                    defaultValue = "123"
                    nullable = true
                }
            )
        ) { entry ->
            val buyGoods = BuyGoods(
                tillName = entry.arguments?.getString("tillName") ?: "",
                tillNumber = entry.arguments?.getString("tillNumber") ?: "",
                amount = entry.arguments?.getString("amount")?.toDoubleOrNull() ?: 0.0,
                date = entry.arguments?.getString("date") ?: ""
            )
            BuyGoodConfirms(buyGoods = buyGoods)
        }



        composable(HomeAction.TillPaymentConfirm.route) { backStackEntry ->
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

        composable(BottomBarScreen.PinLock.route) {
            PinPromptScreen(
                onClickAction = {
                    navController.navigate(AccountActions.Setting.route)
                },
                navController = navController
            )
        }

        composable(BottomBarScreen.Account.route) {
            AccountScreen(
                navigateBack = { navController.navigateUp() },
                onNavigateToStatement = { navController.navigate(AccountActions.StatementDetail.route) },
                navigateToSettings = { navController.navigate(AccountActions.Setting.route) },
                navigateToAboutSaccoRide = { navController.navigate(AccountActions.AboutSaccoRide.route) },
                navigateToNotification = { navController.navigate(AccountActions.Notification.route) },
                navigateToManagePin = { navController.navigate(AccountActions.ManagePin.route) },
                navigateToManagePassword = { navController.navigate(AccountActions.ManagePassword.route) },
                navController = navController,
            )
        }


        composable(AccountActions.StatementDetail.route) {
            StatementDetail(
                onViewStatement = { navController.navigate(HomeAction.Statements.route) },
                navigateBack = { navController.navigateUp() }
            )
        }
        composable(AccountActions.Notification.route) {
            NotificationsScreen(
                navigateBack = { navController.navigateUp() }

            )
        }

        composable(AccountActions.Theme.route) {
            ThemeScreen(
                navigateBack = { navController.navigateUp() }
            )
        }

        composable(AccountActions.Setting.route) {
            SettingsScreen(
                navigateToTheme = { navController.navigate(AccountActions.Theme.route) },
                navigateToBiometricSettings = { navController.navigate(AccountActions.BiometricSettings.route) },
                navigateBack = { navController.navigateUp() }

            )
        }

        composable(AccountActions.BiometricSettings.route) {
            BiometricSettingsScreen()
        }

        composable(AccountActions.AboutSaccoRide.route) {
            AboutSaccoRide(
                navigateBack = { navController.navigateUp() }
            )
        }
        composable(AccountActions.ManagePin.route) {

            ResetPinScreen(
                onClickAction = {},
                navigateBack = { navController.navigateUp() }

            )
        }

        composable(AccountActions.ManagePassword.route) {
            ResetPasswordScreen(
                onClickAction = {},
                navigateBack = { navController.navigateUp() }

            )

        }

//        accountNavGraph(navController = navController)

    }


}


@Composable
fun BuyGoodConfirms(buyGoods: BuyGoods) {
    Text(text = "Till Name: $buyGoods.tillName")
    Text(text = "Till Number: $buyGoods.tillNumber")
    Text(text = "Amount: $buyGoods.amount")
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


sealed class HomeAction(val route: String) {

    object PinLock : HomeAction("pinlock")
    object PayWithSacco : HomeAction("pay_with_sacco")
    object SendMoney : HomeAction(route = "send_money")
    object BuyGoods : HomeAction(route = "buy_goods")
    object BuyAirtime : HomeAction(route = "buy airtime")
    object PayBill : HomeAction(route = "paybill")
    object Savings : HomeAction(route = "Saving")
    object Withdraw : HomeAction(route = "Withdraw")
    object Deposit : HomeAction(route = "deposit")
    object Loan : HomeAction("Loan")
    object BillScreen : HomeAction("BillScreen")

    object AllTransaction : HomeAction("AllTransaction")

    object Statements : HomeAction("Statement")

    object BankTransfer : HomeAction("bank_transfer")

    object BillConfirm :
        HomeAction("BillConfirm/{accountName}/{businessNumber}/{accountNo}/{amount}/{date}") {
        fun sendData(
            accountName: String,
            businessNumber: String,
            accountNo: String,
            amount: Double,
            date: String
        ): String {
            return "BillConfirm/$accountName/$businessNumber/$accountNo/$amount/$date"
        }
    }

    object TillConfirm : HomeAction("TillConfirm?tillName={tillName}&tillNumber={tillNumber}&amount={amount}&date={date}") {
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


    object TillPaymentConfirm :
        HomeAction("TillPaymentConfirm/{tillName}/{tillNumber}/{amount}/{date}")
}


sealed class BottomBarScreen(val route: String, @DrawableRes val icon: Int, val title: String) {

    object PinLock : BottomBarScreen("pinlock", R.drawable.about_icon, "PInlock")
    object Home : BottomBarScreen("home", R.drawable.home, "Home")
    object Transaction : BottomBarScreen("transact", R.drawable.sync_alt_icon, "Transact")
    object OurMarket : BottomBarScreen("market", R.drawable.market_icons, "Market")
    object Loan : BottomBarScreen("loan", R.drawable.loan_icon, "Loan")
    object LoanPinScreen : BottomBarScreen("loan_pin", R.drawable.loan_icon, "Loan")

    object Account : BottomBarScreen("account", R.drawable.account_circle, "Account")
    object AccountPinScreen : BottomBarScreen("account_pin", R.drawable.account_circle, "Account")

}

