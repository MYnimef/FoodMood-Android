package com.mynimef.foodmood.presentation.screens.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mynimef.foodmood.R
import com.mynimef.foodmood.presentation.elements.MyLogInButton
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun SignUpSecondScreen(
    navigateTo: (route: String) -> Unit,
    viewModel: SignUpViewModel,
) {
    SignUpSecondScreen(
        navigateTo = navigateTo,
        food = viewModel.food.collectAsState().value,
        triggerFood = viewModel::triggerFood,
        water = viewModel.water.collectAsState().value,
        triggerWater = viewModel::triggerWater,
        weight = viewModel.weight.collectAsState().value,
        triggerWeight = viewModel::triggerWeight,
    )
}

@Composable
private fun SignUpSecondScreen(
    navigateTo: (route: String) -> Unit,
    food: Boolean,
    triggerFood: () -> Unit,
    water: Boolean,
    triggerWater: () -> Unit,
    weight: Boolean,
    triggerWeight: () -> Unit,
) {
    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        CenterElements(
            navigateTo = navigateTo,
            food = food,
            triggerFood = triggerFood,
            water = water,
            triggerWater = triggerWater,
            weight = weight,
            triggerWeight = triggerWeight
        )
    }
}

@Composable
private fun CenterElements(
    navigateTo: (route: String) -> Unit,
    food: Boolean,
    triggerFood: () -> Unit,
    water: Boolean,
    triggerWater: () -> Unit,
    weight: Boolean,
    triggerWeight: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = 30.dp),
            text = stringResource(R.string.pref),
            style = MaterialTheme.typography.titleLarge,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
        ) {
            RadioButton(
                modifier = Modifier.align(Alignment.CenterVertically),
                selected = true, onClick = { },
                colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primaryContainer))
            TextButton( onClick = {},) {
                Text(
                    stringResource(R.string.emotion),
                    modifier = Modifier.align(Alignment.CenterVertically),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primaryContainer)
            }
        }
       
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
        ) {
            RadioButton(
                modifier = Modifier.align(Alignment.CenterVertically),
                selected = food,
                onClick = triggerFood,
                colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primaryContainer))
            TextButton(
                onClick = triggerFood,
            ) {
                Text(
                    stringResource(R.string.food),
                    style = MaterialTheme.typography.titleMedium,
                    color = if (food) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
        ) {
            RadioButton(
                modifier = Modifier.align(Alignment.CenterVertically),
                selected = water,
                onClick = triggerWater,
                colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primaryContainer))
            TextButton(
                onClick = triggerWater,
            ) {
                Text(stringResource(R.string.water),
                    modifier = Modifier.align(Alignment.CenterVertically),
                    style = MaterialTheme.typography.titleMedium,
                    color = if (water) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
        ) {
            RadioButton(
                modifier = Modifier.align(Alignment.CenterVertically),
                selected = weight,
                onClick = triggerWeight,
                colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primaryContainer))
            TextButton(
                onClick = triggerWeight,
            ) {
                Text(stringResource(R.string.weight),
                    modifier = Modifier.align(Alignment.CenterVertically),
                    style = MaterialTheme.typography.titleMedium,
                    color = if (weight) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }

        MyLogInButton(text = stringResource(R.string.complete)) {
            navigateTo("client")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpSecondScreenPreview() {
    FoodMoodTheme {
        val food = rememberSaveable { mutableStateOf(false) }
        val water = rememberSaveable { mutableStateOf(false) }
        val weight = rememberSaveable { mutableStateOf(false) }

        SignUpSecondScreen(
            navigateTo = {},
            food = food.value,
            triggerFood = { food.value = !food.value },
            water = water.value,
            triggerWater = { water.value = !water.value },
            weight = weight.value,
            triggerWeight = { weight.value = !weight.value },
        )
    }
}