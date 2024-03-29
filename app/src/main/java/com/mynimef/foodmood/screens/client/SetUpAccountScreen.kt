package com.mynimef.foodmood.screens.client

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mynimef.foodmood.R
import com.mynimef.foodmood.elements.MyIcon
import com.mynimef.foodmood.elements.MySettingsButton
import com.mynimef.foodmood.elements.fields.MyTextFieldSettings
import com.mynimef.foodmood.theme.FoodMoodTheme

@Composable
fun SetUpAccountScreen() {
    val viewModel: SetUpAccountViewModel = hiltViewModel()

    SetUpAccountScreen(
        signOutClick = viewModel::signOut
    )
}

@Composable
private fun SetUpAccountScreen(
    signOutClick: () -> Unit,
) {
    val name = remember { mutableStateOf("") }
    val login = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        CenterElements(
            name = name,
            login = login,
            password = password,
            signOutClick = signOutClick,
        )
    }
}

@Composable
private fun CenterElements(
    name: MutableState<String>,
    login: MutableState<String>,
    password: MutableState<String>,
    signOutClick: () -> Unit,
) {
    val nameEdit = rememberSaveable { mutableStateOf(false) }
    val loginEdit = rememberSaveable { mutableStateOf(false) }
    val passwordEdit = rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
            .background(MaterialTheme.colorScheme.background)
        ,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp)
        ) {
            MyIcon(
                drawableId = R.drawable.ic_set_up_account,
                modifier = Modifier
                    .padding(bottom = 30.dp, top = 70.dp)
                    .align(Alignment.CenterVertically)
            )
            Text(
                stringResource(R.string.userinfo),
                modifier = Modifier
                    .padding(bottom = 30.dp, top = 70.dp, start = 15.dp)
                    .align(Alignment.CenterVertically),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

        MyTextFieldSettings(value = name.value,
            label = stringResource(R.string.name),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            enabled = nameEdit.value,
            trailingIcon = {
                IconButton(
                    onClick = { nameEdit.value = !nameEdit.value }
                ) {
                    MyIcon(
                        drawableId = if (nameEdit.value) R.drawable.ic_edit else R.drawable.ic_edit_off
                    )
                }
            },
            onValueChange = {
                name.value = it
            }
        )

        MyTextFieldSettings(value = login.value,
            label = stringResource(R.string.email),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            enabled = loginEdit.value,
            trailingIcon = {
                IconButton(
                    onClick = { loginEdit.value = !loginEdit.value }
                ) {
                    MyIcon(
                        drawableId = if (loginEdit.value) R.drawable.ic_edit else R.drawable.ic_edit_off
                    )
                }
            },
            onValueChange = {
                login.value = it
            }
        )

        MyTextFieldSettings(
            value = password.value,
            label = stringResource(R.string.password),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            enabled = passwordEdit.value,
            trailingIcon = {
                IconButton(
                    onClick = { passwordEdit.value = !passwordEdit.value }
                ) {
                    MyIcon(
                        drawableId = if (passwordEdit.value) R.drawable.ic_edit else R.drawable.ic_edit_off
                    )
                }
            },
            onValueChange = {
                password.value = it
            }
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MySettingsButton(
                modifier = Modifier
                    .padding(top = 15.dp, bottom = 10.dp)
                    .fillMaxWidth()
                ,
                text = R.string.change,
                onClick = {},
                hint = R.string.change_hint,
                color = MaterialTheme.colorScheme.tertiary
            )
            MySettingsButton(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp)
                    .fillMaxWidth()
                ,
                text = R.string.exit,
                onClick = signOutClick,
            )
            MySettingsButton(
                modifier = Modifier
                    .padding(top = 5.dp, bottom = 10.dp)
                    .fillMaxWidth()
                ,
                text = R.string.delete,
                onClick = {},
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SetUpAccountScreenPreview() = FoodMoodTheme {
    SetUpAccountScreen(
        signOutClick = {}
    )
}