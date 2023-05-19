package com.mynimef.foodmood.data.models.requests

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("name") val name: String,
    @SerializedName("device") val device: String,
)
