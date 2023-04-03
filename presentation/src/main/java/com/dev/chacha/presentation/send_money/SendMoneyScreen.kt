package com.dev.chacha.presentation.send_money

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dev.chacha.presentation.buy_goods.BuyGoodsScreen
import com.dev.chacha.presentation.paybill.PayBillScreen
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.components.AppToolbar
import com.dev.chacha.presentation.common.theme.SaccoRideTheme
import com.dev.chacha.presentation.send_money.artel_money.ArtelMoneyScreen
import com.dev.chacha.presentation.send_money.mpesa.MpesaScreen
import com.dev.chacha.presentation.send_money.tkash.TkashScreen
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SendMoneyScreen() {
    val tabs = listOf(SendMoneyTabItem.Mpesa,SendMoneyTabItem.AirtelMoney, SendMoneyTabItem.Tkash)
    val pagerState = rememberPagerState()
    Scaffold(
        topBar = {
            AppToolbar(
                title = stringResource(id = R.string.send_money),
                showBackArrow = true
            )
        },
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            SendMoneyTabs(tabs = tabs, pagerState = pagerState)
            SendMoneyTabsContent(tabs = tabs, pagerState = pagerState)
        }
    }


}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SendMoneyTabs(tabs: List<SendMoneyTabItem>, pagerState: PagerState) {
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
fun SendMoneyTabsContent(tabs: List<SendMoneyTabItem>, pagerState: PagerState) {
    HorizontalPager(state = pagerState, count = tabs.size) { page ->
        tabs[page].screen()
    }
}


@Composable
@Preview
fun SendMoneyPreview() {
    SaccoRideTheme {
        SendMoneyScreen()
    }
}

typealias ComposableFun = @Composable () -> Unit
sealed class SendMoneyTabItem( var title: String, var screen: ComposableFun) {
    object Mpesa : SendMoneyTabItem( "MPESA", { MpesaScreen() })
    object AirtelMoney : SendMoneyTabItem( "AIRTEL", { ArtelMoneyScreen() })
    object Tkash : SendMoneyTabItem( "T-KASH", { TkashScreen() })
}