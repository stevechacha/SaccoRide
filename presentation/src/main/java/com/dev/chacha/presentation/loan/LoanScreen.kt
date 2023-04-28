package com.dev.chacha.presentation.loan

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.components.AppToolbar
import com.dev.chacha.presentation.common.components.ContinueButton
import com.dev.chacha.presentation.common.components.RideOutlinedTextField
import com.dev.chacha.presentation.common.components.SaccoOutlinedTextField
import com.dev.chacha.presentation.home.components.HomeTopBar
import com.dev.chacha.presentation.loan.components.LoanCard
import com.dev.chacha.presentation.loan.components.LoanTextView

@Composable
fun LoanScreen(
    navController: NavController
) {
    var isPayLoanExpanded by remember { mutableStateOf(false) }
    var isRequestLoanExpanded by remember { mutableStateOf(false) }

    LaunchedEffect(isPayLoanExpanded) {
        if (isPayLoanExpanded) {
            isRequestLoanExpanded = false
        }
    }

    LaunchedEffect(isRequestLoanExpanded) {
        if (isRequestLoanExpanded) {
            isPayLoanExpanded = false
        }
    }
    Scaffold(
        topBar = {
            HomeTopBar(
                title = R.string.loan
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            LoanCard(
                loan = Loan(
                    balance = 10000000.0,
                    limit = 100000.0,
                    dueDate = "12 June,2023"
                )
            )
            PayLoans(
                expanded = isPayLoanExpanded,
                onExpandToggle = { isPayLoanExpanded = !isPayLoanExpanded }
            )

            RequestLoans(
                expanded = isRequestLoanExpanded,
                onExpandToggle = { isRequestLoanExpanded = !isRequestLoanExpanded }
            )
            LoanTextView(
                loanBalance = "Ksh. 1ooo",
                loanLimit = "Ksh. 1ooo",
                loanDueDate = "12 June,2023"
            )
            Spacer(modifier = Modifier.height(10.dp))
            ServiceLoan()
        }
    }



}

@Composable
fun PayLoans(
    expanded: Boolean,
    onExpandToggle: () -> Unit
) {
    val (amount, setAmount) = rememberSaveable { mutableStateOf("") }

    val extraPaddingValues by animateDpAsState(
        if (expanded) 24.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            onExpandToggle()
        }

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .padding(bottom = extraPaddingValues.coerceAtLeast(0.dp)),
            verticalArrangement = Arrangement.Center
        ) {

            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.loan_icon),
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            onExpandToggle()
                        }
                    )
                    Text(
                        text = "Pay Loan",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Icon(
                    if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    "contentDescription"
                )
            }



            Spacer(modifier = Modifier.height(10.dp))
            if (expanded) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    RideOutlinedTextField(
                        value = amount,
                        onValueChange = { setAmount(it) },
                        keyboardType = KeyboardType.Phone,
                        hint = stringResource(id = R.string.amount),
                        supportText = stringResource(id = R.string.amount_support_text)
                    )

                    ContinueButton(
                        text = "Continue",
                        onClick = { /* TODO */ }
                    )
                }
            }
        }
    }
}

@Composable
fun RequestLoans(
    expanded: Boolean,
    onExpandToggle: () -> Unit
) {
    val (amount, setAmount) = rememberSaveable { mutableStateOf("") }

    val extraPaddingValues by animateDpAsState(
        if (expanded) 24.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            onExpandToggle()
        }
        ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .padding(bottom = extraPaddingValues.coerceAtLeast(0.dp)),
            verticalArrangement = Arrangement.Center
        ) {

            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.loan_icon),
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            onExpandToggle()
                        }
                    )
                    Text(
                        text = "Request Loan",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Icon(
                    if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    "contentDescription"
                )
            }



            Spacer(modifier = Modifier.height(10.dp))
            if (expanded) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    RideOutlinedTextField(
                        value = amount,
                        onValueChange = { setAmount(it) },
                        keyboardType = KeyboardType.Phone,
                        hint = stringResource(id = R.string.amount),
                        supportText = stringResource(id = R.string.amount_support_text)
                    )

                    ContinueButton(
                        text = "Continue",
                        onClick = { /* TODO */ }
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun LoansssPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        LoanScreen(
            navController = rememberNavController()
        )
    }

}

