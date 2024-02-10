package com.mynimef.data_remote.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mynimef.domain.models.ETypeEmotion
import com.mynimef.domain.models.ETypeMeal
import com.mynimef.domain.models.responses.ICardResponse

internal data class CardResponse(

    @Expose
    @SerializedName("meal_type")
    override val mealType: ETypeMeal,

    @Expose
    @SerializedName("emotion_type")
    override val emotionType: ETypeEmotion,

    @Expose
    @SerializedName("emotion_description")
    override val emotionDescription: String,

    @Expose
    @SerializedName("food_description")
    override val foodDescription: String?

): ICardResponse
