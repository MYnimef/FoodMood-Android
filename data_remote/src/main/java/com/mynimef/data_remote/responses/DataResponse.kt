package com.mynimef.data_remote.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mynimef.domain.models.responses.IDataResponse

internal data class DataResponse(

    @Expose
    @SerializedName("value")
    override val value: Float,

    @Expose
    @SerializedName("year")
    override val year: Int,

    @Expose
    @SerializedName("month")
    override val month: Int,

    @Expose
    @SerializedName("day")
    override val day: Int

): IDataResponse
