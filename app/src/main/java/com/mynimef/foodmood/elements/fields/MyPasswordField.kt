package com.mynimef.foodmood.elements.fields

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.mynimef.foodmood.R
import com.mynimef.domain.extensions.CheckPair
import com.mynimef.foodmood.elements.MyIcon
import com.mynimef.foodmood.theme.FoodMoodTheme

@Composable
fun MyPasswordField(
    modifier: Modifier = Modifier,
    label: String,
    pairProvider: () -> CheckPair,
    onValueChange: (String) -> Unit,
) {
    val passwordVisibleState = rememberSaveable { mutableStateOf(false) }
    val passwordVisible = passwordVisibleState.value

    MyCheckField(
        modifier = modifier,
        label = label,
        pairProvider = pairProvider,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
    ) {
        IconButton(
            onClick = { passwordVisibleState.value = !passwordVisible }
        ) {
            MyIcon(
                drawableId = if (passwordVisible) R.drawable.ic_visibility else R.drawable.ic_visibility_off
            )
        }
    }
}

@Composable
@Preview
private fun MyPasswordFieldPreview() = FoodMoodTheme {
    val passwordPair = remember { mutableStateOf("" to false) }

    MyPasswordField(
        label = stringResource(R.string.password),
        pairProvider = { passwordPair.value },
        onValueChange = {
            val isError = it.length < 8
            passwordPair.value = it to isError
        }
    )
}