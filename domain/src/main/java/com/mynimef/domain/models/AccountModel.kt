package com.mynimef.domain.models

import com.mynimef.domain.models.enums.ERole

interface AccountModel {

    val id: Long

    val role: ERole

    val refreshToken: String

}
