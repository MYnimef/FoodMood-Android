package com.mynimef.foodmood.data.models

import com.mynimef.foodmood.data.models.enums.ETypeEmotion

data class EmotionCardInfoCreate(
    val emotion: ETypeEmotion,
    val emotionDescription: String,
    val foodDescription: String,
)
