package com.mynimef.foodmood.data

import com.mynimef.foodmood.data.api.AuthAPI
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

    fun signUp() {

    }

    fun signIn() {

    }
}