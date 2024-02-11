package com.mynimef.foodmood.screens.client

import android.icu.util.TimeZone
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynimef.data.RepositoryImpl
import com.mynimef.data.enums.EToast
import com.mynimef.domain.ApiError
import com.mynimef.domain.ApiException
import com.mynimef.domain.ApiSuccess
import com.mynimef.domain.AppRepository
import com.mynimef.domain.models.requests.IClientInfoRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientMainViewModel @Inject constructor(
    private val repository: AppRepository
): ViewModel() {

    val navigation = RepositoryImpl.clientNavMain.asSharedFlow()

    fun initClient(): Unit = with(repository) {
        viewModelScope.launch(Dispatchers.IO) {

            when (val result = network.clientGetInfo(
                IClientInfoRequest.create(TimeZone.getDefault().id)
            )) {
                is ApiError -> when (result.code) {
                    401 ->  {
                        RepositoryImpl.toastFlow.emit(EToast.AUTH_FAILED)
                        signOut()
                    }
                    else -> {}
                }
                is ApiException -> RepositoryImpl.toastFlow.emit(EToast.NO_CONNECTION)
                is ApiSuccess -> {
                    val data = result.data
                    storage.deleteAllCards()
                    data.dayCards.forEach {
                        storage.insertCard(it)
                    }
                    storage.insertClient(data)
                }
            }
        }
    }

}