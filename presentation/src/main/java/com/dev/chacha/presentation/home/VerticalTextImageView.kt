package com.dev.chacha.presentation.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.theme.PrimaryColor

@Composable
fun VerticalTextImageView(
    @DrawableRes drawable: Int,
    @StringRes stringRes: Int,
    onItemClick : () -> Unit
) {
    Card(
        modifier = Modifier
            .height(100.dp)
            .width(100.dp)
            .clickable(onClick = onItemClick)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            val (image, nameText) = createRefs()
            Image(
                painter = painterResource(id = drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .size(40.dp)
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                colorFilter = ColorFilter.tint(PrimaryColor)
            )
            Text(
                text = stringResource(id = stringRes),
                modifier = Modifier
                    .constrainAs(nameText) {
                        top.linkTo(image.bottom, margin = 8.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                fontSize = 14.sp

            )

        }

    }

}