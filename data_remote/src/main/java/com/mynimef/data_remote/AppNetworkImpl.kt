package com.mynimef.data_remote

import com.google.gson.GsonBuilder
import com.mynimef.domain.ApiError
import com.mynimef.domain.ApiException
import com.mynimef.domain.ApiResult
import com.mynimef.domain.ApiSuccess
import com.mynimef.domain.IAppNetwork
import com.mynimef.domain.models.requests.IClientAddCardRequest
import com.mynimef.domain.models.requests.IClientDataRequest
import com.mynimef.domain.models.requests.IClientInfoRequest
import com.mynimef.domain.models.requests.ISignInRequest
import com.mynimef.domain.models.requests.ISignUpRequest
import com.mynimef.domain.models.requests.IWaterIncreaseRequest
import com.mynimef.domain.models.responses.ICardResponse
import com.mynimef.domain.models.responses.IClientDataResponse
import com.mynimef.domain.models.responses.IClientInfoResponse
import com.mynimef.domain.models.responses.ISignInResponse
import com.mynimef.domain.models.responses.IWaterIncreaseResponse
import com.mynimef.data_remote.api.AuthAPI
import com.mynimef.data_remote.api.ClientAPI
import com.mynimef.data_remote.requests.ClientAddCardRequest
import com.mynimef.data_remote.requests.ClientDataRequest
import com.mynimef.data_remote.requests.ClientInfoRequest
import com.mynimef.data_remote.requests.RefreshTokenRequest
import com.mynimef.data_remote.requests.SignInRequest
import com.mynimef.data_remote.requests.SignUpRequest
import com.mynimef.data_remote.requests.WaterIncreaseRequest
import com.mynimef.domain.IAppNetworkRoot
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


@Suppress("UNCHECKED_CAST")
class AppNetworkImpl: IAppNetworkRoot {

    private val authAPI: AuthAPI
    private val clientAPI: ClientAPI

    private lateinit var refreshToken: String
    private var accessToken: String? = null

    init {
        val gson = GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://91.240.254.42:8080/")
            //.baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create(gson))
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

    override suspend fun signUpClient(request: ISignUpRequest) =
        handleApi { authAPI.signUpClient(SignUpRequest(request)) as Response<ISignInResponse> }
    override suspend fun signIn(request: ISignInRequest) =
        handleApi { authAPI.signIn(SignInRequest(request)) as Response<ISignInResponse> }

    override suspend fun clientGetInfo(request: IClientInfoRequest) =
        handleAuthApi { clientAPI.getInfo(it, ClientInfoRequest(request)) as Response<IClientInfoResponse> }
    override suspend fun clientAddCard(request: IClientAddCardRequest) =
        handleAuthApi { clientAPI.addCard(it, ClientAddCardRequest(request)) as Response<ICardResponse> }
    override suspend fun clientGetDayCards(day: Int, month: Int, year: Int) =
        handleAuthApi { clientAPI.getDateCards(it, day, month, year) as Response<List<ICardResponse>> }
    override suspend fun clientIncreaseWater(request: IWaterIncreaseRequest) =
        handleAuthApi { clientAPI.increaseWater(it, WaterIncreaseRequest(request)) as Response<IWaterIncreaseResponse> }
    override suspend fun clientGetData(request: IClientDataRequest) =
        handleAuthApi { clientAPI.getData(it, ClientDataRequest(request)) as Response<IClientDataResponse> }

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