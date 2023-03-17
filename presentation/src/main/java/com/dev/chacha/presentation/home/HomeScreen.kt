package com.dev.chacha.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.theme.SaccoRideTheme



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onClickAction: () -> Unit
) {
    Scaffold(
        topBar = {
            HomeToolbar()
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {

            BalanceCard()
            Spacer(modifier = Modifier.height(16.dp))
            HomeService()

        }

    }
}




@Composable
fun BalanceCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                    Image(
                        painter = painterResource(id = R.drawable.sessions_icon),
                        contentDescription = "Current Balance",
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape),
                        alignment = Alignment.Center,
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)

                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "Send money",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center

                    )

                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                    Image(
                        painter = painterResource(id = R.drawable.sessions_icon),
                        contentDescription = "Current Balance",
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(color = MaterialTheme.colorScheme.onSecondaryContainer)
                    )
                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = "PayBill",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    )

                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                    Image(
                        painter = painterResource(id = R.drawable.sessions_icon),
                        contentDescription = "Current Balance",
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(color = MaterialTheme.colorScheme.onSecondaryContainer)
                    )
                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = "Buy goods",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    )

                }

            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                    Image(
                        painter = painterResource(id = R.drawable.sessions_icon),
                        contentDescription = "Current Balance",
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(color = MaterialTheme.colorScheme.onSecondaryContainer)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "Buy airtime",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    )

                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                    Image(
                        painter = painterResource(id = R.drawable.sessions_icon),
                        contentDescription = "Current Balance",
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(color = MaterialTheme.colorScheme.onSecondaryContainer)
                    )
                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = "Deposit",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    )

                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                    Image(
                        painter = painterResource(id = R.drawable.sessions_icon),
                        contentDescription = "Current Balance",
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(color = MaterialTheme.colorScheme.onSecondaryContainer)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "Withdraw",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    )

                }

            }

        }

    }

}


@Composable
fun HomeToolbar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(start = 20.dp, end = 20.dp, top = 19.dp, bottom = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = "Welcome Back !")
            Text(text = "Stephen Chacha")
        }
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.sessions_icon),
            contentDescription = null
        )
    }
}

@Composable
fun Profile(user: User) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {
        val (image, text) = createRefs()
        Image(
            painter = painterResource(id = R.drawable.sessions_icon),
            contentDescription = "Profile",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(color = MaterialTheme.colorScheme.onSecondaryContainer)
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
        )
        Text(
            text = "Profile",
            modifier = Modifier
                .constrainAs(text) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(image.end)
                }
        )

        if (user.title != null) {
            Text(
                text = user.title.substring(0, 2),
                modifier = Modifier
                    .constrainAs(text) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(image.end)
                    }
            )
        } else {

            AsyncImage(
                model = user.title,
                contentDescription = "user",
                modifier = Modifier
                    .constrainAs(text) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(image.end)
                    }
            )


        }
    }
}

data class User(
    val title: String? = null,
    val icon: Int
)


@Composable
fun HomeService() {
    Card(
        modifier = Modifier
            .height(120.dp)
            .width(120.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            val (image, nameText) = createRefs()
            Image(
                painter = painterResource(id = R.drawable.home_icon),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .size(40.dp)
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
            Text(
                text = "Name",
                modifier = Modifier
                    .constrainAs(nameText) {
                        top.linkTo(image.bottom, margin = 8.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

        }

    }
}

@Composable
@Preview
fun HomeScreenPreview() {
    SaccoRideTheme {
        HomeScreen {
        }
    }
}