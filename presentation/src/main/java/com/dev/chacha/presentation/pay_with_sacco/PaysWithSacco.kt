package com.dev.chacha.presentation.pay_with_sacco

import PayBills
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.buy_goods.BayGoods
import com.dev.chacha.presentation.common.components.AppTopBar
import com.dev.chacha.presentation.common.theme.PrimaryColor
import com.dev.chacha.presentation.contactList.ContactSelectionScreen
import com.dev.chacha.presentation.paybill.PayBillEvent
import com.dev.chacha.presentation.paybill.PayBillSelection
import com.dev.chacha.presentation.paybill.PayBillViewModel
import com.dev.chacha.presentation.paybill.component.PayBillItem
import com.dev.chacha.presentation.paybill.payBillItems
import com.google.accompanist.pager.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(
    ExperimentalPagerApi::class, ExperimentalMaterialApi::class,
    ExperimentalFoundationApi::class, ExperimentalFoundationApi::class,
    ExperimentalCoroutinesApi::class
)
@Composable
fun PayWithSacco(
    navigateBack: () -> Unit,
    navController: NavController
) {
    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)

    val payBillViewModel : PayBillViewModel = viewModel()
    val payBillState by payBillViewModel.payBillState.collectAsState()

    val coroutineScope = rememberCoroutineScope()

    val tabs = listOf(
        TabItem.BuyGoods(scaffoldState = sheetState,navController),
        TabItem.PayBill(scaffoldState = sheetState,navController)

    )
    val pagerState = rememberPagerState()


    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            if (pagerState.currentPage == 0) {
                BayGood()
            } else if (pagerState.currentPage == 1) {
                PayBillSelection(
                    onPayBillSelected = { payBill ->
                        coroutineScope.launch {
                            payBillViewModel.onPayBillEvent(PayBillEvent.BusinessNumberChanged(payBill.businessNumber))
                            payBillViewModel.onPayBillEvent(PayBillEvent.AccountNameChanged(payBill.name))
                            sheetState.collapse()
                        }
                    }, navController = navController,
                    payBillViewModel = payBillViewModel
                )
            } else {
                Text(text = "else")
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
                    Tabs(tabs = tabs, pagerState = pagerState,)
                    TabsContent(tabs = tabs, pagerState = pagerState)
                }
            }
        }

    }


}

@Composable
fun BayGood() {
    Scaffold { paddingValues ->
     Column(
         modifier = Modifier.padding(paddingValues)
     ) {
         LazyColumn(){
             items(100){
                 Row(
                     modifier = Modifier.fillMaxWidth(),
                     verticalAlignment = Alignment.CenterVertically
                 ) {
                     Icon(painter = painterResource(id = R.drawable.home_icon), contentDescription = null , modifier = Modifier.size(25.dp))
                     Spacer(modifier = Modifier.weight(1f))
                     Text(text = it.toString())

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
fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {
    val coroutineScope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = MaterialTheme.colorScheme.background,
        divider = {
            TabRowDefaults.Divider(
                thickness = 0.5.dp,
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
                onClick = {
                    coroutineScope.launch { pagerState.scrollToPage(index) }
                },
                text = {
                    Text(
                        text = item.title,
                        color = if (pagerState.currentPage == index) MaterialTheme.colorScheme.primary
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
fun TabsContent(tabs: List<TabItem>, pagerState: PagerState) {
    HorizontalPager(state = pagerState, count = tabs.size) { page ->
        tabs[page].screen()
    }
}


typealias ComposableFun = @Composable () -> Unit
sealed class TabItem @OptIn(ExperimentalMaterialApi::class) constructor(
    var title: String,
    var screen: ComposableFun,
) {

    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalMaterialApi::class)
    data class BuyGoods(val scaffoldState: BottomSheetState,
        val navController: NavController
    ) : TabItem("BUY GOODS", screen = { BayGoods(scaffoldState,navController) })

    @OptIn(ExperimentalMaterialApi::class)
    data class PayBill(
        val scaffoldState: BottomSheetState,
        val navController: NavController
    ) : TabItem(
        "PAYBILL",
        screen = { PayBills(scaffoldState,navController) })
}

