package com.mynimef.foodmood

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mynimef.foodmood.presentation.NavigationScreen
import com.mynimef.foodmood.ui.theme.FoodMoodTheme

class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodMoodTheme {
                NavigationScreen()
            }
        }
    }
}