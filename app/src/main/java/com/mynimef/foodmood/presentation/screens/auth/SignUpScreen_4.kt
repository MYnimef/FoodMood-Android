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
import com.mynimef.foodmood.R
import com.mynimef.foodmood.presentation.elements.MyLoginButton
import com.mynimef.foodmood.presentation.elements.fields.MyEmailField
import com.mynimef.foodmood.presentation.elements.fields.MyPasswordField
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun SignUpScreen_4(
    viewModel: SignUpViewModel,
) {
    val emailPairState = viewModel.emailPair.collectAsStateWithLifecycle()
    val passwordPairState = viewModel.passwordPair.collectAsStateWithLifecycle()
    val repeatPasswordPairState = viewModel.repeatPasswordPair.collectAsStateWithLifecycle()
    val buttonActiveState  = viewModel.thirdButtonActive.collectAsStateWithLifecycle()

    SignUpScreen_4(
        emailPairProvider = { emailPairState.value },
        setEmail = viewModel::setEmail,
        passwordPairProvider = { passwordPairState.value },
        setPassword = viewModel::setPassword,
        repeatPasswordPairProvider = { repeatPasswordPairState.value },
        setRepeatPassword = viewModel::setRepeatPassword,
        signUp = viewModel::signUp,
        buttonActiveProvider = { buttonActiveState.value },
    )
}

@Composable
private fun SignUpScreen_4(
    emailPairProvider: () -> Pair<String, Boolean>,
    setEmail: (String) -> Unit,
    passwordPairProvider: () -> Pair<String, Boolean>,
    setPassword: (String) -> Unit,
    repeatPasswordPairProvider: () -> Pair<String, Boolean>,
    setRepeatPassword: (String) -> Unit,
    buttonActiveProvider: () -> Boolean,
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
        MyEmailField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
            ,
            label = stringResource(R.string.email),
            pairProvider = emailPairProvider,
            onValueChange = setEmail
        )
        MyPasswordField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
            ,
            label = stringResource(R.string.password),
            pairProvider = passwordPairProvider,
            onValueChange = setPassword
        )
        MyPasswordField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
            ,
            label = stringResource(R.string.repeat_pass),
            pairProvider = repeatPasswordPairProvider,
            onValueChange = setRepeatPassword
        )
        MyLoginButton(
            text = stringResource(R.string.complete),
            enabledProvider = buttonActiveProvider,
            onClick = signUp
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreen_4_Preview() = FoodMoodTheme {
    val emailState = remember { mutableStateOf("" to true) }
    val passwordState = remember { mutableStateOf("" to true) }
    val repeatPasswordState = remember { mutableStateOf("" to true) }
    val buttonActiveState = remember { mutableStateOf(false) }

    SignUpScreen_4(
        emailPairProvider = { emailState.value },
        setEmail = {
            emailState.value = it to true
        },
        passwordPairProvider = { passwordState.value },
        setPassword = {
            passwordState.value = it to true
        },
        repeatPasswordPairProvider= { repeatPasswordState.value },
        setRepeatPassword = {
            repeatPasswordState.value = it to true
        },
        signUp = {},
        buttonActiveProvider = { buttonActiveState.value }
    )
}