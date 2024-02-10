package com.mynimef.foodmood.data.models.requests

import com.google.gson.annotations.SerializedName
import com.mynimef.domain.models.ETypeEmotion
import com.mynimef.domain.models.ETypeMeal
import com.mynimef.domain.models.requests.IClientAddCardRequest

data class ClientAddCardRequest(

    @SerializedName("meal_type")
    override val mealType: ETypeMeal,

    @SerializedName("emotion_type")
    override val emotionType: ETypeEmotion,

    @SerializedName("emotion_description")
    override val emotionDescription: String,

    @SerializedName("food_description")
    override val foodDescription: String?,

    @SerializedName("time_zone")
    override val timeZone: String,

    ): IClientAddCardRequest