package com.mynimef.remote_data.requests

import com.google.gson.annotations.SerializedName
import com.mynimef.domain.models.requests.ISignInRequest

internal data class SignInRequest(

    @SerializedName("email")
    override val email: String,

    @SerializedName("password")
    override val password: String,

    @SerializedName("device")
    override val device: String

): ISignInRequest {

    companion object {
        fun fromModel(model: ISignInRequest) = SignInRequest(
            email = model.email,
            password = model.password,
            device = model.device
        )
    }

}
