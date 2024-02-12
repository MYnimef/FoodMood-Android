package com.mynimef.data_remote.requests

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mynimef.domain.models.ETypeEmotion
import com.mynimef.domain.models.ETypeMeal
import com.mynimef.domain.models.requests.IClientAddCardRequest

internal data class ClientAddCardRequest(

    val model: IClientAddCardRequest,

    @Expose
    @SerializedName("meal_type")
    override val mealType: ETypeMeal = model.mealType,

    @Expose
    @SerializedName("emotion_type")
    override val emotionType: ETypeEmotion = model.emotionType,

    @Expose
    @SerializedName("emotion_description")
    override val emotionDescription: String = model.emotionDescription,

    @Expose
    @SerializedName("food_description")
    override val foodDescription: String? = model.foodDescription,

    @Expose
    @SerializedName("time_zone")
    override val timeZone: String = model.timeZone

): IClientAddCardRequest