package com.mynimef.foodmood.presentation.screens.client

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
    val client = viewModel.getClient().collectAsState().value

    if (client != null) {
        CreateScreenSecond(
            mealType = viewModel.mealType.collectAsState().value,
            emotionType = viewModel.emotionType.collectAsState().value,
            setEmotionType = viewModel::setEmotionType,
            emotionDescription = viewModel.emotionDescription.collectAsState().value,
            setEmotionDescription = viewModel::setEmotionDescription,
            trackFood = client.trackFood,
            foodDescription = viewModel.foodDescription.collectAsState().value,
            setFoodDescription = viewModel::setFoodDescription,
            onComplete = viewModel::create,
        )
    }
}

@Composable
private fun CreateScreenSecond(
    mealType: ETypeMeal,
    emotionType: ETypeEmotion,
    setEmotionType: (ETypeEmotion) -> Unit,
    emotionDescription: String,
    setEmotionDescription: (String) -> Unit,
    trackFood: Boolean,
    foodDescription: String,
    setFoodDescription: (String) -> Unit,
    onComplete: () -> Unit,
) {
    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        CenterElements(
            mealType = mealType,
            emotionType = emotionType,
            setEmotionType = setEmotionType,
            emotionDescription = emotionDescription,
            setEmotionDescription = setEmotionDescription,
            trackFood = trackFood,
            foodDescription = foodDescription,
            setFoodDescription = setFoodDescription,
            onComplete = onComplete,
        )
    }
}

@Composable
private fun CenterElements(
    mealType: ETypeMeal,
    emotionType: ETypeEmotion,
    setEmotionType: (ETypeEmotion) -> Unit,
    emotionDescription: String,
    setEmotionDescription: (String) -> Unit,
    trackFood: Boolean,
    foodDescription: String,
    setFoodDescription: (String) -> Unit,
    onComplete: () -> Unit,
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
                border = BorderStroke(2.dp, MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.2f)),
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
                    MyEmotionButton(
                        emotion = ETypeEmotion.GREAT,
                        selectedEmotion = emotionType,
                        onClick = setEmotionType
                    )
                    MyEmotionButton(
                        emotion = ETypeEmotion.GOOD,
                        selectedEmotion = emotionType,
                        onClick = setEmotionType
                    )
                    MyEmotionButton(
                        emotion = ETypeEmotion.NORMAL,
                        selectedEmotion = emotionType,
                        onClick = setEmotionType
                    )
                    MyEmotionButton(
                        emotion = ETypeEmotion.BAD,
                        selectedEmotion = emotionType,
                        onClick = setEmotionType
                    )
                    MyEmotionButton(
                        emotion = ETypeEmotion.VERY_BAD,
                        selectedEmotion = emotionType,
                        onClick = setEmotionType
                    )
                }
            }
            EditDescription(
                question = stringResource(R.string.emotion_question),
                description = emotionDescription,
                hint = stringResource(id = R.string.write_here),
                setDescription = setEmotionDescription,
            )
            if (trackFood) {
                Spacer(modifier = Modifier.height(10.dp))
                EditDescription(
                    question = stringResource(id = R.string.food_question),
                    description = foodDescription,
                    hint = stringResource(id = R.string.write_here),
                    setDescription = setFoodDescription,
                )
            }
            Button(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 10.dp)
                ,
                enabled = emotionDescription.isNotEmpty(),
                onClick = onComplete,
            ) {
                Text(stringResource(id = R.string.complete))
            }
        }
    }
}

@Composable
private fun EditDescription(
    question: String,
    hint: String,
    description: String,
    setDescription: (String) -> Unit
) {
    Text(
        text = question,
        modifier = Modifier.padding(bottom = 10.dp, top = 10.dp),
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
            value = description,
            hint = hint,
            onValueChange = setDescription,
        )
    }
}

@Preview
@Composable
private fun CreateScreenSecondPreview() {
    FoodMoodTheme {
        CreateScreenSecond(
            mealType = ETypeMeal.DINNER,
            emotionType = ETypeEmotion.NORMAL,
            setEmotionType = {},
            emotionDescription = "",
            setEmotionDescription = {},
            trackFood = true,
            foodDescription = "",
            setFoodDescription = {},
            onComplete = {},
        )
    }
}