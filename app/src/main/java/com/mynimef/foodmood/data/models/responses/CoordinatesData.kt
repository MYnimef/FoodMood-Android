package com.mynimef.foodmood.data.models.responses

import com.google.gson.annotations.SerializedName

data class CoordinatesData(
    @SerializedName("x") val x: Float,
    @SerializedName("y") val y: Float,
)
