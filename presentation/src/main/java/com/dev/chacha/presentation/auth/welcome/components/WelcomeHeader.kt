package com.dev.chacha.presentation.auth.welcome.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.chacha.presentation.common.theme.Brutalista


@Composable
fun WelcomeHeader(title: String, desc: String) {
    Column {
        Text(
            text = title,
            fontFamily = Brutalista,
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp,
            modifier = Modifier.testTag("welcome_header_title")
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = desc,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            modifier = Modifier.testTag("welcome_header_description"),
            fontFamily = Brutalista
        )
    }
}