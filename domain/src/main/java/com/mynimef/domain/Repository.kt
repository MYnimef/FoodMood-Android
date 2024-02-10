package com.mynimef.domain

interface Repository {

    suspend fun signIn()

    suspend fun signOut()

}