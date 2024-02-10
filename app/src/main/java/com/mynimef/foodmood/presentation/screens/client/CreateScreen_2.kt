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
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mynimef.foodmood.R
import com.mynimef.domain.models.ETypeEmotion
import com.mynimef.domain.models.ETypeMeal
import com.mynimef.foodmood.presentation.elements.MyDate
import com.mynimef.foodmood.presentation.elements.MyEmotionButton
import com.mynimef.foodmood.presentation.elements.MyGradient
import com.mynimef.foodmood.presentation.elements.MyIcon
import com.mynimef.foodmood.presentation.elements.fields.MyTextFieldEmotionalCard
import com.mynimef.foodmood.presentation.extensions.getIcon
import com.mynimef.foodmood.presentation.extensions.getLabel
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun CreateScreen_2(
    viewModel: CreateViewModel
) {
    val client = viewModel.client.collectAsState().value
    val emotion = viewModel.emotionType.collectAsState()
    val emotionDescription = viewModel.emotionDescription.collectAsState()
    val foodDescription = viewModel.foodDescription.collectAsState()
    val isActive = viewModel.buttonCompleteActive.collectAsState()

    if (client != null) {
        CreateScreen_2(
            mealType = viewModel.mealType.collectAsState().value,
            emotionTypeProvider = { emotion.value },
            setEmotionType = viewModel::setEmotionType,
            emotionDescriptionProvider = { emotionDescription.value },
            setEmotionDescription = viewModel::setEmotionDescription,
            trackFood = client.trackFood,
            foodDescriptionProvider = { foodDescription.value },
            setFoodDescription = viewModel::setFoodDescription,
            isActiveProvider = { isActive.value },
            onComplete = viewModel::create,
        )
    }
}

@Composable
private fun CreateScreen_2(
    mealType: ETypeMeal,
    emotionTypeProvider: () -> ETypeEmotion,
    setEmotionType: (ETypeEmotion) -> Unit,
    emotionDescriptionProvider: () -> String,
    setEmotionDescription: (String) -> Unit,
    trackFood: Boolean,
    foodDescriptionProvider: () -> String,
    setFoodDescription: (String) -> Unit,
    isActiveProvider: () -> Boolean,
    onComplete: () -> Unit,
) {
    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .statusBarsPadding()
        ,
    ) {
        CenterElements(
            mealType = mealType,
            emotionTypeProvider = emotionTypeProvider,
            setEmotionType = setEmotionType,
            emotionDescriptionProvider = emotionDescriptionProvider,
            setEmotionDescription = setEmotionDescription,
            trackFood = trackFood,
            foodDescriptionProvider = foodDescriptionProvider,
            setFoodDescription = setFoodDescription,
            isActiveProvider = isActiveProvider,
            onComplete = onComplete,
        )
        MyGradient(
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
            ,
            colorTop = MaterialTheme.colorScheme.background,
            colorBottom = Color.Transparent
        )
        MyDate()
    }
}

@Composable
private fun CenterElements(
    mealType: ETypeMeal,
    emotionTypeProvider: () -> ETypeEmotion,
    setEmotionType: (ETypeEmotion) -> Unit,
    emotionDescriptionProvider: () -> String,
    setEmotionDescription: (String) -> Unit,
    trackFood: Boolean,
    foodDescriptionProvider: () -> String,
    setFoodDescription: (String) -> Unit,
    isActiveProvider: () -> Boolean,
    onComplete: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
            .imePadding()
            .verticalScroll(rememberScrollState())
        ,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ) {
        Row(
            modifier = Modifier
                .padding(top = 65.dp)
            ,
        ) {
            MyIcon(
                drawableId = mealType.getIcon(),
                modifier = Modifier.size(55.dp),
            )
            Text(
                text = stringResource(id = mealType.getLabel()),
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
                    selectedEmotionProvider = emotionTypeProvider,
                    onClick = setEmotionType
                )
                MyEmotionButton(
                    emotion = ETypeEmotion.GOOD,
                    selectedEmotionProvider = emotionTypeProvider,
                    onClick = setEmotionType
                )
                MyEmotionButton(
                    emotion = ETypeEmotion.NORMAL,
                    selectedEmotionProvider = emotionTypeProvider,
                    onClick = setEmotionType
                )
                MyEmotionButton(
                    emotion = ETypeEmotion.BAD,
                    selectedEmotionProvider = emotionTypeProvider,
                    onClick = setEmotionType
                )
                MyEmotionButton(
                    emotion = ETypeEmotion.VERY_BAD,
                    selectedEmotionProvider = emotionTypeProvider,
                    onClick = setEmotionType
                )
            }
        }
        EditDescription(
            question = stringResource(R.string.emotion_question),
            descriptionProvider = emotionDescriptionProvider,
            hint = stringResource(id = R.string.write_here),
            setDescription = setEmotionDescription,
        )
        if (trackFood) {
            Spacer(modifier = Modifier.height(10.dp))
            EditDescription(
                question = stringResource(id = R.string.food_question),
                descriptionProvider = foodDescriptionProvider,
                hint = stringResource(id = R.string.write_here),
                setDescription = setFoodDescription,
            )
        }
        CompleteButton(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 10.dp)
            ,
            isActiveProvider = isActiveProvider,
            onClick = onComplete,
        )
    }
}

@Composable
private fun EditDescription(
    question: String,
    hint: String,
    descriptionProvider: () -> String,
    setDescription: (String) -> Unit,
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
            value = descriptionProvider(),
            hint = hint,
            onValueChange = setDescription,
        )
    }
}

@Composable
private fun CompleteButton(
    modifier: Modifier,
    isActiveProvider: () -> Boolean,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        enabled = isActiveProvider(),
        onClick = onClick,
    ) {
        Text(stringResource(id = R.string.complete))
    }
}

@Preview(showBackground = true)
@Composable
private fun CreateScreen_2_Preview() = FoodMoodTheme {
    val emotionType = remember { mutableStateOf(ETypeEmotion.NORMAL) }
    val emotionDescription = remember { mutableStateOf("") }
    val foodDescription = remember { mutableStateOf("") }

    CreateScreen_2(
        mealType = ETypeMeal.DINNER,
        emotionTypeProvider = { emotionType.value },
        setEmotionType = { emotionType.value = it },
        emotionDescriptionProvider = { emotionDescription.value },
        setEmotionDescription = { emotionDescription.value = it },
        trackFood = true,
        foodDescriptionProvider = { foodDescription.value },
        setFoodDescription = { foodDescription.value = it },
        isActiveProvider = { true },
        onComplete = {}
    )
}