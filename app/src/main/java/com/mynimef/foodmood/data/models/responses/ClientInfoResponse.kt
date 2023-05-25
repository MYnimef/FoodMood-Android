package com.mynimef.foodmood.data.models.responses

import com.google.gson.annotations.SerializedName

data class ClientInfoResponse(
    @SerializedName("name") val name: String,
    @SerializedName("track_food") val trackFood: Boolean,
    @SerializedName("track_water") val trackWater: Boolean,
    @SerializedName("track_weight") val trackWeight: Boolean,
    @SerializedName("water_amount") val waterAmount: Float,
    @SerializedName("day_cards") val dayCards: List<CardResponse>
)