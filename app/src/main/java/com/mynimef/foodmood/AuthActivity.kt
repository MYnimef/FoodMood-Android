package com.mynimef.foodmood

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.mynimef.domain.models.enums.EAppState
import com.mynimef.foodmood.screens.auth.AuthMainScreen
import com.mynimef.foodmood.theme.FoodMoodTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AuthActivity: ComponentActivity() {

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }
        }

        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.appState.collect {
                when (it) {
                    EAppState.AUTH -> {
                    }
                    EAppState.CLIENT -> {
                        val myIntent = Intent(this@AuthActivity, ClientActivity::class.java)
                        startActivity(myIntent)
                        finish()
                    }
                    EAppState.TRAINER -> {}
                }
            }
        }

        setContent {
            FoodMoodTheme {
                AuthMainScreen()
            }
        }
    }

}