package com.mynimef.foodmood.presentation.elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
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
            modifier = Modifier.fillMaxWidth(),
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                MaterialTheme.colorScheme.onError,
                color,
                MaterialTheme.colorScheme.secondaryContainer,
                MaterialTheme.colorScheme.onSecondaryContainer,
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
            text = if (hint !=0 ) stringResource(id = hint) else "",
            modifier = Modifier.padding(top = 3.dp),
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            textAlign = TextAlign.Center
        )
    }
}


@Preview
@Composable
private fun MySettingsButtonPreview() {
    FoodMoodTheme {
        MySettingsButton(
            modifier = Modifier
                .padding(top = 5.dp, bottom = 10.dp)
                .fillMaxWidth()
            ,
            text = R.string.delete,
            onClick = {},
            0,
            MaterialTheme.colorScheme.tertiary
        )
    }
}