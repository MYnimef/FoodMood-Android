package com.mynimef.foodmood.presentation.screens.client

import android.icu.util.TimeZone
import androidx.lifecycle.ViewModel
import com.mynimef.foodmood.data.models.ApiError
import com.mynimef.foodmood.data.models.ApiException
import com.mynimef.foodmood.data.models.ApiSuccess
import com.mynimef.foodmood.data.models.enums.EToast
import com.mynimef.foodmood.data.models.requests.ClientInfoRequest
import com.mynimef.foodmood.data.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ClientNavigationViewModel: ViewModel() {

    private var job: Job? = null

    fun initClient() {
        job = CoroutineScope(Dispatchers.IO).launch {
            when (val result = Repository.clientGetInfo(
                ClientInfoRequest(TimeZone.getDefault().id)
            )) {
                is ApiError -> {
                    when (result.code) {
                        401 -> {
                            Repository.toast(EToast.AUTH_FAILED)
                            Repository.signOut()
                        }
                        else -> {}
                    }
                }
                is ApiException -> Repository.toast(EToast.NO_CONNECTION)
                is ApiSuccess -> Repository.initClient(result.data)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}