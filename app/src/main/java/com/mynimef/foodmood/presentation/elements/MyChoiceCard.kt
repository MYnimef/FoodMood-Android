package com.mynimef.foodmood.presentation.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mynimef.foodmood.R
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun MyChoiceCard(
    modifier: Modifier = Modifier.size(100.dp),
    drawableId: Int,
    drawableFraction: Float = 0.7f,
    stringId: Int,
    fontSize: TextUnit = 18.sp,
    onClick: () -> Unit,
) {
    Box(modifier = modifier) {
        MyChoiceCard(
            drawableId = drawableId,
            drawableFraction = drawableFraction,
            stringId = stringId,
            fontSize = fontSize,
            onClick = onClick,
        )
    }
}

@Composable
private fun MyChoiceCard(
    drawableId: Int,
    drawableFraction: Float,
    stringId: Int,
    fontSize: TextUnit,
    onClick: () -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                onClick = onClick,
                role = Role.Button,
            ),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MyIcon(
                drawableId = drawableId,
                modifier = Modifier
                    .fillMaxSize(drawableFraction)
                    .align(Alignment.CenterHorizontally)
                )
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(stringId),
                fontSize = fontSize,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MyChoiceCardPreview() {
    FoodMoodTheme {
        MyChoiceCard(
            modifier = Modifier
                .size(100.dp),
            drawableId = R.drawable.ic_food_breakfast,
            drawableFraction = 0.7f,
            stringId = R.string.type_food_breakfast,
            fontSize = 18.sp,
            onClick = {},
        )
    }
}