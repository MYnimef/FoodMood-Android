package com.mynimef.foodmood.data.models.responses

import com.google.gson.annotations.SerializedName
import com.mynimef.domain.models.response.IWaterIncreaseResponse

data class WaterIncreaseResponse(

    @SerializedName("total_amount")
    override val totalAmount: Float

): IWaterIncreaseResponse
