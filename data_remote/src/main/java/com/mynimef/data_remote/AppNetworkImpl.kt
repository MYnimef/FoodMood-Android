package com.mynimef.data_remote

import com.google.gson.GsonBuilder
import com.mynimef.data_remote.api.AppAPI
import com.mynimef.data_remote.requests.RefreshTokenRequest
import com.mynimef.data_remote.requests.SignInRequest
import com.mynimef.data_remote.requests.SignUpRequest
import com.mynimef.domain.IAppNetwork
import com.mynimef.domain.ITokenGetter
import com.mynimef.domain.ITokenRefresher
import com.mynimef.domain.models.requests.IRefreshTokenRequest
import com.mynimef.domain.models.requests.ISignInRequest
import com.mynimef.domain.models.requests.ISignUpRequest
import com.mynimef.domain.models.responses.IRefreshTokenResponse
import com.mynimef.domain.models.responses.ISignInResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

fun createAppNetworkImpl(tokenGetter: ITokenGetter) = AppNetworkImpl(
    tokenGetter
).apply {
    tokenRefresher = this
}

@Suppress("UNCHECKED_CAST")
class AppNetworkImpl internal constructor(
    override val tokenGetter: ITokenGetter
) : Network(tokenGetter), IAppNetwork, ITokenRefresher {

    private val api: AppAPI

    private var accessToken: String? = null

    init {
        val gson = GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()

        val retrofit = Retrofit.Builder()
            //.baseUrl("http://91.240.254.42:8080/")
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        api = retrofit.create()
    }

    override fun removeAccessToken() {
        accessToken = null
    }

    override suspend fun signUpClient(request: ISignUpRequest) =
        handleApi { api.signUpClient(SignUpRequest(request)) as Response<ISignInResponse> }
    override suspend fun signIn(request: ISignInRequest) =
        handleApi { api.signIn(SignInRequest(request)) as Response<ISignInResponse> }

    override suspend fun refreshToken(request: IRefreshTokenRequest) =
        handleApi { api.refreshToken(RefreshTokenRequest(request)) as Response<IRefreshTokenResponse> }

}