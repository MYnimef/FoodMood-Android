package com.mynimef.remote_data.responses

import com.google.gson.annotations.SerializedName
import com.mynimef.domain.models.ClientModel

internal data class ClientResponse(

    @SerializedName("name")
    override val name: String,

    @SerializedName("track_food")
    override val trackFood: Boolean,

    @SerializedName("track_water")
    override val trackWater: Boolean,

    @SerializedName("track_weight")
    override val trackWeight: Boolean,

    @SerializedName("water_amount")
    override val waterAmount: Float

): ClientModel
