package com.mynimef.remote_data.requests

import com.google.gson.annotations.SerializedName
import com.mynimef.domain.models.requests.ISignUpRequest

internal data class SignUpRequest(

    @SerializedName("name")
    override val name: String,

    @SerializedName("email")
    override val email: String,

    @SerializedName("track_food")
    override val trackFood: Boolean,

    @SerializedName("track_water")
    override val trackWater: Boolean,

    @SerializedName("track_weight")
    override val trackWeight: Boolean,

    @SerializedName("password")
    override val password: String,

    @SerializedName("device")
    override val device: String

): ISignUpRequest {

    companion object {
        fun fromModel(model: ISignUpRequest) = SignUpRequest(
            name = model.name,
            email = model.email,
            trackFood = model.trackFood,
            trackWater = model.trackWater,
            trackWeight = model.trackWeight,
            password = model.password,
            device = model.device
        )
    }

}
