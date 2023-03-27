package com.dev.chacha.presentation.transaction

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.chacha.presentation.common.theme.PrimaryColor

@Composable
fun HorizontalCardItem(
    @DrawableRes drawable:  Int,
    @StringRes text: Int,
    onItemClick: ()-> Unit
) {
    Card(
        modifier = Modifier
            .height(100.dp)
            .width(100.dp)
            .clickable(onClick = onItemClick)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
           Image(
               painter =  painterResource(id = drawable),
               contentDescription = null,
               modifier = Modifier
                   .fillMaxWidth()
                   .height(40.dp),
               colorFilter = ColorFilter.tint(PrimaryColor)
           )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = text),
                modifier = Modifier.fillMaxWidth(),
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )
        }

    }

}