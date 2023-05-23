package com.mynimef.foodmood.presentation.elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.mynimef.foodmood.presentation.theme.FoodMoodTheme
import java.lang.Error

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextFieldEmotionalCard(
    value: String,
    hint: String,
    modifier: Modifier = Modifier,
    maxLines: Int = 5,
    onValueChange: (String) -> Unit,
) {
    BasicTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        value = if (value.isEmpty()) hint else value,
        onValueChange = onValueChange,
        maxLines = maxLines,
        minLines = 4,
        textStyle = MaterialTheme.typography.bodyMedium
    )
}

@Preview
@Composable
fun MyTextFieldEmotionalCardPreview() {
    FoodMoodTheme {
        MyTextFieldEmotionalCard(
            value = "",
            "emailtest",
            onValueChange = {},
        )
    }
}