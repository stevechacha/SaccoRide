import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.BottomSheetState
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.baybill.components.BillDialog
import com.dev.chacha.presentation.common.components.ContinueButton
import com.dev.chacha.presentation.common.components.RideOutlinedTextField
import com.dev.chacha.presentation.common.navigation.HomeAction
import com.dev.chacha.presentation.paybill.PayBill
import com.dev.chacha.presentation.paybill.PayBillEvent
import com.dev.chacha.presentation.paybill.PayBillViewModel
import com.dev.chacha.presentation.paybill.component.PayBillItem
import com.dev.chacha.presentation.paybill.payBillItems
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PayBills(
    sheetState: BottomSheetState,
    navController: NavController

) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        PayBillScreen(
            onPayBillClick = {},
            sheetState = sheetState,
            navController = navController

        )

    }

}

@OptIn(
    ExperimentalMaterialApi::class, ExperimentalMaterialApi::class,
    ExperimentalFoundationApi::class, ExperimentalCoroutinesApi::class
)

@Composable
fun PayBillScreen(
    onPayBillClick: (PayBill) -> Unit,
    sheetState: BottomSheetState,
    navController: NavController
) {

    val payBillViewModel: PayBillViewModel = viewModel()
    val payBillState by payBillViewModel.payBillState.collectAsState()
    val scope = rememberCoroutineScope()
    val dateTimeFormat = SimpleDateFormat("MMM dd yyyy, h:mm a", Locale.getDefault())
    val currentDateTime = dateTimeFormat.format(Date())

    var showDialog by rememberSaveable { mutableStateOf(false) }

    if (showDialog) {
        BillDialog(
            onDismiss = {
                showDialog = false
            },
            onClickSend = { payBill->
               val route = HomeAction.BillConfirm.sendData(
                        accountName = payBill.name,
                        businessNumber = payBill.businessNumber,
                        accountNumber = payBill.accountNumber.toString(),
                        amount = payBill.amount!!.toDouble(),
                        date = payBill.date.toString()
                    )
                navController.navigate(route = route)
            },
            payBill = PayBill(
                name = payBillState.accountName,
                businessNumber = payBillState.businessNumber,
                accountNumber = payBillState.accountNumber,
                amount = payBillState.amount.toDouble(),
                date = System.currentTimeMillis().toString()
            )
        )
    }


    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .background(MaterialTheme.colorScheme.background)

    ) {

        RideOutlinedTextField(
            value = payBillState.businessNumber,
            onValueChange = {
                payBillViewModel.onPayBillEvent(PayBillEvent.BusinessNumberChanged(it))
            },
            keyboardType = KeyboardType.Phone,
            hint = stringResource(id = R.string.businessNumber),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Search",
                    modifier = Modifier
                        .padding(8.dp)
                        .size(24.dp)
                        .clickable {
                            scope.launch {
                                if (sheetState.isCollapsed) {
                                    sheetState.expand()
                                } else {
                                    sheetState.collapse()
                                }
                            }
                        },
                    tint = LocalContentColor.current.copy(alpha = ContentAlpha.medium)

                )
            },
            supportText = payBillState.accountName

        )
        Spacer(modifier = Modifier.height(10.dp))
        val accountNumberLimit = 20

        RideOutlinedTextField(
            value = payBillState.accountNumber,
            onValueChange = {
                payBillViewModel.onPayBillEvent(PayBillEvent.AccountNumberChanged(it))
            },
            keyboardType = KeyboardType.Text,
            hint = stringResource(id = R.string.accountNumber),
            accountNumberLength = "${payBillState.accountNumber.length}/${accountNumberLimit}"

        )
        Spacer(modifier = Modifier.height(10.dp))

        RideOutlinedTextField(
            value = payBillState.amount.toString(),
            onValueChange = { amount->
                payBillViewModel.onPayBillEvent(PayBillEvent.AmountChanged(amount))
            },
            keyboardType = KeyboardType.Number,
            hint = stringResource(id = R.string.amount),

            )

        Spacer(modifier = Modifier.height(16.dp))

        ContinueButton(
            text = stringResource(id = R.string.continuee),
            onClick = {
                showDialog = true
            },
            enable = payBillState.isPayBillEnabled
        )

        Spacer(modifier = Modifier.height(25.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Frequent",
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                payBillItems.forEachIndexed { index, payBill ->
                    item {
                        PayBillItem(
                            payBill = payBill,
                            onPayBillItemClick = {
                                payBillViewModel.onPayBillEvent(PayBillEvent.BusinessNumberChanged(businessNumber = payBill.businessNumber))
                                payBillViewModel.onPayBillEvent(PayBillEvent.AccountNameChanged(accountName = payBill.name))
                            }
                        )

                    }

                }
            }

        }

    }
}

@Composable
@Preview
fun PayBillScreenPreview() {
    Column {
        LazyColumn {
            items(20) {
                PayBillItem(
                    payBill = PayBill(
                        name = "John Doe",
                        businessNumber = "1234567890",
                        image = null
                    )
                ) {

                }
            }
        }
    }

}