package com.dev.chacha.presentation.bottomnav

import android.annotation.SuppressLint
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dev.chacha.presentation.common.navigation.BottomBarScreen
import com.dev.chacha.presentation.common.navigation.bottomNavigationItems
import com.dev.chacha.presentation.common.theme.SaccoRideTheme


@SuppressLint("RememberReturnType")
@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.background,
        elevation = 5.dp
    ) {
        bottomNavigationItems.forEach { destination ->
            BottomNavigationItem(
                selected = currentDestination?.hierarchy?.any { it.route == destination.route } == true,
                icon = {
                    Icon(
                        painterResource(id = destination.icon),
                        contentDescription = destination.title,
                        tint = if (currentDestination?.route == destination.route) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.onSurfaceVariant
                        }
                    )
                },
                label = {
                    Text(
                        text = destination.title,
                        fontSize = 9.sp,
                        color = if (currentDestination?.route == destination.route) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.onSurfaceVariant
                        },
                        fontWeight = if (currentDestination?.route == destination.route) {
                            FontWeight.ExtraBold
                        } else {
                            FontWeight.Normal
                        }
                    )
                        },
                alwaysShowLabel = true,
                onClick = {
                    navController.navigate(destination.route) {
                        popUpTo(BottomBarScreen.Home.route) { inclusive = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
    }
}


@Preview
@Composable
fun BottomNavigationPreview() {
    SaccoRideTheme {
        BottomNavigationBar(rememberNavController())
    }
}