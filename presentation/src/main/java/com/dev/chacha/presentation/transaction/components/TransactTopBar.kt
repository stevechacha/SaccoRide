package com.dev.chacha.presentation.transaction.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.chacha.presentation.R


@Composable
fun TransactTopBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.transaction),
                fontSize = 14.sp
            )
        },
        navigationIcon = {
            Image(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                colorFilter = ColorFilter.tint(
                    MaterialTheme.colorScheme.onBackground
                )

            )
        },
        actions = {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(end = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.home_icon),
                    contentDescription = "Edit",
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(CircleShape)
                        .size(25.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_send_money),
                    contentDescription = "Settings",
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(CircleShape)
                        .size(25.dp)
                )
            }
        },
        backgroundColor = MaterialTheme.colorScheme.background
    )

}