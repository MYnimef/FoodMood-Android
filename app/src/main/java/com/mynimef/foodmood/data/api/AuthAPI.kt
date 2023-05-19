package com.mynimef.foodmood.data.api

import com.mynimef.foodmood.data.models.requests.SignInRequest
import com.mynimef.foodmood.data.models.requests.SignUpRequest
import com.mynimef.foodmood.data.models.responses.SignInResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthAPI {

    @POST("/auth/signup")
    fun signUp(@Body signUpRequest: SignUpRequest): Call<SignInResponse>

    @POST("/auth/signin")
    fun signIn(@Body signInRequest: SignInRequest): Call<SignInResponse>
}