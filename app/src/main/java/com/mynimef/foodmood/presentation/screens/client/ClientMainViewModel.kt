package com.mynimef.foodmood.presentation.screens.client

import android.icu.util.TimeZone
import androidx.lifecycle.ViewModel
import com.mynimef.domain.ApiError
import com.mynimef.domain.ApiException
import com.mynimef.domain.ApiSuccess
import com.mynimef.domain.models.requests.IClientInfoRequest
import com.mynimef.data.enums.EToast
import com.mynimef.data.RepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ClientMainViewModel: ViewModel() {

    private var job: Job? = null

    val navigation = RepositoryImpl.clientNavMain.asSharedFlow()

    fun initClient() = with(RepositoryImpl) {
        job = CoroutineScope(Dispatchers.IO).launch {

            when (val result = network.clientGetInfo(
                IClientInfoRequest.create(TimeZone.getDefault().id)
            )) {
                is ApiError -> when (result.code) {
                    401 ->  {
                        toastFlow.emit(EToast.AUTH_FAILED)
                        signOut()
                    }
                    else -> {}
                }
                is ApiException -> toastFlow.emit(EToast.NO_CONNECTION)
                is ApiSuccess -> {
                    val data = result.data
                    storage.deleteAllCards()
                    data.dayCards.forEach {
                        storage.insertCard(it)
                    }
                    storage.insertClient(data.client)
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}