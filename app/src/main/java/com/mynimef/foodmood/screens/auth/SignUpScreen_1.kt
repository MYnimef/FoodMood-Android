package com.mynimef.foodmood.screens.auth

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mynimef.foodmood.R
import com.mynimef.domain.models.enums.ENavAuth
import com.mynimef.foodmood.elements.MyLoginButton
import com.mynimef.foodmood.elements.fields.MyCheckField
import com.mynimef.foodmood.theme.FoodMoodTheme

@Composable
fun SignUpScreen_1(viewModel: SignUpViewModel) {
    val namePairState = viewModel.namePair.collectAsStateWithLifecycle()
    val buttonState = viewModel.firstButtonActive.collectAsStateWithLifecycle()

    SignUpScreen_1(
        namePairProvider = { namePairState.value },
        setName= viewModel::setName,
        buttonActiveProvider = { buttonState.value },
        onNext = { viewModel.navigateTo(ENavAuth.SIGN_UP_2) }
    )
}

@Composable
private fun SignUpScreen_1(
    namePairProvider: () -> Pair<String, Boolean>,
    setName: (String) -> Unit,
    buttonActiveProvider: () -> Boolean,
    onNext: () -> Unit,
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
            text = stringResource(R.string.welcome_mess),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
        )
        MyCheckField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
            ,
            label = stringResource(R.string.name),
            pairProvider = namePairProvider,
            onValueChange = setName,
        )
        MyLoginButton(
            text = stringResource(R.string.next),
            enabled = buttonActiveProvider(),
            onClick = onNext,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreen_1_Preview() = FoodMoodTheme {
    val namePairState = remember { mutableStateOf("" to true) }

    SignUpScreen_1(
        namePairProvider = { namePairState.value },
        setName = { namePairState.value = it to true },
        buttonActiveProvider = { true },
        onNext = {}
    )
}