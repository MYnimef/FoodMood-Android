package com.mynimef.foodmood.presentation.elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mynimef.foodmood.R
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun MySettingsButton(
    modifier: Modifier,
    text: Int,
    onClick: () -> Unit,
    hint: Int = 0,
    color: Color =  MaterialTheme.colorScheme.error,
) {
    Column(
        modifier = modifier,
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
            ,
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onError,
                contentColor = color,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
            ),
            border = BorderStroke(2.dp, color)
        ) {
            Text(
                stringResource(id = text),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 26.sp,
                color = color
            )
        }
        Text(
            text = if (hint != 0) stringResource(id = hint) else "",
            modifier = Modifier.padding(top = 3.dp),
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            textAlign = TextAlign.Center
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun MySettingsButtonPreview() = FoodMoodTheme {
    MySettingsButton(
        modifier = Modifier
            .padding(top = 5.dp, bottom = 10.dp)
            .fillMaxWidth()
        ,
        text = R.string.delete,
        onClick = {},
        hint = 0,
        color = MaterialTheme.colorScheme.tertiary
    )
}