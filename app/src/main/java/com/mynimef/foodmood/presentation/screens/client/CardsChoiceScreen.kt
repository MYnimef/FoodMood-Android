package com.mynimef.foodmood.presentation.screens.client

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.mynimef.foodmood.data.models.enums.EClientNavigation
import com.mynimef.foodmood.presentation.elements.MyChoiceCard
import com.mynimef.foodmood.presentation.elements.MyPolygonLayout
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun CardsChoiceScreen(
    navigateTo: (route: EClientNavigation) -> Unit
) {
    Box(
       modifier = Modifier.fillMaxSize(),
       contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.pick),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
        )

        MyPolygonLayout(
            modifier = Modifier
                .padding(horizontal = 60.dp)
        ) {
            MyChoiceCard(
                drawableId = R.drawable.ic_food_breakfast,
                stringId = R.string.type_food_breakfast,
            ) {
                navigateTo(EClientNavigation.CREATE)
            }
            MyChoiceCard(
                drawableId = R.drawable.ic_food_dinner,
                stringId = R.string.type_food_dinner,
            ) {
                navigateTo(EClientNavigation.CREATE)
            }
            MyChoiceCard(
                drawableId = R.drawable.ic_food_lunch,
                stringId = R.string.type_food_snack2,
            ) {
                navigateTo(EClientNavigation.CREATE)
            }
            MyChoiceCard(
                drawableId = R.drawable.ic_food_lunch,
                stringId = R.string.type_food_snack1,
            ) {
                navigateTo(EClientNavigation.CREATE)
            }
            MyChoiceCard(
                drawableId = R.drawable.ic_food_supper,
                stringId = R.string.type_food_supper,
            ) {
                navigateTo(EClientNavigation.CREATE)
            }
        }
    }
}

@Composable
private fun MyChoiceCard(
    drawableId: Int,
    stringId: Int,
    onClick: () -> Unit,
) {
    MyChoiceCard(
        modifier = Modifier
            .size(100.dp),
        drawableId = drawableId,
        drawableFraction = 0.7f,
        stringId = stringId,
        fontSize = 18.sp,
        onClick = onClick,
    )
}

@Preview(showBackground = true)
@Composable
private fun CardsChoicePreview() {
    FoodMoodTheme {
        CardsChoiceScreen(
            navigateTo = {}
        )
    }
}