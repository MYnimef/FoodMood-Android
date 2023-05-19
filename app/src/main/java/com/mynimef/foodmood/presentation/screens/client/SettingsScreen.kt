package com.mynimef.foodmood.presentation.screens.client

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mynimef.foodmood.R
import com.mynimef.foodmood.presentation.elements.MyIcon
import com.mynimef.foodmood.presentation.elements.MyLogInButton
import com.mynimef.foodmood.presentation.screens.shared.PreferencesViewModel
import com.mynimef.foodmood.presentation.screens.trainer.SettingsViewModel
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun SettingsScreen(navigateTo: (route: String) -> Unit) {

    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        CenterElements(
            navigateTo = navigateTo)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CenterElements(
    navigateTo: (route: String) -> Unit,

    ) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(stringResource(R.string.settings),
            modifier = Modifier.padding(bottom = 30.dp),
            style = MaterialTheme.typography.titleLarge)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
        ) {
            IconButton(onClick = { navigateTo("")}) {
                MyIcon(drawableId = R.drawable.ic_user)
            }
            Text(stringResource(R.string.user_settings),
                style = MaterialTheme.typography.titleMedium)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
        ) {
            IconButton(onClick = { navigateTo("")}) {
                MyIcon(drawableId = R.drawable.ic_preference)
            }
            Text(stringResource(R.string.prefrences),
                style = MaterialTheme.typography.titleMedium)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
        ) {
            IconButton(onClick = { navigateTo("")}) {
                MyIcon(drawableId = R.drawable.ic_notification)
            }
            Text(stringResource(R.string.notifications),
                style = MaterialTheme.typography.titleMedium)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
        ) {
            IconButton(onClick = { navigateTo("")}) {
                MyIcon(drawableId = R.drawable.ic_language)
            }
            Text(stringResource(R.string.language),
                style = MaterialTheme.typography.titleMedium)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
        ) {
            IconButton(onClick = { navigateTo("")}) {
                MyIcon(drawableId = R.drawable.ic_error)
            }
            Text(stringResource(R.string.error),
                style = MaterialTheme.typography.titleMedium)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
        ) {
            IconButton(onClick = { navigateTo("")}) {
                MyIcon(drawableId = R.drawable.ic_idea)
            }
            Text(stringResource(R.string.idea),
                style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Preview
@Composable
fun SettingsPrev() {
    FoodMoodTheme {
        SettingsScreen {}
    }
}