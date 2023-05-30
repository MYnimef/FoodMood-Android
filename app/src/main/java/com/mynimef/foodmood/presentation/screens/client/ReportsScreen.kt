package com.mynimef.foodmood.presentation.screens.client

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
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
        period = viewModel.period.collectAsState().value,
        setPeriod = viewModel::setPeriod,
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
    setPeriod: (ETypePeriod) -> Unit,
    coordinate: List<Pair<Float, Float>>,
) {
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
            modifier = Modifier.padding(bottom = 30.dp, top = 35.dp),
            style = MaterialTheme.typography.titleLarge
        )
        Row(
            modifier = Modifier
                .padding(bottom = 35.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                onClick = { setPeriod(ETypePeriod.DAYS_7) }) {
                Text(text = stringResource(id = R.string.days_7))
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                onClick = { setPeriod(ETypePeriod.DAYS_31) }) {
                Text(text = stringResource(id = R.string.days_31))
            }
        }
        MyEmotionsChart(
            modifier = Modifier
                .fillMaxWidth(1f)
                .aspectRatio(2f),
            emotionsData = coordinate,
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