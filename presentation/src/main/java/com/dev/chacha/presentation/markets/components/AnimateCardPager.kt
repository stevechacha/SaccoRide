package com.dev.chacha.presentation.markets.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dev.chacha.presentation.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AnimatedCardPager(cards: List<CardItem>) {
    val pagerState = rememberPagerState(initialPage = 0)
    val cardCount = getCardImages()
    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            val nextPage = (pagerState.currentPage + 1) % cards.size
            pagerState.animateScrollToPage(nextPage)
        }
    }
    HorizontalPager(
        count = cardCount.size,
        state = pagerState,
        userScrollEnabled = true
    ) { page ->
        AnimatedCardItem(cards[page])

    }

}

@Composable
fun AnimatedCardItem(card: CardItem) {
    var isVisible by remember { mutableStateOf(false) }
    val transition = updateTransition(targetState = isVisible, label = "cardTransition")
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .animateContentSize()
            .clickable { isVisible = !isVisible }
    ) {
        Icon(
            painter = painterResource(id = card.image),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
                .padding(5.dp)
        )
    }

    LaunchedEffect(key1 = card) {
        isVisible = true
    }
}

fun getCardImages(): List<CardItem> = listOf(
    CardItem(
        R.drawable.sako_icon,
    ),
    CardItem(
        R.drawable.ryde,
    ),
    CardItem(
        R.drawable.sako_ride_icons,
    ),
    CardItem(
        R.drawable.sako_icon,
    ),
    CardItem(
        R.drawable.ryde,
    )
)

data class CardItem(
    val image: Int
)
