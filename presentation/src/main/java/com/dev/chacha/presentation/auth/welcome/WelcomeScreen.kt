package com.dev.chacha.presentation.auth.welcome

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.auth.welcome.components.FeatureCard
import com.dev.chacha.presentation.auth.welcome.components.WelcomeHeader
import com.dev.chacha.presentation.auth.welcome.components.getFeatures
import com.dev.chacha.presentation.common.components.AppOutlinedButton
import com.dev.chacha.presentation.common.components.ContinueButton

@Composable
fun WelcomeScreen(
    onSignUp: () -> Unit,
    onLogin: () -> Unit

) {
    val features = getFeatures()

    Scaffold(
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 10.dp)
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState(), true)
            ) {

                WelcomeHeader(
                    title = stringResource(R.string.welcome_title_one),
                    desc = stringResource(R.string.welcome_sub_one)
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.margin_16)))

                features.forEach {
                    FeatureCard(it)
                }
            }
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            ) {
                ContinueButton(
                    text = stringResource(id = R.string.sign_in),
                    onClick = onLogin,
                    enable = true
                )
                Spacer(modifier = Modifier.height(14.dp))
                AppOutlinedButton(
                    onClick = { onSignUp() },
                    text = R.string.sign_up
                )

            }
        }
    )

}

