package com.mynimef.foodmood.presentation.elements.fields

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun MyCheckField(
    modifier: Modifier = Modifier,
    label: String,
    valuesProvider: () -> Pair<String, Boolean>,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    val values = valuesProvider()

    MyTextFieldLogin(
        modifier = modifier,
        value = values.first,
        label = label,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        isError = values.second,
        onValueChange = onValueChange,
        trailingIcon = trailingIcon,
    )
}

@Composable
@Preview
private fun MyCheckFieldPreview() = FoodMoodTheme {
    val values = remember { mutableStateOf("" to false) }

    MyCheckField(
        label = "Type 'Hello'",
        valuesProvider = { values.value },
        onValueChange = {
            val isError = it != "Hello"
            values.value = it to isError
        }
    )
}