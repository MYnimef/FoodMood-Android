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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mynimef.foodmood.R
import com.mynimef.foodmood.presentation.elements.MyDate
import com.mynimef.foodmood.presentation.elements.MyEmotionButton
import com.mynimef.foodmood.presentation.elements.MyIcon
import com.mynimef.foodmood.presentation.elements.MyTextFieldEmotionalCard
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun EmotionCardScreen() {
    val viewModel: EmotionCardViewModel = viewModel()

  EmotionCardScreen(
      emotion = viewModel.emotion.collectAsState().value,
      textEmotion = viewModel.textEmotion.collectAsState().value,
      setTextEmotion = viewModel::setTextEmotion,
      textFood = viewModel.textEmotion.collectAsState().value,
      setTextFood = viewModel::setTextEmotion,
  )
}

@Composable
private fun EmotionCardScreen(
    emotion: String,
    textEmotion: String,
    setTextEmotion: (String) -> Unit,
    textFood: String,
    setTextFood: (String) -> Unit,
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
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CenterElements(
    emotion: String,
    textEmotion: String,
    setTextEmotion: (String) -> Unit,
    textFood: String,
    setTextFood: (String) -> Unit,
) {
    val passwordVisible = rememberSaveable { mutableStateOf(false) }
    Column() {
        MyDate()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 30.dp),
            ) {
                MyIcon(
                    drawableId = R.drawable.ic_food_breakfast,
                    modifier = Modifier.size(55.dp)
                )
                Text(
                    "Breakfast",
                    modifier = Modifier
                        .padding(start = 15.dp)
                        .align(Alignment.CenterVertically),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.onPrimaryContainer),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 6.dp)
                ) {
                    MyEmotionButton(drawableId = R.drawable.ic_mood_great)
                    Spacer(modifier = Modifier.padding(horizontal = 5.dp))
                    MyEmotionButton(drawableId = R.drawable.ic_mood_good)
                    Spacer(modifier = Modifier.padding(horizontal = 5.dp))
                    MyEmotionButton(drawableId = R.drawable.ic_mood_normal)
                    Spacer(modifier = Modifier.padding(horizontal = 5.dp))
                    MyEmotionButton(drawableId = R.drawable.ic_mood_bad)
                    Spacer(modifier = Modifier.padding(horizontal = 5.dp))
                    MyEmotionButton(drawableId = R.drawable.ic_mood_very_bad)
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
                    label = "How do you feel?", onValueChange = setTextEmotion
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
                    label = "How do ypu feel?", onValueChange = setTextFood
                )
            }
        }
    }
}


@Preview
@Composable
fun EmotionCardPrev() {
    FoodMoodTheme {
        EmotionCardScreen(
            emotion = "emotion",
            textEmotion = "textEmotiontest",
            setTextEmotion = {},
            "Food", {}
        )
    }
}