package com.mynimef.foodmood.presentation.elements

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mynimef.foodmood.data.models.enums.ETypeEmotion

@Composable
fun MyEmotionButton(
    emotion: ETypeEmotion,
    onClick: (ETypeEmotion) -> Unit,
) {
    IconButton(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .size(60.dp)
        ,
        onClick = { onClick(emotion) }
    ) {
        MyIcon(
            drawableId = emotion.icon,
            modifier = Modifier.size(55.dp)
        )
    }
}