package com.mynimef.foodmood.data.repository.api

import com.mynimef.foodmood.data.models.requests.ClientAddCardRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ClientAPI {

    @POST("/api/client/card/add")
    suspend fun addCard(
        @Header("Authorization") token: String,
        @Body request: ClientAddCardRequest,
    ): Response<Unit>
}