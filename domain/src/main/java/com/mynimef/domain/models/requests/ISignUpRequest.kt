package com.mynimef.domain.models.requests

interface ISignUpRequest: ISignInRequest {

    val name: String

    val trackFood: Boolean

    val trackWater: Boolean

    val trackWeight: Boolean

    companion object {
        fun create(
            name: String,
            trackFood: Boolean,
            trackWater: Boolean,
            trackWeight: Boolean,
            email: String,
            password: String,
            device: String
        ) = object : ISignUpRequest {
            override val name: String
                get() = name
            override val trackFood: Boolean
                get() = trackFood
            override val trackWater: Boolean
                get() = trackWater
            override val trackWeight: Boolean
                get() = trackWeight
            override val email: String
                get() = email
            override val password: String
                get() = password
            override val device: String
                get() = device

        }
    }

}