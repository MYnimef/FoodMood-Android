package com.mynimef.foodmood.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.mynimef.foodmood.presentation.screens.shared.MainNavigationScreen
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme
import kotlinx.coroutines.launch

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

        lifecycleScope.launch {
            viewModel.toastFlow.collect { message ->
                Toast.makeText(this@MainActivity, message.text, Toast.LENGTH_LONG).show()
            }
        }

        setContent {
            FoodMoodTheme {
                MainNavigationScreen()
            }
        }
    }
}