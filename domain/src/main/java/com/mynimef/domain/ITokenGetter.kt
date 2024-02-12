package com.mynimef.domain

interface ITokenGetter {

    suspend fun getRefreshToken(accountId: Long): String

}