package com.mynimef.foodmood.presentation.screens.client

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
    HomeScreen(
        water = viewModel.water.collectAsState().value,
        setWater = viewModel::setWater,
        cards = viewModel.getCards().collectAsState(initial = emptyList()).value
    )
}

@Composable
private fun HomeScreen(
    water: Float,
    setWater: (Float) -> Unit,
    cards: List<CardEntity>,
) {
    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        MyDate()
        Spacer(modifier = Modifier.height(20.dp))
        CenterElements(
            progress = water,
            setWater = setWater,
            cards = cards
        )
    }
}

@Composable
private fun CenterElements(
    progress: Float,
    setWater: (Float) -> Unit,
    cards: List<CardEntity>,
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
            //.verticalScroll(rememberScrollState())
        ,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        item {
            MyWaterPanel(setWater = setWater)
            LinearProgressIndicator(
                progress = progress,
                color = MaterialTheme.colorScheme.tertiary,
                trackColor = MaterialTheme.colorScheme.tertiaryContainer,
                modifier = Modifier
                    .fillMaxHeight(0.3f)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
            )
        }
        items(cards) { card ->
            MyFoodCard(
                iconEatId = card.mealType.icon,
                typeEatId = card.mealType.label,
                textEmotion = card.emotionDescription,
                iconEmotionId = card.emotionType.icon,
            )
            Spacer(modifier = Modifier.height(20.dp))
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
            water = 0f,
            setWater = {},
            cards = cards,
        )
    }
}