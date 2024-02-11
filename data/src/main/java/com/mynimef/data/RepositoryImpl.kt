package com.mynimef.data

import com.mynimef.data.enums.ENavAuth
import com.mynimef.data.enums.ENavClientBottomBar
import com.mynimef.data.enums.ENavClientMain
import com.mynimef.data.enums.EToast
import kotlinx.coroutines.flow.MutableSharedFlow

object RepositoryImpl {

    val authNavMain by lazy { MutableSharedFlow<ENavAuth>() }
    val clientNavMain by lazy { MutableSharedFlow<ENavClientMain>() }
    val clientNavBottomBar by lazy { MutableSharedFlow<ENavClientBottomBar>() }

    val toastFlow = MutableSharedFlow<EToast>()

}