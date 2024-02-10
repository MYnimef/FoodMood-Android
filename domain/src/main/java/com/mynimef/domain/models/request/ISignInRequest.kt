package com.mynimef.domain.models.request

interface ISignInRequest {

    val email: String

    val password: String

    val device: String

}