package com.dev.chacha.presentation.notification.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp


@Composable
fun NotificationItem(
    notification: Notification,
    onItemClick: (Notification) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(notification) }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = notification.iconResId),
            contentDescription = null, // Provide a meaningful description if needed
            modifier = Modifier
                .size(25.dp)
                .clip(shape = CircleShape)
        )

        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f)
        ) {
            Text(
                text = notification.title,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = notification.message,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = notification.timestamp,
                color = Color.Gray
            )
        }
    }
}
