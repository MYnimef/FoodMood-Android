package com.mynimef.foodmood.data.models.responses

import com.google.gson.annotations.SerializedName
import com.mynimef.domain.models.response.IDataResponse

data class DataResponse(

    @SerializedName("value")
    override val value: Float,

    @SerializedName("year")
    override val year: Int,

    @SerializedName("month")
    override val month: Int,

    @SerializedName("day")
    override val day: Int

): IDataResponse
