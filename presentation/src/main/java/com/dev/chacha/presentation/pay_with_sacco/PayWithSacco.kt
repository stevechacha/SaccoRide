package com.dev.chacha.presentation.pay_with_sacco

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.buy_goods.BuyGoodsScreen
import com.dev.chacha.presentation.paybill.PayBill
import com.dev.chacha.presentation.paybill.PayBillScreen
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@Composable
fun PayWithSacco() {
 PayWithSaccoContent()

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PayWithSaccoContent() {
    val tabs = listOf(TabItem.BuyGoods, TabItem.PayBill)
    val pagerState = rememberPagerState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Savings",
                        fontSize = 14.sp
                    )
                },
                backgroundColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground,
                elevation = 0.dp,
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icons.Default.ArrowBack
                    }
                }
            )
        },
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            Tabs(tabs = tabs, pagerState = pagerState)
            TabsContent(tabs = tabs, pagerState = pagerState)
        }
    }

}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {
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
fun TabsContent(tabs: List<TabItem>, pagerState: PagerState) {
    HorizontalPager(state = pagerState, count = tabs.size) { page ->
        tabs[page].screen()
    }
}
val payBills = PayBill(
    name = "",
    businessNumber = ""
)
val payBill = listOf(
    PayBill(
        name = "John Doe",
        businessNumber = "0712345678",
        image = R.drawable.home_icon,
    ),
    PayBill(
        name = "John Doe",
        businessNumber = "0712345678",
        image = null,
    )
)
typealias ComposableFun = @Composable () -> Unit
sealed class TabItem( var title: String, var screen: ComposableFun) {
    object BuyGoods : TabItem( "BUY GOODS", { BuyGoodsScreen() })
    object PayBill : TabItem( "PAYBILL", { PayBillScreen(onPayBillClick = {}, payBill = payBills) })
}