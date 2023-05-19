package com.dev.chacha.presentation.notification

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dev.chacha.presentation.account.component.AccountCard
import com.dev.chacha.presentation.common.components.AppToolbar
import com.dev.chacha.presentation.notification.components.getNotifications

@Composable
fun NotificationsScreen(
    navigateBack:()->Unit
) {
    Scaffold(
        topBar = {
            AppToolbar(
                title = "Notifications ",
                navigateBack = {navigateBack()}
            )
        },
    ) { paddingValues ->

        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)){
            NotificationList(
                notifications =  getNotifications(),
                onItemClick = {}
            )

        }

    }

}
