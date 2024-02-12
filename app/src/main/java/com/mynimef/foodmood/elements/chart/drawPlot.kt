package com.mynimef.foodmood.elements.chart

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class PlotCoordinates(
    val values: List<Pair<Float, Float>>,
    val minX: Float = values.first().first,
    val maxX: Float = values.last().first,
    val minY: Float,
    val maxY: Float,
)

data class PlotBorders(
    val width: Float,
    val height: Float,
    val startX: Float,
    val endY: Float,
)

data class PlotLineStyle(
    val color: Color = Color.Black,
    val width: Dp = 2.dp,
)

data class PlotMarkStyle(
    val color: Color = Color.Red,
    val radius: Dp = 4.dp,
)

fun DrawScope.drawPlot(
    coordinates: PlotCoordinates,
    borders: PlotBorders,
    lineStyle: PlotLineStyle,
    underColors: List<Color>,
) {
    if (coordinates.values.isEmpty()) {
        return
    }

    val scaledCoordinates = getScaledCoordinates(coordinates, borders)
    val stroke = getPath(scaledCoordinates)
    drawUnder(
        path = stroke,
        colors = underColors,
        startX = scaledCoordinates.first().first,
        endX = scaledCoordinates.last().first,
        endY = borders.height,
    )
    drawPath(
        path = stroke,
        color = lineStyle.color,
        style = Stroke(
            width = lineStyle.width.toPx(),
            cap = StrokeCap.Round
        )
    )
}

fun DrawScope.drawPlot(
    coordinates: PlotCoordinates,
    borders: PlotBorders,
    lineStyle: PlotLineStyle,
) {
    if (coordinates.values.isEmpty()) {
        return
    }

    val scaledCoordinates = getScaledCoordinates(coordinates, borders)
    val stroke = getPath(scaledCoordinates)
    drawPath(
        path = stroke,
        color = lineStyle.color,
        style = Stroke(
            width = lineStyle.width.toPx(),
            cap = StrokeCap.Round
        )
    )
}

fun DrawScope.drawPlot(
    coordinates: PlotCoordinates,
    borders: PlotBorders,
    lineStyle: PlotLineStyle,
    markStyle: PlotMarkStyle,
    underColors: List<Color>,
) {
    if (coordinates.values.isEmpty()) {
        return
    }

    val scaledCoordinates = getScaledCoordinates(coordinates, borders)
    val stroke = getPath(scaledCoordinates)
    drawUnder(
        path = stroke,
        colors = underColors,
        startX = scaledCoordinates.first().first,
        endX = scaledCoordinates.last().first,
        endY = borders.height,
    )
    drawPath(
        path = stroke,
        color = lineStyle.color,
        style = Stroke(
            width = lineStyle.width.toPx(),
            cap = StrokeCap.Round
        )
    )
    scaledCoordinates.forEach {
        drawCircle(
            color = markStyle.color,
            radius = markStyle.radius.toPx(),
            center = Offset(it.first, it.second)
        )
    }
}

fun DrawScope.drawPlot(
    coordinates: PlotCoordinates,
    borders: PlotBorders,
    lineStyle: PlotLineStyle,
    markStyle: PlotMarkStyle,
) {
    if (coordinates.values.isEmpty()) {
        return
    }

    val scaledCoordinates = getScaledCoordinates(coordinates, borders)
    val stroke = getPath(scaledCoordinates)

    drawPath(
        path = stroke,
        color = lineStyle.color,
        style = Stroke(
            width = lineStyle.width.toPx(),
            cap = StrokeCap.Round
        )
    )
    scaledCoordinates.forEach {
        drawCircle(
            color = markStyle.color,
            radius = markStyle.radius.toPx(),
            center = Offset(it.first, it.second)
        )
    }
}

private fun getScaledCoordinates(
    coordinates: PlotCoordinates,
    borders: PlotBorders,
): List<Pair<Float, Float>> {
    val rangeX = coordinates.maxX - coordinates.minX
    val rangeY = coordinates.maxY - coordinates.minY

    val scaledCoordinates = mutableListOf<Pair<Float, Float>>()
    coordinates.values.forEach {
        val x = it.first
        val y = it.second

        scaledCoordinates.add(
            Pair(
                borders.startX + borders.width * (x - coordinates.minX) / rangeX,
                borders.endY - borders.height * (y - coordinates.minY) / rangeY,
            )
        )
    }
    return scaledCoordinates
}

private fun getPath(
    coordinates: List<Pair<Float, Float>>
): Path {
    val controlPoints1 = mutableListOf<Pair<Float, Float>>()
    val controlPoints2 = mutableListOf<Pair<Float, Float>>()
    for (i in 1 until coordinates.size) {
        val centerX = (coordinates[i].first + coordinates[i - 1].first) / 2
        controlPoints1.add(Pair(centerX, coordinates[i - 1].second))
        controlPoints2.add(Pair(centerX, coordinates[i].second))
    }

    return Path().apply {
        reset()
        coordinates.first().run { moveTo(first, second) }
        for (i in 0 until coordinates.size - 1) {
            val cp1 = controlPoints1[i]
            val cp2 = controlPoints2[i]
            val coordinate = coordinates[i + 1]
            cubicTo(
                cp1.first, cp1.second,
                cp2.first, cp2.second,
                coordinate.first, coordinate.second
            )
        }
    }
}

private fun DrawScope.drawUnder(
    path: Path,
    colors: List<Color>,
    startX: Float,
    endX: Float,
    endY: Float,
) {
    val fillPath = android.graphics.Path(path.asAndroidPath())
        .asComposePath()
        .apply {
            lineTo(endX, endY)
            lineTo(startX, endY)
            close()
        }

    if (colors.size == 1) {
        drawPath(
            path = fillPath,
            color = colors.first()
        )
    } else {
        drawPath(
            path = fillPath,
            brush = Brush.verticalGradient(
                colors = colors,
                startY = 0f,
                endY = endY,
            ),
        )
    }
}