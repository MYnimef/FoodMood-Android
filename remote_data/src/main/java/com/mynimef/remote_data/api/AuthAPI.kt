package com.mynimef.remote_data.api

import com.mynimef.remote_data.requests.RefreshTokenRequest
import com.mynimef.remote_data.requests.SignInRequest
import com.mynimef.remote_data.requests.SignUpRequest
import com.mynimef.remote_data.responses.RefreshTokenResponse
import com.mynimef.remote_data.responses.SignInResponse
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