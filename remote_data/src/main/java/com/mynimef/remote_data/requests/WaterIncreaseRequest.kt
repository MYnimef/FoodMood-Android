package com.mynimef.remote_data.requests

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mynimef.domain.models.requests.IWaterIncreaseRequest

internal data class WaterIncreaseRequest(

    val model: IWaterIncreaseRequest,

    @Expose
    @SerializedName("amount")
    override val amount: Float = model.amount,

    @Expose
    @SerializedName("time_zone")
    override val timeZone: String = model.timeZone

): IWaterIncreaseRequest
