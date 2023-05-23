package com.mynimef.foodmood.presentation.screens.shared

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mynimef.foodmood.data.models.enums.EAppState
import com.mynimef.foodmood.presentation.screens.client.ClientNavigationScreen
import com.mynimef.foodmood.presentation.screens.trainer.TrainerNavigationScreen
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun MainNavigationScreen() {
    val viewModel: MainNavigationViewModel = viewModel()

    MainNavigationScreen(
        appState = viewModel.getAppState().collectAsState().value
    )
}

@Composable
private fun MainNavigationScreen(
    appState: EAppState
) {
    Navigation(
        modifier = Modifier
            .fillMaxSize(),
        appState = appState
    )
}

@Composable
private fun Navigation(
    modifier: Modifier,
    appState: EAppState
) {
    Crossfade(
        modifier = modifier,
        targetState = appState
    ) { targetState ->
        when (targetState) {
            EAppState.NONE -> AuthNavigationScreen()
            EAppState.CLIENT -> ClientNavigationScreen()
            EAppState.TRAINER -> TrainerNavigationScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainNavigationScreenPreview() {
    FoodMoodTheme {
        MainNavigationScreen(
            appState = EAppState.CLIENT
        )
    }
}