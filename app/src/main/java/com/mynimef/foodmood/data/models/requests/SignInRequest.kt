package com.mynimef.foodmood.data.models.requests

import com.google.gson.annotations.SerializedName
import com.mynimef.domain.models.requests.ISignInRequest

data class SignInRequest(

    @SerializedName("email")
    override val email: String,

    @SerializedName("password")
    override val password: String,

    @SerializedName("device")
    override val device: String

): ISignInRequest
