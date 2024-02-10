package com.mynimef.data_remote.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mynimef.domain.models.ERole
import com.mynimef.domain.models.responses.ISignInResponse

internal data class SignInResponse(

    @Expose
    @SerializedName("refresh_token")
    override val refreshToken: String,

    @Expose
    @SerializedName("access_token")
    override val accessToken: String,

    @Expose
    @SerializedName("role")
    override val role: ERole

): ISignInResponse
