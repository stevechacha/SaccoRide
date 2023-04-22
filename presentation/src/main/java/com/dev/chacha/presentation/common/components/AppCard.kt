package com.dev.chacha.presentation.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.common.util.MontserratBold

@Composable
fun AppCard(
    modifier: Modifier = Modifier,
    sponsorsLogos: List<String>
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    color = Color.Green,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(horizontal = 30.dp, vertical = 10.dp)
                .testTag("sponsors_section"),
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.user_profile),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    lineHeight = 25.sp,
                    fontFamily = MontserratBold
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(30.dp))

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(coil.base.R.drawable.ic_100tb)
                    .build(),
                placeholder = painterResource(coil.base.R.drawable.ic_100tb),
                contentDescription = stringResource(id = R.string.user_profile)
            )

            FlowRow(
                modifier = Modifier.padding(top = 16.dp),
                mainAxisAlignment = MainAxisAlignment.SpaceEvenly,
                mainAxisSize = SizeMode.Expand,
                mainAxisSpacing = 16.dp,
                crossAxisSpacing = 16.dp
            ) {
                sponsorsLogos.forEach { sponsorLogo ->
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(sponsorLogo)
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(coil.base.R.drawable.ic_100tb),
                        contentDescription = stringResource(id = R.string.user_profile)
                    )
                }
            }
        }
    }
}