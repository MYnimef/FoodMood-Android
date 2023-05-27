package com.mynimef.foodmood.presentation.elements

import android.graphics.Paint
import android.graphics.PointF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun MyChart(
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
    markColor: Color = Color.Red,
    markRadius: Float = 10f,
    paddingSpace: Dp,
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
            .background(Color.White)
            .padding(horizontal = 8.dp, vertical = 12.dp)
        ,
        contentAlignment = Center,
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize(),
        ) {
            val xAxisSpace = (size.width - paddingSpace.toPx()) / xSize
            val yAxisSpace = size.height / ySize

            val iconSize = minOf(size.width, size.height) * iconScale

            val xAxisIterator = if (xDiapason) 0.5f else 1f
            xLabels?.forEachIndexed { index, value ->
                drawContext.canvas.nativeCanvas.drawText(
                    value,
                    xAxisSpace * (index + xAxisIterator),
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

            val yAxisIterator = if (yDiapason) 0.5f else 1f
            yLabels?.forEachIndexed { index, value ->
                drawContext.canvas.nativeCanvas.drawText(
                    value,
                    paddingSpace.toPx() / 2f,
                    size.height - yAxisSpace * (index + yAxisIterator),
                    textPaint
                )
            }
            yPainters?.forEachIndexed { index, painter ->
                translate(
                    left = paddingSpace.toPx() / 2f,
                    top = size.height - yAxisSpace * (index + yAxisIterator) - iconSize / 2
                ) { with(painter) { draw(Size(iconSize, iconSize)) } }
            }

            val scaledCoordinates = mutableListOf<PointF>()
            coordinates.forEach {
                val x = xAxisSpace * it.first
                val y = size.height - yAxisSpace * it.second
                scaledCoordinates.add(PointF(x, y))
                if (markCoordinates) {
                    drawCircle(
                        color = markColor,
                        radius = markRadius,
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

            /** filling the area under the path */
            val fillPath = android.graphics.Path(stroke.asAndroidPath())
                .asComposePath()
                .apply {
                    lineTo(size.width, size.height - yAxisSpace)
                    lineTo(xAxisSpace, size.height - yAxisSpace)
                    close()
                }
            drawPath(
                fillPath,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Cyan,
                        Color.Transparent,
                    ),
                    endY = size.height - yAxisSpace
                ),
            )

            drawPath(
                stroke,
                color = Color.Black,
                style = Stroke(
                    width = 5f,
                    cap = StrokeCap.Round
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyChartTextPreview() {
    FoodMoodTheme {
        MyChart(
            modifier = Modifier,
            xLabels = listOf(
                "da",
                "2",
                "3",
                "4",
                "5",
            ),
            xSize = 5,
            yLabels = listOf(
                "1",
                "2",
                "dada",
                "4",
                "5",
            ),
            ySize = 5,
            yDiapason = false,
            coordinates = listOf(
                Pair(1f, 3f),
                Pair(2.5f, 2f),
                Pair(3f, 2f),
                Pair(4f, 0f),
                Pair(5f, 2f),
            ),
            markCoordinates = true,
            paddingSpace = 2.dp,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MyChartIconPreview() {
    FoodMoodTheme {
        MyChart(
            modifier = Modifier.size(200.dp, 200.dp),
            xLabels = listOf(
                "da",
                "2",
                "3",
                "4",
                "5",
            ),
            xSize = 5,
            yIcons = ETypeEmotion.values().map { it.icon },
            ySize = 5,
            yDiapason = true,
            coordinates = listOf(
                Pair(1f, 3f),
                Pair(2.5f, 2f),
                Pair(3f, 2f),
                Pair(4f, 0f),
                Pair(5f, 2f),
            ),
            paddingSpace = 2.dp,
        )
    }
}