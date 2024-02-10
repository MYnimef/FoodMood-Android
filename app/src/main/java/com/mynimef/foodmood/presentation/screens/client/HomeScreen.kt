package com.mynimef.foodmood.presentation.screens.client

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mynimef.domain.models.CardModel
import com.mynimef.domain.models.ETypeEmotion
import com.mynimef.domain.models.ETypeMeal
import com.mynimef.foodmood.presentation.elements.MyDate
import com.mynimef.foodmood.presentation.elements.MyFoodCard
import com.mynimef.foodmood.presentation.elements.MyGradient
import com.mynimef.foodmood.presentation.elements.MyWaterPanel
import com.mynimef.foodmood.presentation.extensions.getIcon
import com.mynimef.foodmood.presentation.extensions.getLabel
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme
import kotlinx.coroutines.delay

private fun log(message: String) {
    Log.d("HOME_SCREEN", message)
}

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = viewModel()

    val trackWaterState = viewModel.trackWater.collectAsStateWithLifecycle()
    val waterAmountState = viewModel.waterAmount.collectAsStateWithLifecycle()
    val cardsState = viewModel.cards.collectAsStateWithLifecycle()

    if (viewModel.dataLoaded.collectAsStateWithLifecycle().value) {
        HomeScreen(
            trackWaterProvider = { trackWaterState.value },
            waterAmountProvider = { waterAmountState.value },
            setWater = viewModel::setWater,
            cardsProvider = { cardsState.value },
            onRefresh = viewModel::update
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    trackWaterProvider: () -> Boolean,
    waterAmountProvider: () -> Float,
    setWater: (Float) -> Unit,
    cardsProvider: () -> List<CardModel>,
    onRefresh: suspend () -> Unit,
) {
    log("HomeScreen")

    val pullRefreshState = rememberPullToRefreshState()

    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .nestedScroll(pullRefreshState.nestedScrollConnection)
    ) {
        CenterElements(
            trackWaterProvider = trackWaterProvider,
            waterAmountProvider = waterAmountProvider,
            setWater = setWater,
            cardsProvider = cardsProvider,
        )
        MyGradient(
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
            ,
            colorTop = MaterialTheme.colorScheme.background,
            colorBottom = Color.Transparent
        )
        MyGradient(
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
                .align(Alignment.BottomCenter)
            ,
            colorTop = Color.Transparent,
            colorBottom = MaterialTheme.colorScheme.background
        )
        MyDate()
        MyPullToRefresh(
            modifier = Modifier.align(Alignment.TopCenter),
            state = pullRefreshState,
            onRefresh = onRefresh
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MyPullToRefresh(
    modifier: Modifier,
    state: PullToRefreshState,
    onRefresh: suspend () -> Unit
) {
    log("PullToRefresh")

    if (state.isRefreshing) {
        LaunchedEffect(Unit) {
            onRefresh()
            state.endRefresh()
        }
    }

    PullToRefreshContainer(
        modifier = modifier,
        state = state
    )
}

@Composable
private fun CenterElements(
    trackWaterProvider: () -> Boolean,
    waterAmountProvider: () -> Float,
    setWater: (Float) -> Unit,
    cardsProvider: () -> List<CardModel>,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
        ,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        item {
            Spacer(modifier = Modifier.height(65.dp))
        }
        if (trackWaterProvider()) {
            item {
                MyWaterPanel(
                    waterAmountProvider = waterAmountProvider,
                    setWater = setWater
                )
            }
        }
        items(cardsProvider()) { card ->
            MyFoodCard(
                iconEatId = card.mealType.getIcon(),
                typeEatId = card.mealType.getLabel(),
                textEmotion = card.emotionDescription,
                emotion = card.emotionType,
            )
        }
        item {
            Spacer(modifier = Modifier.height(65.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() = FoodMoodTheme {
    val cards = listOf(
        CardModel.create(
            mealType = ETypeMeal.BREAKFAST,
            emotionType = ETypeEmotion.NORMAL,
            emotionDescription = "nice",
            foodDescription = "nice",
        ),
        CardModel.create(
            mealType = ETypeMeal.BRUNCH,
            emotionType = ETypeEmotion.BAD,
            emotionDescription = "nice",
            foodDescription = "nice",
        ),
        CardModel.create(
            mealType = ETypeMeal.LUNCH,
            emotionType = ETypeEmotion.VERY_BAD,
            emotionDescription = "nice",
            foodDescription = "nice",
        ),
        CardModel.create(
            mealType = ETypeMeal.SUPPER,
            emotionType = ETypeEmotion.GOOD,
            emotionDescription = "nice",
            foodDescription = "nice",
        ),
        CardModel.create(
            mealType = ETypeMeal.DINNER,
            emotionType = ETypeEmotion.GOOD,
            emotionDescription = "nice",
            foodDescription = "nice",
        ),
    )

    val waterAmountState = remember { mutableFloatStateOf(0f) }

    HomeScreen(
        trackWaterProvider = { true },
        waterAmountProvider = { waterAmountState.floatValue },
        setWater = { waterAmountState.floatValue += it },
        cardsProvider = { cards },
        onRefresh = {
            delay(5000)
        }
    )
}