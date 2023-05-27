package com.mynimef.foodmood.presentation.elements

import android.graphics.Paint
import android.graphics.PointF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mynimef.foodmood.data.models.enums.ETypeEmotion
import com.mynimef.foodmood.extensions.toInt
import com.mynimef.foodmood.presentation.theme.EmotionDarkGreen
import com.mynimef.foodmood.presentation.theme.EmotionGreen
import com.mynimef.foodmood.presentation.theme.EmotionOrange
import com.mynimef.foodmood.presentation.theme.EmotionRed
import com.mynimef.foodmood.presentation.theme.EmotionYellow
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun MyLineChart(
    modifier : Modifier,
    xLabels: List<String>? = null,
    xIcons: List<Int>? = null,
    xSize: Int,
    xDiapason: Boolean = false,
    yLabels: List<String>? = null,
    yIcons: List<Int>? = null,
    ySize: Int,
    yDiapason: Boolean = false,
    iconScale: Float = 0.1f,
    coordinates: List<Pair<Float, Float>>,
    markCoordinates: Boolean = false,
    markColor: Color = MaterialTheme.colorScheme.primary,
    markRadius: Dp = 4.dp,
    lineColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    lineWidth: Dp = 2.dp,
    underColors: List<Color> = emptyList(),
    bottomPadding: Dp = 20.dp,
    leftPadding: Dp = 20.dp,
) {
    val density = LocalDensity.current
    val textPaint = remember(density) {
        Paint().apply {
            color = android.graphics.Color.BLACK
            textAlign = Paint.Align.CENTER
            textSize = density.run { 12.sp.toPx() }
        }
    }

    val xPainters = xIcons?.map {
        val vector = ImageVector.vectorResource(it)
        rememberVectorPainter(image = vector)
    }
    val yPainters = yIcons?.map {
        val vector = ImageVector.vectorResource(it)
        rememberVectorPainter(image = vector)
    }

    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 8.dp, vertical = 12.dp)
        ,
        contentAlignment = Center,
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize(),
        ) {
            val paddingLeft = leftPadding.toPx()
            val paddingBottom = bottomPadding.toPx()

            val width = size.width - paddingLeft
            val height = size.height - paddingBottom

            val xAxisSpace = width / (xSize - (!xDiapason).toInt())
            val yAxisSpace = height / (ySize - (!yDiapason).toInt())

            val iconSize = minOf(size.width, size.height) * iconScale

            val xAxisIterator = if (xDiapason) 0.5f else 0f
            xLabels?.forEachIndexed { index, value ->
                drawContext.canvas.nativeCanvas.drawText(
                    value,
                    paddingLeft + xAxisSpace * (index - xAxisIterator),
                    size.height,
                    textPaint
                )
            }
            xPainters?.forEachIndexed { index, painter ->
                translate(
                    left = 10f,
                    top = size.height
                ) { with(painter) { draw(Size(iconSize, iconSize)) } }
            }

            val yAxisIterator = if (yDiapason) 0.5f else 0f
            yLabels?.forEachIndexed { index, value ->
                drawContext.canvas.nativeCanvas.drawText(
                    value,
                    0f,
                    height - yAxisSpace * (index - yAxisIterator),
                    textPaint
                )
            }
            yPainters?.forEachIndexed { index, painter ->
                translate(
                    left = 0f,
                    top = height - yAxisSpace * (index + yAxisIterator) - iconSize / 2
                ) { with(painter) { draw(Size(iconSize, iconSize)) } }
            }

            val scaledCoordinates = mutableListOf<PointF>()
            coordinates.forEach {
                val x = paddingLeft + xAxisSpace * it.first
                val y = height - yAxisSpace * it.second
                scaledCoordinates.add(PointF(x, y))
                if (markCoordinates) {
                    drawCircle(
                        color = markColor,
                        radius = markRadius.toPx(),
                        center = Offset(x, y)
                    )
                }
            }

            val controlPoints1 = mutableListOf<PointF>()
            val controlPoints2 = mutableListOf<PointF>()
            for (i in 1 until scaledCoordinates.size) {
                val centerX = (scaledCoordinates[i].x + scaledCoordinates[i - 1].x) / 2
                controlPoints1.add(PointF(centerX, scaledCoordinates[i - 1].y))
                controlPoints2.add(PointF(centerX, scaledCoordinates[i].y))
            }

            /** drawing the path */
            val stroke = Path().apply {
                reset()
                scaledCoordinates.first().run { moveTo(x, y) }
                for (i in 0 until scaledCoordinates.size - 1) {
                    val cp1 = controlPoints1[i]
                    val cp2 = controlPoints2[i]
                    val coordinate = scaledCoordinates[i + 1]
                    cubicTo(
                        cp1.x, cp1.y,
                        cp2.x, cp2.y,
                        coordinate.x, coordinate.y
                    )
                }
            }

            if (underColors.isNotEmpty()) {
                val fillPath = android.graphics.Path(stroke.asAndroidPath())
                    .asComposePath()
                    .apply {
                        lineTo(size.width, height)
                        lineTo(paddingLeft, height)
                        close()
                    }
                if (underColors.size == 1) {
                    drawPath(
                        path = fillPath,
                        color = underColors.first()
                    )
                } else {
                    drawPath(
                        path = fillPath,
                        brush = Brush.verticalGradient(
                            colors = underColors,
                            startY = 0f,
                            endY = height,
                        ),
                    )
                }
            }

            drawPath(
                stroke,
                color = lineColor,
                style = Stroke(
                    width = lineWidth.toPx(),
                    cap = StrokeCap.Round
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyLineChartTextPreview() {
    FoodMoodTheme {
        val xLabels = listOf("0", "1", "2", "3", "4")
        val yLabels = listOf("0", "1", "2", "3", "4")

        MyLineChart(
            modifier = Modifier,
            xLabels = xLabels,
            xSize = xLabels.size,
            yLabels = yLabels,
            ySize = yLabels.size,
            yDiapason = false,
            coordinates = listOf(
                Pair(0f, 0f),
                Pair(1.5f, 2f),
                Pair(2.5f, 1.5f),
                Pair(3f, 1f),
                Pair(4f, 2f),
            ),
            lineColor = MaterialTheme.colorScheme.onPrimaryContainer,
            markCoordinates = true,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MyLineChartIconPreview() {
    FoodMoodTheme {
        val xLabels = listOf("0", "1", "2", "3", "4", "5")
        val yIcons = ETypeEmotion.values().map { it.icon }

        MyLineChart(
            modifier = Modifier.size(200.dp, 200.dp),
            xLabels = xLabels,
            xSize = xLabels.size,
            yIcons = yIcons,
            ySize = yIcons.size,
            yDiapason = true,
            coordinates = listOf(
                Pair(0f, 2.5f),
                Pair(1f, 3f),
                Pair(3f, 5f),
                Pair(4f, 3f),
                Pair(5f, 2f),
            ),
            underColors = listOf(
                EmotionDarkGreen,
                EmotionGreen,
                EmotionYellow,
                EmotionOrange,
                EmotionRed,
            ),
        )
    }
}