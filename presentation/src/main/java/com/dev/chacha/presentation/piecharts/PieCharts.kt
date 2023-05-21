package com.dev.chacha.presentation.piecharts

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.chacha.presentation.common.theme.Blue
import com.dev.chacha.presentation.common.theme.Brutalista
import com.dev.chacha.presentation.common.theme.Cyan
import com.dev.chacha.presentation.common.theme.DeepRed
import com.dev.chacha.presentation.common.theme.LightGreen
import com.dev.chacha.presentation.common.theme.LightYellow
import com.dev.chacha.presentation.common.theme.Purple1
import com.dev.chacha.presentation.common.theme.Purple200
import com.dev.chacha.presentation.common.theme.Purple500
import com.dev.chacha.presentation.common.theme.Purple700
import com.dev.chacha.presentation.common.theme.Teal200
import com.dev.chacha.presentation.common.theme.purple
import com.dev.chacha.presentation.piechart.components.DetailsPieChart

@Composable
fun PieCharts(
    data: Map<String, Int>,
    radiusOuter: Dp = 90.dp,
    chartBarWidth: Dp = 20.dp,
) {

    val totalSum = data.values.sum()
    val floatValue = mutableListOf<Float>()

    data.values.forEachIndexed { index, values ->
        floatValue.add(index, 360 * values.toFloat() / totalSum.toFloat())
    }

    // add the colors as per the number of data(no. of pie chart entries)
    // so that each data will get a color
    val colors = listOf(LightGreen, Cyan, DeepRed, LightYellow, purple,)

    var lastValue = 0f

    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6F),
            ),
            shape = RoundedCornerShape(5.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp, vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Box(
                    modifier = Modifier.size(radiusOuter),
                    contentAlignment = Alignment.Center
                ) {
                    Canvas(
                        modifier = Modifier
                            .size(radiusOuter * 1.2f)
                            .align(Alignment.Center)
                    ) {
                        // draw each Arc for each data entry in Pie Chart
                        floatValue.forEachIndexed { index, value ->
                            drawArc(
                                color = colors[index],
                                lastValue,
                                value,
                                useCenter = false,
                                style = Stroke(chartBarWidth.toPx(), cap = StrokeCap.Butt)
                            )
                            lastValue += value
                        }
                    }

                    Text(
                        text = "Total\n$totalSum\nKSH",
                        style = TextStyle(
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onBackground,
                            textAlign = TextAlign.Center,
                            fontFamily = Brutalista,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier.align(Alignment.Center),
                        maxLines = 3
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp)
                ) {
                    // To see the data in a more structured way
                    // Compose Function in which Items are showing data
                    DetailsPieCharts(
                        data = data,
                        colors = colors,
                        totalSum = totalSum
                    )
                }
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(5.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6F),
            )

        ) {
            DetailPieChart(
                data = data,
                colors = colors
            )

        }



    }


}



@Composable
@Preview
fun PreviewsPieChart() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            PieCharts(
                data = mapOf(
                    Pair("Pay Bill", 79095),
                    Pair("Withdrawal", 51810),
                    Pair("Send Money", 20360),
                    Pair("Buy Goods", 13200),
                    Pair("Loans", 12050),
                )
            )

        }


    }
}