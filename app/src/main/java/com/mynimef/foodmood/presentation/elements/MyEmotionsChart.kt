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
private fun MyEmotionsChart(
    modifier: Modifier,
    emotionsDataX: List<Float>,
    emotionsDataY: List<Float>,
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
            diapason = false
        ),
        yIcons = PlotIcons(
            icons = yIcons,
            iconsSize = 20.dp,
            diapason = true
        ),
        coordinates = PlotCoordinates(
            x = emotionsDataX,
            minX = 0f,
            maxX = period.toFloat(),
            y = emotionsDataY,
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
        MyEmotionsChart(
            modifier = Modifier.size(2000.dp, 200.dp).padding(20.dp),
            emotionsDataX = listOf(0f, 3f, 5f, 9f, 15f),
            emotionsDataY = listOf(5f, 3f, 1f, 3f, 2f),
            periodType = ETypePeriod.DAYS_31
        )
    }
}