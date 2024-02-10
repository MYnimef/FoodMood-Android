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

interface AppNetwork<

        SignUpReq: ISignUpRequest,
        SignInReq: ISignInRequest,
        ClientInfoReq: IClientInfoRequest,
        ClientAddCardReq: IClientAddCardRequest,
        WaterIncreaseReq: IWaterIncreaseRequest,
        ClientDataReq: IClientDataRequest,

        SignInResp: ISignInResponse,
        ClientInfoResp: IClientInfoResponse,
        CardResp: ICardResponse,
        WaterIncreaseResp: IWaterIncreaseResponse,
        ClientDataResp: IClientDataResponse

        >
{

    fun updateRefreshToken(token: String)

    fun updateAccessToken(token: String?)

    suspend fun signUpClient(request: SignUpReq): ApiResult<SignInResp>

    suspend fun signIn(request: SignInReq): ApiResult<SignInResp>

    suspend fun clientGetInfo(request: ClientInfoReq): ApiResult<ClientInfoResp>

    suspend fun clientAddCard(request: ClientAddCardReq): ApiResult<CardResp>

    suspend fun clientGetDayCards(day: Int, month: Int, year: Int): ApiResult<List<CardResp>>

    suspend fun clientIncreaseWater(request: WaterIncreaseReq): ApiResult<WaterIncreaseResp>

    suspend fun clientGetData(request: ClientDataReq): ApiResult<ClientDataResp>

}