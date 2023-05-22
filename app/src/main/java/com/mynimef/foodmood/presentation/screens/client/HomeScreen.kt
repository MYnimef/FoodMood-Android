package com.mynimef.foodmood.presentation.screens.client

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mynimef.foodmood.R
import com.mynimef.foodmood.presentation.elements.MyDate
import com.mynimef.foodmood.presentation.elements.MyFoodCard
import com.mynimef.foodmood.presentation.elements.MyIcon
import com.mynimef.foodmood.presentation.elements.MyTextFieldSettings
import com.mynimef.foodmood.presentation.screens.trainer.HomeViewModel
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = viewModel()

    val waterL = remember { mutableStateOf("") }
    val weightKg = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        MyDate()
        Spacer(modifier = Modifier.height(20.dp))
        CenterElements()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CenterElements() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        MyFoodCard(
           iconEatId = R.drawable.ic_breakfast,
           typeEatId = R.string.type_food_breakfast,
           textEmotion = "test",
           iconEmotionId = R.drawable.ic_mood_great
        )
        Spacer(modifier = Modifier.height(20.dp))
        MyFoodCard(
            iconEatId = R.drawable.ic_breakfast,
            typeEatId = R.string.type_food_snack,
            textEmotion = "test",
            iconEmotionId = R.drawable.ic_mood_great
        )
        Spacer(modifier = Modifier.height(20.dp))
        MyFoodCard(
            iconEatId = R.drawable.ic_dinner,
            typeEatId = R.string.type_food_dinner,
            textEmotion = "test",
            iconEmotionId = R.drawable.ic_mood_great
        )
        Spacer(modifier = Modifier.height(20.dp))
        MyFoodCard(
            iconEatId = R.drawable.ic_breakfast,
            typeEatId = R.string.type_food_snack,
            textEmotion = "test",
            iconEmotionId = R.drawable.ic_mood_great
        )
        Spacer(modifier = Modifier.height(20.dp))
        MyFoodCard(
            iconEatId = R.drawable.ic_supper,
            typeEatId = R.string.type_food_supper,
            textEmotion = "test",
            iconEmotionId = R.drawable.ic_mood_great
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Preview
@Composable
fun HomePrev() {
    FoodMoodTheme {
        HomeScreen()
    }
}