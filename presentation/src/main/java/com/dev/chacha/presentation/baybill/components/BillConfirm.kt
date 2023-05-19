package com.dev.chacha.presentation.baybill.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.chacha.presentation.paybill.PayBill
import timber.log.Timber
import java.text.SimpleDateFormat
import java.time.temporal.TemporalAmount
import java.util.Date
import java.util.Locale

@Composable
fun BillCOnfirm(
    accountName: String,
    accountNumber: String,
    businessNumber: String,
    amount: Double,
    date: String

) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, top = 16.dp, bottom = 10.dp)
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                val dateTimeFormat = SimpleDateFormat("MMM dd yyyy, h:mm a", Locale.getDefault())
                val currentDateTime = dateTimeFormat.format(Date())

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.width(100.dp)
                    ) {

                        Text(
                            text = "Pay to:",
                            maxLines = 1,
                            textAlign = TextAlign.Start,
                            overflow = TextOverflow.Ellipsis
                        )

                        Text(
                            text = "Business No",
                            maxLines = 1,
                            textAlign = TextAlign.Start,
                            overflow = TextOverflow.Ellipsis
                        )

                        Text(
                            text = "Account No",
                            maxLines = 1,
                            textAlign = TextAlign.Start,
                            overflow = TextOverflow.Ellipsis
                        )

                        Text(
                            text = "Amount",
                            maxLines = 1,
                            textAlign = TextAlign.Start,
                            overflow = TextOverflow.Ellipsis
                        )

                        Text(
                            text = "Date/Time",
                            maxLines = 1,
                            textAlign = TextAlign.Start,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Column(
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = accountName,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = businessNumber.toString(),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = accountNumber.toString(),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = amount.toString(),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = currentDateTime.toString(),
                            maxLines = 5,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = date.toString(),
                            maxLines = 5,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                }

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Done")
                }
            }

        }

    }
    
}

@Composable
fun BillConfirmItem(
    payBill: PayBill,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, top = 16.dp, bottom = 10.dp)
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                val dateTimeFormat = SimpleDateFormat("MMM dd yyyy, h:mm a", Locale.getDefault())
                val currentDateTime = dateTimeFormat.format(Date())

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.width(100.dp)
                    ) {

                        Text(
                            text = "Pay to:",
                            maxLines = 1,
                            textAlign = TextAlign.Start,
                            overflow = TextOverflow.Ellipsis
                        )

                        Text(
                            text = "Business No",
                            maxLines = 1,
                            textAlign = TextAlign.Start,
                            overflow = TextOverflow.Ellipsis
                        )

                        Text(
                            text = "Account No",
                            maxLines = 1,
                            textAlign = TextAlign.Start,
                            overflow = TextOverflow.Ellipsis
                        )

                        Text(
                            text = "Amount",
                            maxLines = 1,
                            textAlign = TextAlign.Start,
                            overflow = TextOverflow.Ellipsis
                        )

                        Text(
                            text = "Date/Time",
                            maxLines = 1,
                            textAlign = TextAlign.Start,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Column(
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = payBill.name,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = payBill.businessNumber.toString(),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = payBill.accountNumber.toString(),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = payBill.amount.toString(),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = currentDateTime.toString(),
                            maxLines = 5,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = payBill.date.toString(),
                            maxLines = 5,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Done")
                }
            }

            Column {
                payBill.apply {
                    amount?.let {
                        BillCOnfirm(
                            accountName = name,
                            accountNumber = payBill.accountNumber.toString(),
                            amount = payBill.amount!!.toDouble(),
                            date = "",
                            businessNumber = payBill.businessNumber.toString()
                        )
                    }
                }
                Timber.tag(payBill.toString()).d("PayBill Passed in the bundle")
                println(payBill.amount)
            }


        }

    }


}

@Composable
@Preview
fun Previewwww() {
    BillConfirmItem(
        payBill = payBill
    )

}
