package com.mynimef.domain.models

interface AccountModel {

    val id: Long

    val role: ERole

    val refreshToken: String

}
