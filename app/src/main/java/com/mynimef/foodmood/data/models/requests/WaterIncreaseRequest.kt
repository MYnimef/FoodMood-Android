package com.mynimef.foodmood.data.models.requests

import com.google.gson.annotations.SerializedName

data class WaterIncreaseRequest(
    @SerializedName("amount") val amount: Float,
    @SerializedName("time_zone") val timeZone: String,
)
