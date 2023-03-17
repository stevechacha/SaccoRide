package com.dev.chacha.presentation.transaction

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
            AppToolbar(
                title ="Transaction"
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 10.dp, vertical = 16.dp)
        ) {


            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item {
                    SendMoneyCard()
                    Spacer(modifier = Modifier.width(10.dp))

                }
                item {
                    WithdrawCard()
                    Spacer(modifier = Modifier.width(10.dp))

                }
                item {
                    BuyAirtimeCard()
                    Spacer(modifier = Modifier.width(10.dp))

                }

                item {
                    BuyGoodsCard()
                    Spacer(modifier = Modifier.width(10.dp))

                }
                item {
                    PayBillCard()
                    Spacer(modifier = Modifier.width(10.dp))

                }
                item {
                    DepositCard()
                    Spacer(modifier = Modifier.width(10.dp))

                }

            }
        }



    }
}

@Composable
fun SubmitButton(
    onClickAction: () -> Unit
) {
    val submitButtonColor = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.onBackground,
        contentColor = MaterialTheme.colorScheme.onPrimary
    )
    Button(
        onClick = onClickAction,
        modifier = Modifier.padding(16.dp),
        colors = submitButtonColor
    ) {
        Text(
            text = stringResource(id = R.string.submit),
            modifier = Modifier.padding(5.dp)
        )
    }
}

@Composable
fun UserProfile(
    userProfile: UserProfile
) {
    Column (modifier = Modifier.padding(horizontal = 24.dp)){
        AsyncImage(
            model = userProfile.imageUrl,
            contentDescription =
            stringResource(id = R.string.user_profile),
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
                .size(100.dp)
                .clip(CircleShape),
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop
        )

        Row {
            Text(text = userProfile.name)

            IconButton(onClick = { /*TODO*/ }) {
                   Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit"
                )
            }

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings"
                )
            }
        }

    }
}

data class UserProfile(
    val imageUrl: String,
    val name: String
)

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SubmitButtonPreview() {
    SaccoRideTheme {
        SubmitButton(
            onClickAction = { }
        )
    }
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