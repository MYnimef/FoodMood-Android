package com.mynimef.domain

interface IAppNetwork: IAppNetworkRoot, ITokenRefresher {

    val tokenGetter: ITokenGetter

    fun removeAccessToken()

}