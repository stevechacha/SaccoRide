package com.dev.chacha.presentation.markets

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.markets.components.AnimatedCardPager
import com.dev.chacha.presentation.markets.components.AnimatedCardPagerIndicator
import com.dev.chacha.presentation.markets.components.getCardImages
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.lerp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.lerp
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.dev.chacha.presentation.util.HexagonShapeClipper
import kotlin.math.cos
import kotlin.math.sin



@Composable
fun MarketTypeCard(
    title: String,
    subTitle: String,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    @DrawableRes imageResourceId: Int,
    containerColor: Color = MaterialTheme.colorScheme.surface
) {
    Card(
        modifier = modifier
            .height(110.dp)
            .clickable { onClick(title) },
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()

        ) {
            Image(
                modifier = Modifier
                    .size(40.dp)
                    .offset(x = 110.dp, y = 30.dp)
                    .rotate(30f),
                painter = painterResource(id = imageResourceId),
                contentDescription = null
            )
            Text(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp),
                text = title,
                fontWeight = FontWeight.Bold,
            )
        }
    }

}

@Composable
fun MarketScreensList() {
    val  cardsItems = getCardImages()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(170.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {

            item(span = { GridItemSpan(this.maxCurrentLineSpan) }) {
                AnimatedCardPagerIndicator(cards =   cardsItems)
            }

            items(marketTypeOption.size) { index ->
                MarketTypeCard(
                    title = marketTypeOption[index].title,
                    subTitle = marketTypeOption[index].subTitle,
                    imageResourceId = marketTypeOption[index].imageResourceId,
                    containerColor = marketTypeOption[index].containerColor,
                    onClick = { marketTypeOption ->
                        when (marketTypeOption) {
                            "title" -> {


                            }
                            "title" -> {

                            }

                            "title" -> {

                            }

                        }

                    },
                )
            }
        }

    }

}







@Composable
@Preview
fun PreviewMarketType() {
    MarketScreensList()
}


data class MarketType(
    val title: String,
    val subTitle: String,
    val imageResourceId: Int,
    val containerColor: Color,
)

private val marketTypeOption = listOf(
    MarketType(
        title = "title",
        subTitle = "subTitle",
        imageResourceId = R.drawable.home_icon,
        containerColor = Color(0, 48, 72)
    ),
    MarketType(
        title = "title",
        subTitle = "subTitle",
        imageResourceId = R.drawable.home_icon,
        containerColor = Color(71, 126, 149)
    ),
    MarketType(
        title = "title",
        subTitle = "subTitle",
        imageResourceId = R.drawable.home_icon,
        containerColor = Color(141, 103, 171)
    ),
    MarketType(
        title = "title",
        subTitle = "subTitle",
        imageResourceId = R.drawable.home_icon,
        containerColor = Color(140, 25, 50)
    ),
    MarketType(
        title = "title",
        subTitle = "subTitle",
        imageResourceId = R.drawable.home_icon,
        containerColor = Color(186, 93, 7)
    ),
    MarketType(
        title = "title",
        subTitle = "subTitle",
        imageResourceId = R.drawable.home_icon,
        containerColor = Color(119, 119, 119)
    ),
    MarketType(
        title = "title",
        subTitle = "subTitle",
        imageResourceId = R.drawable.home_icon,
        containerColor = Color(144, 168, 192)
    ),
    MarketType(
        title = "title",
        subTitle = "subTitle",
        imageResourceId = R.drawable.home_icon,
        containerColor = Color(230, 30, 50)
    ),
    MarketType(
        title = "title",
        subTitle = "subTitle",
        imageResourceId = R.drawable.home_icon,
        containerColor = Color(71, 125, 149)
    ),
    MarketType(
        title = "title",
        subTitle = "subTitle",
        imageResourceId = R.drawable.home_icon,
        containerColor = Color(141, 103, 171)
    ),
    MarketType(
        title = "title",
        subTitle = "subTitle",
        imageResourceId = R.drawable.home_icon,
        containerColor = Color(30, 50, 100)
    ),

    )