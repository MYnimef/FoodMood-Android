package com.mynimef.remote_data.responses

import com.google.gson.annotations.SerializedName
import com.mynimef.domain.models.responses.IClientDataResponse

internal data class ClientDataResponse(

    @SerializedName("emotion_data")
    override val emotionData: List<DataResponse>,

    @SerializedName("water_data")
    override val waterData: List<DataResponse>?,

    @SerializedName("weight_data")
    override val weightData: List<DataResponse>?

): IClientDataResponse
