package com.dev.chacha.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.home.User


@Composable
fun Profile(user: User) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {
        val (image, text) = createRefs()
        Image(
            painter = painterResource(id = R.drawable.sessions_icon),
            contentDescription = "Profile",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(color = MaterialTheme.colorScheme.onSecondaryContainer)
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
        )
        Text(
            text = "Profile",
            modifier = Modifier
                .constrainAs(text) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(image.end)
                }
        )

        if (user.title != null) {
            Text(
                text = user.title.substring(0, 2),
                modifier = Modifier
                    .constrainAs(text) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(image.end)
                    }
            )
        } else {

            AsyncImage(
                model = user.title,
                contentDescription = "user",
                modifier = Modifier
                    .constrainAs(text) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(image.end)
                    }
            )


        }
    }
}