package com.mynimef.foodmood.presentation.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.mynimef.foodmood.data.models.enums.ETypeEmotion
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme
import racra.compose.smooth_corner_rect_library.AbsoluteSmoothCornerShape

@Composable
fun MyFoodCard(
    modifier: Modifier = Modifier,
    iconEatId: Int,
    typeEatId: Int,
    textEmotion: String,
    emotion: ETypeEmotion,
) {
    Column(modifier = modifier) {
        Row {
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
                .clip(AbsoluteSmoothCornerShape(10.dp, 100))
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
            Column(modifier = Modifier.align(Alignment.TopEnd).padding(end = 15.dp)) {
                IconButton(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .size(60.dp)
                    ,
                    onClick = {  }
                ) {
                    MyIcon(
                        drawableId = emotion.icon,
                        modifier = Modifier
                            .size(55.dp)
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
        MyFoodCard(
            iconEatId = R.drawable.ic_food_breakfast,
            typeEatId = R.string.type_food_breakfast,
            textEmotion = "fvgeb",
            emotion = ETypeEmotion.BAD
        )
    }
}