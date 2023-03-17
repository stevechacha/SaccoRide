package com.dev.chacha.presentation.common.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.dev.chacha.presentation.auth.create_password.CreatePasswordScreen
import com.dev.chacha.presentation.auth.register.RegisterScreen
import com.dev.chacha.presentation.auth.forgot.ForgotPasswordScreen
import com.dev.chacha.presentation.auth.login.LoginScreen
import com.dev.chacha.presentation.auth.login.LoginViewModel
import com.dev.chacha.presentation.auth.welcome.WelcomeScreen
import com.dev.chacha.presentation.onboard.OnBoardScreen
import com.dev.chacha.presentation.pin.PinLockScreen


fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Onboard.route
    ) {
        composable(AuthScreen.Onboard.route) {
            OnBoardScreen(
                onClickAction = {
                    navController.navigate(AuthScreen.PinLock.route)
                },
                navController = navController
            )
        }
        composable(AuthScreen.PinLock.route) {
            PinLockScreen(
                onClickAction = {
                    navController.navigate(AuthScreen.Welcome.route)
                }
            )
        }

        composable(AuthScreen.Welcome.route) {
            WelcomeScreen(
                onSignUp = {
                    navController.navigate(AuthScreen.Register.route)
                },
                onLogin = {
                    navController.navigate(AuthScreen.Login.route)
                }
            )
        }
        composable(route = AuthScreen.Login.route) {
            LoginScreen(
                onClick = {
                    navController.popBackStack()
                    navController.navigate(Graph.HOME)
                },
                onSignUpClick = {
                    navController.navigate(AuthScreen.Register.route)
                },
                onForgotClick = {
                    navController.navigate(AuthScreen.Forgot.route)
                },
            )
        }
        composable(route = AuthScreen.Register.route) {
            RegisterScreen(
                onClick = {
                    navController.popBackStack()
                    navController.navigate(Graph.HOME)
                },
                onLoginClick = {
                    navController.navigate(AuthScreen.Login.route)
                }
            )
        }
        composable(route = AuthScreen.CreatePassword.route) {
            CreatePasswordScreen(
                onClickAction = {
                    navController.popBackStack()
                    navController.navigate(Graph.HOME)
                }
            )
        }
        composable(route = AuthScreen.Forgot.route) {
            ForgotPasswordScreen(
                onClick = {
                    navController.popBackStack()
                    navController.navigate(Graph.HOME)
                },
                onLoginClick = {
                    navController.navigate(AuthScreen.Login.route)
                }
            )
        }
    }
}


sealed class AuthScreen(val route: String) {

    object PinLock : AuthScreen("welcome_route")

    object Onboard : AuthScreen("onboard_route")
    object Welcome : AuthScreen("welcome")
    object Login : AuthScreen(route = "LOGIN")
    object Register : AuthScreen(route = "REGISTER")
    object Forgot : AuthScreen(route = "FORGOT")
    object CreatePassword : AuthScreen(route = "CREATE_ACCOUNT")
}