package com.mynimef.data_remote.api

import com.mynimef.data_remote.requests.ClientAddCardRequest
import com.mynimef.data_remote.requests.ClientDataRequest
import com.mynimef.data_remote.requests.ClientInfoRequest
import com.mynimef.data_remote.requests.WaterIncreaseRequest
import com.mynimef.data_remote.responses.CardResponse
import com.mynimef.data_remote.responses.ClientDataResponse
import com.mynimef.data_remote.responses.ClientInfoResponse
import com.mynimef.data_remote.responses.WaterIncreaseResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

internal interface ClientAPI {

    @POST("/api/client/info")
    suspend fun getInfo(
        @Header("Authorization") token: String,
        @Body request: ClientInfoRequest,
    ): Response<ClientInfoResponse>

    @POST("/api/client/card/add")
    suspend fun addCard(
        @Header("Authorization") token: String,
        @Body request: ClientAddCardRequest,
    ): Response<CardResponse>

    @GET("/api/client/card/get/{day}/{month}/{year}")
    suspend fun getDateCards(
        @Header("Authorization") token: String,
        @Path("day") day: Int,
        @Path("month") month: Int,
        @Path("year") year: Int,
    ): Response<List<CardResponse>>

    @POST("/api/client/water/increase")
    suspend fun increaseWater(
        @Header("Authorization") token: String,
        @Body request: WaterIncreaseRequest
    ): Response<WaterIncreaseResponse>

    @POST("/api/client/data")
    suspend fun getData(
        @Header("Authorization") token: String,
        @Body request: ClientDataRequest
    ): Response<ClientDataResponse>

}