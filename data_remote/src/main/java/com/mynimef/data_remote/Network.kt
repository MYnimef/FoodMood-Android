package com.mynimef.data_remote

import com.mynimef.domain.ApiError
import com.mynimef.domain.ApiException
import com.mynimef.domain.ApiResult
import com.mynimef.domain.ApiSuccess
import com.mynimef.domain.ITokenGetter
import com.mynimef.domain.ITokenRefresher
import com.mynimef.domain.models.requests.IRefreshTokenRequest
import retrofit2.HttpException
import retrofit2.Response

@Suppress("UNCHECKED_CAST")
open class Network(
    private val tokenGetter: ITokenGetter
) {

    internal lateinit var tokenRefresher: ITokenRefresher

    private var accessToken: String? = null

    protected suspend fun <T: Any> handleApi(
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

    protected suspend fun <T: Any> handleAuthApi(
        accountId: Long,
        execute: suspend (token: String) -> Response<T>,
    ): ApiResult<T> {
        if (accessToken == null) {
            val refreshResult = tokenRefresher.refreshToken(IRefreshTokenRequest.create(tokenGetter.getRefreshToken(accountId)))
            if (refreshResult is ApiSuccess) {
                accessToken = refreshResult.data.accessToken
            } else {
                return refreshResult as ApiResult<T>
            }
        }
        val result = handleApi { execute(accessToken!!) }
        if (result is ApiError && result.code == 401) {
            val refreshResult = tokenRefresher.refreshToken(IRefreshTokenRequest.create(tokenGetter.getRefreshToken(accountId)))
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