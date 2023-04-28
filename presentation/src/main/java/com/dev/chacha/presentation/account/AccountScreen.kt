package com.dev.chacha.presentation.account


import androidx.compose.animation.rememberSplineBasedDecay
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.account.component.AccountCard
import com.dev.chacha.presentation.account.component.BalanceCard
import com.dev.chacha.presentation.account.component.RowIconText
import com.dev.chacha.presentation.account.component.UserAccountVerticalCard
import com.dev.chacha.presentation.auth.reset_password.ResetPasswordScreen
import com.dev.chacha.presentation.common.navigation.AccountAction
import com.dev.chacha.presentation.piechart.PieChart


@Composable
fun AccountScreen(
    navigateBack: () -> Unit,
    onNavigateToStatement: () -> Unit,
    navigateToSettings: ()-> Unit,
    navigateToAboutSaccoRide : ()-> Unit,
    navigateToNotification: ()-> Unit,
    navigateToManagePin: ()-> Unit,
    navigateToManagePassword: ()-> Unit,
    navController: NavController
) {
    val topAppBarState = rememberTopAppBarScrollState()
    val decayAnimationSpec = rememberSplineBasedDecay<Float>()
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(decayAnimationSpec, topAppBarState)
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
                            painter = painterResource(id = R.drawable.sacco_logo),
                            contentDescription = "Localized description",
                            tint = MaterialTheme.colorScheme.onBackground
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
        Box(modifier = Modifier.fillMaxWidth()) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = paddingValues
                ) {
                    item {
                        BalanceCard(
                            username = "Stephen Chacha",
                            contact = "0746656813",
                            balance = "Ksh. 2000",
                            drawable = R.drawable.ic_account_circle,
                            onClick = { onNavigateToStatement() }
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                    }
                    items(accountOptions) { accountItem ->
                        AccountCard(
                            title = accountItem.title,
                            icon = accountItem.icon,
                            onClick = { accountOptions ->
                                when (accountOptions) {
                                    "View Statement"->{
                                        navController.navigate(AccountAction.StatementDetail.route)

                                    }
                                    "Notification"->{
                                        navigateToNotification()
                                    }
                                    "Manage your PIN" -> {
                                        navigateToManagePin()
                                    }
                                    "Change your password"->{
                                        navigateToManagePassword()
                                    }
                                    "Setting"->{
                                        navigateToSettings()
                                    }
                                    "About SaccoRide"->{
                                        navigateToAboutSaccoRide()
                                    }
                                    "Sign Out"->{

                                    }
                                }

                            },
                            )
                    }


                }

            }

        }

}

data class AccountItem(
    val title: String,
    val icon: Int
)

private val accountOptions = listOf(
    AccountItem(
        title = "View Statement",
        icon = R.drawable.insights_icon
    ),
    AccountItem(
        title = "Notification",
        icon = R.drawable.notifications
    ),
    AccountItem(
        title = "Manage your PIN",
        icon = R.drawable.sim_card
    ),
    AccountItem(
        title = "Change your password",
        icon = R.drawable.password
    ),
    AccountItem(
        title = "Setting",
        icon = R.drawable.settings_icons
    ),
    AccountItem(
        title = "About SaccoRide",
        icon = R.drawable.apps_outage
    ),
    AccountItem(
        title = "Sign Out",
        icon = R.drawable.power_rounded
    )
)


@Composable
@Preview
fun AccountScreenPreview() {
    Surface(modifier = Modifier.fillMaxSize()) {
        AccountScreen(
            navigateBack = {},
            onNavigateToStatement = {},
            navigateToAboutSaccoRide = {},
            navigateToNotification = {},
            navigateToSettings = {},
            navigateToManagePassword = {},
            navigateToManagePin = {},
            navController = rememberNavController()
        )
    }
}







