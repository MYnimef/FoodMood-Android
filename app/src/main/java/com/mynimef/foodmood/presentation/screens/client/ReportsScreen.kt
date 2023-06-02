package com.mynimef.foodmood.presentation.screens.client

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mynimef.foodmood.R
import com.mynimef.foodmood.data.models.enums.ETypePeriod
import com.mynimef.foodmood.extensions.OnLifecycleEvent
import com.mynimef.foodmood.extensions.noRippleClickable
import com.mynimef.foodmood.presentation.elements.MyEmotionsChart
import com.mynimef.foodmood.presentation.elements.MyWaterChart
import com.mynimef.foodmood.presentation.elements.MyWeightChart
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun ReportsScreen() {
    val viewModel: ReportsViewModel = viewModel()
    val periodState = viewModel.period.collectAsState()
    val coordinatesEmotionsState = viewModel.coordinatesEmotions.collectAsState()
    val coordinatesWaterState = viewModel.coordinatesWater.collectAsState()
    val coordinatesWeightState = viewModel.coordinatesWeight.collectAsState()

    OnLifecycleEvent { _, event ->
        if (event == Lifecycle.Event.ON_CREATE) {
            viewModel.getData()
        }
    }

    ReportsScreen(
        periodProvider = { periodState.value },
        setPeriod = viewModel::setPeriod,
        coordinatesEmotionsProvider = { coordinatesEmotionsState.value },
        coordinatesWaterProvider = { coordinatesWaterState.value },
        coordinatesWeightProvider = { coordinatesWeightState.value },
    )
}

@Composable
private fun ReportsScreen(
    periodProvider: () -> ETypePeriod,
    setPeriod: (ETypePeriod) -> Unit,
    coordinatesEmotionsProvider: () -> List<Pair<Float, Float>>,
    coordinatesWaterProvider: () -> List<Pair<Float, Float>>,
    coordinatesWeightProvider: () -> List<Pair<Float, Float>>,
) {
    Column() {
        Row(
            modifier = Modifier
                .padding(vertical = 35.dp, horizontal = 30.dp)
            ,
            horizontalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp))
                    .fillMaxWidth()
                    .weight(1f)
                    .noRippleClickable(
                        onClick = { setPeriod(ETypePeriod.DAYS_7) }
                    )
                    .background(color = MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 5.dp),
                    text = stringResource(id = R.string.days_7),
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.Center
                )
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp))
                    .fillMaxWidth()
                    .weight(1f)
                    .noRippleClickable(
                        onClick = { setPeriod(ETypePeriod.DAYS_31) }
                    )
                    .background(color = MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 5.dp),
                    text = stringResource(id = R.string.days_31),
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.Center
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            EmotionData(
                coordinatesProvider = coordinatesEmotionsProvider,
                periodProvider = periodProvider,
            )
            WaterData(
                coordinatesProvider = coordinatesWaterProvider,
                periodProvider = periodProvider,
            )
            WeightData(
                coordinatesProvider = coordinatesWeightProvider,
                periodProvider = periodProvider,
            )
        }
    }
}

@Composable
private fun EmotionData(
    coordinatesProvider: () -> List<Pair<Float, Float>>,
    periodProvider: () -> ETypePeriod,
) {
    Text(
        text = stringResource(R.string.emotion_chart),
        modifier = Modifier.padding(bottom = 30.dp),
        style = MaterialTheme.typography.titleLarge
    )
    MyEmotionsChart(
        modifier = Modifier
            .fillMaxWidth(1f)
            .aspectRatio(2f),
        emotionsData = coordinatesProvider(),
        periodType = periodProvider(),
    )
}

@Composable
private fun WaterData(
    coordinatesProvider: () -> List<Pair<Float, Float>>,
    periodProvider: () -> ETypePeriod,
) {
    Text(
        text = stringResource(R.string.water),
        modifier = Modifier.padding(bottom = 30.dp, top = 35.dp),
        style = MaterialTheme.typography.titleLarge
    )
    MyWaterChart(
        modifier = Modifier
            .fillMaxWidth(1f)
            .aspectRatio(2f),
        waterData = coordinatesProvider(),
        periodType = periodProvider(),
    )
}

@Composable
private fun WeightData(
    coordinatesProvider: () -> List<Pair<Float, Float>>,
    periodProvider: () -> ETypePeriod,
) {
    Text(
        text = stringResource(R.string.weight),
        modifier = Modifier.padding(bottom = 30.dp, top = 35.dp),
        style = MaterialTheme.typography.titleLarge
    )
    MyWeightChart(
        modifier = Modifier
            .fillMaxWidth(1f)
            .aspectRatio(2f),
        weightData = coordinatesProvider(),
        periodType = periodProvider(),
    )
}

@Preview(showBackground = true)
@Composable
private fun ReportsScreenPreview() {
    FoodMoodTheme {
        ReportsScreen()
    }
}