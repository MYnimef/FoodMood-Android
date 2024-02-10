package com.mynimef.remote_data.responses

import com.google.gson.annotations.SerializedName
import com.mynimef.domain.models.responses.IWaterIncreaseResponse

internal data class WaterIncreaseResponse(

    @SerializedName("total_amount")
    override val totalAmount: Float

): IWaterIncreaseResponse
