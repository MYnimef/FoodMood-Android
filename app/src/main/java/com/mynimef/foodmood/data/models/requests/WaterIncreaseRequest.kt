package com.mynimef.foodmood.data.models.requests

import com.google.gson.annotations.SerializedName
import com.mynimef.domain.models.request.IWaterIncreaseRequest

data class WaterIncreaseRequest(

    @SerializedName("amount")
    override val amount: Float,

    @SerializedName("time_zone")
    override val timeZone: String

): IWaterIncreaseRequest
