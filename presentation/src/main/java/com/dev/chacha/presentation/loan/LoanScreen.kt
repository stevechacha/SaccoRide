package com.dev.chacha.presentation.loan

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.components.AppToolbar
import com.dev.chacha.presentation.common.theme.SaccoRideTheme

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun LoanScreen(
    navController: NavController
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            AppToolbar(
                title = "Loan",
                showBackArrow = true
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoanTextView(
                loanBalance = "Ksh. 1ooo",
                loanLimit = "Ksh. 1ooo",
                loanDueDate = "12 June,2023"
            )

            Spacer(modifier = Modifier.height(20.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item {
                    LoanVerticalCard(
                        drawable = R.drawable.ic_send_money,
                        text = R.string.loan_statement,
                        onItemClick = { }
                    )
                }

                item {
                    LoanVerticalCard(
                        drawable = R.drawable.ic_send_money,
                        text = R.string.pay_loan,
                        onItemClick = { }
                    )
                }

                item {
                    LoanVerticalCard(
                        drawable = R.drawable.ic_send_money,
                        text = R.string.request_loan,
                        onItemClick = { }
                    )

                }
            }

        }
    }

}


@RequiresApi(Build.VERSION_CODES.P)
@Composable
@Preview
fun LoanScreenPreview() {
    SaccoRideTheme {
        LoanScreen(navController = rememberNavController())
    }
}






