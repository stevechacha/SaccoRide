package com.dev.chacha.presentation.baybill.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.navigation.HomeAction
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun BillConfirm(
    accountName: String,
    accountNumber: String,
    businessNumber: String,
    amount: Double,
    date: String

) {
    val dateTimeFormat = SimpleDateFormat("MMM dd yyyy, h:mm a", Locale.getDefault())
    val currentDateTime = dateTimeFormat.format(Date())
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        val (card, button) = createRefs()

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .constrainAs(card) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 12.dp, top = 16.dp, bottom = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = Modifier.size(46.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_home),
                        contentDescription = "show error / success icon depending"
                    )
                }
                Text(text = "Message Show or Success")

                ConstraintLayout(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    val (payToColumn, infoColumn) = createRefs()

                    Column(
                        modifier = Modifier.constrainAs(payToColumn) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                        },
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center,
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

                    Column(
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .constrainAs(infoColumn) {
                                top.linkTo(parent.top)
                                start.linkTo(payToColumn.end, margin = 8.dp)
                                end.linkTo(button.start, margin = 8.dp)
                            }
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
                    }

                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .constrainAs(button) {
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                    ) {
                        Text(text = "Done")
                    }
                }
            }
        }
    }
}





@Composable
@Preview
fun PayBillConfirmScreen() {
    Column {
        payBill.apply {
            amount?.let {
                BillConfirm(
                    accountName = name,
                    accountNumber = payBill.accountNumber.toString(),
                    amount = payBill.amount!!.toDouble(),
                    date = "nnnnn",
                    businessNumber = payBill.businessNumber
                )
            }
        }
        Timber.tag(payBill.toString()).d("PayBill Passed in the bundle")
        println(payBill.amount)
    }
}