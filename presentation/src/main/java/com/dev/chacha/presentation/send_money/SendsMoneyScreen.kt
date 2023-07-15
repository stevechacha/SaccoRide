package com.dev.chacha.presentation.send_money

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.components.AppToolbar
import com.dev.chacha.presentation.contactList.ContactListViewModel
import com.dev.chacha.presentation.contactList.ContactSelectionScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalCoroutinesApi::class)
@Composable
fun SendsMoneyScreen(navigateBack:()->Unit, ) {

    val sendMoneyViewModel: SendMoneyViewModel = viewModel()
    val sendMoneyState by sendMoneyViewModel.sendMoneyState.collectAsState()
    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
    val viewModel = viewModel<ContactListViewModel>()
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()


    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            ContactSelectionScreen(
                onContactSelected = { contact ->
                    coroutineScope.launch {
                        sendMoneyViewModel.onSendMoneyEvent(SendMoneyEvent.PhoneNumberChanged(contact.phoneNumber))
                        sheetState.collapse()
                    }
                }, navController = navController,
            )
        },
        sheetPeekHeight = 0.dp,
        sheetBackgroundColor = Color.Unspecified.copy(alpha = 0F),
        modifier = Modifier.fillMaxSize()

    ) {
        Scaffold(
            topBar = {
                AppToolbar(
                    title = stringResource(id = R.string.send_money),
                    showBackArrow = true,
                    navigateBack = {navigateBack()}
                )
            },
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(horizontal = 16.dp)
            ) {
                SendMoneyBottomSheetContent(
                    coroutineScope = coroutineScope,
                    scaffoldState = scaffoldState,
                    sendMoneyState = sendMoneyState,
                    onPhoneNumberChanged = { phoneNumber->
                        sendMoneyViewModel.onSendMoneyEvent(SendMoneyEvent.PhoneNumberChanged(phoneNumber))
                    },
                    onAmountChanged = { amount ->
                        sendMoneyViewModel.onSendMoneyEvent(SendMoneyEvent.AmountChanged(amount))
                    },
                    contactListViewModel = viewModel,
                    onRecipientProvider = {  newProvider->
                        sendMoneyViewModel.onSendMoneyEvent(SendMoneyEvent.RecipientProviderSelected(newProvider))
                    }
                )

            }
        }
    }


}

@Composable
@Preview
fun SendsMoneyScreenPreview() {
    SendsMoneyScreen(
        navigateBack = {}
    )

}






val recipientProvider = listOf(
    RecipientProvider.Mpesa,
    RecipientProvider.AirtelMoney,
    RecipientProvider.Tkash
)

enum class RecipientProvider(val icon: ImageVector) {
    Mpesa(Icons.Default.Phone),
    AirtelMoney(Icons.Default.AttachMoney),
    Tkash(Icons.Default.AccountBalanceWallet)
}
