package com.mynimef.foodmood.data.models.responses

import com.google.gson.annotations.SerializedName

data class ClientDataResponse(
    @SerializedName("emotion_data") val emotionData: List<DataResponse>,
    @SerializedName("water_data") val waterData: List<DataResponse>?,
    @SerializedName("weight_data") val weightData: List<DataResponse>?,
)
