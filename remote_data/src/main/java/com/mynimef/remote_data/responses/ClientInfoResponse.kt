package com.mynimef.remote_data.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mynimef.domain.models.responses.IClientInfoResponse

internal data class ClientInfoResponse(

    override val client: ClientResponse,

    @Expose
    @SerializedName("day_cards")
    override val dayCards: List<CardResponse>

): IClientInfoResponse