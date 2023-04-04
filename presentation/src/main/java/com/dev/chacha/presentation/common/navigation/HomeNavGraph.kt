package com.dev.chacha.presentation.common.navigation

import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
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
import com.dev.chacha.presentation.buy_artime.BuyAirtimeScreen
import com.dev.chacha.presentation.buy_goods.BuyGoods
import com.dev.chacha.presentation.buy_goods.BuyGoodsScreen
import com.dev.chacha.presentation.deposit.DepositScreen
import com.dev.chacha.presentation.home.HomeScreen
import com.dev.chacha.presentation.information.Information
import com.dev.chacha.presentation.markets.MarketScreen
import com.dev.chacha.presentation.overview.Overview
import com.dev.chacha.presentation.pay_with_sacco.PayWithSacco
import com.dev.chacha.presentation.paybill.PayBillItem
import com.dev.chacha.presentation.paybill.PayBillScreen
import com.dev.chacha.presentation.pin.PinLockScreen
import com.dev.chacha.presentation.savings.SavingsScreen
import com.dev.chacha.presentation.send_money.SendMoneyScreen
import com.dev.chacha.presentation.withdraw.WithdrawScreen

@RequiresApi(Build.VERSION_CODES.P)
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
        /*composable(BottomBarScreen.PinLock.route){
            showBottomBar(false)
            PinLockScreen(
                onClickAction = { *//*TODO*//* },
                navController = navController
            )
        }*/

        composable(BottomBarScreen.Home.route){
            showBottomBar(true)
            HomeScreen(
                onSendMoneyClicked = { navController.navigate(HomeAction.SendMoney.route) },
                onBuyAirtimeClicked = { navController.navigate(HomeAction.BuyAirtime.route) },
                onBuyGoodsClicked = { navController.navigate(HomeAction.BuyGoods.route) },
                onPayBillClicked = { navController.navigate(HomeAction.PayWithSacco.route) },
                onWithdrawClicked = { navController.navigate(HomeAction.Withdraw.route) },
                onDepositClicked = { navController.navigate(HomeAction.Deposit.route) },
                onLoanClicked = {  navController.navigate(HomeAction.Loan.route) },
                onMarketClicked = { navController.navigate(BottomBarScreen.OurMarket.route) },
                onSavingsClicked = { navController.navigate(HomeAction.Savings.route) },
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
            LoanScreen (
                navController = navController,
            )

        }
        composable(BottomBarScreen.OurMarket.route){
            showBottomBar(true)
            MarketScreen()
        }

        composable(BottomBarScreen.Account.route){
            showBottomBar(true)
            AccountScreen()

        }

       composable(HomeAction.SendMoney.route){
            showBottomBar(false)
            SendMoneyScreen()

        }
        composable(HomeAction.BuyAirtime.route){
            showBottomBar(false)
            BuyAirtimeScreen()
        }
        composable(HomeAction.BuyGoods.route){
            showBottomBar(false)
            BuyGoods()
        }

        composable(HomeAction.Withdraw.route){
            showBottomBar(false)
            WithdrawScreen()
        }
        composable(HomeAction.Deposit.route){
            showBottomBar(false)
            DepositScreen()
        }

        composable(HomeAction.PayWithSacco.route){
            showBottomBar(false)
            PayWithSacco()
        }
        composable(HomeAction.Savings.route){
            showBottomBar(false)
            SavingsScreen()
        }

        detailsNavGraph(navController = navController)

    }

}



fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAILS,
        startDestination = HomeAction.SendMoney.route
    ) {
        composable(route = HomeAction.SendMoney.route) {
            Information() {
                navController.navigate(HomeAction.SendMoney.route)
            }
        }
        composable(route = HomeAction.BuyAirtime.route) {
            Overview() {
                navController.popBackStack(
                    route = HomeAction.BuyAirtime.route,
                    inclusive = false
                )
            }
        }
    }
}

sealed class HomeAction(val route: String) {

    object PinLock: HomeAction("pinlock")
    object SendMoney : HomeAction(route = "send_money")
    object BuyGoods : HomeAction(route = "buy_goods")
    object BuyAirtime : HomeAction(route = "buy airtime")
    object PayBill : HomeAction(route = "paybill")
    object PayWithSacco: HomeAction(route = "pay_with_sacco")
    object Savings : HomeAction(route = "Saving")
    object Withdraw : HomeAction(route = "Withdraw")
    object Deposit: HomeAction(route = "deposit")
    object Loan: HomeAction("Loan")
}


sealed class BottomBarScreen(val route: String, @DrawableRes val icon: Int, val title: String){

    object PinLock: BottomBarScreen("pinlock",R.drawable.about_icon,"PInlock")
    object Home: BottomBarScreen("home", R.drawable.ic_home, "Home")
    object Transaction: BottomBarScreen("transact",R.drawable.sessions_icon,"Transact")
    object OurMarket: BottomBarScreen("market",R.drawable.about_icon,"Market")
    object Loan: BottomBarScreen("loan",R.drawable.sessions_icon,"Loan")
    object Account: BottomBarScreen("account",R.drawable.about_icon,"Account")
}

val bottomNavigationItems = listOf(
    BottomBarScreen.Home,
    BottomBarScreen.Transaction,
    BottomBarScreen.Loan,
    BottomBarScreen.OurMarket,
    BottomBarScreen.Account
)