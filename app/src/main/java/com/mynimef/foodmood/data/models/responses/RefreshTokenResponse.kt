package com.mynimef.foodmood.data.models.responses

import com.google.gson.annotations.SerializedName

data class RefreshTokenResponse(
    @SerializedName("access_token") val accessToken: String
)
