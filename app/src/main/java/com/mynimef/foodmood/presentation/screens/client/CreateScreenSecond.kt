package com.mynimef.foodmood.presentation.screens.client

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mynimef.foodmood.R
import com.mynimef.foodmood.data.models.enums.ETypeEmotion
import com.mynimef.foodmood.data.models.enums.ETypeMeal
import com.mynimef.foodmood.presentation.elements.MyDate
import com.mynimef.foodmood.presentation.elements.MyEmotionButton
import com.mynimef.foodmood.presentation.elements.MyIcon
import com.mynimef.foodmood.presentation.elements.MyTextFieldEmotionalCard
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun CreateScreenSecond(
    viewModel: CreateViewModel
) {
    CreateScreenSecond(
        emotion = viewModel.emotion.collectAsState().value,
        textEmotion = viewModel.textEmotion.collectAsState().value,
        setTextEmotion = viewModel::setTextEmotion,
        textFood = viewModel.textFood.collectAsState().value,
        setTextFood = viewModel::setTextFood,
        mealType = viewModel.mealType.collectAsState().value,
        setMealType = viewModel::setMealType,
    )
}

@Composable
private fun CreateScreenSecond(
    emotion: String,
    textEmotion: String,
    setTextEmotion: (String) -> Unit,
    textFood: String,
    setTextFood: (String) -> Unit,
    mealType: ETypeMeal,
    setMealType: (ETypeMeal) -> Unit,
) {
    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        CenterElements(
            emotion = emotion,
            textEmotion = textEmotion,
            setTextEmotion = setTextEmotion,
            textFood = textFood,
            setTextFood = setTextFood,
            mealType = mealType,
            setMealType = setMealType,
        )
    }
}

@Composable
private fun CenterElements(
    emotion: String,
    textEmotion: String,
    setTextEmotion: (String) -> Unit,
    textFood: String,
    setTextFood: (String) -> Unit,
    mealType: ETypeMeal,
    setMealType: (ETypeMeal) -> Unit,
) {
    Column {
        MyDate()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
                .verticalScroll(rememberScrollState())
            ,
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 30.dp)
                ,
            ) {
                MyIcon(
                    drawableId = mealType.icon,
                    modifier = Modifier.size(55.dp),
                )
                Text(
                    text = stringResource(id = mealType.label),
                    modifier = Modifier
                        .padding(start = 15.dp)
                        .align(Alignment.CenterVertically)
                    ,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
                ,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.onPrimaryContainer),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 6.dp)
                    ,
                    horizontalArrangement = Arrangement.spacedBy(
                        space = 5.dp,
                        alignment = Alignment.CenterHorizontally,
                    )
                ) {
                    MyEmotionButton(drawableId = ETypeEmotion.GREAT.icon)
                    MyEmotionButton(drawableId = ETypeEmotion.GOOD.icon)
                    MyEmotionButton(drawableId = ETypeEmotion.NORMAL.icon)
                    MyEmotionButton(drawableId = ETypeEmotion.BAD.icon)
                    MyEmotionButton(drawableId = ETypeEmotion.VERY_BAD.icon)
                }
            }
            Text(
                text = stringResource(id = R.string.emotion_question),
                modifier = Modifier.padding(bottom = 10.dp),
                style = MaterialTheme.typography.titleMedium
            )
            Box(
                modifier = Modifier
                    .clip(AbsoluteRoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f)
                    .background(color = MaterialTheme.colorScheme.primaryContainer)
            ) {
                MyTextFieldEmotionalCard(
                    value = textEmotion,
                    hint = stringResource(id = R.string.write_here), onValueChange = setTextEmotion
                )
            }
            Text(
                text = stringResource(id = R.string.food_question),
                modifier = Modifier.padding(bottom = 10.dp, top = 20.dp),
                style = MaterialTheme.typography.titleMedium
            )
            Box(
                modifier = Modifier
                    .clip(AbsoluteRoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f)
                    .background(color = MaterialTheme.colorScheme.primaryContainer)
            ) {
                MyTextFieldEmotionalCard(
                    value = textFood,
                    hint = stringResource(id = R.string.write_here), onValueChange = setTextFood
                )
            }
            Button(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 10.dp),
                enabled = textEmotion.isNotEmpty(),
                onClick = {},) {
                Text(stringResource(id = R.string.complete))
            }
        }
    }
}


@Preview
@Composable
private fun CreateScreenSecondPreview() {
    FoodMoodTheme {
        CreateScreenSecond(
            emotion = "emotion",
            textEmotion = "",
            setTextEmotion = {},
            textFood = "textEmotiontest",
            setTextFood = {},
            mealType = ETypeMeal.DINNER,
            setMealType = {},
        )
    }
}