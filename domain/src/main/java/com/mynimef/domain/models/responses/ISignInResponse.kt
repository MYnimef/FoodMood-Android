package com.mynimef.domain.models.responses

import com.mynimef.domain.models.AccountModel

interface ISignInResponse: AccountModel {

    val accessToken: String

}