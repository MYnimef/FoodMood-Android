package com.mynimef.remote_data.responses

import com.google.gson.annotations.SerializedName
import com.mynimef.domain.models.ERole
import com.mynimef.domain.models.responses.ISignInResponse

internal data class SignInResponse(

    @SerializedName("refresh_token")
    override val refreshToken: String,

    @SerializedName("access_token")
    override val accessToken: String,

    @SerializedName("role")
    override val role: ERole

): ISignInResponse
