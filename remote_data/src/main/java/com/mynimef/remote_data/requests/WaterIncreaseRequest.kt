package com.mynimef.remote_data.requests

import com.google.gson.annotations.SerializedName
import com.mynimef.domain.models.requests.IWaterIncreaseRequest

internal data class WaterIncreaseRequest(

    @SerializedName("amount")
    override val amount: Float,

    @SerializedName("time_zone")
    override val timeZone: String

): IWaterIncreaseRequest {

    companion object {
        fun fromModel(model: IWaterIncreaseRequest) = WaterIncreaseRequest(
            amount = model.amount,
            timeZone = model.timeZone
        )
    }

}
