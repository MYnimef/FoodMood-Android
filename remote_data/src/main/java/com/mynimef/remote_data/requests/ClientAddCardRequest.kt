package com.mynimef.remote_data.requests

import com.google.gson.annotations.SerializedName
import com.mynimef.domain.models.ETypeEmotion
import com.mynimef.domain.models.ETypeMeal
import com.mynimef.domain.models.requests.IClientAddCardRequest

internal data class ClientAddCardRequest(

    @SerializedName("meal_type")
    override val mealType: ETypeMeal,

    @SerializedName("emotion_type")
    override val emotionType: ETypeEmotion,

    @SerializedName("emotion_description")
    override val emotionDescription: String,

    @SerializedName("food_description")
    override val foodDescription: String?,

    @SerializedName("time_zone")
    override val timeZone: String

): IClientAddCardRequest {
    companion object {
        fun fromModel(model: IClientAddCardRequest): ClientAddCardRequest {
            return ClientAddCardRequest(
                mealType = model.mealType,
                emotionType = model.emotionType,
                emotionDescription = model.emotionDescription,
                foodDescription = model.foodDescription,
                timeZone = model.timeZone
            )
        }
    }
}