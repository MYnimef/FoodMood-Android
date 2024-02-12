package com.mynimef.data_remote.requests

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mynimef.domain.models.requests.ISignUpRequest

internal data class SignUpRequest(

    val model: ISignUpRequest,

    @Expose
    @SerializedName("name")
    override val name: String = model.name,

    @Expose
    @SerializedName("email")
    override val email: String = model.email,

    @Expose
    @SerializedName("track_food")
    override val trackFood: Boolean = model.trackFood,

    @Expose
    @SerializedName("track_water")
    override val trackWater: Boolean = model.trackWater,

    @Expose
    @SerializedName("track_weight")
    override val trackWeight: Boolean = model.trackWeight,

    @Expose
    @SerializedName("password")
    override val password: String = model.password,

    @Expose
    @SerializedName("device")
    override val device: String = model.device

): ISignUpRequest
