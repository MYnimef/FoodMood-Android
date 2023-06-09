package com.mynimef.foodmood.presentation.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.commandiron.wheel_picker_compose.WheelDatePicker
import com.mynimef.foodmood.R
import com.mynimef.foodmood.data.models.enums.ENavAuth
import com.mynimef.foodmood.presentation.elements.MyLoginButton
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme
import java.time.LocalDate

@Composable
fun SignUpScreenSecond(
    viewModel: SignUpViewModel,
) {
    SignUpScreenSecond(
        setBirthday= viewModel::setBirthday,
        buttonActive = viewModel.secondButtonActive.collectAsState().value,
        onNext = { viewModel.navigateTo(ENavAuth.SIGN_UP_THIRD) },
    )
}

@Composable
private fun SignUpScreenSecond(
    setBirthday: (String) -> Unit,
    buttonActive: Boolean,
    onNext: () -> Unit,
) {
    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        CenterElements(
            setBirthday = setBirthday,
            buttonActive = buttonActive,
            onNext = onNext
        )
    }
}

@Composable
private fun CenterElements(
    setBirthday: (String) -> Unit,
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
            text = stringResource(R.string.birthday),
            style = MaterialTheme.typography.titleLarge
        )

        WheelDatePicker(
            modifier = Modifier.padding(bottom = 30.dp),
            maxDate = LocalDate.now(),
        ) {
                snappedDate -> setBirthday(snappedDate.toString())
        }

        MyLoginButton(
            text = stringResource(R.string.next),
            enabled = buttonActive,
            onClick = onNext
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpSecondScreenPreview() {
    FoodMoodTheme {
        val date = remember { mutableStateOf("") }

        SignUpScreenSecond(
            setBirthday = { date.value = it },
            buttonActive = true,
            onNext = {}
        )
    }
}