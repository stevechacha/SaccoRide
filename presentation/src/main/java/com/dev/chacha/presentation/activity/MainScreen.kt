package com.dev.chacha.presentation.activity

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.SwipeableDefaults
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dev.chacha.presentation.bottomnav.BottomNavigationBar
import com.dev.chacha.presentation.common.navigation.RootNavGraph
import com.dev.chacha.presentation.common.theme.SaccoRideTheme
import com.dev.chacha.presentation.common.theme.Theme
import com.dev.chacha.presentation.theme.ThemeViewModel
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import kotlinx.coroutines.Dispatchers

@OptIn(ExperimentalMaterialNavigationApi::class)
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun MainScreen(
    viewModel: ThemeViewModel = hiltViewModel()
) {
    val bottomSheetNavigator = rememberBottomSheetNavigator()
    val navController = rememberNavController(bottomSheetNavigator)

    val themeValue by viewModel.theme.collectAsState(
        initial = Theme.FOLLOW_SYSTEM.themeValue,
        context = Dispatchers.Main.immediate
    )
    val themeName by viewModel.theme.collectAsState(
        initial = Theme.FOLLOW_SYSTEM.themeName,
        context = Dispatchers.Main.immediate
    )

    SaccoRideTheme(theme = themeValue) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { BottomNavigationBar(navController = navController) }
                ) { padding ->
                    Column(
                        modifier = Modifier
                            .padding(padding)
                    ) {
                        RootNavGraph(
                            navController = navController,
                        )
                    }
                }

            }



    }

}

@ExperimentalMaterialNavigationApi
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun rememberBottomSheetNavigator(
    animationSpec: AnimationSpec<Float> = SwipeableDefaults.AnimationSpec,
    skipHalfExpanded: Boolean = true,
): BottomSheetNavigator {
    val sheetState = rememberModalBottomSheetState(
        ModalBottomSheetValue.Hidden,
        animationSpec,
        skipHalfExpanded,
    )
    return remember(sheetState) {
        BottomSheetNavigator(sheetState = sheetState)
    }
}