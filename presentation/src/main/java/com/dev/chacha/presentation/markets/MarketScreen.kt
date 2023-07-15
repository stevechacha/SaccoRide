package com.dev.chacha.presentation.markets


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.markets.components.AnimatedCardPager
import com.dev.chacha.presentation.markets.components.AnimatedCardPagerIndicator
import com.dev.chacha.presentation.markets.components.MarketTopBar
import com.dev.chacha.presentation.markets.components.ShopCard
import com.dev.chacha.presentation.markets.components.getCardImages
import kotlin.random.Random


@Composable
fun MarketScreen(
    navigateBack: ()->Unit
) {
    Scaffold(
        topBar = {
            MarketTopBar(
                title = "Market"
            )
        },
    ) { paddingValues ->

        val cardsItems = getCardImages()



        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
        ) {


            LazyVerticalGrid(
                columns = GridCells.Adaptive(170.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {

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
}



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



@Composable
@Preview
fun MarketPreview() {
    Surface(modifier = Modifier.fillMaxSize()) {
        MarketScreen(
            navigateBack = {}
        )
    }
}