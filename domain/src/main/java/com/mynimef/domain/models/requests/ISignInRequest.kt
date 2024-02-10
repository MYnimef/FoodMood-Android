package com.mynimef.domain.models.requests

interface ISignInRequest {

    val email: String

    val password: String

    val device: String

    companion object {
        fun create(
            email: String,
            password: String,
            device: String
        ) = object : ISignInRequest {
            override val email: String
                get() = email
            override val password: String
                get() = password
            override val device: String
                get() = device
        }
    }

}