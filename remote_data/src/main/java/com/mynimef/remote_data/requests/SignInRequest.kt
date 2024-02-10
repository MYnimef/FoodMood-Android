package com.mynimef.remote_data.requests

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mynimef.domain.models.requests.ISignInRequest

internal data class SignInRequest(

    val model: ISignInRequest,

    @Expose
    @SerializedName("email")
    override val email: String = model.email,

    @Expose
    @SerializedName("password")
    override val password: String = model.password,

    @Expose
    @SerializedName("device")
    override val device: String = model.device

): ISignInRequest
