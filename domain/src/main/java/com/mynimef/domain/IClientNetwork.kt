package com.mynimef.domain

import com.mynimef.domain.models.requests.IClientAddCardRequest
import com.mynimef.domain.models.requests.IClientDataRequest
import com.mynimef.domain.models.requests.IClientInfoRequest
import com.mynimef.domain.models.requests.IWaterIncreaseRequest
import com.mynimef.domain.models.responses.ICardResponse
import com.mynimef.domain.models.responses.IClientDataResponse
import com.mynimef.domain.models.responses.IClientInfoResponse
import com.mynimef.domain.models.responses.IWaterIncreaseResponse

interface IClientNetwork {

    suspend fun getInfo(accountId: Long, request: IClientInfoRequest): ApiResult<IClientInfoResponse>

    suspend fun addCard(accountId: Long, request: IClientAddCardRequest): ApiResult<ICardResponse>

    suspend fun getDayCards(accountId: Long, day: Int, month: Int, year: Int): ApiResult<List<ICardResponse>>

    suspend fun increaseWater(accountId: Long, request: IWaterIncreaseRequest): ApiResult<IWaterIncreaseResponse>

    suspend fun getData(accountId: Long, request: IClientDataRequest): ApiResult<IClientDataResponse>

}