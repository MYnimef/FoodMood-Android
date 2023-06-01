package com.mynimef.foodmood.presentation.screens.client

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.mynimef.foodmood.presentation.elements.MyIcon
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun SettingsScreen() {
    val viewModel: SettingsViewModel = viewModel()

    SettingsScreen(
        navigateTo = viewModel::navigateTo,
    )
}

@Composable
private fun SettingsScreen(
    navigateTo: (ENavClientMain) -> Unit,
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
                .padding(start = 30.dp, end = 30.dp,)
            ,
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SettingsElement(
                drawableId = R.drawable.ic_set_up_account,
                text = stringResource(R.string.user_settings),
                onClick = { navigateTo(ENavClientMain.SET_UP_ACCOUNT) }
            )
            SettingsElement(
                drawableId = R.drawable.ic_set_up_preferences,
                text = stringResource(R.string.prefrences),
                onClick = { navigateTo(ENavClientMain.SET_UP_PREFERENCES) }
            )
            SettingsElement(
                drawableId = R.drawable.ic_set_up_notifications,
                text = stringResource(R.string.notifications),
                onClick = { navigateTo(ENavClientMain.SET_UP_NOTIFICATIONS) }
            )
            SettingsElement(
                drawableId = R.drawable.ic_set_up_language,
                text = stringResource(R.string.language),
                onClick = {}
            )
            SettingsElement(
                drawableId = R.drawable.ic_set_up_error,
                text = stringResource(R.string.error),
                onClick = {}
            )
            SettingsElement(
                drawableId = R.drawable.ic_set_up_idea,
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
    TextButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
            ,
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier.padding(start = 5.dp))
            MyIcon(
                drawableId = drawableId
            )
            Spacer(modifier = Modifier.padding(start = 20.dp))
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = text,
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