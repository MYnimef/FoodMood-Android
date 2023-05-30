package com.mynimef.foodmood.data.models.responses

import com.google.gson.annotations.SerializedName
import com.mynimef.foodmood.data.models.database.CardEntity
import com.mynimef.foodmood.data.models.enums.ETypeEmotion
import com.mynimef.foodmood.data.models.enums.ETypeMeal

data class CardResponse(
    @SerializedName("meal_type") val mealType: ETypeMeal,
    @SerializedName("emotion_type") val emotionType: ETypeEmotion,
    @SerializedName("emotion_description") val emotionDescription: String,
    @SerializedName("food_description") val foodDescription: String?,
) {
    fun toCardEntity() = CardEntity(
        mealType = mealType,
        emotionType = emotionType,
        emotionDescription = emotionDescription,
        foodDescription = foodDescription,
    )
}
