package com.mynimef.presentation.elements

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mynimef.data.enums.ETypePeriod
import com.mynimef.presentation.elements.chart.AxisLines
import com.mynimef.presentation.elements.chart.LineChart
import com.mynimef.presentation.elements.chart.PlotCoordinates
import com.mynimef.presentation.elements.chart.PlotLabels
import com.mynimef.presentation.elements.chart.PlotLineStyle
import com.mynimef.presentation.elements.chart.PlotMarkStyle
import com.mynimef.presentation.extensions.getNum
import com.mynimef.presentation.theme.Blue
import com.mynimef.presentation.theme.FoodMoodTheme
import com.mynimef.presentation.theme.LightBlue
import java.time.LocalDate


@Composable
fun MyWaterChart(
    modifier: Modifier,
    waterData: List<Pair<Float, Float>>,
    periodType: ETypePeriod,
) {
    val date = LocalDate.now()
    val period = periodType.getNum()

    val xLabels = listOf(
        date.minusDays(period.toLong()).toString(),
        date.minusDays((period / 2).toLong()).toString(),
        date.toString(),
    )

    val maxY: Float
    val minY: Float
    val averageY: Float

    if (waterData.isNotEmpty()) {
        maxY = (waterData.maxBy{ pair -> pair.second}.second)*1.2f
        minY = 0 + maxY/3
        averageY = minY + maxY/3
    } else {
        maxY = 2100f
        minY = 700f
        averageY = 1400f
    }

    val yLabels = listOf(
        "0",
        minY.toInt().toString(),
        averageY.toInt().toString(),
        maxY.toInt().toString(),
    )

    val iconSize = 20.dp
    val paddingY = iconSize * 1.5f

    LineChart(
        modifier = modifier.padding(end = paddingY),
        xLabels = PlotLabels(
            labels = xLabels,
            fontColor = MaterialTheme.colorScheme.onPrimaryContainer,
            padding = 15.dp,
            diapason = false
        ),
        yLabels = PlotLabels(
            labels = yLabels,
            fontColor = MaterialTheme.colorScheme.onPrimaryContainer,
            padding = paddingY,
            diapason = false
        ),
        verticalLines = AxisLines(
            offset = 15.dp
        ),
        horizontalLines = AxisLines(
            offset = 15.dp
        ),
        coordinates = PlotCoordinates(
            values = waterData,
            minX = 0f,
            maxX = period.toFloat(),
            minY = 0f,
            maxY = maxY,
        ),
        lineStyle = PlotLineStyle(
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            width = 1.dp
        ),
        markStyle = PlotMarkStyle(
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            radius = 2.dp,
        ),
        underColors = listOf(
            Blue,
            LightBlue,
            Color.Unspecified,
        ),
    )
}

@Preview(showBackground = true)
@Composable
private fun MyEmotionsChartPreview() = FoodMoodTheme {
    val data = listOf(
        Pair(0f, 9f),
        Pair(3f, 3f),
        Pair(5f, 1f),
        Pair(9f, 3f),
        Pair(15f, 2f)
    )

    MyWaterChart(
        modifier = Modifier.size(2000.dp, 200.dp).padding(20.dp),
        waterData = data,
        periodType = ETypePeriod.DAYS_31
    )
}