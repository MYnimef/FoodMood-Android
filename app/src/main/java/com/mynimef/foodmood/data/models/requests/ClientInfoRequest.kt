package com.mynimef.foodmood.data.models.requests

import com.google.gson.annotations.SerializedName

data class ClientInfoRequest(
    @SerializedName("time_zone") val timeZone: String
)
