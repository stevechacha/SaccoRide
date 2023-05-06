package com.dev.chacha.presentation.loan

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.components.AppToolbar
import com.dev.chacha.presentation.common.components.ContinueButton
import com.dev.chacha.presentation.common.components.SaccoOutlinedTextField
import com.dev.chacha.presentation.loan.components.LoanCard
import com.dev.chacha.presentation.loan.components.LoanTextView
import com.dev.chacha.presentation.loan.components.LoanVerticalCard

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun LoansScreen(
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
                .verticalScroll(rememberScrollState())
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LoanCard(
                loan = Loan(
                    balance = 10000000.0,
                    limit = 100000.0,
                    dueDate = "12 June,2023"
                )
            )
            RequestLoan()
            PayLoan()
            LoanTextView(
                loanBalance = "Ksh. 1ooo",
                loanLimit = "Ksh. 1ooo",
                loanDueDate = "12 June,2023"
            )
            ServiceLoan()

        }
    }

}

@Composable
fun ServiceLoan() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        LoanVerticalCard(
            drawable = R.drawable.ic_send_money,
            text = R.string.loan_statement,
            onItemClick = { }
        )

        LoanVerticalCard(
            drawable = R.drawable.ic_send_money,
            text = R.string.pay_loan,
            onItemClick = { }
        )

        LoanVerticalCard(
            drawable = R.drawable.ic_send_money,
            text = R.string.request_loan,
            onItemClick = { }
        )
    }

}


@Composable
fun PayLoan() {
    var expanded by remember { mutableStateOf(false) }

    val (mobileNumber, setMobileNumber) = rememberSaveable { mutableStateOf("") }
    val (amount, setAmount) = rememberSaveable { mutableStateOf("") }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    val extraPaddingValues by animateDpAsState(
        if (expanded) 24.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .clickable(onClick = { expanded = !expanded })
                .padding(bottom = extraPaddingValues.coerceAtLeast(0.dp)),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Pay Loan")
                Icon(icon, "contentDescription",
                    Modifier.clickable { expanded = !expanded })
            }
            Spacer(modifier = Modifier.height(10.dp))
            if (expanded) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {

                    SaccoOutlinedTextField(
                        value = amount,
                        onValueChange = {
                            setAmount(it)
                        },
                        keyboardType = KeyboardType.Phone,
                        hint = stringResource(id = R.string.amount),
                        supportText = stringResource(id = R.string.amount_support_text),

                        )

                    ContinueButton(
                        text = "Continue",
                        onClick = { /*TODO*/ }
                    )

                }

            }

        }
    }
}

@Composable
fun RequestLoan() {
    val (mobileNumber, setMobileNumber) = rememberSaveable { mutableStateOf("") }
    val (amount, setAmount) = rememberSaveable { mutableStateOf("") }

    var expanded by remember { mutableStateOf(false) }
    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    val extraPaddingValues by animateDpAsState(
        if (expanded) 24.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { expanded = !expanded }),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Request Loan")
                Icon(icon, "contentDescription",
                    Modifier.clickable { expanded = !expanded })
            }
            Spacer(modifier = Modifier.height(10.dp))
            if (expanded) {
                Column(
                    modifier = Modifier
                        .padding(bottom = extraPaddingValues.coerceAtLeast(0.dp)),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    SaccoOutlinedTextField(
                        value = amount,
                        onValueChange = {
                            setAmount(it)
                        },
                        keyboardType = KeyboardType.Phone,
                        hint = stringResource(id = R.string.amount),
                        supportText = stringResource(id = R.string.amount_support_text),

                        )
                    ContinueButton(
                        text = "Continue",
                        onClick = { /*TODO*/ }
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
    LoanScreen(
        navController = rememberNavController(),
        navigateToContact = {}
    )

}








