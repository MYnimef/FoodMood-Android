package com.mynimef.foodmood.presentation.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mynimef.foodmood.R
import com.mynimef.foodmood.presentation.elements.MyLoginButton
import com.mynimef.foodmood.presentation.elements.fields.MyPasswordField
import com.mynimef.foodmood.presentation.elements.fields.MyTextFieldLogin
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun SignUpScreenFourth(
    viewModel: SignUpViewModel,
) {
    val passwordValuesState = viewModel.passwordValues.collectAsStateWithLifecycle()
    val repeatPasswordValuesState = viewModel.repeatPasswordValues.collectAsStateWithLifecycle()

    SignUpScreenFourth(
        login = viewModel.email.collectAsState().value,
        setLogin = viewModel::setEmail,
        passwordValuesProvider = { passwordValuesState.value },
        setPassword = viewModel::setPassword,
        repeatPasswordValuesProvider = { repeatPasswordValuesState.value },
        setRepeatPassword = viewModel::setRepeatPassword,
        signUp = viewModel::signUp,
        buttonActive = viewModel.thirdButtonActive.collectAsState().value,
    )
}

@Composable
private fun SignUpScreenFourth(
    login: String,
    setLogin: (String) -> Unit,
    passwordValuesProvider: () -> Pair<String, Boolean>,
    setPassword: (String) -> Unit,
    repeatPasswordValuesProvider: () -> Pair<String, Boolean>,
    setRepeatPassword: (String) -> Unit,
    buttonActive: Boolean,
    signUp: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 30.dp)
            .imePadding()
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = 30.dp),
            text = stringResource(R.string.final_signup),
            style = MaterialTheme.typography.titleLarge
        )
        MyTextFieldLogin(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
            ,
            value = login,
            label = stringResource(R.string.email),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            visualTransformation = VisualTransformation.None,
            isError = false,
            onValueChange = setLogin,
        )
        MyPasswordField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
            ,
            label = stringResource(R.string.password),
            valuesProvider = passwordValuesProvider,
            onValueChange = setPassword
        )
        MyPasswordField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
            ,
            label = stringResource(R.string.repeat_pass),
            valuesProvider = repeatPasswordValuesProvider,
            onValueChange = setRepeatPassword
        )
        MyLoginButton(
            text = stringResource(R.string.complete),
            enabled = buttonActive,
            onClick = signUp
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpFourthScreenPreview() = FoodMoodTheme {
    val login = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val repeatPassword = remember { mutableStateOf("") }

    SignUpScreenFourth(
        login = login.value,
        setLogin = { login.value = it },
        passwordValuesProvider = { password.value to false },
        setPassword = { password.value = it },
        repeatPasswordValuesProvider= { repeatPassword.value to false },
        setRepeatPassword = { repeatPassword.value = it },
        signUp = {},
        buttonActive = true
    )
}