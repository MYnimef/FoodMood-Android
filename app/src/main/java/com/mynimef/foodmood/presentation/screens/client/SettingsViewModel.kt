package com.mynimef.foodmood.presentation.screens.client

import androidx.lifecycle.ViewModel
import com.mynimef.foodmood.data.models.enums.ENavigationSettings
class SettingsViewModel: ViewModel() {

    lateinit var navigation: (ENavigationSettings) -> Unit

}
