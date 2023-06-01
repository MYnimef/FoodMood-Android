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
        setSettings = viewModel::setSettingsType,
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
            stringResource(R.string.settings),
            modifier = Modifier.padding(bottom = 30.dp, top = 70.dp, start = 30.dp),
            style = MaterialTheme.typography.titleLarge
        )
        Column(
            modifier = Modifier
                .padding(start = 30.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
            Row() {
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .noRippleClickable(
                            onClick = { setSettings(ENavClientMain.SETTINGS_USER_INFO) })
                        .padding(bottom = 20.dp)
                        .fillMaxWidth(0.15f)
                ) {
                    MyIcon(
                        modifier = Modifier
                            .align(Alignment.CenterStart),
                        drawableId = R.drawable.ic_user)
                }
                TextButton(
                    onClick = { setSettings(ENavClientMain.SETTINGS_USER_INFO) },
                ) {
                    Text(
                        stringResource(R.string.user_settings),
                        modifier = Modifier.align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
            Row() {
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .noRippleClickable(
                            onClick = { setSettings(ENavClientMain.SETTINGS_PREFERENCES) })
                        .padding(bottom = 20.dp)
                        .fillMaxWidth(0.15f)
                ) {
                    MyIcon(
                        drawableId = R.drawable.ic_preference)
                }
                TextButton(
                    onClick = { setSettings(ENavClientMain.SETTINGS_PREFERENCES) },
                ) {
                    Text(
                        stringResource(R.string.prefrences),
                        modifier = Modifier.align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
            Row() {
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .noRippleClickable(
                            onClick = { setSettings(ENavClientMain.SETTINGS_NOTIFICATIONS) })
                        .padding(bottom = 20.dp)
                        .fillMaxWidth(0.15f)
                ) {
                    MyIcon(
                        drawableId = R.drawable.ic_notification)
                }
                TextButton(
                    onClick = { setSettings(ENavClientMain.SETTINGS_NOTIFICATIONS) },
                ) {
                    Text(
                        stringResource(R.string.notifications),
                        modifier = Modifier.align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
            Row() {
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .noRippleClickable(
                            onClick = { })
                        .padding(bottom = 20.dp)
                        .fillMaxWidth(0.15f)
                ) {
                    MyIcon(
                        drawableId = R.drawable.ic_language)
                }
                TextButton(
                    onClick = { },
                ) {
                    Text(
                        stringResource(R.string.language),
                        modifier = Modifier.align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
            Row() {
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .noRippleClickable(
                            onClick = { })
                        .padding(bottom = 20.dp)
                        .fillMaxWidth(0.15f)
                ) {
                    MyIcon(
                        drawableId = R.drawable.ic_error)
                }
                TextButton(
                    onClick = { },
                ) {
                    Text(
                        stringResource(R.string.error),
                        modifier = Modifier.align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
            Row() {
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .noRippleClickable(
                            onClick = { })
                        .padding(bottom = 20.dp)
                        .fillMaxWidth(0.15f)
                ) {
                    MyIcon(
                        drawableId = R.drawable.ic_idea)
                }
                TextButton(
                    onClick = { },
                ) {
                    Text(
                        stringResource(R.string.idea),
                        modifier = Modifier.align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
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