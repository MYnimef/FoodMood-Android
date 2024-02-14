package com.mynimef.data_remote

import com.google.gson.GsonBuilder
import com.mynimef.data_remote.api.ClientAPI
import com.mynimef.data_remote.requests.ClientAddCardRequest
import com.mynimef.data_remote.requests.ClientDataRequest
import com.mynimef.data_remote.requests.ClientInfoRequest
import com.mynimef.data_remote.requests.WaterIncreaseRequest
import com.mynimef.domain.IAppNetwork
import com.mynimef.domain.IClientNetwork
import com.mynimef.domain.ITokenGetter
import com.mynimef.domain.models.requests.IClientAddCardRequest
import com.mynimef.domain.models.requests.IClientDataRequest
import com.mynimef.domain.models.requests.IClientInfoRequest
import com.mynimef.domain.models.requests.IWaterIncreaseRequest
import com.mynimef.domain.models.responses.ICardResponse
import com.mynimef.domain.models.responses.IClientDataResponse
import com.mynimef.domain.models.responses.IClientInfoResponse
import com.mynimef.domain.models.responses.IWaterIncreaseResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

fun createClientNetworkImpl(
    tokenGetter: ITokenGetter,
    appNetwork: IAppNetwork
) = ClientNetworkImpl(
    tokenGetter
).apply {
    tokenRefresher = appNetwork
}

@Suppress("UNCHECKED_CAST")
class ClientNetworkImpl internal constructor(
    tokenGetter: ITokenGetter
): Network(tokenGetter = tokenGetter), IClientNetwork {

    private val api: ClientAPI

    init {
        val gson = GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()

        val retrofit = Retrofit.Builder()
            //.baseUrl("http://91.240.254.42:8080/")
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        api = retrofit.create()
    }

    override suspend fun getInfo(accountId: Long, request: IClientInfoRequest) =
        handleAuthApi(accountId) { api.getInfo(it, ClientInfoRequest(request)) as Response<IClientInfoResponse> }
    override suspend fun addCard(accountId: Long, request: IClientAddCardRequest) =
        handleAuthApi(accountId) { api.addCard(it, ClientAddCardRequest(request)) as Response<ICardResponse> }
    override suspend fun getDayCards(accountId: Long, day: Int, month: Int, year: Int) =
        handleAuthApi(accountId) { api.getDateCards(it, day, month, year) as Response<List<ICardResponse>> }
    override suspend fun increaseWater(accountId: Long, request: IWaterIncreaseRequest) =
        handleAuthApi(accountId) { api.increaseWater(it, WaterIncreaseRequest(request)) as Response<IWaterIncreaseResponse> }
    override suspend fun getData(accountId: Long, request: IClientDataRequest) =
        handleAuthApi(accountId) { api.getData(it, ClientDataRequest(request)) as Response<IClientDataResponse> }

}