package com.mynimef.foodmood.data.repository.api

import com.mynimef.foodmood.data.models.requests.ClientAddCardRequest
import com.mynimef.foodmood.data.models.responses.CardResponse
import com.mynimef.foodmood.data.models.responses.ClientResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ClientAPI {

    @GET("/api/client")
    suspend fun getClient(@Header("Authorization") token: String): Response<ClientResponse>

    @POST("/api/client/card/add")
    suspend fun addCard(
        @Header("Authorization") token: String,
        @Body request: ClientAddCardRequest,
    ): Response<CardResponse>

    @GET("/api/client/card/get/{day}/{month}/{year}")
    suspend fun getDateCards(
        @Path("day") day: Int,
        @Path("month") month: Int,
        @Path("year") year: Int,
    ): Response<List<CardResponse>>
}