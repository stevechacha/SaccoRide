package com.dev.chacha.presentation.paybill

import android.Manifest
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.components.AppTopBar
import com.dev.chacha.presentation.common.components.RetryButton
import com.dev.chacha.presentation.contactList.Contact
import com.dev.chacha.presentation.contactList.ContactListViewModel
import com.dev.chacha.presentation.contactList.ContactUiEvent
import com.dev.chacha.presentation.contactList.component.ContactItem
import com.dev.chacha.presentation.contactList.component.NoMatchFound
import com.dev.chacha.presentation.paybill.component.PayBillItem
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun PayBillSelection(
    payBillViewModel: PayBillViewModel,
    navController: NavController,
    onPayBillSelected: (PayBill) -> Unit
) {
    val payBillState by payBillViewModel.payBillState.collectAsState()
    val lazyState = rememberLazyListState()

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Search PayBill",
                initialValue = payBillState.searchParams,
                onSearchParamChange = { searchParams ->
                    payBillViewModel.onPayBillEvent(PayBillEvent.SearchPayBill(searchParams))
                },
                showBackArrow = true,
                showSearchBar = true,
            )
        },
        content = {
            when {
                payBillState.isLoading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }
                payBillState.payBillList.isNotEmpty() -> {
                    LazyColumn(
                        modifier = Modifier.padding(it).fillMaxSize(),
                        state = lazyState
                    ) {
                        items(payBillState.payBillList) { payBill ->
                            PayBillItem(payBill = payBill) {
                                payBillViewModel.onPayBillEvent(PayBillEvent.GetPayBill)
                                navController.previousBackStackEntry?.savedStateHandle?.set("selectedPayBill", payBill)
                                onPayBillSelected(payBill)
                                navController.popBackStack()
                            }
                        }
                    }
                }
                payBillState.error.isNotEmpty() -> {
                    RetryButton(
                        error = payBillState.error,
                        onRetryEvent = {
                            payBillViewModel.onPayBillEvent(PayBillEvent.GetPayBill)
                        }
                    )
                }
                else -> {
                    NoMatchFound(lottie = R.raw.no_match_found_lottie)
                }
            }
        }
    )
}
