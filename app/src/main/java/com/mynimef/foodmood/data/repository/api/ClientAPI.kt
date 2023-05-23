package com.mynimef.foodmood.data.repository.api

import com.mynimef.foodmood.data.models.requests.ClientAddCardRequest
import com.mynimef.foodmood.data.models.responses.ClientResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ClientAPI {

    @GET("/api/client")
    suspend fun getClient(@Header("Authorization") token: String): Response<ClientResponse>

    @POST("/api/client/card/add")
    suspend fun addCard(
        @Header("Authorization") token: String,
        @Body request: ClientAddCardRequest,
    ): Response<Unit>
}