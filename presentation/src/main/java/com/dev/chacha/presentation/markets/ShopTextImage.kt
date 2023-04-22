package com.dev.chacha.presentation.markets

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.theme.PrimaryColor

@Composable
fun ShopTextImage(
    @StringRes text: Int,
    @DrawableRes drawable : Int,
    onItemClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
            .clickable(onClick = onItemClick),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = drawable) ,
                contentDescription = null,
                modifier = Modifier.size(25.dp),
                tint = PrimaryColor
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = text),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall

            )
        }
    }

}

@Composable
@Preview
fun ShopTextImagePreview() {
    ShopTextImage(
        text = R.string.electronics,
        drawable = R.drawable.home_icon,
        onItemClick = {}
    )
}