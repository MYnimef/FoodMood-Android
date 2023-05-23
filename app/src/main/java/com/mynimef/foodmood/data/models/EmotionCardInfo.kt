package com.mynimef.foodmood.data.models

import com.mynimef.foodmood.data.models.enums.ETypeEmotion

data class EmotionCardInfo (
    val emotion: ETypeEmotion,
    val foodType: String,
    val emotionDescription: String,
    val foodDescription: String,
)