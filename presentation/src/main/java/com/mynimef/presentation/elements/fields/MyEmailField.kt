package com.mynimef.presentation.elements.fields

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.mynimef.presentation.R
import com.mynimef.domain.extensions.CheckPair
import com.mynimef.presentation.theme.FoodMoodTheme


@Composable
fun MyEmailField(
    modifier: Modifier = Modifier,
    label: String,
    pairProvider: () -> CheckPair,
    onValueChange: (String) -> Unit,
) {
    MyCheckField(
        modifier = modifier,
        label = label,
        pairProvider = pairProvider,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
    )
}


@Preview
@Composable
private fun MyEmailFieldPreview() = FoodMoodTheme {
    val emailPairState = remember { mutableStateOf("" to false) }

    MyEmailField(
        label = stringResource(R.string.email),
        pairProvider = { emailPairState.value },
        onValueChange = {}
    )
}