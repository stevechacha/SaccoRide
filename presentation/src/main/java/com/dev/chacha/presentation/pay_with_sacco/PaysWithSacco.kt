package com.dev.chacha.presentation.pay_with_sacco

import PayBills
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dev.chacha.presentation.buy_goods.BayGoods
import com.dev.chacha.presentation.common.components.AppTopBar
import com.dev.chacha.presentation.common.theme.PrimaryColor
import com.dev.chacha.presentation.paybill.component.PayBillItem
import com.dev.chacha.presentation.paybill.payBillItems
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch


@OptIn(
    ExperimentalPagerApi::class, ExperimentalMaterialApi::class,
    ExperimentalFoundationApi::class, ExperimentalFoundationApi::class
)
@Composable
fun PayWithSacco(
    navigateBack: () -> Unit
) {
    val payWithSaccoViewModel: PayWithSaccoViewModel = viewModel()
    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)

    val targetPayWithSacco by rememberSaveable { mutableStateOf(payWithSaccoViewModel.targetPayWithSacco) }
    val buyGoodsWithSacco by rememberSaveable { mutableStateOf(payWithSaccoViewModel.buyGoodsWithSacco) }


    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
    val tabselected = remember {
        mutableStateOf(-1)
    }
    val tabs = listOf(
        TabItem.BuyGoods(scaffoldState = sheetState, tabselected = tabselected),
        TabItem.PayBill(scaffoldState = sheetState, tabselected = tabselected)

    )
    val pagerState = rememberPagerState()


    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {

            if (targetPayWithSacco == "paybill"){
                PaysBills()
            }


        },
        sheetPeekHeight = 0.dp,
        sheetBackgroundColor = Color.Unspecified.copy(alpha = 0F),
        backgroundColor = MaterialTheme.colorScheme.background,
        sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)

    ) {
        CompositionLocalProvider(
            LocalOverscrollConfiguration provides null
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "Lipa Na Sacco",
                                fontSize = 14.sp
                            )
                        },
                        backgroundColor = MaterialTheme.colorScheme.background,
                        contentColor = MaterialTheme.colorScheme.onBackground,
                        elevation = 0.dp,
                        navigationIcon = {
                            IconButton(onClick = { navigateBack() }) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onBackground,
                                )

                            }
                        }
                    )
                },
            ) { padding ->
                Column(modifier = Modifier.padding(padding)) {
                    Tabs(tabs = tabs, pagerState = pagerState, tabselected = tabselected)
                    TabsContent(tabs = tabs, pagerState = pagerState, tabselected = tabselected)
                }
            }
        }


    }


}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PaysBills() {
    val scope = rememberCoroutineScope()
    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Search PayBill",
                initialValue = "",
                onSearchParamChange = {},
                showSearchBar = true
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(horizontal = 12.dp)
        ) {
            LazyColumn {
                payBillItems.forEachIndexed { index, payBill ->
                    item {
                        PayBillItem(
                            payBill = payBill,
                            onPayBillClick = {
                                scope.launch {
                                    sheetState.collapse()
                                }
                            }
                        )

                    }

                }
            }
        }

    }
}

@Composable
fun Bayyy() {
    Text(text = "Buy goods")
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState, tabselected: MutableState<Int>) {
    val coroutineScope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = tabselected.value,
        backgroundColor = MaterialTheme.colorScheme.background,
        divider = {
            TabRowDefaults.Divider(
                thickness = 1.dp,
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
                selected = tabselected.value == index,
                onClick = {
                    tabselected.value = index
                    coroutineScope.launch { pagerState.scrollToPage(index) }
                },
                text = {
                    Text(
                        text = item.title,
                        color = if (tabselected.value == index) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onBackground
                    )
                },
                selectedContentColor = PrimaryColor,
                unselectedContentColor = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(tabs: List<TabItem>, pagerState: PagerState, tabselected: MutableState<Int>) {
    HorizontalPager(state = pagerState, count = tabs.size) { page ->
        tabselected.value = page
        tabs[page].screen()
    }
}


typealias ComposableFun = @Composable () -> Unit

sealed class TabItem @OptIn(ExperimentalMaterialApi::class) constructor(
    var title: String,
    var screen: ComposableFun,
) {

    @OptIn(ExperimentalMaterialApi::class)
    data class BuyGoods(
        val scaffoldState: BottomSheetState,
        val tabselected: MutableState<Int>
    ) : TabItem("BUY GOODS", screen = { BayGoods(scaffoldState) })

    @OptIn(ExperimentalMaterialApi::class)
    data class PayBill(
        val scaffoldState: BottomSheetState,
        val tabselected: MutableState<Int>
    ) : TabItem(
        "PAYBILL",
        screen = { PayBills(scaffoldState) })
}

/*sealed class TabItem(
    val title: String,
    val tabselected: MutableState<Int>,
    val screen: @Composable () -> Unit
) {
    class BuyGoods(
        scaffoldState: BottomSheetState,
        tabselected: MutableState<Int>
    ) : TabItem("BUY GOODS", tabselected, screen = { Bayyy() })

    class PayBill(
        scaffoldState: BottomSheetState,
        tabselected: MutableState<Int>
    ) : TabItem("PAYBILL", tabselected, screen = { PaysBills() })
}*/
