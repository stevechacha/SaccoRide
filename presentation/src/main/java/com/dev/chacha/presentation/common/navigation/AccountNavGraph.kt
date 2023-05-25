package com.dev.chacha.presentation.common.navigation

import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.about_sacco.AboutSaccoRide
import com.dev.chacha.presentation.account.AccountScreen
import com.dev.chacha.presentation.auth.reset_password.ResetPasswordScreen
import com.dev.chacha.presentation.auth.reset_pin.ResetPinScreen
import com.dev.chacha.presentation.notification.NotificationsScreen
import com.dev.chacha.presentation.pin.PinPromptScreen
import com.dev.chacha.presentation.setting.SettingsScreen
import com.dev.chacha.presentation.setting.biometric_settings.BiometricSettingsScreen
import com.dev.chacha.presentation.statement.component.StatementDetail
import com.dev.chacha.presentation.theme.ThemeScreen


@RequiresApi(Build.VERSION_CODES.P)
fun NavGraphBuilder.accountNavGraph(
    navController: NavHostController,
) {
    navigation(
        route = Graph.ACCOUNT,
        startDestination = BottomBarScreen.PinLock.route
    ) {
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
                navigateBack = {navController.navigateUp()}
            )
        }
        composable(AccountActions.Notification.route) {
            NotificationsScreen(
                navigateBack = {navController.navigateUp()}

            )
        }

        composable(AccountActions.Theme.route) {
            ThemeScreen(
                navigateBack = {navController.navigateUp()}
            )
        }

        composable(AccountActions.Setting.route) {
            SettingsScreen(
                navigateToTheme = { navController.navigate(AccountActions.Theme.route) },
                navigateToBiometricSettings = {navController.navigate(AccountActions.BiometricSettings.route)},
                navigateBack = {navController.navigateUp()}

            )
        }

        composable(AccountActions.BiometricSettings.route){
            BiometricSettingsScreen()
        }

        composable(AccountActions.AboutSaccoRide.route) {
            AboutSaccoRide(
                navigateBack = {navController.navigateUp()}
            )
        }
        composable(AccountActions.ManagePin.route) {

            ResetPinScreen(
                onClickAction = {},
                navigateBack = {navController.navigateUp()}

            )
        }

        composable(AccountActions.ManagePassword.route) {
            ResetPasswordScreen(
                onClickAction = {},
                navigateBack = {navController.navigateUp()}

            )

        }

    }

}


sealed class AccountActions(val route: String, @DrawableRes val icon: Int, val title: String) {
    object PinLock : AccountActions("PinLock", R.drawable.account_circle,"PinLock")
    object Account: AccountActions("account",R.drawable.account_circle,"Account")
    object StatementDetail : AccountActions("View Statement",R.drawable.account_circle,"Statement")
    object Setting : AccountActions("Settings",R.drawable.account_circle,"Setting")
    object Notification : AccountActions("Notification",R.drawable.account_circle,"Notification")
    object AboutSaccoRide : AccountActions("AboutSacco",R.drawable.account_circle,"About sacco")
    object ManagePin : AccountActions("manage_pin",R.drawable.account_circle,"Manage pin")
    object ManagePassword : AccountActions("Mange_password",R.drawable.account_circle,"Manage Password")
    object Theme : AccountActions("Theme",R.drawable.account_circle,"Theme")
    object BiometricSettings: AccountActions("Biometric_Settings",R.drawable.account_circle,"Biometris")
}