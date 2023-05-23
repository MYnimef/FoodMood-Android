package com.mynimef.foodmood.data.models.requests

import com.google.gson.annotations.SerializedName
import com.mynimef.foodmood.data.models.enums.ETypeEmotion
import com.mynimef.foodmood.data.models.enums.ETypeMeal

data class ClientAddCardRequest(
    @SerializedName("meal_type") val mealType: ETypeMeal,
    @SerializedName("emotion_type") val emotionType: ETypeEmotion,
    @SerializedName("emotion_description") val emotionDescription: String,
    @SerializedName("food_description") val foodDescription: String?,
    @SerializedName("time_zone") val timeZone: String,
)