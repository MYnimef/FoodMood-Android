package com.mynimef.foodmood.presentation.screens.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mynimef.foodmood.R
import com.mynimef.foodmood.presentation.elements.MyIcon
import com.mynimef.foodmood.presentation.elements.MyLogInButton
import com.mynimef.foodmood.presentation.elements.MyTextFieldLogin
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun PreferncesScreen(navigateTo: (route: String) -> Unit) {
    val viewModel: PreferencesViewModel = viewModel()

    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        CenterElements(
            navigateTo = navigateTo,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CenterElements(
    navigateTo: (route: String) -> Unit,

) {

    val food = rememberSaveable { mutableStateOf(false) }
    val water = rememberSaveable { mutableStateOf(false) }
    val weight = rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.pref),
            modifier = Modifier.padding(bottom = 30.dp),
            style = MaterialTheme.typography.titleLarge)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
        ) {
            RadioButton(
                modifier = Modifier.align(Alignment.CenterVertically),
                selected = true, onClick = { },
                colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primaryContainer))
            Text(stringResource(R.string.emotion),
                modifier = Modifier.align(Alignment.CenterVertically),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primaryContainer)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
        ) {
            RadioButton(
                modifier = Modifier.align(Alignment.CenterVertically),
                selected = food.value, onClick = {
                    food.value = !food.value
                },
                colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primaryContainer))
            Text(
                stringResource(R.string.food),
                modifier = Modifier.align(Alignment.CenterVertically),
                style = MaterialTheme.typography.titleMedium,
                color = if (food.value) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.onPrimaryContainer
                )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
        ) {
            RadioButton(
                modifier = Modifier.align(Alignment.CenterVertically),
                selected = water.value, onClick = {
                    water.value = !water.value
                },
                colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primaryContainer))
            Text(stringResource(R.string.water),
                modifier = Modifier.align(Alignment.CenterVertically),
                style = MaterialTheme.typography.titleMedium,
                color = if (water.value) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
        ) {
            RadioButton(
                modifier = Modifier.align(Alignment.CenterVertically),
                selected = weight.value, onClick = {
                    weight.value = !weight.value
                },
                colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primaryContainer))
            Text(stringResource(R.string.weight),
                modifier = Modifier.align(Alignment.CenterVertically),
                style = MaterialTheme.typography.titleMedium,
                color = if (weight.value) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

        MyLogInButton(text = stringResource(R.string.complete)) {
            navigateTo("client")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreferencesScreenPreview() {
    FoodMoodTheme {
        PreferncesScreen {}
    }
}