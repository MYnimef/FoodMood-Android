package com.mynimef.domain.models.requests

interface IClientDataRequest {

    val timeZone: String

    val days: Long

    companion object {
        fun create(
            timeZone: String,
            days: Long
        ) = object: IClientDataRequest {
            override val timeZone: String
                get() = timeZone
            override val days: Long
                get() = days
        }
    }

}