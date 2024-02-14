package com.mynimef.data

import com.mynimef.domain.models.enums.ENavAuth
import com.mynimef.data.enums.EToast
import kotlinx.coroutines.flow.MutableSharedFlow

object RepositoryImpl {

    val authNavMain by lazy { MutableSharedFlow<ENavAuth>() }

    val toastFlow = MutableSharedFlow<EToast>()

}