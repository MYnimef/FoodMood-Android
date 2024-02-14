package com.mynimef.domain

import com.mynimef.domain.models.requests.IRefreshTokenRequest
import com.mynimef.domain.models.responses.IRefreshTokenResponse

interface ITokenRefresher {

    suspend fun refreshToken(request: IRefreshTokenRequest): ApiResult<IRefreshTokenResponse>

}