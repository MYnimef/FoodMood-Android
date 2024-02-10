package com.mynimef.remote_data.requests

import com.google.gson.annotations.SerializedName
import com.mynimef.domain.models.requests.IClientDataRequest

internal data class ClientDataRequest(

    @SerializedName("time_zone")
    override val timeZone: String,

    @SerializedName("days")
    override val days: Long

): IClientDataRequest {

    companion object {
        fun fromModel(model: IClientDataRequest) = ClientDataRequest(
            timeZone = model.timeZone,
            days = model.days
        )
    }

}
