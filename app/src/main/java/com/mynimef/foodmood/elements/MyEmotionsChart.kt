package com.mynimef.foodmood.elements

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mynimef.domain.models.enums.ETypeEmotion
import com.mynimef.data.enums.ETypePeriod
import com.mynimef.foodmood.elements.chart.AxisLines
import com.mynimef.foodmood.elements.chart.LineChart
import com.mynimef.foodmood.elements.chart.PlotCoordinates
import com.mynimef.foodmood.elements.chart.PlotIcons
import com.mynimef.foodmood.elements.chart.PlotLabels
import com.mynimef.foodmood.elements.chart.PlotLineStyle
import com.mynimef.foodmood.elements.chart.PlotMarkStyle
import com.mynimef.foodmood.extensions.getIcon
import com.mynimef.foodmood.extensions.getNum
import com.mynimef.foodmood.theme.EmotionDarkGreen
import com.mynimef.foodmood.theme.EmotionGreen
import com.mynimef.foodmood.theme.EmotionOrange
import com.mynimef.foodmood.theme.EmotionRed
import com.mynimef.foodmood.theme.EmotionYellow
import com.mynimef.foodmood.theme.FoodMoodTheme
import java.time.LocalDate

@Composable
fun MyEmotionsChart(
    modifier: Modifier,
    emotionsData: List<Pair<Float, Float>>,
    periodType: ETypePeriod,
) {
    val date = LocalDate.now()
    val period = periodType.getNum()

    val xLabels = listOf(
        date.minusDays(period.toLong()).toString(),
        date.minusDays((period / 2).toLong()).toString(),
        date.toString(),
    )
    val yIcons = ETypeEmotion.entries.map { it.getIcon() }

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
        yIcons = PlotIcons(
            icons = yIcons,
            iconsSize = iconSize,
            padding = paddingY,
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
private fun MyEmotionsChartPreview() = FoodMoodTheme {
    val data = listOf(
        Pair(0f, 5f),
        Pair(3f, 3f),
        Pair(5f, 1f),
        Pair(9f, 3f),
        Pair(15f, 2f)
    )

    MyEmotionsChart(
        modifier = Modifier
            .size(2000.dp, 200.dp)
            .padding(20.dp)
        ,
        emotionsData = data,
        periodType = ETypePeriod.DAYS_31
    )
}