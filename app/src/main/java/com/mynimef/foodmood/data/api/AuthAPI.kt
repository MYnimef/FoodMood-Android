package com.mynimef.foodmood.data.api

import com.mynimef.foodmood.data.models.requests.SignInRequest
import com.mynimef.foodmood.data.models.requests.SignUpRequest
import com.mynimef.foodmood.data.models.responses.SignInResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthAPI {

    @POST("/auth/signup")
    suspend fun signUp(@Body request: SignUpRequest): Response<SignInResponse>

    @POST("/auth/signin")
    suspend fun signIn(@Body request: SignInRequest): Response<SignInResponse>
}