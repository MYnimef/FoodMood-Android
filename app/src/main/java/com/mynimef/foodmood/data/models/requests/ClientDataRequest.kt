package com.mynimef.foodmood.data.models.requests

import com.google.gson.annotations.SerializedName

data class ClientDataRequest(
    @SerializedName("time_zone") val timeZone: String,
    @SerializedName("days") val days: Long,
)
