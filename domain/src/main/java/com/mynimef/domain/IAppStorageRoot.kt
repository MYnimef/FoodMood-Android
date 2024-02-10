package com.mynimef.domain

import com.mynimef.domain.models.EAppState

interface IAppStorageRoot: IAppStorage {

    fun setAppState(state: EAppState)

}