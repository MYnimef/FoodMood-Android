package com.mynimef.foodmood.data.models.requests

import com.google.gson.annotations.SerializedName
import com.mynimef.domain.models.requests.IClientDataRequest

data class ClientDataRequest(

    @SerializedName("time_zone")
    override val timeZone: String,

    @SerializedName("days")
    override val days: Long

): IClientDataRequest
