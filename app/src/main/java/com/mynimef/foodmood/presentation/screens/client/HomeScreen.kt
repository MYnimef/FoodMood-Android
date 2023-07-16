package com.mynimef.foodmood.presentation.screens.client

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mynimef.foodmood.data.models.database.CardEntity
import com.mynimef.foodmood.data.models.enums.ETypeEmotion
import com.mynimef.foodmood.data.models.enums.ETypeMeal
import com.mynimef.foodmood.presentation.elements.MyDate
import com.mynimef.foodmood.presentation.elements.MyFoodCard
import com.mynimef.foodmood.presentation.elements.MyWaterPanel
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = viewModel()
    val client = viewModel.client.collectAsState().value
    val cardsState = viewModel.getCards().collectAsState(initial = emptyList())

    if (client != null) {
        HomeScreen(
            trackWater = client.trackWater,
            waterAmount = client.waterAmount,
            setWater = viewModel::setWater,
            cardsProvider = { cardsState.value },
            refreshing = viewModel.refreshing.collectAsState().value,
            onRefresh = viewModel::update
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun HomeScreen(
    trackWater: Boolean,
    waterAmount: Float,
    setWater: (Float) -> Unit,
    cardsProvider: () -> List<CardEntity>,
    refreshing: Boolean,
    onRefresh: () -> Unit,
) {
    val pullRefreshState = rememberPullRefreshState(
        refreshing = refreshing,
        onRefresh = onRefresh
    )

    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        CenterElements(
            trackWater = trackWater,
            waterAmount = waterAmount,
            setWater = setWater,
            cardsProvider = cardsProvider,
        )
        MyDate()
        PullRefreshIndicator(
            refreshing = refreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@Composable
private fun CenterElements(
    trackWater: Boolean,
    waterAmount: Float,
    setWater: (Float) -> Unit,
    cardsProvider: () -> List<CardEntity>,
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
        if (trackWater) {
            item {
                MyWaterPanel(setWater = setWater)
                LinearProgressIndicator(
                    progress = waterAmount,
                    color = MaterialTheme.colorScheme.tertiary,
                    trackColor = MaterialTheme.colorScheme.tertiaryContainer,
                    modifier = Modifier
                        .fillMaxHeight(0.3f)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
                )
            }
        }
        items(cardsProvider()) { card ->
            MyFoodCard(
                iconEatId = card.mealType.icon,
                typeEatId = card.mealType.label,
                textEmotion = card.emotionDescription,
                emotion = card.emotionType,
            )
        }
        item {
            Spacer(modifier = Modifier.padding(vertical = 20.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    FoodMoodTheme {
        val cards = listOf(
            CardEntity(
                mealType = ETypeMeal.BREAKFAST,
                emotionType = ETypeEmotion.NORMAL,
                emotionDescription = "nice",
                foodDescription = "nice",
            ),
            CardEntity(
                mealType = ETypeMeal.BRUNCH,
                emotionType = ETypeEmotion.BAD,
                emotionDescription = "nice",
                foodDescription = "nice",
            ),
            CardEntity(
                mealType = ETypeMeal.LUNCH,
                emotionType = ETypeEmotion.VERY_BAD,
                emotionDescription = "nice",
                foodDescription = "nice",
            ),
            CardEntity(
                mealType = ETypeMeal.SUPPER,
                emotionType = ETypeEmotion.GOOD,
                emotionDescription = "nice",
                foodDescription = "nice",
            ),
            CardEntity(
                mealType = ETypeMeal.DINNER,
                emotionType = ETypeEmotion.GOOD,
                emotionDescription = "nice",
                foodDescription = "nice",
            ),
        )

        HomeScreen(
            trackWater = true,
            waterAmount = 0f,
            setWater = {},
            cardsProvider = { cards },
            refreshing = false,
            onRefresh = {}
        )
    }
}