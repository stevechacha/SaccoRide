package com.dev.chacha.presentation.about_sacco

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dev.chacha.presentation.common.components.AppToolbar

@Composable
fun AboutSaccoRide() {
    Scaffold(
        topBar = {
            AppToolbar(
                title = "About SaccoRide "
            )
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier.fillMaxSize()
                .padding(paddingValues)
        ) {

        }

    }

}