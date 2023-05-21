package com.mynimef.foodmood.data.repository

import com.mynimef.foodmood.data.models.requests.RefreshTokenRequest
import com.mynimef.foodmood.data.repository.api.AuthAPI
import com.mynimef.foodmood.data.models.requests.SignInRequest
import com.mynimef.foodmood.data.models.requests.SignUpRequest
import com.mynimef.foodmood.data.models.responses.RefreshTokenResponse
import com.mynimef.foodmood.data.models.responses.SignInResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService {
    private val authAPI: AuthAPI

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        authAPI = retrofit.create(AuthAPI::class.java)
    }

    suspend fun signUp(request: SignUpRequest): Response<SignInResponse> = authAPI.signUp(request)
    suspend fun signIn(request: SignInRequest): Response<SignInResponse> = authAPI.signIn(request)
    suspend fun refreshToken(request: RefreshTokenRequest): Response<RefreshTokenResponse> = authAPI.refreshToken(request)
}