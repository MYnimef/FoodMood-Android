package com.mynimef.foodmood.presentation.screens.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
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
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.mynimef.foodmood.R
import com.mynimef.foodmood.data.models.enums.ENavigationAuth
import com.mynimef.foodmood.presentation.elements.MyIcon
import com.mynimef.foodmood.presentation.elements.MyLoginButton
import com.mynimef.foodmood.presentation.elements.MyTextFieldLogin
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun SignUpScreenFirst(
    parentNavigateTo: (route: ENavigationAuth) -> Unit,
    navigateTo: (route: String) -> Unit,
    viewModel: SignUpViewModel,
) {
    SignUpScreenFirst(
        navigateTo = navigateTo,
        name = viewModel.name.collectAsState().value,
        setName= viewModel::setName,
        birthday = viewModel.birthday.collectAsState().value,
        setBirthday= viewModel::setBirthday,
        buttonActive = viewModel.firstButtonActive.collectAsState().value,
    )
}

@Composable
private fun SignUpScreenFirst(
    navigateTo: (route: String) -> Unit,
    name: String,
    setName: (String) -> Unit,
    birthday: String,
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
            name = name,
            setName = setName,
            birthday = birthday,
            setBirthday = setBirthday,
            buttonActive = buttonActive
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CenterElements(
    navigateTo: (route: String) -> Unit,
    name: String,
    setName: (String) -> Unit,
    birthday: String,
    setBirthday: (String) -> Unit,
    buttonActive: Boolean,
) {
    val calendarState = rememberUseCaseState()

    CalendarDialog(
        state = calendarState,
        config = CalendarConfig(
            monthSelection = true,
            yearSelection = true,
        ),
        selection = CalendarSelection.Date { date ->
            setBirthday(date.toString())
        }
    )

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

        MyTextFieldLogin(
            value = birthday,
            label = stringResource(R.string.birthday),
            readOnly = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            visualTransformation = VisualTransformation.None,
            trailingIcon = {
                IconButton(
                    onClick = {
                        calendarState.show()
                    }
                ) {
                    MyIcon(drawableId = R.drawable.ic_date_picker)
                }
            },
            isError = false,
            onValueChange = setBirthday
        )

        MyLoginButton(
            text = stringResource(R.string.next),
            enabled = buttonActive,
        ) {
            navigateTo("second")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpFirstScreenPreview() {
    FoodMoodTheme {
        val name = remember { mutableStateOf("") }
        val date = remember { mutableStateOf("") }

        SignUpScreenFirst(
            navigateTo = {},
            name = name.value,
            setName = { name.value = it },
            birthday = date.value,
            setBirthday = { date.value = it },
            buttonActive = true
        )
    }
}