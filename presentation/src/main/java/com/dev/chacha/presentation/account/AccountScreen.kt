package com.dev.chacha.presentation.account


import androidx.compose.animation.rememberSplineBasedDecay
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
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.account.component.BalanceCard
import com.dev.chacha.presentation.account.component.RowIconText
import com.dev.chacha.presentation.account.component.UserAccountVerticalCard
import com.dev.chacha.presentation.common.components.AppToolbar
import com.dev.chacha.presentation.piechart.PieChart


@Composable
fun AccountScreen(
    navigateBack: () -> Unit,
    onNavigateToStatement: ()-> Unit
) {
    val topAppBarState = rememberTopAppBarScrollState()
    val decayAnimationSpec = rememberSplineBasedDecay<Float>()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(decayAnimationSpec,topAppBarState)
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(
                        text = "Account",
                        fontSize = 20.sp,
                        maxLines = 2,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navigateBack() }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    Row(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(end = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = "Localized description"
                            )
                        }
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = "Localized description"
                            )
                        }
                    }

                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    scrolledContainerColor = MaterialTheme.colorScheme.background
                ),
                scrollBehavior = scrollBehavior
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            val balance = 20.00

            BalanceCard(
                username = "Stephen Chacha",
                contact = "0746656813",
                balance = "Ksh${balance}",
                drawable = R.drawable.ic_account_circle,
                onClick = {onNavigateToStatement()}
            )
            Spacer(modifier = Modifier.height(16.dp))

            UserAccountVerticalCard(
                loanBalance = "100",
                loanLimit = "1000",
                loanDueDate = "12 June",
                savingAmount = "1000"
            )
            Spacer(modifier = Modifier.height(16.dp))
            PieChart(
                data = mapOf(
                    Pair("Send Money", 15000),
                    Pair("Buy Goods", 12000),
                    Pair("Buy Airtime", 11000),
                    Pair("Pay Bill", 13440),
                    Pair("Others", 10000),
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            RowIconText(
                icon = R.drawable.ic_home,
                title = R.string.manage_pin,
                onClick = {}
            )
            Spacer(modifier = Modifier.height(16.dp))

            RowIconText(
                icon = R.drawable.ic_home,
                title = R.string.check_sacco_statement,
                onClick = {}
            )

        }

    }

}


@Composable
@Preview
fun AccountScreenPreview() {
    Surface(modifier = Modifier.fillMaxSize()) {
        AccountScreen(
            navigateBack = {},
            onNavigateToStatement = {}
        )
    }
}







