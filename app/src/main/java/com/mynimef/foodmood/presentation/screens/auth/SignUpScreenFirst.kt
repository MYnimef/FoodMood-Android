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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mynimef.foodmood.R
import com.mynimef.foodmood.data.models.enums.ENavAuth
import com.mynimef.foodmood.presentation.elements.MyLoginButton
import com.mynimef.foodmood.presentation.elements.fields.MyTextFieldLogin
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun SignUpScreenFirst(viewModel: SignUpViewModel) {
    val nameState = viewModel.name.collectAsState()
    val buttonState = viewModel.firstButtonActive.collectAsState()

    SignUpScreenFirst(
        nameProvider = { nameState.value },
        setName= viewModel::setName,
        buttonActiveProvider = { buttonState.value },
        onNext = { viewModel.navigateTo(ENavAuth.SIGN_UP_SECOND) }
    )
}

@Composable
private fun SignUpScreenFirst(
    nameProvider: () -> String,
    setName: (String) -> Unit,
    buttonActiveProvider: () -> Boolean,
    onNext: () -> Unit,
) {
    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        CenterElements(
            nameProvider = nameProvider,
            setName = setName,
            buttonActiveProvider = buttonActiveProvider,
            onNext = onNext,
        )
    }
}

@Composable
private fun CenterElements(
    nameProvider: () -> String,
    setName: (String) -> Unit,
    buttonActiveProvider: () -> Boolean,
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
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
        )
        Element1(
            nameProvider = nameProvider,
            setName = setName,
        )
        Element2(
            buttonActiveProvider = buttonActiveProvider,
            onNext = onNext,
        )
    }
}

@Composable
private fun Element1(
    nameProvider: () -> String,
    setName: (String) -> Unit,
) {
    MyTextFieldLogin(
        value = nameProvider(),
        label = stringResource(R.string.name),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        isError = false,
        visualTransformation = VisualTransformation.None,
        onValueChange = setName,
    )
}

@Composable
private fun Element2(
    buttonActiveProvider: () -> Boolean,
    onNext: () -> Unit,
) {
    MyLoginButton(
        text = stringResource(R.string.next),
        enabled = buttonActiveProvider(),
        onClick = onNext,
    )
}

@Preview(showBackground = true)
@Composable
private fun SignUpFirstScreenPreview() {
    FoodMoodTheme {
        val name = remember { mutableStateOf("") }

        SignUpScreenFirst(
            nameProvider = { name.value },
            setName = { name.value = it },
            buttonActiveProvider = { true },
            onNext = {}
        )
    }
}