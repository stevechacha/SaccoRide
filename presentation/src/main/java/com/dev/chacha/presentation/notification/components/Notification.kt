package com.dev.chacha.presentation.notification.components

import com.dev.chacha.presentation.R

data class Notification(
    val iconResId: Int,
    val title: String,
    val message: String,
    val timestamp: String
)

fun getNotifications(): List<Notification> = listOf(
    Notification(iconResId = R.drawable.notifications, title = "Notification 1", message = "Message 1", timestamp = "2 hours ago"),
    Notification(iconResId = R.drawable.notifications, title = "Notification 2", message = "Message 2", timestamp = "1 day ago"),
    Notification(iconResId = R.drawable.notifications, title = "Notification 3", message = "Message 3", timestamp = "3 days ago"),

)



