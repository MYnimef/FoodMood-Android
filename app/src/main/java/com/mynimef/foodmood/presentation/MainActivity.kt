package com.mynimef.foodmood.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.mynimef.domain.models.EAppState
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

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            FoodMoodTheme {
                Crossfade(
                    modifier = Modifier
                        .fillMaxSize()
                    ,
                    targetState = viewModel.appState.collectAsStateWithLifecycle().value,
                    label = "main",
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