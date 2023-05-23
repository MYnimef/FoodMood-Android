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
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mynimef.foodmood.R
import com.mynimef.foodmood.data.models.enums.ENavigationClient
import com.mynimef.foodmood.presentation.elements.MyIcon
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun SettingsScreen(navigateTo: (route: ENavigationClient) -> Unit) {

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
    navigateTo: (route: ENavigationClient) -> Unit,

    ) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(stringResource(R.string.settings),
            modifier = Modifier.padding(bottom = 30.dp, top = 70.dp),
            style = MaterialTheme.typography.titleLarge)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ) {
            IconButton(
                modifier = Modifier.align(Alignment.CenterVertically),
                onClick = { navigateTo(ENavigationClient.HOME) }) {
                MyIcon(drawableId = R.drawable.ic_user)
            }
            TextButton(
                onClick = { navigateTo(ENavigationClient.SETTINGS) },) {
                Text(stringResource(R.string.user_settings),
                    modifier = Modifier.align(Alignment.CenterVertically),
                    style = MaterialTheme.typography.titleMedium)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ) {
            IconButton(
                modifier = Modifier.align(Alignment.CenterVertically),
                onClick = { navigateTo(ENavigationClient.SETTINGS)}) {
                MyIcon(drawableId = R.drawable.ic_preference)
            }
            TextButton(
                onClick = { navigateTo(ENavigationClient.SETTINGS) },) {
                Text(stringResource(R.string.prefrences),
                    modifier = Modifier.align(Alignment.CenterVertically),
                    style = MaterialTheme.typography.titleMedium)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ) {
            IconButton(
                modifier = Modifier.align(Alignment.CenterVertically),
                onClick = { navigateTo(ENavigationClient.SETTINGS)}) {
                MyIcon(drawableId = R.drawable.ic_notification)
            }
            TextButton(
                onClick = { navigateTo(ENavigationClient.SETTINGS) },) {
                Text(stringResource(R.string.notifications),
                    modifier = Modifier.align(Alignment.CenterVertically),
                    style = MaterialTheme.typography.titleMedium)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ) {
            IconButton(
                modifier = Modifier.align(Alignment.CenterVertically),
                onClick = { navigateTo(ENavigationClient.SETTINGS)}) {
                MyIcon(drawableId = R.drawable.ic_language)
            }
            TextButton(
                onClick = { navigateTo(ENavigationClient.SETTINGS) },) {
                Text(stringResource(R.string.language),
                    modifier = Modifier.align(Alignment.CenterVertically),
                    style = MaterialTheme.typography.titleMedium)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ) {
            IconButton(
                modifier = Modifier.align(Alignment.CenterVertically),
                onClick = { navigateTo(ENavigationClient.SETTINGS)}) {
                MyIcon(drawableId = R.drawable.ic_error)
            }
            TextButton(
                onClick = { navigateTo(ENavigationClient.SETTINGS) },) {
                Text(stringResource(R.string.error),
                    modifier = Modifier.align(Alignment.CenterVertically),
                    style = MaterialTheme.typography.titleMedium)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ) {
            IconButton(
                modifier = Modifier.align(Alignment.CenterVertically),
                onClick = { navigateTo(ENavigationClient.SETTINGS)}) {
                MyIcon(drawableId = R.drawable.ic_idea)
            }
            TextButton(
                onClick = { navigateTo(ENavigationClient.SETTINGS) },) {
                Text(stringResource(R.string.idea),
                    modifier = Modifier.align(Alignment.CenterVertically),
                    style = MaterialTheme.typography.titleMedium)
            }
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