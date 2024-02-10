package com.mynimef.remote_data.responses

import com.google.gson.annotations.SerializedName
import com.mynimef.domain.models.responses.IDataResponse

internal data class DataResponse(

    @SerializedName("value")
    override val value: Float,

    @SerializedName("year")
    override val year: Int,

    @SerializedName("month")
    override val month: Int,

    @SerializedName("day")
    override val day: Int

): IDataResponse
