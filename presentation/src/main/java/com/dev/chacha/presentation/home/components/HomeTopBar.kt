package com.dev.chacha.presentation.home.components

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dev.chacha.presentation.R

@Composable
fun HomeTopBar(@StringRes title: Int = R.string.app_name) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = dimensionResource(id = R.dimen.margin_13)),
    ) {
        HorizontalImageTextView(
            drawable = R.drawable.sacco_logo,
            stringRes = title,
            modifier = Modifier.weight(1f),
            MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.margin_10)))

        Image(
            painter = painterResource(id = R.drawable.ic_home),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .size(30.dp),
        )
    }
}