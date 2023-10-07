package com.mynimef.foodmood.presentation.elements.fields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun MyTextFieldLogin(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    readOnly: Boolean = false,
    keyboardOptions: KeyboardOptions,
    visualTransformation: VisualTransformation,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    onValueChange: (String) -> Unit
) {
    TextField(
        modifier = modifier,
        readOnly = readOnly,
        value = value,
        label =  { Text(text = label) },
        singleLine = true,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        trailingIcon = trailingIcon,
        isError = isError,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
            unfocusedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
            focusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedContainerColor = MaterialTheme.colorScheme.background,
            cursorColor = MaterialTheme.colorScheme.onPrimaryContainer,
            focusedIndicatorColor = MaterialTheme.colorScheme.onPrimaryContainer,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledIndicatorColor = MaterialTheme.colorScheme.onPrimaryContainer,
            focusedLeadingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
            unfocusedLeadingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledLeadingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
            errorLeadingIconColor = MaterialTheme.colorScheme.error,
            focusedTrailingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
            unfocusedTrailingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledTrailingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
            errorTrailingIconColor = MaterialTheme.colorScheme.error,
            focusedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,
            unfocusedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,
            errorLabelColor = MaterialTheme.colorScheme.error,
            focusedPlaceholderColor = MaterialTheme.colorScheme.onPrimaryContainer,
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledPlaceholderColor = MaterialTheme.colorScheme.onPrimaryContainer,
            focusedSupportingTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
            unfocusedSupportingTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledSupportingTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
            errorSupportingTextColor = MaterialTheme.colorScheme.error,
            errorTextColor = MaterialTheme.colorScheme.error,
            disabledContainerColor = MaterialTheme.colorScheme.background,
            errorContainerColor = MaterialTheme.colorScheme.background,
            errorCursorColor = MaterialTheme.colorScheme.error,
            errorIndicatorColor = MaterialTheme.colorScheme.error,
        )
    )
}

@Preview
@Composable
private fun MyTextFieldLoginPreview() = FoodMoodTheme {
    MyTextFieldLogin(
        modifier = Modifier.fillMaxWidth(),
        value = "email",
        label = "email",
        readOnly = false,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        visualTransformation = VisualTransformation.None,
        isError = true,
        onValueChange = {}
    )
}