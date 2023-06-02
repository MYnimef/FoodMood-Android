package com.mynimef.foodmood.presentation.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.mynimef.foodmood.R
import com.mynimef.foodmood.data.models.enums.ENavAuth
import com.mynimef.foodmood.presentation.elements.MyLoginButton
import com.mynimef.foodmood.presentation.elements.MyTextFieldLogin
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun SignUpScreenFirst(viewModel: SignUpViewModel) {
    SignUpScreenFirst(
        name = viewModel.name.collectAsState().value,
        setName= viewModel::setName,
        buttonActive = viewModel.firstButtonActive.collectAsState().value,
        onNext = { viewModel.navigateTo(ENavAuth.SIGN_UP_SECOND) }
    )
}

@Composable
private fun SignUpScreenFirst(
    name: String,
    setName: (String) -> Unit,
    buttonActive: Boolean,
    onNext: () -> Unit,
) {
    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        CenterElements(
            name = name,
            setName = setName,
            buttonActive = buttonActive,
            onNext = onNext,
        )
    }
}

@Composable
private fun CenterElements(
    name: String,
    setName: (String) -> Unit,
    buttonActive: Boolean,
    onNext: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
            .background(MaterialTheme.colorScheme.background)
            .imePadding()
        ,
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
            isError = false,
            visualTransformation = VisualTransformation.None,
            onValueChange = setName,
        )

        MyLoginButton(
            text = stringResource(R.string.next),
            enabled = buttonActive,
            onClick = onNext,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpFirstScreenPreview() {
    FoodMoodTheme {
        val name = remember { mutableStateOf("") }

        SignUpScreenFirst(
            name = name.value,
            setName = { name.value = it },
            buttonActive = true,
            onNext = {}
        )
    }
}