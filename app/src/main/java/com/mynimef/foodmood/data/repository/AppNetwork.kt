package com.mynimef.foodmood.data.repository

import com.mynimef.foodmood.data.models.ApiError
import com.mynimef.foodmood.data.models.ApiException
import com.mynimef.foodmood.data.models.ApiResult
import com.mynimef.foodmood.data.models.ApiSuccess
import com.mynimef.foodmood.data.models.requests.ClientAddCardRequest
import com.mynimef.foodmood.data.models.requests.ClientDataRequest
import com.mynimef.foodmood.data.models.requests.ClientInfoRequest
import com.mynimef.foodmood.data.models.requests.RefreshTokenRequest
import com.mynimef.foodmood.data.repository.api.AuthAPI
import com.mynimef.foodmood.data.models.requests.SignInRequest
import com.mynimef.foodmood.data.models.requests.SignUpRequest
import com.mynimef.foodmood.data.models.requests.WaterIncreaseRequest
import com.mynimef.foodmood.data.repository.api.ClientAPI
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class AppNetwork {

    private val authAPI: AuthAPI
    private val clientAPI: ClientAPI

    lateinit var refreshToken: String
    var accessToken: String? = null

    init {
        val retrofit = Retrofit.Builder()
            //.baseUrl("http://77.232.135.37:8080/")
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        authAPI = retrofit.create()
        clientAPI = retrofit.create()
    }

    suspend fun signUpClient(request: SignUpRequest) =
        handleApi { authAPI.signUpClient(request) }
    suspend fun signIn(request: SignInRequest) =
        handleApi { authAPI.signIn(request) }

    suspend fun clientGetInfo(request: ClientInfoRequest) =
        handleAuthApi { clientAPI.getInfo(it, request) }
    suspend fun clientAddCard(request: ClientAddCardRequest) =
        handleAuthApi { clientAPI.addCard(it, request) }
    suspend fun clientGetDayCards(day: Int, month: Int, year: Int) =
        handleAuthApi { clientAPI.getDateCards(it, day, month, year) }
    suspend fun clientIncreaseWater(request: WaterIncreaseRequest) =
        handleAuthApi { clientAPI.increaseWater(it, request) }
    suspend fun clientGetData(request: ClientDataRequest) =
        handleAuthApi { clientAPI.getData(it, request) }

    private suspend fun <T: Any> handleApi(
        execute: suspend () -> Response<T>
    ): ApiResult<T> {
        return try {
            val response = execute()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                ApiSuccess(body)
            } else {
                ApiError(code = response.code(), message = response.message())
            }
        } catch (e: HttpException) {
            ApiError(code = e.code(), message = e.message())
        } catch (e: Throwable) {
            ApiException(e)
        }
    }

    private suspend fun <T: Any> handleAuthApi(
        execute: suspend (token: String) -> Response<T>,
    ): ApiResult<T> {
        if (accessToken == null) {
            val refreshResult = handleApi { authAPI.refreshToken(RefreshTokenRequest(refreshToken)) }
            if (refreshResult is ApiSuccess) {
                accessToken = refreshResult.data.accessToken
            } else {
                return refreshResult as ApiResult<T>
            }
        }
        val result = handleApi { execute(accessToken!!) }
        if (result is ApiError && result.code == 401) {
            val refreshResult = handleApi { authAPI.refreshToken(RefreshTokenRequest(refreshToken)) }
            return if (refreshResult is ApiSuccess) {
                accessToken = refreshResult.data.accessToken
                handleApi { execute(accessToken!!) }
            } else {
                result
            }
        }
        return result
    }

}