package com.mynimef.foodmood.presentation.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.commandiron.wheel_picker_compose.WheelDatePicker
import com.mynimef.foodmood.R
import com.mynimef.foodmood.data.models.enums.ENavAuth
import com.mynimef.foodmood.presentation.elements.MyLoginButton
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme
import java.time.LocalDate

@Composable
fun SignUpScreen_2(
    viewModel: SignUpViewModel,
) {
    val buttonActiveState = viewModel.secondButtonActive.collectAsStateWithLifecycle()

    SignUpScreen_2(
        setBirthday= viewModel::setBirthday,
        buttonActiveProvider = { buttonActiveState.value },
        onNext = { viewModel.navigateTo(ENavAuth.SIGN_UP_3) },
    )
}

@Composable
private fun SignUpScreen_2(
    setBirthday: (String) -> Unit,
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
        verticalArrangement = Arrangement.spacedBy(30.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.birthday),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
        )
        BoxWithConstraints {
            WheelDatePicker(
                textStyle = TextStyle(
                    fontSize = 20.sp
                ),
                size = DpSize(
                    width = maxWidth,
                    height = 200.dp
                ),
                maxDate = LocalDate.now(),
            ) { snappedDate ->
                setBirthday(snappedDate.toString())
            }
        }
        MyLoginButton(
            text = stringResource(R.string.next),
            enabledProvider = buttonActiveProvider,
            onClick = onNext
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreen_2_Preview() = FoodMoodTheme {
    val date = remember { mutableStateOf("") }

    SignUpScreen_2(
        setBirthday = { date.value = it },
        buttonActiveProvider = { true },
        onNext = {}
    )
}