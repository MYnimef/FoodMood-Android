package com.mynimef.domain.models.requests

interface IClientInfoRequest {

    val timeZone: String

    companion object {
        fun create(
            timeZone: String
        ) = object : IClientInfoRequest {
            override val timeZone: String
                get() = timeZone
        }
    }

}