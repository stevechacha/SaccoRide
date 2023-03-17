package com.dev.chacha.presentation.auth.welcome

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.theme.PrimaryColor
import kotlin.system.exitProcess





@Composable
fun WelcomeScreen(
    onSignUp: () -> Unit,
    onLogin: () -> Unit
) {
    WelcomeContent(
        onSignUp = onSignUp,
        onLogin = onLogin
    )

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun WelcomeContent(
    onSignUp: () -> Unit,
    onLogin: () -> Unit
) {
    Dialog(
        onDismissRequest = {
            exitProcess(0)
        },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false
        )
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding(),
                contentAlignment = Alignment.TopStart
            ) {


                Image(
                    painter = painterResource(id = R.drawable.main_icon),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp, end = 24.dp),
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 24.sp,
                        text = "Make your shopping enjoyable with us"
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    Button(
                        onClick = {onLogin()},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp, end = 8.dp),
                        shape = RoundedCornerShape(8)

                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            textAlign = TextAlign.Center,
                            text = "Sign In"
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    OutlinedButton(
                        onClick = {onSignUp()},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp, end = 8.dp),
                        colors = ButtonDefaults.outlinedButtonColors(),
                        border = BorderStroke(1.dp, PrimaryColor),
                        shape = RoundedCornerShape(8)
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            textAlign = TextAlign.Center,
                            text = "Sign Up"
                        )
                    }
                    Spacer(modifier = Modifier.height(42.dp))
                }
            }
        }
    }

}


@Composable
@Preview
fun WelcomeScreenPreview() {
        WelcomeScreen(onSignUp = { /*TODO*/ }) {

    }

}
