package com.mynimef.foodmood.presentation.screens.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mynimef.foodmood.R
import com.mynimef.foodmood.presentation.elements.MyIcon
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun LoginScreen(navigateTo: (route: String) -> Unit) {
    val viewModel: LoginViewModel = viewModel()

    val login = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        CenterElements(
            navigateTo = navigateTo,
            login = login,
            password = password
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CenterElements(
    navigateTo: (route: String) -> Unit,
    login: MutableState<String>,
    password: MutableState<String>
) {
    val passwordVisible = rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.welcome))
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = login.value,
            label = { Text(stringResource(R.string.email)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = {
                login.value = it
            }
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
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
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Right,
            text = stringResource(R.string.signup)
        )
        Button(
            onClick = {
                navigateTo("trainer")
            }
        ) {
            Text(stringResource(R.string.signin))
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