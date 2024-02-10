package com.mynimef.remote_data.responses

import com.google.gson.annotations.SerializedName
import com.mynimef.domain.models.responses.IRefreshTokenResponse

internal data class RefreshTokenResponse(

    @SerializedName("access_token")
    override val accessToken: String

): IRefreshTokenResponse
