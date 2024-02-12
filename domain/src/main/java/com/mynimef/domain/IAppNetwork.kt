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

interface IAppNetwork {

    suspend fun signUpClient(request: ISignUpRequest): ApiResult<ISignInResponse>

    suspend fun signIn(request: ISignInRequest): ApiResult<ISignInResponse>

    suspend fun clientGetInfo(accountId: Long, request: IClientInfoRequest): ApiResult<IClientInfoResponse>

    suspend fun clientAddCard(accountId: Long, request: IClientAddCardRequest): ApiResult<ICardResponse>

    suspend fun clientGetDayCards(accountId: Long, day: Int, month: Int, year: Int): ApiResult<List<ICardResponse>>

    suspend fun clientIncreaseWater(accountId: Long, request: IWaterIncreaseRequest): ApiResult<IWaterIncreaseResponse>

    suspend fun clientGetData(accountId: Long, request: IClientDataRequest): ApiResult<IClientDataResponse>

}