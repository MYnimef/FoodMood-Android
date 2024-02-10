package com.mynimef.remote_data.requests

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

internal data class RefreshTokenRequest(

    @Expose
    @SerializedName("refresh_token")
    val refreshToken: String

)
