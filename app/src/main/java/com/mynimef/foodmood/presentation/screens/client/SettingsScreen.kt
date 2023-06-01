package com.mynimef.foodmood.presentation.screens.client

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mynimef.foodmood.R
import com.mynimef.foodmood.data.models.enums.ENavClientMain
import com.mynimef.foodmood.extensions.noRippleClickable
import com.mynimef.foodmood.presentation.elements.MyIcon
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun SettingsScreen() {
    val viewModel: SettingsViewModel = viewModel()

    SettingsScreen(
        setSettings = viewModel::navigateTo,
    )
}

@Composable
private fun SettingsScreen(
    setSettings: (route: ENavClientMain) -> Unit,
) {
    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.settings),
            modifier = Modifier
                .padding(bottom = 30.dp, top = 70.dp, start = 30.dp)
            ,
            style = MaterialTheme.typography.titleLarge
        )
        Column(
            modifier = Modifier
                .padding(start = 30.dp)
            ,
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
            ) {
            SettingsElement(
                drawableId = R.drawable.ic_user,
                text = stringResource(R.string.user_settings),
                onClick = { setSettings(ENavClientMain.SET_UP_ACCOUNT) }
            )
            SettingsElement(
                drawableId = R.drawable.ic_preference,
                text = stringResource(R.string.prefrences),
                onClick = { setSettings(ENavClientMain.SET_UP_PREFERENCES) }
            )
            SettingsElement(
                drawableId = R.drawable.ic_notification,
                text = stringResource(R.string.notifications),
                onClick = { setSettings(ENavClientMain.SET_UP_NOTIFICATIONS) }
            )
            SettingsElement(
                drawableId = R.drawable.ic_language,
                text = stringResource(R.string.language),
                onClick = {}
            )
            SettingsElement(
                drawableId = R.drawable.ic_error,
                text = stringResource(R.string.error),
                onClick = {}
            )
            SettingsElement(
                drawableId = R.drawable.ic_idea,
                text = stringResource(R.string.idea),
                onClick = {}
            )
        }
    }
}

@Composable
private fun SettingsElement(
    drawableId: Int,
    text: String,
    onClick: () -> Unit,
) {
    Row() {
        Box(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .noRippleClickable(
                    onClick = onClick
                )
                .padding(bottom = 20.dp)
                .fillMaxWidth(0.15f)
        ) {
            MyIcon(drawableId = drawableId)
        }
        TextButton(
            onClick = onClick,
        ) {
            Text(
                text = text,
                modifier = Modifier.align(Alignment.CenterVertically),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Preview
@Composable
private fun SettingsScreenPreview() {
    FoodMoodTheme {
        SettingsScreen()
    }
}