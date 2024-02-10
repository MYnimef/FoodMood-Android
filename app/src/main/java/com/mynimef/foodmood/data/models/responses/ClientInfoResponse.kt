package com.mynimef.foodmood.data.models.responses

import com.google.gson.annotations.SerializedName
import com.mynimef.domain.models.response.IClientInfoResponse

data class ClientInfoResponse(

    override val client: ClientResponse,

    @SerializedName("day_cards")
    override val dayCards: List<CardResponse>

): IClientInfoResponse