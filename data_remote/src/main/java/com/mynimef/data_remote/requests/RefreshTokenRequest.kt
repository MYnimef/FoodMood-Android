package com.mynimef.data_remote.requests

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mynimef.domain.models.requests.IRefreshTokenRequest

internal data class RefreshTokenRequest(

    val model: IRefreshTokenRequest,

    @Expose
    @SerializedName("refresh_token")
    override val refreshToken: String = model.refreshToken

): IRefreshTokenRequest
