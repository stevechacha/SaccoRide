package com.dev.chacha.presentation.home.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.theme.PrimaryColor

@Composable
fun TextImageItem(
    @DrawableRes drawable: Int,
    @StringRes stringRes: Int,
    onItemClick: () -> Unit
) {
    val sizeIcon = dimensionResource(id = R.dimen.margin_24)
    Column(
        modifier = Modifier
            .width(90.dp)
            .height(80.dp)
            .clickable(onClick = onItemClick),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(48.dp)
                .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08F)),
            contentAlignment = Alignment.Center

        ) {
            Icon(
                painter = painterResource(id = drawable),
                contentDescription = "",
                modifier = Modifier
                    .size(sizeIcon),
                tint = PrimaryColor
            )

        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = stringResource(id = stringRes),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelSmall,
            overflow = TextOverflow.Ellipsis

        )

    }

}

@Composable
@Preview
fun TextImageItemPreview() {
    TextImageItem(
        drawable = R.drawable.ic_home,
        stringRes = R.string.sendMoney,
        onItemClick = { }
    )


}