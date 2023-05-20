package com.mynimef.foodmood.data

import com.mynimef.foodmood.data.api.AuthAPI
import com.mynimef.foodmood.data.models.requests.SignInRequest
import com.mynimef.foodmood.data.models.requests.SignUpRequest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NetworkService {
    private val authAPI: AuthAPI

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://77.232.135.37:8080/api")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        authAPI = retrofit.create(AuthAPI::class.java)
    }

    suspend fun signUp(request: SignUpRequest) = authAPI.signUp(request)
    suspend fun signIn(request: SignInRequest) = authAPI.signIn(request)
}