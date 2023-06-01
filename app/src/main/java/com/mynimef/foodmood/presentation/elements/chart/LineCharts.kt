package com.mynimef.foodmood.presentation.elements.chart

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mynimef.foodmood.data.models.enums.ETypeEmotion
import com.mynimef.foodmood.presentation.theme.EmotionDarkGreen
import com.mynimef.foodmood.presentation.theme.EmotionGreen
import com.mynimef.foodmood.presentation.theme.EmotionOrange
import com.mynimef.foodmood.presentation.theme.EmotionRed
import com.mynimef.foodmood.presentation.theme.EmotionYellow
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

data class PlotLabels(
    val labels: List<String>,
    val fontSize: TextUnit = 12.sp,
    val fontColor: Color,
    val padding: Dp,
    val diapason: Boolean = false,
)

data class PlotIcons(
    val icons: List<Int>,
    val iconsSize: Dp,
    val padding: Dp = iconsSize * 1.5f,
    val diapason: Boolean = false,
)

@Composable
fun LineChart(
    modifier : Modifier,
    xLabels: PlotLabels,
    yIcons: PlotIcons,
    verticalLines: AxisLines,
    coordinates: PlotCoordinates,
    lineStyle: PlotLineStyle = PlotLineStyle(),
    markStyle: PlotMarkStyle,
    underColors: List<Color>,
) {
    val density = LocalDensity.current
    val textPaint = remember(density) {
        Paint().apply {
            color = xLabels.fontColor.toArgb()
            textAlign = Paint.Align.CENTER
            textSize = density.run { xLabels.fontSize.toPx() }
        }
    }
    val yPainters = yIcons.icons.map {
        val vector = ImageVector.vectorResource(it)
        rememberVectorPainter(image = vector)
    }

    Box(
        modifier = modifier,
        contentAlignment = Center,
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize(),
        ) {
            val yIconsSize = yIcons.iconsSize.toPx()

            val paddingX = yIcons.padding.toPx()
            val paddingY = xLabels.padding.toPx()

            val width = size.width - paddingX
            val height = size.height - paddingY

            drawPlot(
                coordinates = coordinates,
                borders = PlotBorders(
                    width = width,
                    height = height,
                    startX = paddingX,
                    endY = height,
                ),
                lineStyle = lineStyle,
                markStyle = markStyle,
                underColors = underColors,
            )

            drawAxisY(
                axis = AxisVectors(
                    icons = yPainters,
                    iconSize = yIconsSize,
                    diapason = yIcons.diapason,
                ),
                height = height,
            )

            drawAxisX(
                axis = AxisLabels(
                    labels = xLabels.labels,
                    paint = textPaint,
                    diapason = xLabels.diapason
                ),
                width = width,
                start = paddingX,
                heightPosition = size.height,
                lines = verticalLines,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview3() {
    FoodMoodTheme {
        val xLabels = listOf(
            "01/05/23",
            "15.05.23",
            "30.05.23"
        )
        val yIcons = ETypeEmotion.values().map { it.icon }

        LineChart(
            modifier = Modifier.size(2000.dp, 200.dp).padding(20.dp),
            xLabels = PlotLabels(
                labels = xLabels,
                fontSize = 8.sp,
                fontColor = Color.Black,
                padding = 10.dp,
                diapason = false
            ),
            yIcons = PlotIcons(
                icons = yIcons,
                iconsSize = 20.dp,
                diapason = true
            ),
            verticalLines = AxisLines(
                offset = 10.dp
            ),
            coordinates = PlotCoordinates(
                values = listOf(
                    Pair(0f, 5f),
                    Pair(1f, 3f),
                    Pair(3f, 5f),
                    Pair(4f, 3f),
                    Pair(9f, 5f)
                ),
                minX = 0f,
                maxX = 9f,
                minY = 0f,
                maxY = 5f,
            ),
            markStyle = PlotMarkStyle(),
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