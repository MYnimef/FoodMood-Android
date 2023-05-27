package com.mynimef.foodmood.presentation.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.commandiron.wheel_picker_compose.WheelDatePicker
import com.mynimef.foodmood.R
import com.mynimef.foodmood.data.models.enums.ENavigationAuth
import com.mynimef.foodmood.presentation.elements.MyLoginButton
import com.mynimef.foodmood.presentation.elements.MyTextFieldLogin
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme
import java.time.LocalDate

@Composable
fun SignUpScreenSecond(
    navigateTo: (route: String) -> Unit,
    viewModel: SignUpViewModel,
) {
    SignUpScreenSecond(
        navigateTo = navigateTo,
        setBirthday= viewModel::setBirthday,
        buttonActive = viewModel.secondButtonActive.collectAsState().value,
    )
}

@Composable
private fun SignUpScreenSecond(
    navigateTo: (route: String) -> Unit,
    setBirthday: (String) -> Unit,
    buttonActive: Boolean,
) {
    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        CenterElements(
            navigateTo = navigateTo,
            setBirthday = setBirthday,
            buttonActive = buttonActive
        )
    }
}

@Composable
private fun CenterElements(
    navigateTo: (route: String) -> Unit,
    setBirthday: (String) -> Unit,
    buttonActive: Boolean,
) {
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
        ) {
            navigateTo("third")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpSecondScreenPreview() {
    FoodMoodTheme {
        val date = remember { mutableStateOf("") }

        SignUpScreenSecond(
            navigateTo = {},
            setBirthday = { date.value = it },
            buttonActive = true
        )
    }
}