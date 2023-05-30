package com.mynimef.foodmood.presentation.screens.client

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mynimef.foodmood.R
import com.mynimef.foodmood.data.models.enums.ETypePeriod
import com.mynimef.foodmood.extensions.OnLifecycleEvent
import com.mynimef.foodmood.presentation.elements.MyEmotionsChart
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme


@Composable
fun ReportsScreen() {
    val viewModel: ReportsViewModel = viewModel()
    ReportsScreen(
        period = ETypePeriod.DAYS_31,
        coordinate = viewModel.coordinate.collectAsState().value,
    )
    OnLifecycleEvent { _, event ->
        if (event == Lifecycle.Event.ON_CREATE) {
            viewModel.plot()
        }
    }
}

@Composable
private fun ReportsScreen(
    period: ETypePeriod,
    coordinate: List<Coordinate>,
) {
    val emotionX = mutableListOf<Float>()
    val emotionY = mutableListOf<Float>()
    coordinate.forEach {
        emotionX.add(it.x)
        emotionY.add(it.y)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
            .verticalScroll(rememberScrollState())
        ,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            stringResource(R.string.emotion_chart),
            modifier = Modifier.padding(bottom = 30.dp, top = 70.dp),
            style = MaterialTheme.typography.titleLarge
        )

        MyEmotionsChart(
            modifier = Modifier
                .fillMaxWidth(1f)
                .aspectRatio(2f),
            emotionsData = emptyList(),
            periodType = period,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ReportsPreview() {
    FoodMoodTheme {
        ReportsScreen()
    }
}