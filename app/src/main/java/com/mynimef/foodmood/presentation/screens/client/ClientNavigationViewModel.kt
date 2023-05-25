package com.mynimef.foodmood.presentation.screens.client

import androidx.lifecycle.ViewModel
import com.mynimef.foodmood.data.models.ApiError
import com.mynimef.foodmood.data.models.ApiException
import com.mynimef.foodmood.data.models.ApiSuccess
import com.mynimef.foodmood.data.models.enums.EToast
import com.mynimef.foodmood.data.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ClientNavigationViewModel: ViewModel() {

    private var job: Job? = null

    private val _toastMessage = MutableSharedFlow<EToast>()
    val toastMessage = _toastMessage.asSharedFlow()

    fun initClient() {
        job = CoroutineScope(Dispatchers.IO).launch {
            when (val result = Repository.getClient()) {
                is ApiError -> {
                    when (result.code) {
                        401 -> {
                            _toastMessage.emit(EToast.TOAST401CLIENT)
                            Repository.signOut()
                        }
                        else -> {}
                    }
                }
                is ApiException -> _toastMessage.emit(EToast.TOASTNOCON)
                is ApiSuccess -> Repository.setClient(result.data)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}