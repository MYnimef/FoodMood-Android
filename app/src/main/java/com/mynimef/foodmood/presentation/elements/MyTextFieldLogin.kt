package com.mynimef.foodmood.presentation.elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextFieldLogin(
    value: String,
    label: String,
    keyboardOptions: KeyboardOptions,
    visualTranfromation: VisualTransformation,
    trailingIcon: @Composable() (() -> Unit)?,
    onValueChange: (String) -> Unit
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = value,
        label =  { Text(label) },
        singleLine = true,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTranfromation,
        trailingIcon = trailingIcon,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
            containerColor = MaterialTheme.colorScheme.background,
            cursorColor = MaterialTheme.colorScheme.onPrimaryContainer,
            focusedIndicatorColor = MaterialTheme.colorScheme.onPrimaryContainer,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledIndicatorColor = MaterialTheme.colorScheme.onPrimaryContainer,
            focusedLeadingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
            unfocusedLeadingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledLeadingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
            errorLeadingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
            focusedTrailingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
            unfocusedTrailingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledTrailingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
            errorTrailingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
            focusedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,
            unfocusedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,
            errorLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,
            placeholderColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledPlaceholderColor = MaterialTheme.colorScheme.onPrimaryContainer,
            focusedSupportingTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
            unfocusedSupportingTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledSupportingTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
            errorSupportingTextColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )
}

@Preview
@Composable
fun PrevMyTextFieldLogin() {
    FoodMoodTheme {
       // MyTextFieldLogin( "tncn", "email", KeyboardOptions(keyboardType = KeyboardType.Email), {})
    }
}