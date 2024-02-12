package com.mynimef.domain

import com.mynimef.domain.models.EAppState
import kotlinx.coroutines.flow.Flow

interface IAppStorageRoot: IAppStorage, ITokenGetter {

    fun getAppState(): Flow<EAppState>

    fun getActualAccountId(): Flow<Long>

    suspend fun setAppState(state: EAppState)

}