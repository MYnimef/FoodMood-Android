package com.mynimef.foodmood.presentation.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mynimef.foodmood.R
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun MyWaterIcon(
    drawableId: Int,
    stringId: Int,
    onClick: () -> Unit,
) {
    IconButton(
        modifier = Modifier
            .padding(vertical = 5.dp)
            .size(80.dp),
        onClick = onClick) {
        Column() {
            MyIcon(
                drawableId = drawableId,
                modifier = Modifier
                    .size(40.dp)
                    .padding(bottom = 5.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(stringId),
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview
@Composable
fun MyWaterIconPrev() {
    FoodMoodTheme {
        MyWaterIcon(R.drawable.ic_bottle, R.string.type_food_dinner, {})
    }
}