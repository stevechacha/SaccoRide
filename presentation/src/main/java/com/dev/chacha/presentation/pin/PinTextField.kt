package com.dev.chacha.presentation.pin

import android.content.Context
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.util.TestTags
import com.google.common.io.Files.append


@Composable
fun PinTextField(
    value: String,
    hint: String = "",
    maxLength: Int = 4,
    error: String = "",
    singleLine: Boolean = true,
    maxLines: Int = 1,
    keyboardType: KeyboardType = KeyboardType.Number,
    isPasswordToggleDisplayed: Boolean = keyboardType == KeyboardType.NumberPassword,
    isPasswordVisible: Boolean = false,
    onPasswordToggleClick: (Boolean) -> Unit = {},
    onValueChange: (String) -> Unit,
    focusRequester: FocusRequester = FocusRequester(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }


) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val textFieldColors = TextFieldDefaults.textFieldColors(
        containerColor = Color.Transparent,
        cursorColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        errorIndicatorColor = Color.Transparent,
        disabledTextColor = Color.Transparent,

    )

    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)) {
        TextField(
            value = value,
            onValueChange = {newValue ->
                if (newValue.length <= maxLength) {
                onValueChange(newValue) }
            },
            maxLines = maxLines,
            placeholder = {
                Text(
                    text = hint,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth().padding(start = 50.dp,).align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.labelSmall,
                    color = LocalContentColor.current.copy(alpha = ContentAlpha.medium),
                    fontWeight = FontWeight.Bold,
                )
            },
            isError = error != "",
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            visualTransformation = if (!isPasswordVisible && isPasswordToggleDisplayed) {
                customVisualTransformation(value, context)
            } else {
                VisualTransformation.None
            },
            singleLine = singleLine,
            trailingIcon = if (isPasswordToggleDisplayed) {
                val icon: @Composable () -> Unit = {
                    IconButton(onClick = {
                        onPasswordToggleClick(!isPasswordVisible)
                    }, modifier = Modifier.semantics {
                        testTag = TestTags.PASSWORD_TOGGLE
                    }) {
                        Icon(
                            imageVector = if (isPasswordVisible) {
                                Icons.Filled.VisibilityOff
                            } else {
                                Icons.Filled.Visibility
                            }, tint = MaterialTheme.colorScheme.primary, contentDescription = if (isPasswordVisible) {
                                stringResource(id = R.string.password_visible_content_description)
                            } else {
                                stringResource(id = R.string.password_hidden_content_description)
                            }, modifier = Modifier.padding(end = 10.dp)
                        )
                    }
                }
                icon
            } else null,
            interactionSource = interactionSource,
            modifier = Modifier
                .fillMaxWidth()
                .semantics {
                    testTag = TestTags.STANDARD_TEXT_FIELD
                }
                .focusRequester(focusRequester = focusRequester),
            colors = textFieldColors,
            readOnly = true,
            textStyle = TextStyle(textAlign = TextAlign.Center),
        )
        keyboardController?.hide()

        if (error.isNotEmpty()) {
            Text(
                text = error,

                textAlign = TextAlign.End, modifier = Modifier.fillMaxWidth()

            )

        }
    }

}


private fun customVisualTransformation(text: String, context: Context): VisualTransformation {
    val obfuscatedText = buildAnnotatedString {
        repeat(text.length) {
            val dotSize = 28.sp  // Adjust the size based on your preference
            val dotFont = FontFamily.SansSerif
            withStyle(style = SpanStyle(fontSize = dotSize, fontFamily = dotFont)) {
                append("*")
            }
        }
    }
    return VisualTransformation { TransformedText(obfuscatedText, OffsetMapping.Identity) }
}

