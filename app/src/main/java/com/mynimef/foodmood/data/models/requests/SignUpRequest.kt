package com.mynimef.foodmood.data.models.requests

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("track_food") val trackFood: Boolean,
    @SerializedName("track_water") val trackWater: Boolean,
    @SerializedName("track_weight") val trackWeight: Boolean,
    @SerializedName("password") val password: String,
    @SerializedName("device") val device: String,
)
