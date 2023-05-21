package com.dev.chacha.presentation.piecharts

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun DetailsPieChartItems(
    data: Pair<String, Int>,
    color: Color,
    totalSum: Int
) {
    val percentage = (data.second.toFloat() / totalSum.toFloat()) * 100

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 1.dp)
    ) {
        Canvas(modifier = Modifier.size(40.dp * 0.2f)) {
            drawArc(
                color = color,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Butt)
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = data.first,
            fontSize = 11.sp,
            textAlign = TextAlign.Start,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "%.1f%%".format(percentage),
            fontSize = 11.sp,
            textAlign = TextAlign.End,
            overflow = TextOverflow.Ellipsis
        )
    }
}
