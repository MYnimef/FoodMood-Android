package com.mynimef.foodmood.presentation.screens.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mynimef.foodmood.R
import com.mynimef.foodmood.presentation.elements.MyIcon
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navigateTo: (route: String) -> Unit) {
    val viewModel: LoginViewModel = viewModel()

    val login = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordVisible = rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.welcome))
        TextField(
            value = login.value,
            label = { Text(stringResource(R.string.email)) },
            singleLine = true,
            onValueChange = {
                login.value = it
            }
        )
        TextField(
            value = password.value,
            label = { Text(stringResource(R.string.password)) },
            singleLine = true,
            visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = {
                password.value = it
            },
            trailingIcon = {
                IconButton(
                    onClick = { passwordVisible.value = !passwordVisible.value }
                ) {
                    MyIcon(drawableId = if (passwordVisible.value) R.drawable.ic_visibility else R.drawable.ic_visibility_off)
                }
            }
        )
        Text(text = "Нет аккаунта?")
        Button(
            onClick = {
                navigateTo("trainer")
            }
        ) {
            Text(stringResource(R.string.login))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    FoodMoodTheme {
        LoginScreen {}
    }
}