package com.dev.chacha.presentation.transaction

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.components.AppToolbar
import com.dev.chacha.presentation.common.theme.SaccoRideTheme



@Composable
fun TransactionScreen(
    onClickAction: () -> Unit
) {
    TransactionContent(
        onClickAction = onClickAction
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionContent(
    onClickAction: () -> Unit
) {
    Scaffold(
        topBar = {
            TransactToolBar()
        }
    ) { paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 8.dp, vertical = 8.dp)
        ) {

            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                   HorizontalCardItem(
                       drawable = R.drawable.ic_send_money,
                       text = R.string.send_money,
                       onItemClick = { onClickAction() }
                   )
                }
                item {
                    HorizontalCardItem(
                        drawable = R.drawable.ic_send_money,
                        text = R.string.buy_airtime,
                    onItemClick = { onClickAction() } )


                }
                item {
                    HorizontalCardItem(
                        drawable = R.drawable.ic_send_money,
                        text = R.string.send_money,
                        onItemClick = { onClickAction() }
                    )

                }

                item {
                    HorizontalCardItem(
                        drawable = R.drawable.ic_send_money,
                        text = R.string.send_money,
                        onItemClick = { onClickAction() }
                    )

                }
                item {
                    HorizontalCardItem(
                        drawable = R.drawable.ic_send_money,
                        text = R.string.send_money,
                        onItemClick = { onClickAction() }
                    )

                }
                item {
                    HorizontalCardItem(
                        drawable = R.drawable.ic_send_money,
                        text = R.string.send_money,
                        onItemClick = { onClickAction() }
                    )

                }

            }
        }

    }
}

@Composable
fun TransactToolBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.transaction),
            )
        },
        navigationIcon = {
            Image(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                colorFilter = ColorFilter.tint(
                    MaterialTheme.colorScheme.onBackground
                )

            )
        },
        actions = {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(end = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.home_icon),
                    contentDescription = "Edit",
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(CircleShape)
                        .size(40.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_send_money),
                    contentDescription = "Settings",
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(CircleShape)
                        .size(40.dp)
                )
            }
        },
        backgroundColor = MaterialTheme.colorScheme.background
    )

}

@Composable
@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES)
fun TransactionScreenPreview() {
    SaccoRideTheme {
        TransactionScreen(
            onClickAction = {

            }
        )
    }
}