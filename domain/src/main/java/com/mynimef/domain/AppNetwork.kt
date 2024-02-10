package com.mynimef.domain

import com.mynimef.domain.models.requests.IClientAddCardRequest
import com.mynimef.domain.models.requests.IClientDataRequest
import com.mynimef.domain.models.requests.IClientInfoRequest
import com.mynimef.domain.models.requests.ISignInRequest
import com.mynimef.domain.models.requests.ISignUpRequest
import com.mynimef.domain.models.requests.IWaterIncreaseRequest
import com.mynimef.domain.models.responses.ICardResponse
import com.mynimef.domain.models.responses.IClientDataResponse
import com.mynimef.domain.models.responses.IClientInfoResponse
import com.mynimef.domain.models.responses.ISignInResponse
import com.mynimef.domain.models.responses.IWaterIncreaseResponse

interface AppNetwork {

    fun updateRefreshToken(token: String)

    fun updateAccessToken(token: String?)

    suspend fun signUpClient(request: ISignUpRequest): ApiResult<ISignInResponse>

    suspend fun signIn(request: ISignInRequest): ApiResult<ISignInResponse>

    suspend fun clientGetInfo(request: IClientInfoRequest): ApiResult<IClientInfoResponse>

    suspend fun clientAddCard(request: IClientAddCardRequest): ApiResult<ICardResponse>

    suspend fun clientGetDayCards(day: Int, month: Int, year: Int): ApiResult<List<ICardResponse>>

    suspend fun clientIncreaseWater(request: IWaterIncreaseRequest): ApiResult<IWaterIncreaseResponse>

    suspend fun clientGetData(request: IClientDataRequest): ApiResult<IClientDataResponse>

}