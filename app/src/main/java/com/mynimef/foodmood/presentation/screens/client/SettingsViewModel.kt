package com.mynimef.foodmood.presentation.screens.client

import androidx.lifecycle.ViewModel
import com.mynimef.foodmood.data.models.enums.ENavClientMain
import com.mynimef.foodmood.data.models.enums.ENavigationSettings
import com.mynimef.foodmood.data.models.enums.ETypeMeal
import com.mynimef.foodmood.data.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SettingsViewModel: ViewModel() {

    private var job: Job? = null

    lateinit var navigation: (ENavigationSettings) -> Unit

    private val _settingsType = MutableStateFlow(ENavClientMain.SETTINGS_USER_INFO)
    val settingslType = _settingsType.asStateFlow()

    fun setSettingsType(value: ENavClientMain) {
        _settingsType.value = value
        job = CoroutineScope(Dispatchers.Main).launch {
            Repository.clientNavMain.emit(_settingsType.value)
        }
    }

}
