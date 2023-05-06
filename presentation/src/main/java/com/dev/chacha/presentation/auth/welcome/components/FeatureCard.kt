package com.dev.chacha.presentation.auth.welcome.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.theme.Brutalista


@Composable
fun FeatureCard(feature: Feature) {
    Row(modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.margin_10))) {
        Icon(
            painter = painterResource(id = feature.iconRes),
            contentDescription = null,
            modifier = Modifier.size(40.dp),
            tint = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = feature.title,
                fontFamily = Brutalista,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = feature.desc,
                fontFamily = Brutalista,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            )
        }
    }
}

data class Feature(val title: String, val desc: String, val iconRes: Int)

@Composable
fun getFeatures(): List<Feature> = listOf(
    Feature(
        title = stringResource(R.string.intro_feature_one_title),
        desc = stringResource(R.string.intro_feature_one_desc),
        iconRes = R.drawable.insights_icon
    ),
    Feature(
        title = stringResource(R.string.intro_feature_two_title),
        desc = stringResource(R.string.intro_feature_two_desc),
        iconRes = R.drawable.insights_icon
    ),
    Feature(
        title = stringResource(R.string.intro_feature_three_title),
        desc = stringResource(R.string.intro_feature_three_desc),
        iconRes = R.drawable.icon_arrow_back
    )
)