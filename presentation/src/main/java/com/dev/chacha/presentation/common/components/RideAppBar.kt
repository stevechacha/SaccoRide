package com.dev.chacha.presentation.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dev.chacha.presentation.R

@Composable
fun RideAppBar(
    modifier: Modifier = Modifier,
    userProfile: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .padding(start = 20.dp, end = 20.dp, top = 19.dp, bottom = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.sessions_icon),
            contentDescription = null
        )
        Spacer(modifier = Modifier.weight(1f))

        FeedbackButton()

        Spacer(modifier = Modifier.width(15.dp))

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(userProfile)
                .build(),
            placeholder = painterResource(id = R.drawable.sessions_icon),
            contentDescription = stringResource(id = R.string.user_profile) ,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
        )

    }

}

@Composable
fun FeedbackButton() {

}