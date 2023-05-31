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
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ClientMainViewModel: ViewModel() {

    private var job: Job? = null

    val navigation = Repository.clientNavMain.asSharedFlow()

    fun initClient() = with(Repository) {
        job = CoroutineScope(Dispatchers.IO).launch {

            when (val result = network.clientGetInfo(
                ClientInfoRequest(TimeZone.getDefault().id)
            )) {
                is ApiError -> {
                    when (result.code) {
                        401 ->  {
                            toastFlow.emit(EToast.AUTH_FAILED)
                            signOut()
                        }
                        else -> {}
                    }
                }
                is ApiException -> toastFlow.emit(EToast.NO_CONNECTION)
                is ApiSuccess -> {
                    val data = result.data
                    storage.deleteAllCards()
                    data.dayCards.forEach {
                        storage.insertCard(it.toCardEntity())
                    }
                    storage.insertClient(data.toClientEntity())
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}