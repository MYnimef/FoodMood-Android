package com.mynimef.foodmood.presentation.elements

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mynimef.data.enums.ETypePeriod
import com.mynimef.foodmood.presentation.elements.chart.AxisLines
import com.mynimef.foodmood.presentation.elements.chart.LineChart
import com.mynimef.foodmood.presentation.elements.chart.PlotCoordinates
import com.mynimef.foodmood.presentation.elements.chart.PlotLabels
import com.mynimef.foodmood.presentation.elements.chart.PlotLineStyle
import com.mynimef.foodmood.presentation.elements.chart.PlotMarkStyle
import com.mynimef.foodmood.presentation.extensions.getNum
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme
import java.time.LocalDate
import kotlin.math.floor

@Composable
fun MyWeightChart(
    modifier: Modifier,
    weightData: List<Pair<Float, Float>>,
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

    if (weightData.isNotEmpty()) {
        maxY = weightData.maxBy{ pair -> pair.second}.second + 5f
        minY = floor(0 + maxY/3f * 100f )/100f
        averageY = floor((minY + maxY/3f) * 100f) / 100f
    } else {
        maxY = 90f
        minY = 30f
        averageY = 60f
    }

    val yLabels = listOf(
        "0.0",
        minY.toString().format("%.2f", minY),
        averageY.toString(),
        maxY.toString(),
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
            values = weightData,
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
            Color.Unspecified,
        ),
    )
}

@Preview(showBackground = true)
@Composable
private fun MyEmotionsChartPreview() {
    FoodMoodTheme {
        val data = listOf(
            Pair(0f, 9f),
            Pair(3f, 3f),
            Pair(5f, 1f),
            Pair(9f, 3f),
            Pair(15f, 2f),
        )

        MyWeightChart(
            modifier = Modifier.size(2000.dp, 200.dp).padding(20.dp),
            weightData = data,
            periodType = ETypePeriod.DAYS_31
        )
    }
}