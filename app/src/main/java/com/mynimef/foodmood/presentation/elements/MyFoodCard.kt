package com.mynimef.foodmood.presentation.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mynimef.foodmood.R
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun MyFoodCard(
    iconEatId: Int,
    typeEatId: Int,
    textEmotion: String,
    iconEmotionId: Int,) {
    Column() {
        Row() {
            MyIcon(drawableId=iconEatId,
            modifier = Modifier
                .size(55.dp)
                .align(Alignment.CenterVertically))
            Text(
                stringResource(id = typeEatId),
                modifier = Modifier
                    .padding(start = 15.dp)
                    .align(Alignment.CenterVertically),
                style = MaterialTheme.typography.titleMedium)
        }
        Box(
            modifier = Modifier
                .clip(AbsoluteRoundedCornerShape(10.dp))
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Column() {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        MaterialTheme.colorScheme.background
                    ),
                    modifier = Modifier
                        .padding(start = 10.dp, top = 10.dp),
                    onClick = {}) {
                    Text(
                        text = stringResource(R.string.emotion_button),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Text(
                    text = textEmotion,
                    modifier = Modifier.padding(10.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Column(modifier = Modifier.align(Alignment.TopEnd)) {
                IconButton(
                    modifier = Modifier.size(65.dp),
                    onClick = {}) {
                    MyIcon(
                        drawableId = iconEmotionId,
                        modifier = Modifier.padding(end = 10.dp, top = 14.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun MyFoodCardPreview() {
    FoodMoodTheme {
        MyFoodCard(R.drawable.ic_breakfast, R.string.type_food_breakfast,"fvgeb", R.drawable.ic_mood_great)
    }
}