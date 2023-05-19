package com.dev.chacha.presentation.notification

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dev.chacha.presentation.notification.components.Notification
import com.dev.chacha.presentation.notification.components.NotificationItem
import com.dev.chacha.presentation.notification.components.getNotifications

@Composable
fun NotificationList(
    notifications: List<Notification>,
    onItemClick: (Notification) -> Unit
) {
    val notification = getNotifications()
    LazyColumn {
        items(notification.size){ index->
            NotificationItem(
                notification = notification[index],
                onItemClick = {}
            )
            Divider(
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
