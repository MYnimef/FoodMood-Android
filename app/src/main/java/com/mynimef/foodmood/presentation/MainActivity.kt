package com.mynimef.foodmood.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.mynimef.foodmood.data.models.enums.EAppState
import com.mynimef.foodmood.presentation.screens.auth.AuthMainScreen
import com.mynimef.foodmood.presentation.screens.client.ClientMainScreen
import com.mynimef.foodmood.presentation.screens.trainer.TrainerNavigationScreen
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
                Crossfade(
                    modifier = Modifier
                        .fillMaxSize()
                    ,
                    targetState = viewModel.appState.collectAsState().value
                ) { targetState ->
                    when (targetState) {
                        EAppState.AUTH -> AuthMainScreen()
                        EAppState.CLIENT -> ClientMainScreen()
                        EAppState.TRAINER -> TrainerNavigationScreen()
                    }
                }
            }
        }
    }
}