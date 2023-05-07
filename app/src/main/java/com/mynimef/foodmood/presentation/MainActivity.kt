package com.mynimef.foodmood.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mynimef.foodmood.presentation.screens.trainer.TrainerNavigationScreen
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodMoodTheme {
                TrainerNavigationScreen()
            }
        }
    }
}