package com.dev.chacha.presentation.buy_artime

import androidx.compose.foundation.layout.*
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TopAppBar
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.buy_goods.BuyGoodsScreen
import com.dev.chacha.presentation.common.components.RideOutlinedTextField
import com.dev.chacha.presentation.common.theme.SaccoRideTheme
import com.dev.chacha.presentation.paybill.PayBillScreen
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BuyAirtimeScreen() {
    val tabs = listOf(BuyAirtimeTabItem.Safaricom, BuyAirtimeTabItem.Telkom, BuyAirtimeTabItem.Artel)
    val pagerState = rememberPagerState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.buy_airtime))
                },
                backgroundColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground,
                elevation = 0.dp
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            BuyAirtimeTabs(tabs = tabs, pagerState = pagerState)
            BuyAirtimeTabsContent(tabs = tabs, pagerState = pagerState)

        }

    }

}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun BuyAirtimeTabs(tabs: List<BuyAirtimeTabItem>, pagerState: PagerState) {
    val coroutineScope = rememberCoroutineScope()

    TabRow(

        selectedTabIndex = pagerState.currentPage,
        backgroundColor = MaterialTheme.colorScheme.background,
        divider = {
            TabRowDefaults.Divider(
                thickness = 1.dp,
//                      color = MaterialTheme.colorScheme.background
            )
        },
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                color = MaterialTheme.colorScheme.primary,
                height = 2.dp
            )
        },
    ) {
        tabs.forEachIndexed { index, item ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                text = {
                    Text(
                        text = item.title,
                        color = if (pagerState.currentPage == index) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onBackground

                    )
                },
                selectedContentColor = MaterialTheme.colorScheme.primary,
                unselectedContentColor = MaterialTheme.colorScheme.onBackground
            )
        }
    }

}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun BuyAirtimeTabsContent(tabs: List<BuyAirtimeTabItem>, pagerState: PagerState) {
    HorizontalPager(state = pagerState, count = tabs.size) { page ->
        tabs[page].screen()
    }
}

typealias ComposableFun = @Composable () -> Unit
sealed class BuyAirtimeTabItem( var title: String, var screen: ComposableFun) {
    object Safaricom : BuyAirtimeTabItem( "SAFARICOM", { Safaricom() })
    object Telkom : BuyAirtimeTabItem( "TELKOM", { Telkom() })
    object Artel : BuyAirtimeTabItem( "AIRTEL", { Artel() })
}

@Composable
@Preview
fun BuyAirtimePreview() {
    SaccoRideTheme {
        BuyAirtimeScreen()
    }
}