package com.dev.chacha.presentation.pin

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.outlined.Backspace
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.activity.MainActivity
import com.dev.chacha.presentation.common.theme.Brutalista
import com.dev.chacha.presentation.fingerprint.Biometric
import kotlinx.coroutines.delay

const val pinSize = 4
var password = "5491" //sample password

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun PinLockScreen(
    onClickAction: () -> Unit,
    navController: NavController
) {
    val inputPin = remember { mutableStateListOf<Int>() }
    val error = remember { mutableStateOf<String>("") }
    val showSuccess = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val isFingerprintEnabled = remember { mutableStateOf(false) }




    if (inputPin.size == 4) {
        LaunchedEffect(true) {
            delay(300)

            if (inputPin.joinToString("") == password) {
                showSuccess.value = true
                error.value = "Validating..."
                delay(200)
                onClickAction()
            } else {
                inputPin.clear()
                error.value = "Wrong pin, Please retry!"
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.background)
                        .size(80.dp)
                        .clip(CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profile_icon),
                        contentDescription = "profile image",
                        modifier = Modifier
                            .size(40.dp),
                        alignment = Alignment.Center,
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
                    )

                }

                Spacer(modifier = Modifier.height(16.dp))
                val name = "Stephen Chacha"
                Text(
                    text = name.uppercase(),
                    style = MaterialTheme.typography.labelSmall
                )
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = stringResource(id = R.string.enter_pin),
                    style = MaterialTheme.typography.labelSmall

                )

                Spacer(modifier = Modifier.height(30.dp))

                if (showSuccess.value) {
                    LottieLoadingView(
                        context = context,
                        file = "success.json",
                        iterations = 1,
                        modifier = Modifier.size(100.dp)
                    )
                } else {
                    Row {
                        (0 until pinSize).forEach {
                            Icon(
                                imageVector = if (inputPin.size > it) Icons.Default.Circle else Icons.Outlined.Circle,
                                contentDescription = it.toString(),
                                modifier = Modifier
                                    .padding(8.dp)
                                    .size(30.dp),
//                                tint = Color.Black
                            )
                        }
                    }
                }
                Text(
                    text = error.value,
                    color = MaterialTheme.colorScheme.error,
                )

                Spacer(modifier = Modifier.height(30.dp))
            }

            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 30.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    (1..3).forEach {
                        PinKeyItem(
                            onClick = { inputPin.add(it) }
                        ) {
                            Text(
                                text = it.toString(),
                                fontSize = 22.sp,
                                fontFamily = Brutalista,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    (4..6).forEach {
                        PinKeyItem(
                            onClick = { inputPin.add(it) }
                        ) {
                            Text(
                                text = it.toString(),
                                fontSize = 22.sp,
                                fontFamily = Brutalista,
                                fontWeight = FontWeight.Bold

                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    (7..9).forEach {
                        PinKeyItem(
                            onClick = { inputPin.add(it) }
                        ) {
                            Text(
                                text = it.toString(),
                                fontSize = 22.sp,
                                fontFamily = Brutalista,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(4.dp),
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Success",
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {
//                                onClickAction()
                            }
                    )
                    PinKeyItem(
                        onClick = { inputPin.add(0) },
                    ) {
                        Text(
                            text = "0",
                            modifier = Modifier.padding(4.dp),
                            fontSize = 22.sp,
                            fontFamily = Brutalista,
                            fontWeight = FontWeight.Bold

                        )
                    }
                    if (inputPin.isEmpty()) {
                        Icon(
                            imageVector = Icons.Default.Fingerprint,
                            contentDescription = "Success",
                            modifier = Modifier
                                .size(25.dp)
                                .clickable {
                                    Biometric(
                                        context as MainActivity,
                                        navController,
                                        context,

                                        ).authenticate()
//                                    navController.navigate(AuthScreen.Biometric.route)

                                }
                        )

                    } else {
                        Icon(
                            imageVector = Icons.Outlined.Backspace,
                            contentDescription = "Clear",
                            modifier = Modifier
                                .size(25.dp)
                                .clickable {
                                    if (inputPin.isNotEmpty()) {
                                        inputPin.removeLast()
                                    }
                                }
                        )

                    }

                }
            }
        }
//        BiometricChecker(context as MainActivity,navController,context as MainActivity).authenticate()

    }
}

@Composable
fun LottieLoadingView(
    context: Context,
    file: String,
    modifier: Modifier = Modifier,
    iterations: Int = 10
) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.Asset(file))
    LottieAnimation(
        composition = composition,
        modifier = modifier.defaultMinSize(300.dp),
        iterations = iterations
    )
}

@Composable
fun PinKeyItem(
    onClick: () -> Unit,
    modifier: Modifier = Modifier.padding(8.dp),
    enabled: Boolean = true,
    shape: Shape = shapes.small.copy(CornerSize(percent = 50)),
    tonalElevation: Dp = 4.dp,
    color: Color = Color.Transparent,
    border: BorderStroke? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit,
) {
    Surface(
        modifier = modifier,
        shape = shape,
        tonalElevation = tonalElevation,
        onClick = onClick,
        interactionSource = interactionSource,
        border = border,
        color = color,
        enabled = enabled,
    ) {
        val absoluteElevation = LocalAbsoluteTonalElevation.current + tonalElevation

        CompositionLocalProvider(
            LocalAbsoluteTonalElevation provides absoluteElevation
        ) {
            ProvideTextStyle(
                MaterialTheme.typography.displaySmall
            ) {
                Box(
                    modifier = modifier
                        .defaultMinSize(minWidth = 64.dp, minHeight = 64.dp)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = rememberRipple(),
                            enabled = enabled,
                            role = Role.Button,
                            onClick = onClick
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    content()
                }

            }

        }
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
@Preview
fun PinLockPreview() {
    PinLockScreen(
        onClickAction = { },
        navController = rememberNavController()
    )

}