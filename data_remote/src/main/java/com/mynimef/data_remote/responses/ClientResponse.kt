package com.mynimef.data_remote.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mynimef.domain.models.ClientModel

internal data class ClientResponse(

    @Expose
    @SerializedName("name")
    override val name: String,

    @Expose
    @SerializedName("track_food")
    override val trackFood: Boolean,

    @Expose
    @SerializedName("track_water")
    override val trackWater: Boolean,

    @Expose
    @SerializedName("track_weight")
    override val trackWeight: Boolean,

    @Expose
    @SerializedName("water_amount")
    override val waterAmount: Float

): ClientModel
