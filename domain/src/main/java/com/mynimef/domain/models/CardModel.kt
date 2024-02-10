package com.mynimef.domain.models

interface CardModel {

    val mealType: ETypeMeal

    val emotionType: ETypeEmotion

    val emotionDescription: String

    val foodDescription: String?

}
