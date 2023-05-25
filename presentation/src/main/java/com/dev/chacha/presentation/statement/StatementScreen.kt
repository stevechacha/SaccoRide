package com.dev.chacha.presentation.statement

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.components.AppToolbar
import com.dev.chacha.presentation.common.theme.spacing

@Composable
fun StatementScreen(
    navigateBack:()->Unit
) {
    val spacing = MaterialTheme.spacing
//    val invoice = viewModel.invoice.collectAsState()

    Scaffold(
        topBar = {
            AppToolbar(
                title = "View your statement",
                navigateBack = {navigateBack()}
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(spacing.medium)
        ) {
            Card(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6F),
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.width(130.dp)
                    ) {
                        Text(
                            text = "Name",
                            maxLines = 1,
                            textAlign = TextAlign.Start,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "Contact",
                            maxLines = 1,
                            textAlign = TextAlign.Start,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "Statement Period",
                            maxLines = 1,
                            textAlign = TextAlign.Start,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "Requested Date",
                            maxLines = 1,
                            textAlign = TextAlign.Start,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Column(
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(text = "Stephen Chacha")
                        Text(text = "07564568374")
                        Text(text = "10/11/2022 - 10/12/2022")
                        Text(text = "29/04/2023")
                    }
                }

            }
            Text(
                text = "Sacco Summary Statement",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp)
            )

            Row {
                TableCell(text = stringResource(id = R.string.transaction_type), heading = true, weight = 0.32f)
                TableCell(text = stringResource(id = R.string.money_in), alignment = TextAlign.End, heading = true, weight = 0.32f)
                TableCell(text = stringResource(id = R.string.money_out), alignment = TextAlign.End, heading = true, weight = 0.32f)
            }
            transactionTypeList.forEach { item ->
                Row {
                    TableCell(text = item.transactionType.toString(), weight = 0.32f)
                    TableCell(text = item.transactionAmountIn.toString(), alignment = TextAlign.End, weight = 0.32f)
                    TableCell(text = item.transactionAmountOut.toString(), alignment = TextAlign.End, weight = 0.32f)
                }
            }
            Row {
                TableCell(text = stringResource(id = R.string.total_amount), heading = true, weight = 0.32f)
                TableCell(text = totaMoneyIn.toString(), alignment = TextAlign.End, weight = 0.32f, heading = true)
                TableCell(text = totaMoneyOut.toString(), alignment = TextAlign.End, weight = 0.32f, heading = true)
            }
            Text(
                text = "Sacco Statement",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp)
            )
            Row {
                TableCell(text = stringResource(id = R.string.transaction_detail), heading = true, weight = 0.35f)
                TableCell(text = stringResource(id = R.string.recepient_no), heading = true, weight = 0.25f)
                TableCell(text = stringResource(id = R.string.dete), heading = true, weight = 0.2f)
                TableCell(text = stringResource(id = R.string.money_in),  heading = true, weight = 0.2f)
                TableCell(text = stringResource(id = R.string.money_out), heading = true, weight = 0.2f)
                TableCell(text = stringResource(id = R.string.money_balance), heading = true, weight = 0.2f)
            }
            statementList.forEach { item ->
                Row {
                    TableCell(text = item.transactionDetail, weight = 0.35f)
                    TableCell(text = item.recipientNo, weight = 0.25f)
                    TableCell(text = item.date, weight = 0.2f)
                    TableCell(text = item.moneyIn.toString(), alignment = TextAlign.End, weight = 0.2f)
                    TableCell(text = item.moneyOut.toString(), alignment = TextAlign.End, weight = 0.2f)
                    TableCell(text = item.moneyBalance.toString(), alignment = TextAlign.End, weight = 0.2f)
                }

            }

            Row {
                TableCell(text = stringResource(id = R.string.total_amount), heading = true, weight = 0.35f)
                TableCell(text = "", weight = 0.25f)
                TableCell(text = "", weight = 0.2f)
                TableCell(text = totalMoneyIn.toString(), alignment = TextAlign.End, weight = 0.2f, heading = true)
                TableCell(text = totalMoneyOut.toString(), alignment = TextAlign.End, weight = 0.2f, heading = true)
                TableCell(text = totalBalance.toString(), alignment = TextAlign.End, weight = 0.2f, heading = true)
            }

        }

    }


}

data class Statement(
    val transactionDetail: String,
    val recipientNo: String,
    val date: String,
    val moneyIn: Double,
    val moneyOut: Double,
    val moneyBalance: Double
)

val statementList = listOf(
    Statement("Payment received", "123456789", "2023-04-01", 100.0, 0.0, 100.0),
    Statement("Transfer to savings", "987654321", "2023-04-02", 0.0, 50.0, 50.0),
    Statement("Withdrawal", "555555555", "2023-04-03", 0.0, 20.0, 30.0),
    Statement("Payment received", "123456789", "2023-04-04", 50.0, 0.0, 80.0),
    Statement("Payment received", "123456789", "2023-04-01", 100.0, 0.0, 100.0),
    Statement("Transfer to savings", "987654321", "2023-04-02", 0.0, 50.0, 50.0),
    Statement("Withdrawal", "555555555", "2023-04-03", 0.0, 20.0, 30.0),
    Statement("Payment received", "123456789", "2023-04-04", 50.0, 0.0, 80.0),

)


val totalBalance: Double
    get() = statementList.sumOf { it.moneyBalance }

val totalMoneyIn: Double
    get() = statementList.sumOf { it.moneyIn }
val totalMoneyOut: Double
    get() = statementList.sumOf { it.moneyOut }


data class Transactionss(
    val transactionType: TransactionsType,
    val transactionAmountIn: Double,
    val transactionAmountOut: Double,
    val total: Double = transactionAmountIn + transactionAmountOut
)

val transactionTypeList = listOf(
    Transactionss(
        transactionType = TransactionsType.BuyAirtime,
        transactionAmountIn = 2000.00,
        transactionAmountOut = 300.40
    ),
    Transactionss(
        transactionType = TransactionsType.BuyGoods,
        transactionAmountIn = 2000.00,
        transactionAmountOut = 300.40
    ),
    Transactionss(
        transactionType = TransactionsType.SendMoney,
        transactionAmountIn = 2000.00,
        transactionAmountOut = 300.40
    ),
    Transactionss(
        transactionType = TransactionsType.Withdraw,
        transactionAmountIn = 2000.00,
        transactionAmountOut = 300.40
    )
)


val totaMoneyIn: Double
    get() = transactionTypeList.sumOf { it.transactionAmountIn }
val totaMoneyOut: Double
    get() = transactionTypeList.sumOf { it.transactionAmountOut }





@Composable
@Preview
fun PreviewStatements() {
    StatementScreen(
        navigateBack = {}
    )
}