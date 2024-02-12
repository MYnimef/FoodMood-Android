package com.mynimef.data_remote.requests

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mynimef.domain.models.requests.IClientInfoRequest

internal data class ClientInfoRequest(

    val model: IClientInfoRequest,

    @Expose
    @SerializedName("time_zone")
    override val timeZone: String = model.timeZone

): IClientInfoRequest
