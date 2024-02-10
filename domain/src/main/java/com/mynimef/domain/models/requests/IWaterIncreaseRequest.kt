package com.mynimef.domain.models.requests

interface IWaterIncreaseRequest {

    val amount: Float

    val timeZone: String

    companion object {
        fun create(
            amount: Float,
            timeZone: String
        ) = object : IWaterIncreaseRequest {
            override val amount: Float
                get() = amount
            override val timeZone: String
                get() = timeZone
        }
    }

}