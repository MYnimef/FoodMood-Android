package com.mynimef.foodmood.presentation.screens.client

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mynimef.foodmood.R
import com.mynimef.foodmood.presentation.elements.MyIcon
import com.mynimef.foodmood.presentation.elements.MyLogInButton
import com.mynimef.foodmood.presentation.elements.MyTextFieldSettings
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun PrefSettingsScreen(navigateTo: (route: String) -> Unit) {
    val waterL = remember { mutableStateOf("") }
    val weightKg = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        CenterElements(
            waterL = waterL,
            navigateTo = navigateTo,
            weightKg = weightKg
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CenterElements(
    waterL: MutableState<String>,
    weightKg: MutableState<String>,
    navigateTo: (route: String) -> Unit,
    ) {

    val food = rememberSaveable { mutableStateOf(false) }
    val water = rememberSaveable { mutableStateOf(false) }
    val waterEdit = rememberSaveable { mutableStateOf(false) }
    val weight = rememberSaveable { mutableStateOf(false) }
    val weightEdit = rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp)
        ) {
            MyIcon(
                drawableId = R.drawable.ic_preference,
                modifier = Modifier
                    .padding(bottom = 30.dp, top = 70.dp)
                    .align(Alignment.CenterVertically)
            )
            Text(
                stringResource(R.string.prefrences),
                modifier = Modifier
                    .padding(bottom = 20.dp, top = 70.dp, start = 15.dp)
                    .align(Alignment.CenterVertically),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
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
                .padding(bottom = 20.dp)
        ) {
            RadioButton(
                modifier = Modifier.align(Alignment.CenterVertically),
                selected = food.value, onClick = {
                    food.value = !food.value
                },
                colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primaryContainer))
            TextButton(
                onClick = {  food.value = !food.value },) {
                Text(
                    stringResource(R.string.food),
                    style = MaterialTheme.typography.titleMedium,
                    color = if (food.value) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        ) {
            RadioButton(
                modifier = Modifier.align(Alignment.CenterVertically),
                selected = water.value, onClick = {
                    water.value = !water.value
                },
                colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primaryContainer))
            TextButton(
                onClick = {  water.value = !water.value },) {
                Text(
                    stringResource(R.string.water),
                    modifier = Modifier.align(Alignment.CenterVertically),
                    style = MaterialTheme.typography.titleMedium,
                    color = if (water.value) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp)) {
            MyTextFieldSettings(value = waterL.value,
                label = stringResource(R.string.water_l),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                enabled = waterEdit.value,
                trailingIcon = {
                    IconButton(
                        onClick = {
                            if (water.value) waterEdit.value = !waterEdit.value }
                    ) {
                        MyIcon(
                            drawableId = if (waterEdit.value && water.value) R.drawable.ic_edit else R.drawable.ic_edit_off)
                    }
                },
                onValueChange = {
                    waterL.value = it
                })
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)) {
            TextButton(
                onClick = {navigateTo("notifications") },) {
                Text(
                    stringResource(R.string.water_notif),
                    modifier = Modifier.align(Alignment.CenterVertically),
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 24.sp)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        ) {
            RadioButton(
                modifier = Modifier.align(Alignment.CenterVertically),
                selected = weight.value, onClick = {
                    weight.value = !weight.value
                },
                colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primaryContainer))
            TextButton(
                onClick = {  weight.value = !weight.value },) {
                Text(
                    stringResource(R.string.weight),
                    modifier = Modifier.align(Alignment.CenterVertically),
                    style = MaterialTheme.typography.titleMedium,
                    color = if (weight.value) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp)) {
            MyTextFieldSettings(value = weightKg.value,
                label = stringResource(R.string.weight_kg),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                enabled = weightEdit.value,
                trailingIcon = {
                    IconButton(
                        onClick = {
                            if (weight.value) weightEdit.value = !weightEdit.value }
                    ) {
                        MyIcon(
                            drawableId = if (weightEdit.value && weight.value) R.drawable.ic_edit else R.drawable.ic_edit_off)
                    }
                },
                onValueChange = {
                    weightKg.value = it
                })
        }
    }
}


@Preview
@Composable
fun PrefSettingsScreenPrev() {
    FoodMoodTheme {
        PrefSettingsScreen {}
    }
}