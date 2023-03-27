package com.dev.chacha.presentation.loan

import android.os.Build
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.activity.MainActivity
import com.dev.chacha.presentation.common.components.AppToolbar
import com.dev.chacha.presentation.common.navigation.Graph
import com.dev.chacha.presentation.common.theme.SaccoRideTheme
import com.dev.chacha.presentation.fingerprint.BiometricChecker
import com.dev.chacha.presentation.transaction.HorizontalCardItem

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
                .padding(12.dp)
            ,
            horizontalAlignment = Alignment.CenterHorizontally
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
                                text = "LOAN",
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = "Balance:",
                                fontSize = 14.sp,
                            )
                            Spacer(modifier = Modifier.height(5.dp))

                            Text(
                                text = "Limit:",
                                fontSize = 14.sp,
                                )
                            Spacer(modifier = Modifier.height(5.dp))

                            Text(
                                text = "Due Date:",
                                fontSize = 14.sp,
                                )
                            Spacer(modifier = Modifier.height(5.dp))

                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                text = " ",
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Ksh 0.00",
                                fontSize = 14.sp,
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = "Ksh 0.00",
                                fontSize = 14.sp,

                                )
                            Spacer(modifier = Modifier.height(5.dp))

                            Text(
                                text = "Ksh 0.00",
                                fontSize = 14.sp,
                            )
                        }
                    }

                }

            }
            Spacer(modifier = Modifier.height(20.dp))

            LoansService(
                navController = navController
            )
        }
    }

}


@Composable
fun LoansService(
    navController: NavController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        LoanVerticalCard(
            drawable = R.drawable.ic_send_money,
            text = R.string.loan_statement,
            onItemClick = {  }
        )


        LoanVerticalCard(
            drawable = R.drawable.ic_send_money,
            text = R.string.request_loan,
            onItemClick = {  }
        )

        LoanVerticalCard(
            drawable = R.drawable.ic_send_money,
            text = R.string.pay_loan,
            onItemClick = {  }
        )

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






