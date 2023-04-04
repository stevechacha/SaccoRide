package com.dev.chacha.presentation.loan

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dev.chacha.presentation.R

@Composable
fun LoanTextView(
    loanBalance: String,
    loanLimit: String,
    loanDueDate: String,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = stringResource(id = R.string.loan),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.labelSmall
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = stringResource(id = R.string.loan_balance),
                        style = MaterialTheme.typography.labelSmall
                    )
                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = stringResource(id = R.string.limit),
                        style = MaterialTheme.typography.labelSmall
                    )
                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = stringResource(id = R.string.due_date),
                        style = MaterialTheme.typography.labelSmall
                    )
                    Spacer(modifier = Modifier.height(5.dp))

                }

                Spacer(modifier = Modifier.width(10.dp))

                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = " ",
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.labelSmall

                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = loanBalance,
                        style = MaterialTheme.typography.labelSmall
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = loanLimit,
                        style = MaterialTheme.typography.labelSmall

                    )
                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = loanDueDate,
                        style = MaterialTheme.typography.labelSmall
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Divider(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp),
                    thickness = 1.dp
                )
            }

        }

    }
}