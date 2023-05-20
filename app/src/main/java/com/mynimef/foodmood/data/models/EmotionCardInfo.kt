package com.mynimef.foodmood.data.models

import com.mynimef.foodmood.data.models.Emotion

data class EmotionCardInfo (
    val emotion: Emotion,
    val foodType: String,
    val emotionDescription: String,
    val foodDescription: String,
)