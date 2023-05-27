package com.mynimef.foodmood.presentation.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mynimef.foodmood.data.models.enums.ETypeEmotion
import com.mynimef.foodmood.extensions.noRippleClickable
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun MyEmotionButton(
    emotion: ETypeEmotion,
    selectedEmotion: ETypeEmotion,
    onClick: (ETypeEmotion) -> Unit,
) {
    Box(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .size(60.dp)
            .noRippleClickable (onClick = { onClick(emotion) })
    ) {
        if (emotion == selectedEmotion) {
            Box(modifier = Modifier.fillMaxSize().background(Color.Black, CircleShape))
        }
        MyIcon(
            modifier = Modifier.align(Alignment.Center).size(55.dp),
            drawableId = emotion.icon,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyEmotionButtonPreview() {
    FoodMoodTheme {
        MyEmotionButton(
            emotion = ETypeEmotion.BAD,
            selectedEmotion = ETypeEmotion.BAD,
            onClick = {},
        )
    }
}