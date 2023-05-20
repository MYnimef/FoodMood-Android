package com.mynimef.foodmood.presentation.screens.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mynimef.foodmood.R
import com.mynimef.foodmood.presentation.elements.MyLogInButton
import com.mynimef.foodmood.presentation.elements.MyRadioTextSelector
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun SignUpScreenSecond(
    navigateTo: (route: String) -> Unit,
    viewModel: SignUpViewModel,
) {
    SignUpScreenSecond(
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
private fun SignUpScreenSecond(
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
        MyRadioTextSelector(
            text = stringResource(R.string.emotion),
            selected = true,
            onClick = {},
        )
        MyRadioTextSelector(
            text = stringResource(R.string.food),
            selected = food,
            onClick = triggerFood,
        )
        MyRadioTextSelector(
            text = stringResource(R.string.water),
            selected = water,
            onClick = triggerWater,
        )
        MyRadioTextSelector(
            text = stringResource(R.string.weight),
            selected = weight,
            onClick = triggerWeight,
        )
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

        SignUpScreenSecond(
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