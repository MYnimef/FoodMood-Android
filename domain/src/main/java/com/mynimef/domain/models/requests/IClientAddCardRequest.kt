package com.mynimef.domain.models.requests

import com.mynimef.domain.models.CardModel

interface IClientAddCardRequest: CardModel {

    val timeZone: String

}