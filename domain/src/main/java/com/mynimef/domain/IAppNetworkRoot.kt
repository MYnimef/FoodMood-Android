package com.mynimef.domain

import com.mynimef.domain.models.requests.ISignInRequest
import com.mynimef.domain.models.requests.ISignUpRequest
import com.mynimef.domain.models.responses.ISignInResponse

interface IAppNetworkRoot {

    suspend fun signUpClient(request: ISignUpRequest): ApiResult<ISignInResponse>

    suspend fun signIn(request: ISignInRequest): ApiResult<ISignInResponse>

}