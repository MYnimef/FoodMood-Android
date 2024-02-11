package com.mynimef.domain

import com.mynimef.domain.models.EAppState
import kotlinx.coroutines.flow.Flow

interface IAppStorageRoot: IAppStorage, ITokenGetter {

    val appState: Flow<EAppState>

    suspend fun setAppState(state: EAppState)

}