package com.mynimef.foodmood.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.mynimef.foodmood.presentation.screens.shared.MainNavigationScreen
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

class MainActivity: ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                !viewModel.dataLoaded.value
            }
        }
        viewModel.initData(this)
        super.onCreate(savedInstanceState)
        setContent {
            FoodMoodTheme {
                MainNavigationScreen()
            }
        }
    }
}