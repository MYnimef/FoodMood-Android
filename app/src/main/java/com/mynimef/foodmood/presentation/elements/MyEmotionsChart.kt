package com.mynimef.foodmood.presentation.elements

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mynimef.foodmood.data.models.enums.ETypeEmotion
import com.mynimef.foodmood.data.models.enums.ETypePeriod
import com.mynimef.foodmood.presentation.elements.chart.AxisLines
import com.mynimef.foodmood.presentation.elements.chart.LineChart
import com.mynimef.foodmood.presentation.elements.chart.PlotCoordinates
import com.mynimef.foodmood.presentation.elements.chart.PlotIcons
import com.mynimef.foodmood.presentation.elements.chart.PlotLabels
import com.mynimef.foodmood.presentation.elements.chart.PlotLineStyle
import com.mynimef.foodmood.presentation.elements.chart.PlotMarkStyle
import com.mynimef.foodmood.presentation.theme.EmotionDarkGreen
import com.mynimef.foodmood.presentation.theme.EmotionGreen
import com.mynimef.foodmood.presentation.theme.EmotionOrange
import com.mynimef.foodmood.presentation.theme.EmotionRed
import com.mynimef.foodmood.presentation.theme.EmotionYellow
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme
import java.time.LocalDate

@Composable
fun MyEmotionsChart(
    modifier: Modifier,
    emotionsData: List<Pair<Float, Float>>,
    periodType: ETypePeriod,
) {
    val date = LocalDate.now()
    val period = periodType.period

    val xLabels = listOf(
        date.minusDays(period.toLong()).toString(),
        date.minusDays((period / 2).toLong()).toString(),
        date.toString(),
    )
    val yIcons = ETypeEmotion.values().map { it.icon }

    LineChart(
        modifier = modifier,
        xLabels = PlotLabels(
            labels = xLabels,
            fontSize = 8.sp,
            fontColor = MaterialTheme.colorScheme.onPrimaryContainer,
            padding = 15.dp,
            diapason = false
        ),
        yIcons = PlotIcons(
            icons = yIcons,
            iconsSize = 20.dp,
            diapason = true
        ),
        verticalLines = AxisLines(
            offset = 15.dp
        ),
        coordinates = PlotCoordinates(
            values = emotionsData,
            minX = 0f,
            maxX = period.toFloat(),
            minY = 0f,
            maxY = 5f,
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
            EmotionDarkGreen,
            EmotionGreen,
            EmotionYellow,
            EmotionOrange,
            EmotionRed,
        ),
    )
}

@Preview(showBackground = true)
@Composable
private fun MyEmotionsChartPreview() {
    FoodMoodTheme {
        val data = listOf(
            Pair(0f, 5f),
            Pair(3f, 3f),
            Pair(5f, 1f),
            Pair(9f, 3f),
            Pair(15f, 2f),
        )

        MyEmotionsChart(
            modifier = Modifier.size(2000.dp, 200.dp).padding(20.dp),
            emotionsData = data,
            periodType = ETypePeriod.DAYS_31
        )
    }
}