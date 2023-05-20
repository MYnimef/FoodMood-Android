package com.mynimef.foodmood.data

import android.util.Log
import com.google.gson.Gson
import com.mynimef.foodmood.data.models.requests.SignUpRequest

object Repository {

    private val network = NetworkService()

    suspend fun signUp(request: SignUpRequest): Boolean {
        Log.d("json", Gson().toJson(request))
        val response = network.signUp(request)
        if (response.isSuccessful) {
            return true
        }
        return false
    }

    fun signIn() {

    }
}