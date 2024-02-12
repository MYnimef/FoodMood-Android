package com.mynimef.foodmood.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mynimef.domain.models.ETypeEmotion
import com.mynimef.foodmood.extensions.getIcon
import com.mynimef.foodmood.extensions.noRippleClickable
import com.mynimef.foodmood.theme.FoodMoodTheme

@Composable
fun MyEmotionButton(
    emotion: ETypeEmotion,
    selectedEmotionProvider: () -> ETypeEmotion,
    onClick: (ETypeEmotion) -> Unit,
) {
    val selectedEmotion = selectedEmotionProvider()

    Box(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .size(60.dp)
            .noRippleClickable(onClick = { onClick(emotion) })
    ) {
        if (emotion == selectedEmotion) {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.6f),
                    CircleShape
                ))
        }
        MyIcon(
            modifier = Modifier
                .align(Alignment.Center)
                .size(55.dp),
            drawableId = emotion.getIcon(),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyEmotionButtonPreview() = FoodMoodTheme {
    MyEmotionButton(
        emotion = ETypeEmotion.BAD,
        selectedEmotionProvider = { ETypeEmotion.BAD },
        onClick = {}
    )
}