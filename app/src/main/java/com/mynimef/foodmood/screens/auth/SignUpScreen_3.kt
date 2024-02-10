package com.mynimef.foodmood.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mynimef.foodmood.R
import com.mynimef.data.enums.ENavAuth
import com.mynimef.foodmood.elements.MyLoginButton
import com.mynimef.foodmood.elements.MyRadioTextSelector
import com.mynimef.foodmood.theme.FoodMoodTheme

@Composable
fun SignUpScreen_3(
    viewModel: SignUpViewModel,
) {
    val foodState = viewModel.food.collectAsStateWithLifecycle()
    val waterState = viewModel.water.collectAsStateWithLifecycle()
    val weightState = viewModel.weight.collectAsStateWithLifecycle()

    SignUpScreen_3(
        foodProvider = { foodState.value },
        triggerFood = viewModel::triggerFood,
        waterProvider = { waterState.value },
        triggerWater = viewModel::triggerWater,
        weightProvider = { weightState.value },
        triggerWeight = viewModel::triggerWeight,
        onNext = { viewModel.navigateTo(ENavAuth.SIGN_UP_4) }
    )
}

@Composable
private fun SignUpScreen_3(
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
            .background(color = MaterialTheme.colorScheme.background)
            .padding(horizontal = 30.dp)
        ,
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
private fun SignUpScreen_3_Preview() = FoodMoodTheme {
    val food = rememberSaveable { mutableStateOf(false) }
    val water = rememberSaveable { mutableStateOf(false) }
    val weight = rememberSaveable { mutableStateOf(false) }

    SignUpScreen_3(
        foodProvider = { food.value },
        triggerFood = { food.value = !food.value },
        waterProvider = { water.value },
        triggerWater = { water.value = !water.value },
        weightProvider = { weight.value },
        triggerWeight = { weight.value = !weight.value },
        onNext = {}
    )
}