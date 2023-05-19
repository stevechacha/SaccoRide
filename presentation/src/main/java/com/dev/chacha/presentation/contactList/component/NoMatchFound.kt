package com.dev.chacha.presentation.contactList.component

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dev.chacha.presentation.util.LottieLoader


@Composable
fun NoMatchFound(@RawRes lottie: Int) {
    Box(modifier = Modifier.fillMaxHeight(0.9F).fillMaxWidth(), contentAlignment = Alignment.Center) {
        LottieLoader(lottieFile = lottie, modifier = Modifier.size(250.dp))
    }
}