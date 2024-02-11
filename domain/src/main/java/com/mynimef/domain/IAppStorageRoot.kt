package com.mynimef.domain

import com.mynimef.domain.models.EAppState
import kotlinx.coroutines.flow.StateFlow

interface IAppStorageRoot: IAppStorage, ITokenGetter {

    val appState: StateFlow<EAppState>

    fun setAppState(state: EAppState)

}