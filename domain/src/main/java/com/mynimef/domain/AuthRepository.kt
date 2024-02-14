package com.mynimef.domain

import com.mynimef.domain.models.enums.ENavAuth
import kotlinx.coroutines.flow.MutableSharedFlow

class AuthRepository {

    val navMain by lazy { MutableSharedFlow<ENavAuth>() }

}