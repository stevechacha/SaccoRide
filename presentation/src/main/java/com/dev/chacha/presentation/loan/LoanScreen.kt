package com.dev.chacha.presentation.loan

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.components.ContinueButton
import com.dev.chacha.presentation.common.components.RideOutlinedTextField
import com.dev.chacha.presentation.loan.components.LoanCard
import com.dev.chacha.presentation.piecharts.PieCharts

@Composable
fun LoanScreen(
    navController: NavController,
) {
    var isPayLoanExpanded by remember { mutableStateOf(false) }
    var isRequestLoanExpanded by remember { mutableStateOf(false) }
    val topAppBarState = rememberTopAppBarScrollState()
    val decayAnimationSpec = rememberSplineBasedDecay<Float>()
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(decayAnimationSpec, topAppBarState)

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
            SmallTopAppBar(
                title = {
                    Text(
                        text = "Loans",
                        fontSize = 20.sp,
                        maxLines = 2,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            painter = painterResource(id = R.drawable.sacco_logo),
                            contentDescription = "Localized description",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    scrolledContainerColor = MaterialTheme.colorScheme.background
                ),
                scrollBehavior = scrollBehavior
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
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

            PieCharts(
                data = mapOf(
                    Pair("Pay Bill", 79095),
                    Pair("Withdrawal", 51810),
                    Pair("Send Money", 20360),
                    Pair("Buy Goods", 13200),
                    Pair("Loans", 12050),
                )
            )
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
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp,
            draggedElevation = 0.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6F),
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
                .clickable(onClick = onExpandToggle),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
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
                AnimatedVisibility(visible = expanded) {
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
                            onClick = { },
                            enable = true
                        )
                    }
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
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        onClick = {
            onExpandToggle()
        },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6F),
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
                .clickable(onClick = onExpandToggle),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = extraPaddingValues.coerceAtLeast(0.dp)),
                verticalArrangement = Arrangement.Center,
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
                AnimatedVisibility(visible = expanded) {
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
}

@Composable
@Preview
fun LoansssPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        LoanScreen(
            navController = rememberNavController(),
        )
    }

}

