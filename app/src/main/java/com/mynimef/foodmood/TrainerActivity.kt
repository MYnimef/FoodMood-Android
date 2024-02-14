package com.mynimef.foodmood

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.mynimef.domain.models.enums.EAppState
import com.mynimef.foodmood.screens.trainer.TrainerNavigationScreen
import com.mynimef.foodmood.theme.FoodMoodTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TrainerActivity: ComponentActivity() {

    private val viewModel: TrainerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.appState.collect {
                when (it) {
                    EAppState.AUTH -> {
                        val myIntent = Intent(this@TrainerActivity, AuthActivity::class.java)
                        startActivity(myIntent)
                        finish()
                    }
                    EAppState.CLIENT -> {
                        val myIntent = Intent(this@TrainerActivity, ClientActivity::class.java)
                        startActivity(myIntent)
                        finish()
                    }
                    EAppState.TRAINER -> {}
                }
            }
        }

        setContent {
            FoodMoodTheme {
                TrainerNavigationScreen()
            }
        }
    }

}