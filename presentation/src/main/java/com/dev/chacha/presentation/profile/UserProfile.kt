package com.dev.chacha.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dev.chacha.presentation.R


@Composable
fun UserProfile(
    userProfile: UserProfile
) {
    Column (modifier = Modifier.padding(horizontal = 24.dp)){
        AsyncImage(
            model = userProfile.imageUrl,
            contentDescription =
            stringResource(id = R.string.user_profile),
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
                .size(100.dp)
                .clip(CircleShape),
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop
        )

        Row {
            Text(text = userProfile.name)

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit"
                )
            }

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings"
                )
            }
        }

    }
}

