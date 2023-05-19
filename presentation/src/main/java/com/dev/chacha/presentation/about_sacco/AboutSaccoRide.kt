package com.dev.chacha.presentation.about_sacco

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.components.AppToolbar
import com.dev.chacha.presentation.common.theme.Brutalista

@Composable
fun AboutSaccoRide(
    navigateBack: ()-> Unit
) {
    Scaffold(
        topBar = {
            AppToolbar(
                title = "About SaccoRide ",
                showBackArrow = true,
                navigateBack = navigateBack
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
        ) {
            Text(
                text = "SaccoRide",
                modifier = Modifier.padding(bottom = 16.dp),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(R.string.about_sacco_ride),
                modifier = Modifier.padding(bottom = 16.dp),
                fontSize = 12.sp,
                fontWeight = FontWeight.Thin,
                fontFamily = Brutalista

            )
            PrivacyPolicy()
            TermsAndConditions()
            ServiceCharter()


        }
    }
}

@Composable
fun PrivacyPolicy() {
    Text(
        text = "Privacy Policy",
        modifier = Modifier.padding(bottom = 8.dp),
        fontWeight = FontWeight.Bold
    )
    Text(
        text = stringResource(R.string.sacco_privacy_policy),
        modifier = Modifier.padding(bottom = 16.dp),
        fontSize = 12.sp,
        fontWeight = FontWeight.Thin,
        fontFamily = Brutalista
    )
}

@Composable
fun TermsAndConditions() {
    Text(
        text = "Terms and Conditions",
        modifier = Modifier.padding(bottom = 8.dp),
        fontWeight = FontWeight.Bold

    )
    Text(
        text = stringResource(R.string.sacco_term_and_condition),
        modifier = Modifier.padding(bottom = 16.dp),
        fontSize = 12.sp,
        fontWeight = FontWeight.Thin,
        fontFamily = Brutalista
    )
}

@Composable
fun ServiceCharter() {
    Text(
        text = "Service Charter",
        modifier = Modifier.padding(bottom = 8.dp),
        fontWeight = FontWeight.Bold
    )
    Text(
        text = stringResource(R.string.sacco_service_charter),
        modifier = Modifier.padding(bottom = 16.dp),
        fontSize = 12.sp,
        fontWeight = FontWeight.Thin,
        fontFamily = Brutalista
    )
}


