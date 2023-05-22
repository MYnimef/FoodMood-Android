package com.mynimef.foodmood.presentation.elements


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
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
import com.mynimef.foodmood.data.models.enums.EClientNavigation
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun MyChoiceCard(drawableId: Int,
                 stringResId: Int,
                 onClick: () -> Unit,
) {
    IconButton(
        modifier = Modifier
            .fillMaxSize(),
        onClick = onClick,
    ) {
        Column() {
            MyIcon(
                drawableId = drawableId,
                modifier = Modifier.size(55.dp).align(Alignment.CenterHorizontally)
            )
            Text(stringResource(id = stringResId),
                fontSize = 18.sp,
                textAlign = TextAlign.Center)
        }
    }
}

@Preview
@Composable
fun ChoiceItemPrev() {
    FoodMoodTheme() {
        MyChoiceCard(drawableId = R.drawable.ic_food_breakfast,
            stringResId = R.string.type_food_breakfast) {
        }
    }
}