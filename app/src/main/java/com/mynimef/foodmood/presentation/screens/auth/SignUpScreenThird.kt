package com.mynimef.foodmood.presentation.screens.auth

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
import com.mynimef.foodmood.data.models.enums.ENavAuth
import com.mynimef.foodmood.presentation.elements.MyLoginButton
import com.mynimef.foodmood.presentation.elements.MyRadioTextSelector
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun SignUpScreenThird(
    viewModel: SignUpViewModel,
) {
    val foodState = viewModel.food.collectAsState()
    val waterState = viewModel.water.collectAsState()
    val weightState = viewModel.weight.collectAsState()

    SignUpScreenThird(
        foodProvider = { foodState.value },
        triggerFood = viewModel::triggerFood,
        waterProvider = { waterState.value },
        triggerWater = viewModel::triggerWater,
        weightProvider = { weightState.value },
        triggerWeight = viewModel::triggerWeight,
        onNext = { viewModel.navigateTo(ENavAuth.SIGN_UP_FOURTH) }
    )
}

@Composable
private fun SignUpScreenThird(
    foodProvider: () -> Boolean,
    triggerFood: () -> Unit,
    waterProvider: () -> Boolean,
    triggerWater: () -> Unit,
    weightProvider: () -> Boolean,
    triggerWeight: () -> Unit,
    onNext: () -> Unit,
) {
    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        CenterElements(
            foodProvider = foodProvider,
            triggerFood = triggerFood,
            waterProvider = waterProvider,
            triggerWater = triggerWater,
            weightProvider = weightProvider,
            triggerWeight = triggerWeight,
            onNext = onNext
        )
    }
}

@Composable
private fun CenterElements(
    foodProvider: () -> Boolean,
    triggerFood: () -> Unit,
    waterProvider: () -> Boolean,
    triggerWater: () -> Unit,
    weightProvider: () -> Boolean,
    triggerWeight: () -> Unit,
    onNext: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        verticalArrangement = Arrangement.spacedBy(space = 30.dp, alignment = Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = 30.dp),
            text = stringResource(R.string.pref),
            style = MaterialTheme.typography.titleLarge,
        )
        MyRadioTextSelector(
            text = stringResource(R.string.emotion),
            selectedProvider = { true },
            onClick = {},
        )
        MyRadioTextSelector(
            text = stringResource(R.string.food),
            selectedProvider = foodProvider,
            onClick = triggerFood,
        )
        MyRadioTextSelector(
            text = stringResource(R.string.water),
            selectedProvider = waterProvider,
            onClick = triggerWater,
        )
        MyRadioTextSelector(
            text = stringResource(R.string.weight),
            selectedProvider = weightProvider,
            onClick = triggerWeight,
        )
        MyLoginButton(
            text = stringResource(R.string.next),
            onClick = onNext
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpThirdScreenPreview() {
    FoodMoodTheme {
        val food = rememberSaveable { mutableStateOf(false) }
        val water = rememberSaveable { mutableStateOf(false) }
        val weight = rememberSaveable { mutableStateOf(false) }

        SignUpScreenThird(
            foodProvider = { food.value },
            triggerFood = { food.value = !food.value },
            waterProvider = { water.value },
            triggerWater = { water.value = !water.value },
            weightProvider = { weight.value },
            triggerWeight = { weight.value = !weight.value },
            onNext = {}
        )
    }
}