package com.mynimef.domain.models.responses

import com.mynimef.domain.models.CardModel
import com.mynimef.domain.models.ClientModel

interface IClientInfoResponse: ClientModel {

    val dayCards: List<CardModel>

}