package com.dev.chacha.presentation.bottomnav

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dev.chacha.presentation.common.navigation.DestinationGraph.ACCOUNTS_SCREEN_ROUTE
import com.dev.chacha.presentation.common.navigation.DestinationGraph.HOME_SCREEN_ROUTE
import com.dev.chacha.presentation.common.navigation.DestinationGraph.LOANS_SCREEN_ROUTE
import com.dev.chacha.presentation.common.navigation.DestinationGraph.MARKET_SCREEN_ROUTE
import com.dev.chacha.presentation.common.navigation.DestinationGraph.TRANSACTION_SCREEN_ROUTE
import com.dev.chacha.presentation.common.theme.Brutalista
import com.dev.chacha.presentation.common.theme.PrimaryColor


@SuppressLint("RememberReturnType")
@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val isShowBottomBar = when (currentDestination?.route) {
        HOME_SCREEN_ROUTE, TRANSACTION_SCREEN_ROUTE, LOANS_SCREEN_ROUTE,MARKET_SCREEN_ROUTE, ACCOUNTS_SCREEN_ROUTE, null -> true
        else -> false
    }

    if (isShowBottomBar){
        BottomNavigation(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background),
            backgroundColor = MaterialTheme.colorScheme.background,
            elevation = 8.dp
        ) {
            BottomBarDestination.values().asList().forEach { destination ->
                BottomNavigationItem(
                    selected = currentDestination?.hierarchy?.any { it.route == destination.route } == true,
                    icon = {
                        Icon(
                            painterResource(id = destination.icon),
                            contentDescription = destination.title,
                            tint = if (currentDestination?.route == destination.route) {
                                PrimaryColor
                            } else {
                                MaterialTheme.colorScheme.onSurfaceVariant
                            },
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = {
                        Text(
                            text = destination.title,
                            color = if (currentDestination?.route == destination.route) {
                                PrimaryColor
                            } else {
                                MaterialTheme.colorScheme.onSurfaceVariant
                            },
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp,
                            overflow = TextOverflow.Ellipsis,
                            fontFamily = Brutalista

                        )
                    },
                    alwaysShowLabel = true,
                    onClick = {
                        navController.navigate(destination.route) {
                            popUpTo(HOME_SCREEN_ROUTE) { inclusive = false }
                            launchSingleTop = false
                            restoreState = false
                        }
                    }
                )
            }
        }
    }

}


@Preview
@Composable
fun BottomNavigationPreview() {
    BottomNavigationBar(rememberNavController())

}