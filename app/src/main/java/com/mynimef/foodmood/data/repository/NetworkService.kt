package com.mynimef.foodmood.data.repository

import com.mynimef.foodmood.data.models.requests.ClientAddCardRequest
import com.mynimef.foodmood.data.models.requests.RefreshTokenRequest
import com.mynimef.foodmood.data.repository.api.AuthAPI
import com.mynimef.foodmood.data.models.requests.SignInRequest
import com.mynimef.foodmood.data.models.requests.SignUpRequest
import com.mynimef.foodmood.data.models.responses.RefreshTokenResponse
import com.mynimef.foodmood.data.models.responses.SignInResponse
import com.mynimef.foodmood.data.repository.api.ClientAPI
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class NetworkService {

    private val authAPI: AuthAPI
    private val clientAPI: ClientAPI


    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        authAPI = retrofit.create()
        clientAPI = retrofit.create()
    }

    suspend fun signUpClient(request: SignUpRequest): Response<SignInResponse> =
        authAPI.signUpClient(request)
    suspend fun signIn(request: SignInRequest): Response<SignInResponse> =
        authAPI.signIn(request)
    suspend fun refreshToken(request: RefreshTokenRequest): Response<RefreshTokenResponse> =
        authAPI.refreshToken(request)


    suspend fun clientAddCard(token: String, request: ClientAddCardRequest): Response<Unit> =
        clientAPI.addCard(token, request)
}