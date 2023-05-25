package com.mynimef.foodmood.presentation.screens.shared

import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mynimef.foodmood.R
import com.mynimef.foodmood.data.models.enums.ENavigationAuth
import com.mynimef.foodmood.presentation.elements.MyIcon
import com.mynimef.foodmood.presentation.elements.MyLoginButton
import com.mynimef.foodmood.presentation.elements.MyTextFieldLogin
import com.mynimef.foodmood.presentation.elements.MyToast
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun SignInScreen(
    navigateTo: (route: ENavigationAuth) -> Unit
) {
    val viewModel: SignInViewModel = viewModel()
    MyToast(viewModel.toastMessage)

    SignInScreen(
        navigateTo = navigateTo,
        signIn = viewModel::signIn,
        email = viewModel.email.collectAsState().value,
        setEmail = viewModel::setEmail,
        password = viewModel.password.collectAsState().value,
        setPassword = viewModel::setPassword,
        buttonActive = viewModel.buttonActive.collectAsState().value,
    )
}

@Composable
private fun SignInScreen(
    navigateTo: (route: ENavigationAuth) -> Unit,
    signIn: () -> Unit,
    email: String,
    setEmail: (String) -> Unit,
    password: String,
    setPassword: (String) -> Unit,
    buttonActive: Boolean,
) {
    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        CenterElements(
            navigateTo = navigateTo,
            signIn = signIn,
            email = email,
            setEmail = setEmail,
            password = password,
            setPassword = setPassword,
            buttonActive = buttonActive,
        )
    }
}

@Composable
private fun CenterElements(
    navigateTo: (route: ENavigationAuth) -> Unit,
    signIn: () -> Unit,
    email: String,
    setEmail: (String) -> Unit,
    password: String,
    setPassword: (String) -> Unit,
    buttonActive: Boolean,
) {
    val passwordVisible = rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.welcome),
            modifier = Modifier.padding(bottom = 30.dp),
            style = MaterialTheme.typography.titleLarge
        )

        MyTextFieldLogin(
            value = email,
            label = stringResource(R.string.email),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            isError = false,
            visualTransformation = VisualTransformation.None,
            onValueChange = setEmail
        )

        MyTextFieldLogin(
            value = password,
            label = stringResource(R.string.password),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
            isError = password.length !in 8..20 && password.isNotEmpty(),
            trailingIcon = {
                IconButton(
                    onClick = { passwordVisible.value = !passwordVisible.value }
                ) {
                    MyIcon(drawableId = if (passwordVisible.value) R.drawable.ic_visibility else R.drawable.ic_visibility_off)
                }
            },
            onValueChange = setPassword
        )

        MyLoginButton(
            text = stringResource(R.string.signin),
            enabled = buttonActive,
            onClick = signIn
        )

        MyLoginButton(
            text = stringResource(R.string.signup)
        ) {
            navigateTo(ENavigationAuth.SIGN_UP)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignInScreenPreview() {
    FoodMoodTheme {
        val email = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }

        SignInScreen(
            navigateTo = {},
            signIn = {},
            email = email.value,
            setEmail = { email.value = it },
            password = password.value,
            setPassword = { password.value = it },
            buttonActive = true,
        )

        SignInScreen {}
    }
}