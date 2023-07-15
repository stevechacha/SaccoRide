package com.dev.chacha.presentation.util

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import kotlin.math.cos
import kotlin.math.sin


class HexagonShapeClipper : Shape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        val path = Path()
        val sideLength = size.width / 2

        val centerX = size.width / 2
        val centerY = size.height / 2

        val angleDegrees = 60f
        val angleRadians = Math.toRadians(angleDegrees.toDouble())
        val radius = sideLength / cos(angleRadians)

        val startPointX = centerX + radius * cos(0.toDouble())
        val startPointY = centerY + radius * sin(0.toDouble())
        path.moveTo(startPointX.toFloat(), startPointY.toFloat())

        for (i in 1 until 6) {
            val x = centerX + radius * cos(angleRadians * i)
            val y = centerY + radius * sin(angleRadians * i)
            path.lineTo(x.toFloat(), y.toFloat())
        }

        return Outline.Generic(path)
    }
}