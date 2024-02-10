package com.mynimef.domain.models.requests

interface ISignInRequest {

    val email: String

    val password: String

    val device: String

}