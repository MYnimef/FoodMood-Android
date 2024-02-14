package com.mynimef.domain.models.requests

interface IRefreshTokenRequest {

    val refreshToken: String

    companion object {
        fun create(
            refreshToken: String
        ) = object : IRefreshTokenRequest {
            override val refreshToken: String
                get() = refreshToken
        }
    }

}