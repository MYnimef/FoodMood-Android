package com.mynimef.data_remote.api

import com.mynimef.data_remote.requests.RefreshTokenRequest
import com.mynimef.data_remote.requests.SignInRequest
import com.mynimef.data_remote.requests.SignUpRequest
import com.mynimef.data_remote.responses.RefreshTokenResponse
import com.mynimef.data_remote.responses.SignInResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

internal interface AuthAPI {

    @POST("/api/auth/signup/client")
    suspend fun signUpClient(@Body request: SignUpRequest): Response<SignInResponse>

    @POST("/api/auth/signin")
    suspend fun signIn(@Body request: SignInRequest): Response<SignInResponse>

    @POST("/api/auth/refreshtoken")
    suspend fun refreshToken(@Body request: RefreshTokenRequest): Response<RefreshTokenResponse>

}