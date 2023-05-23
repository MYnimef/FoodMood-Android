package com.mynimef.foodmood.data.models.responses

import com.google.gson.annotations.SerializedName

data class SignInResponse(
    @SerializedName("refresh_token") val refreshToken: String,
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("role") val role: Role,
) {
    enum class Role {
        CLIENT,
        TRAINER,
    }
}
