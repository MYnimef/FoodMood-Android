package com.mynimef.data_remote.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mynimef.domain.models.responses.IClientDataResponse

internal data class ClientDataResponse(

    @Expose
    @SerializedName("emotion_data")
    override val emotionData: List<DataResponse>,

    @Expose
    @SerializedName("water_data")
    override val waterData: List<DataResponse>?,

    @Expose
    @SerializedName("weight_data")
    override val weightData: List<DataResponse>?

): IClientDataResponse
