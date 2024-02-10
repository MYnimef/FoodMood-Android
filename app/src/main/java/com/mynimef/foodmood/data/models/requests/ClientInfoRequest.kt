package com.mynimef.foodmood.data.models.requests

import com.google.gson.annotations.SerializedName
import com.mynimef.domain.models.request.IClientInfoRequest

data class ClientInfoRequest(

    @SerializedName("time_zone")
    override val timeZone: String

): IClientInfoRequest
