package com.mynimef.domain

interface IAppNetworkRoot: IAppNetwork {

    fun updateRefreshToken(token: String)

    fun updateAccessToken(token: String?)

}