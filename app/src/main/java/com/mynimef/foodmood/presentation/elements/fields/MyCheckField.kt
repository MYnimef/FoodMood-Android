package com.mynimef.foodmood.presentation.elements.fields

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.mynimef.domain.extensions.CheckPair
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun MyCheckField(
    modifier: Modifier = Modifier,
    label: String,
    pairProvider: () -> CheckPair,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    val pair = pairProvider()

    MyTextFieldLogin(
        modifier = modifier,
        value = pair.first,
        label = label,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        isError = !pair.second,
        onValueChange = onValueChange,
        trailingIcon = trailingIcon,
    )
}

@Composable
@Preview
private fun MyCheckFieldPreview() = FoodMoodTheme {
    val pair = remember { mutableStateOf("" to true) }

    MyCheckField(
        label = "Type 'Hello'",
        pairProvider = { pair.value },
        onValueChange = {
            val isValid = it == "Hello"
            pair.value = it to isValid
        }
    )
}