package com.dev.chacha.presentation.markets.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.theme.Shapes
import com.dev.chacha.presentation.paybill.component.randomColor

@Composable
fun ShopCard(
    @DrawableRes drawable: Int,
    @StringRes title: Int,
    @StringRes subTitle: Int,
    onClickCard: ()-> Unit
) {
    Card(modifier = Modifier
        .width(200.dp)
        .height(110.dp)
        .clickable(onClick = onClickCard),
        colors = CardDefaults.cardColors(
            containerColor = randomColor()
        ),
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier.size(50.dp)
            ){
                Icon(
                    painter = painterResource(id = drawable) ,
                    contentDescription = null,
                    modifier = Modifier.size(25.dp),
                )

            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(id = title),
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = stringResource(id =  subTitle),
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.fillMaxWidth(),
            )

        }

    }

}

@Composable
@Preview
fun PreviewShopCard() {
    ShopCard(
        drawable = R.drawable.icon_banner,
        title = R.string.paybill,
        subTitle = R.string.paybill,
        onClickCard = {}
    )



}