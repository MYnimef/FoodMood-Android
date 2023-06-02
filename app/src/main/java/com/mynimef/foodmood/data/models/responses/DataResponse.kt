package com.mynimef.foodmood.data.models.responses

import com.google.gson.annotations.SerializedName

data class DataResponse(
    @SerializedName("value") val value: Float,
    @SerializedName("year") val year: Int,
    @SerializedName("month") val month: Int,
    @SerializedName("day") val day: Int,
)
