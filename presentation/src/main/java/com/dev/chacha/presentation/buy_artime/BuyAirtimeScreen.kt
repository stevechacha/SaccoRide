package com.dev.chacha.presentation.buy_artime

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.theme.SaccoRideTheme
import com.dev.chacha.presentation.contactList.ContactListViewModel
import com.dev.chacha.presentation.contactList.ContactSelectionScreen
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class,
    ExperimentalFoundationApi::class
)
@Composable
fun BuyAirtimeScreen() {
    val navController = rememberNavController()
    val modalBottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    var (mobileNumber, setMobileNumber) = rememberSaveable { mutableStateOf("") }
    val (amount, setAmount) = rememberSaveable { mutableStateOf("") }
    var phoneNumber by rememberSaveable { mutableStateOf("") }
    val tabs =
        listOf(BuyAirtimeTabItem.Safaricom, BuyAirtimeTabItem.Telkom, BuyAirtimeTabItem.Artel)
    val pagerState = rememberPagerState()

    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetContent = {
            ContactSelectionScreen(
                onContactSelected = { contact ->
                    phoneNumber = contact.phoneNumber
                    coroutineScope.launch {
                        modalBottomSheetState.hide()
                    }
                } , navController = navController,
                viewModel = ContactListViewModel()
            )

        }
    ) {
        CompositionLocalProvider(
            LocalOverscrollConfiguration provides null
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = stringResource(id = R.string.buy_airtime),
                                style = MaterialTheme.typography.labelSmall
                            )
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

sealed class BuyAirtimeTabItem(var title: String, var screen: ComposableFun) {
    object Safaricom : BuyAirtimeTabItem("SAFARICOM", { Safaricom() })
    object Telkom : BuyAirtimeTabItem("TELKOM", { Telkom() })
    object Artel : BuyAirtimeTabItem("AIRTEL", { Artel() })
}

@Composable
@Preview
fun BuyAirtimePreview() {
        BuyAirtimeScreen()

}