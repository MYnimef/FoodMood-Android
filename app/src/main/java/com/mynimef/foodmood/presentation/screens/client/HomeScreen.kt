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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.mynimef.foodmood.R
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
    )
}

@Composable
private fun HomeScreen(
    water: Float,
    setWater: (Float) -> Unit,
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
            setWater = setWater
        )
    }
}

@Composable
private fun CenterElements(
    progress: Float,
    setWater: (Float) -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
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
        MyFoodCard(
           iconEatId = R.drawable.ic_food_breakfast,
           typeEatId = R.string.type_food_breakfast,
           textEmotion = "test",
           iconEmotionId = R.drawable.ic_mood_great
        )
        Spacer(modifier = Modifier.height(20.dp))
        MyFoodCard(
            iconEatId = R.drawable.ic_food_lunch,
            typeEatId = R.string.type_food_snack1,
            textEmotion = "test",
            iconEmotionId = R.drawable.ic_mood_great
        )
        Spacer(modifier = Modifier.height(20.dp))
        MyFoodCard(
            iconEatId = R.drawable.ic_food_dinner,
            typeEatId = R.string.type_food_dinner,
            textEmotion = "test",
            iconEmotionId = R.drawable.ic_mood_great
        )
        Spacer(modifier = Modifier.height(20.dp))
        MyFoodCard(
            iconEatId = R.drawable.ic_food_lunch,
            typeEatId = R.string.type_food_snack2,
            textEmotion = "test",
            iconEmotionId = R.drawable.ic_mood_great
        )
        Spacer(modifier = Modifier.height(20.dp))
        MyFoodCard(
            iconEatId = R.drawable.ic_food_supper,
            typeEatId = R.string.type_food_supper,
            textEmotion = "test",
            iconEmotionId = R.drawable.ic_mood_great
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    FoodMoodTheme {
        HomeScreen(
            water = 0f,
            setWater = {},
        )
    }
}