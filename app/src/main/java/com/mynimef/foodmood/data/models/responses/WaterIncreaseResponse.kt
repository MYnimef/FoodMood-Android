package com.mynimef.foodmood.data.models.responses

import com.google.gson.annotations.SerializedName

data class WaterIncreaseResponse(
    @SerializedName("total_amount") val totalAmount: Float,
)
