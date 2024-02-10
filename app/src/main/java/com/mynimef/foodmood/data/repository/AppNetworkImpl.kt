package com.mynimef.foodmood.data.repository

import com.mynimef.domain.ApiError
import com.mynimef.domain.ApiException
import com.mynimef.domain.ApiResult
import com.mynimef.domain.ApiSuccess
import com.mynimef.domain.AppNetwork
import com.mynimef.foodmood.data.models.requests.ClientAddCardRequest
import com.mynimef.foodmood.data.models.requests.ClientDataRequest
import com.mynimef.foodmood.data.models.requests.ClientInfoRequest
import com.mynimef.foodmood.data.models.requests.RefreshTokenRequest
import com.mynimef.foodmood.data.models.requests.SignInRequest
import com.mynimef.foodmood.data.models.requests.SignUpRequest
import com.mynimef.foodmood.data.models.requests.WaterIncreaseRequest
import com.mynimef.foodmood.data.models.responses.CardResponse
import com.mynimef.foodmood.data.models.responses.ClientDataResponse
import com.mynimef.foodmood.data.models.responses.ClientInfoResponse
import com.mynimef.foodmood.data.models.responses.SignInResponse
import com.mynimef.foodmood.data.models.responses.WaterIncreaseResponse
import com.mynimef.foodmood.data.repository.api.AuthAPI
import com.mynimef.foodmood.data.repository.api.ClientAPI
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class AppNetworkImpl: AppNetwork<
        SignUpRequest, SignInRequest, ClientInfoRequest, ClientAddCardRequest, WaterIncreaseRequest, ClientDataRequest,
        SignInResponse, ClientInfoResponse, CardResponse, WaterIncreaseResponse, ClientDataResponse
        >
{

    private val authAPI: AuthAPI
    private val clientAPI: ClientAPI

    private lateinit var refreshToken: String
    private var accessToken: String? = null

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://91.240.254.42:8080/")
            //.baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        authAPI = retrofit.create()
        clientAPI = retrofit.create()
    }

    override fun updateRefreshToken(token: String) {
        refreshToken = token
    }

    override fun updateAccessToken(token: String?) {
        accessToken = token
    }

    override suspend fun signUpClient(request: SignUpRequest) =
        handleApi { authAPI.signUpClient(request) }
    override suspend fun signIn(request: SignInRequest) =
        handleApi { authAPI.signIn(request) }

    override suspend fun clientGetInfo(request: ClientInfoRequest) =
        handleAuthApi { clientAPI.getInfo(it, request) }
    override suspend fun clientAddCard(request: ClientAddCardRequest) =
        handleAuthApi { clientAPI.addCard(it, request) }
    override suspend fun clientGetDayCards(day: Int, month: Int, year: Int) =
        handleAuthApi { clientAPI.getDateCards(it, day, month, year) }
    override suspend fun clientIncreaseWater(request: WaterIncreaseRequest) =
        handleAuthApi { clientAPI.increaseWater(it, request) }
    override suspend fun clientGetData(request: ClientDataRequest) =
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

    @Suppress("UNCHECKED_CAST")
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