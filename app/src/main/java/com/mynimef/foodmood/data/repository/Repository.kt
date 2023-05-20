package com.mynimef.foodmood.data.repository

import android.content.Context
import com.mynimef.foodmood.data.models.requests.SignUpRequest

object Repository {

    private lateinit var database: AppDatabase
    private lateinit var network: NetworkService

    fun init(applicationContext: Context) {
        database = AppDatabase.init(applicationContext)
        network = NetworkService()
    }

    suspend fun signUp(request: SignUpRequest): Boolean {
        val response = network.signUp(request)
        if (response.isSuccessful) {
            return true
        }
        return false
    }

    fun signIn() {

    }
}