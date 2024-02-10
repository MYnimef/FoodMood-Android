package com.mynimef.data_remote.requests

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mynimef.domain.models.requests.IClientDataRequest

internal data class ClientDataRequest(

    val model: IClientDataRequest,

    @Expose
    @SerializedName("time_zone")
    override val timeZone: String = model.timeZone,

    @Expose
    @SerializedName("days")
    override val days: Long = model.days

): IClientDataRequest
