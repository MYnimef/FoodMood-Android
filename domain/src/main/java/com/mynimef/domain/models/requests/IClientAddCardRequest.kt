package com.mynimef.domain.models.requests

import com.mynimef.domain.models.CardModel
import com.mynimef.domain.models.ETypeEmotion
import com.mynimef.domain.models.ETypeMeal

interface IClientAddCardRequest: CardModel {

    val timeZone: String

    companion object {
        fun create(
            timeZone: String,
            mealType: ETypeMeal,
            emotionType: ETypeEmotion,
            emotionDescription: String,
            foodDescription: String?
        ) = object : IClientAddCardRequest {
            override val timeZone: String
                get() = timeZone
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