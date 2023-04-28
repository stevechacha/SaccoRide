package com.dev.chacha.presentation.notification

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dev.chacha.presentation.account.component.AccountCard
import com.dev.chacha.presentation.common.components.AppToolbar

@Composable
fun NotificationsScreen() {
    Scaffold(
        topBar = {
            AppToolbar(
                title = "Notification "
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