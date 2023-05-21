package com.dev.chacha.presentation.piecharts

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dev.chacha.presentation.piechart.components.DetailsPieChartItem

@Composable
fun DetailsPieCharts(
    data: Map<String, Int>,
    colors: List<Color>,
    totalSum: Int

) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // create the data items
        data.values.forEachIndexed { index, value ->
            DetailsPieChartItems(
                data = Pair(data.keys.elementAt(index), value),
                color = colors[index],
                totalSum = totalSum

            )
        }

    }
}