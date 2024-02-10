package com.mynimef.foodmood.data.models.responses

import com.google.gson.annotations.SerializedName
import com.mynimef.domain.models.responses.IRefreshTokenResponse

data class RefreshTokenResponse(

    @SerializedName("access_token")
    override val accessToken: String

): IRefreshTokenResponse
