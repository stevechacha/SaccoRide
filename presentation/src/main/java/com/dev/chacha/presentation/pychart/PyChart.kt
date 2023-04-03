package com.dev.chacha.presentation.pychart

import android.graphics.Paint
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.chacha.presentation.common.theme.*
import com.dev.chacha.presentation.common.util.MontserratMedium


@Composable
fun PieChart(
    data: Map<String, Int>,
    radiusOuter: Dp = 90.dp,
    chartBarWidth: Dp = 20.dp,
    animDuration: Int = 1000,
) {

    val totalSum = data.values.sum()
    val floatValue = mutableListOf<Float>()

    // To set the value of each Arc according to
    // the value given in the data, we have used a simple formula.
    // For a detailed explanation check out the Medium Article.
    // The link is in the about section and readme file of this GitHub Repository
    data.values.forEachIndexed { index, values ->
        floatValue.add(index, 360 * values.toFloat() / totalSum.toFloat())
    }

    // add the colors as per the number of data(no. of pie chart entries)
    // so that each data will get a color
    val colors = listOf(
        Purple200,
        Purple500,
        Teal200,
        Purple700,
        Blue
    )

    var animationPlayed by remember { mutableStateOf(false) }

    var lastValue = 0f

    // it is the diameter value of the Pie
    val animateSize by animateFloatAsState(
        targetValue = if (animationPlayed) radiusOuter.value * 2f else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = 0,
            easing = LinearOutSlowInEasing
        )
    )

    // if you want to stabilize the Pie Chart you can use value -90f
    // 90f is used to complete 1/4 of the rotation
    val animateRotation by animateFloatAsState(
        targetValue = if (animationPlayed) 90f * 11f else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = 0,
            easing = LinearOutSlowInEasing
        )
    )

    // to play the animation only once when the function is Created or Recomposed
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Box(
                    modifier = Modifier.size(animateSize.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Canvas(
                        modifier = Modifier
                            .size(radiusOuter * 1.6f)
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

                        // add text inside the Canvas
                        val centerX = size.width / 2f
                        val centerY = size.height / 2f
                        val text = "Total\n88,972.73\nKSH"
                        val textStyle = TextStyle(
                            fontSize = 20.sp,
                            color = PrimaryColor,
                            textAlign = TextAlign.Center,
                            fontFamily = MontserratMedium
                        )

                     /*
                        drawContext.canvas.nativeCanvas.apply {
                            drawText(
                                text,
                                centerX,
                                centerY + (textStyle.fontSize.toPx() / 1.5f),
                                Paint().apply {
                                    textSize = 16.sp.toPx()
                                    textAlign = Paint.Align.CENTER
                                    color = Color.Black.toArgb()
                                    isFakeBoldText = true

                                }
                            )
                        }
*/

                    }

                    Text(
                        text = "Total\n$totalSum\nKSH",
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = PrimaryColor,
                            textAlign = TextAlign.Center,
                            fontFamily = MontserratMedium
                        ),
                        modifier = Modifier.align(Alignment.Center),
                        maxLines = 3
                    )



                }
            }
            Column(modifier = Modifier.padding(start = 12.dp)) {
                // To see the data in more structured way
                // Compose Function in which Items are showing data
                DetailsPieChart(
                    data = data,
                    colors = colors
                )

            }


        }

    }


}

@Composable
fun DetailsPieChart(
    data: Map<String, Int>,
    colors: List<Color>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // create the data items
        data.values.forEachIndexed { index, value ->
            DetailsPieChartItem(
                data = Pair(data.keys.elementAt(index), value),
                color = colors[index]
            )
        }

    }
}

@Composable
fun DetailsPieChartItem(
    data: Pair<String, Int>,
    color: Color,
    radiusOuter: Dp = 60.dp
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 3.dp)
    ) {
        Canvas(modifier = Modifier.size(radiusOuter * 0.2f)) {
            drawArc(
                color = color,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(width = 5.dp.toPx(), cap = StrokeCap.Butt)
            )
        }
        Text(
            modifier = Modifier.padding(start = 15.dp),
            text = data.first,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier.padding(start = 15.dp),
            text = data.second.toString(),
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        )

    }
}


/*@Composable
fun DetailsPieChartItem(
    data: Pair<String, Int>,
    height: Dp = 45.dp,
    color: Color
) {

    Surface(
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 40.dp),
        color = Color.Transparent
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            

            Box(
                modifier = Modifier
                    .background(
                        color = color,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .size(height)
            )

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.padding(start = 15.dp),
                    text = data.first,
                    fontWeight = FontWeight.Medium,
                    fontSize = 22.sp,
                )
                Text(
                    modifier = Modifier.padding(start = 15.dp),
                    text = data.second.toString(),
                    fontWeight = FontWeight.Medium,
                    fontSize = 22.sp,
                )
            }

        }

    }

}*/

@Composable
@Preview
fun PreviewPyChart() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            PieChart(
                data = mapOf(
                    Pair("Sample-1", 150),
                    Pair("Sample-2", 120),
                    Pair("Sample-3", 110),
                    Pair("Sample-4", 170),
                    Pair("Sample-5", 120),
                )
            )

        }


    }
}