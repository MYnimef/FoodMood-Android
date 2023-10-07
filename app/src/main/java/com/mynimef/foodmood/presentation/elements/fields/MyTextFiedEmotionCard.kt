package com.mynimef.foodmood.presentation.elements.fields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun MyTextFieldEmotionalCard(
    value: String,
    hint: String,
    maxLines: Int = 5,
    onValueChange: (String) -> Unit,
) {
    BasicTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
        ,
        value = value,
        onValueChange = onValueChange,
        maxLines = maxLines,
        minLines = 4,
        textStyle = MaterialTheme.typography.bodyMedium,
        decorationBox = { innerTextField ->
            if (value.isEmpty()) {
                Text(
                    text = hint,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            innerTextField()
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun MyTextFieldEmotionalCardPreview() {
    FoodMoodTheme {
        MyTextFieldEmotionalCard(
            value = "",
            "emotion",
            onValueChange = {},
        )
    }
}