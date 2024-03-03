package com.mynimef.domain

import com.mynimef.domain.models.enums.EAppState
import kotlinx.coroutines.flow.Flow

interface IAppStorage: IAppStorageRoot, ITokenGetter {

    fun getAppState(): Flow<EAppState>

    fun getActualAccountId(): Flow<Long>

    suspend fun setAppState(state: EAppState)

}