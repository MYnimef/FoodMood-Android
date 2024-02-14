package com.mynimef.domain.models

import com.mynimef.domain.models.enums.ETypeEmotion
import com.mynimef.domain.models.enums.ETypeMeal

interface CardModel {

    val mealType: ETypeMeal

    val emotionType: ETypeEmotion

    val emotionDescription: String

    val foodDescription: String?

    companion object {
        fun create(
            mealType: ETypeMeal,
            emotionType: ETypeEmotion,
            emotionDescription: String,
            foodDescription: String?
        ): CardModel {
            return object : CardModel {
                override val mealType: ETypeMeal
                    get() = mealType
                override val emotionType: ETypeEmotion
                    get() = emotionType
                override val emotionDescription: String
                    get() = emotionDescription
                override val foodDescription: String?
                    get() = foodDescription
            }
        }
    }

}
