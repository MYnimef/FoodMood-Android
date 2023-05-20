package com.mynimef.foodmood.data.models

import com.mynimef.foodmood.data.models.Emotion

data class EmotionCardInfoCreate(
    val emotion: Emotion,
    val emotionDescription: String,
    val foodDescription: String,
)
