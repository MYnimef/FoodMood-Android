package com.mynimef.foodmood.data.repository.api

import com.mynimef.foodmood.data.models.requests.ClientAddCardRequest
import com.mynimef.foodmood.data.models.requests.WaterIncreaseRequest
import com.mynimef.foodmood.data.models.responses.CardResponse
import com.mynimef.foodmood.data.models.responses.ClientInfoResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ClientAPI {

    @GET("/api/client/info/{time_zone}")
    suspend fun getInfo(
        @Header("Authorization") token: String,
        @Path("time_zone") timeZone: String,
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
    fun increaseWater(
        @Header("Authorization") token: String,
        @Body request: WaterIncreaseRequest
    ): Response<String>

}