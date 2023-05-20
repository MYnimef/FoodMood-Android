package com.mynimef.foodmood.presentation.screens.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.unit.dp
import com.mynimef.foodmood.R
import com.mynimef.foodmood.presentation.elements.MyIcon
import com.mynimef.foodmood.presentation.elements.MyLoginButton
import com.mynimef.foodmood.presentation.elements.MyTextFieldLogin
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun SignUpScreenFirst(
    navigateTo: (route: String) -> Unit,
    viewModel: SignUpViewModel,
) {
    SignUpScreenFirst(
        navigateTo = navigateTo,
        name = viewModel.name.collectAsState().value,
        setName= viewModel::setName,
        login = viewModel.email.collectAsState().value,
        setLogin = viewModel::setEmail,
        password = viewModel.password.collectAsState().value,
        setPassword = viewModel::setPassword,
        repeatPassword = viewModel.repeatPassword.collectAsState().value,
        setRepeatPassword = viewModel::setRepeatPassword,
        buttonActive = viewModel.buttonActive.collectAsState().value,
    )
}

@Composable
private fun SignUpScreenFirst(
    navigateTo: (route: String) -> Unit,
    name: String,
    setName: (String) -> Unit,
    login: String,
    setLogin: (String) -> Unit,
    password: String,
    setPassword: (String) -> Unit,
    repeatPassword: String,
    setRepeatPassword: (String) -> Unit,
    buttonActive: Boolean,
) {
    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        CenterElements(
            navigateTo = navigateTo,
            name = name,
            setName = setName,
            login = login,
            setLogin = setLogin,
            password = password,
            setPassword = setPassword,
            repeatPassword = repeatPassword,
            setRepeatPassword = setRepeatPassword,
            buttonActive = buttonActive
        )
    }
}

@Composable
private fun CenterElements(
    navigateTo: (route: String) -> Unit,
    name: String,
    setName: (String) -> Unit,
    login: String,
    setLogin: (String) -> Unit,
    password: String,
    setPassword: (String) -> Unit,
    repeatPassword: String,
    setRepeatPassword: (String) -> Unit,
    buttonActive: Boolean,
) {
    val passwordVisible = rememberSaveable { mutableStateOf(false) }
    val repeatPasswordVisible = rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = 30.dp),
            text = stringResource(R.string.welcome_mess),
            style = MaterialTheme.typography.titleLarge
        )

        MyTextFieldLogin(
            value = name,
            label = stringResource(R.string.name),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            visualTransformation = VisualTransformation.None,
            onValueChange = setName
        )

        MyTextFieldLogin(
            value = login,
            label = stringResource(R.string.email),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            visualTransformation = VisualTransformation.None,
            onValueChange = setLogin
        )

        MyTextFieldLogin(
            value = password,
            label = stringResource(R.string.password),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(
                    onClick = { passwordVisible.value = !passwordVisible.value }
                ) {
                    MyIcon(drawableId = if (passwordVisible.value) R.drawable.ic_visibility else R.drawable.ic_visibility_off)
                }
            },
            onValueChange = setPassword
        )

        MyTextFieldLogin(
            value = repeatPassword,
            label = stringResource(R.string.repeat_pass),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (repeatPasswordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(
                    onClick = { repeatPasswordVisible.value = !repeatPasswordVisible.value }
                ) {
                    MyIcon(drawableId = if (repeatPasswordVisible.value) R.drawable.ic_visibility else R.drawable.ic_visibility_off)
                }
            },
            onValueChange = setRepeatPassword
        )

        MyLoginButton(
            text = stringResource(R.string.next),
            enabled = buttonActive,
        ) {
            navigateTo("second")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpFirstScreenPreview() {
    FoodMoodTheme {
        val name = remember { mutableStateOf("") }
        val login = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        val repeatPassword = remember { mutableStateOf("") }

        SignUpScreenFirst(
            navigateTo = {},
            name = name.value,
            setName = { name.value = it },
            login = login.value,
            setLogin = { login.value = it },
            password = password.value,
            setPassword = { password.value = it },
            repeatPassword = repeatPassword.value,
            setRepeatPassword = { repeatPassword.value = it },
            buttonActive = true
        )
    }
}