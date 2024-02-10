package com.mynimef.foodmood.data.models.responses

import com.google.gson.annotations.SerializedName
import com.mynimef.domain.models.response.IRefreshTokenResponse

data class RefreshTokenResponse(

    @SerializedName("access_token")
    override val accessToken: String

): IRefreshTokenResponse
