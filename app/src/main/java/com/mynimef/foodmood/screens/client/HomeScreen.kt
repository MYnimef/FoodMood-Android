package com.mynimef.foodmood.screens.client

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
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mynimef.domain.models.CardModel
import com.mynimef.domain.models.ETypeEmotion
import com.mynimef.domain.models.ETypeMeal
import com.mynimef.foodmood.elements.MyDate
import com.mynimef.foodmood.elements.MyFoodCard
import com.mynimef.foodmood.elements.MyGradient
import com.mynimef.foodmood.elements.MyPullToUpdate
import com.mynimef.foodmood.elements.MyWaterPanel
import com.mynimef.foodmood.extensions.getIcon
import com.mynimef.foodmood.extensions.getLabel
import com.mynimef.foodmood.theme.FoodMoodTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = hiltViewModel()

    val updatingState = viewModel.updateFlow.collectAsStateWithLifecycle()
    val trackWaterState = viewModel.trackWater.collectAsStateWithLifecycle()
    val waterAmountState = viewModel.waterAmount.collectAsStateWithLifecycle()
    val cardsState = viewModel.cards.collectAsStateWithLifecycle()

    if (viewModel.dataLoaded.collectAsStateWithLifecycle().value) {
        HomeScreen(
            updatingProvider = { updatingState.value },
            trackWaterProvider = { trackWaterState.value },
            waterAmountProvider = { waterAmountState.value },
            cardsProvider = { cardsState.value },
            setWater = viewModel::setWater,
            onUpdate = viewModel::updateAccount
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    updatingProvider: () -> Boolean,
    trackWaterProvider: () -> Boolean,
    waterAmountProvider: () -> Float,
    cardsProvider: () -> List<CardModel>,
    setWater: (Float) -> Unit,
    onUpdate: () -> Unit,
) {
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
        MyPullToUpdate(
            modifier = Modifier.align(Alignment.TopCenter),
            state = pullRefreshState,
            updatingProvider = updatingProvider,
            onUpdate = onUpdate
        )
    }
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

    val updatingState = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    HomeScreen(
        updatingProvider = { updatingState.value },
        trackWaterProvider = { true },
        waterAmountProvider = { waterAmountState.floatValue },
        cardsProvider = { cards },
        setWater = { waterAmountState.floatValue += it },
        onUpdate = {
            coroutineScope.launch {
                updatingState.value = true
                delay(5000)
                updatingState.value = false
            }
        }
    )
}