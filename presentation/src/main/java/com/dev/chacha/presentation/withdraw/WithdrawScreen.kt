package com.dev.chacha.presentation.withdraw

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.pychart.PieChart

@Composable
fun WithdrawScreen() {
    Scaffold (
        topBar = {
            WithdrawToolbar()
        }
    ){ paddingValues->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
            verticalArrangement = Arrangement.Center
        ) {
            PieChart(
                data = mapOf(
                    Pair("Sample-1", 150),
                    Pair("Sample-2", 120),
                    Pair("Sample-3", 110),
                    Pair("Sample-4", 170),
                    Pair("Sample-5", 120),
                )
            )


        }
    }
}

@Composable
fun WithdrawToolbar() {
    SmallTopAppBar(
        title = {
                Text(text = stringResource(id = R.string.withdraw))
        },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.icon_arrow_back),
                    contentDescription = null
                )
            }
        }
    )
}