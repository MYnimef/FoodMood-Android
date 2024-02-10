package com.mynimef.domain.models.response

import com.mynimef.domain.models.AccountModel

interface ISignInResponse: AccountModel {

    val accessToken: String

}