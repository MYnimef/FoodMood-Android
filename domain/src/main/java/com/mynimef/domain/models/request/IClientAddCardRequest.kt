package com.mynimef.domain.models.request

import com.mynimef.domain.models.CardModel

interface IClientAddCardRequest: CardModel {

    val timeZone: String

}