package com.mynimef.remote_data.requests

import com.google.gson.annotations.SerializedName
import com.mynimef.domain.models.requests.IClientInfoRequest

internal data class ClientInfoRequest(

    @SerializedName("time_zone")
    override val timeZone: String

): IClientInfoRequest {

    companion object {
        fun fromModel(model: IClientInfoRequest) = ClientInfoRequest(
            timeZone = model.timeZone
        )
    }

}
