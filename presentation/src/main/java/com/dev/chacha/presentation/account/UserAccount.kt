package com.dev.chacha.presentation.account

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.chacha.presentation.R

@Composable
fun UserAccountVerticalCard(
    loanBalance: String,
    loanLimit: String,
    loanDueDate: String,
    savingAmount: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
                ,
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.savings),
                    fontSize = 16.sp,


                    )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = savingAmount,
                    fontSize = 16.sp,

                    )

            }
            Spacer(modifier = Modifier.height(16.dp))
            Divider(
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)

            )
            Spacer(modifier = Modifier.height(5.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = stringResource(id = R.string.loan),
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 14.sp,

                        )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = stringResource(id = R.string.loan_balance),
                        fontSize = 12.sp,
                    )
                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = stringResource(id = R.string.limit),
                        fontSize = 12.sp,
                    )
                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = stringResource(id = R.string.due_date),
                        fontSize = 12.sp,
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = " ",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 14.sp,

                        )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = loanBalance,
                        fontSize = 12.sp,
                        textAlign = TextAlign.End
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = loanLimit,
                        fontSize = 12.sp,
                        textAlign = TextAlign.End


                    )
                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = loanDueDate,
                        fontSize = 12.sp,
                        textAlign = TextAlign.End
                    )
                }

                Divider(
                    modifier = Modifier
                        .height(90.dp)
                        .width(1.dp),
                    thickness = 1.dp
                )

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = stringResource(id = R.string.loan),
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 14.sp,

                        )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = stringResource(id = R.string.loan_balance),
                        fontSize = 12.sp,
                    )
                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = stringResource(id = R.string.limit),
                        fontSize = 12.sp,
                    )
                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = stringResource(id = R.string.due_date),
                        fontSize = 12.sp,
                    )

                }
                Spacer(modifier = Modifier.width(16.dp))


                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = " ",
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = loanBalance,
                        fontSize = 12.sp,
                        textAlign = TextAlign.End

                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = loanLimit,
                        fontSize = 12.sp,
                        textAlign = TextAlign.End


                    )
                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = loanDueDate,
                        fontSize = 12.sp,
                        textAlign = TextAlign.End

                    )
                }
            }


        }

    }
}