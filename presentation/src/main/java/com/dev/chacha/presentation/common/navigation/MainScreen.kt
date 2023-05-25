package com.dev.chacha.presentation.common.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dev.chacha.presentation.bottomnav.BottomNavigationBar

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun MainScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {BottomNavigationBar(navController = navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
        ) {
            HomeNavGraph(
                navController = navController,
            )

        }
    }
}