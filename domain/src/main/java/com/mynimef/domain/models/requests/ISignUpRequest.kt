package com.mynimef.domain.models.requests

interface ISignUpRequest: ISignInRequest {

    val name: String

    val trackFood: Boolean

    val trackWater: Boolean

    val trackWeight: Boolean

}