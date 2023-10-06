package com.mynimef.foodmood.presentation.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mynimef.foodmood.R
import com.mynimef.foodmood.presentation.elements.MyLoginButton
import com.mynimef.foodmood.presentation.elements.fields.MyEmailField
import com.mynimef.foodmood.presentation.elements.fields.MyPasswordField
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun SignInScreen() {
    val viewModel: SignInViewModel = viewModel()

    val emailValuesState = viewModel.emailValues.collectAsStateWithLifecycle()
    val passwordValuesState = viewModel.passwordValues.collectAsStateWithLifecycle()
    val buttonActive = viewModel.buttonActive.collectAsStateWithLifecycle()

    SignInScreen(
        signIn = viewModel::signIn,
        signUp = viewModel::signUp,
        emailValuesProvider = { emailValuesState.value },
        setEmail = viewModel::setEmail,
        passwordValuesProvider = { passwordValuesState.value },
        setPassword = viewModel::setPassword,
        buttonActive = buttonActive.value,
    )
}

@Composable
private fun SignInScreen(
    signIn: () -> Unit,
    signUp: () -> Unit,
    emailValuesProvider: () -> Pair<String, Boolean>,
    setEmail: (String) -> Unit,
    passwordValuesProvider: () -> Pair<String, Boolean>,
    setPassword: (String) -> Unit,
    buttonActive: Boolean,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(horizontal = 30.dp)
            .imePadding()
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.welcome),
            modifier = Modifier.padding(bottom = 30.dp),
            style = MaterialTheme.typography.titleLarge
        )
        MyEmailField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
            ,
            label = stringResource(R.string.email),
            valuesProvider = emailValuesProvider,
            onValueChange = setEmail
        )
        MyPasswordField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
            ,
            label = stringResource(R.string.password),
            valuesProvider = passwordValuesProvider,
            onValueChange = setPassword,
        )
        MyLoginButton(
            text = stringResource(R.string.signin),
            enabled = buttonActive,
            onClick = signIn
        )
        MyLoginButton(
            text = stringResource(R.string.signup),
            onClick = signUp,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignInScreenPreview() = FoodMoodTheme {
    val emailPairState = remember { mutableStateOf("" to false) }
    val passwordPairState = remember { mutableStateOf("" to false) }

    SignInScreen(
        signIn = {},
        signUp = {},
        emailValuesProvider = { emailPairState.value },
        setEmail = {
            val isError = false
            emailPairState.value = it to isError
                   },
        passwordValuesProvider = { passwordPairState.value },
        setPassword = {
            val isError = it.length < 8
            passwordPairState.value = it to isError
                      },
        buttonActive = true
    )
}