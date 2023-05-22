package com.mynimef.foodmood.presentation.screens.client

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mynimef.foodmood.R
import com.mynimef.foodmood.data.models.enums.EClientNavigation
import com.mynimef.foodmood.data.models.enums.EFoodType
import com.mynimef.foodmood.presentation.elements.MyChoiceCard
import com.mynimef.foodmood.presentation.elements.MyPolygonLayout
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun CardsChoiceScreen(
    viewModel: CreateViewModel,
    navigateTo: (route: String) -> Unit
) {
    CardsChoiceScreen(
       navigateTo = navigateTo,
       foodType = viewModel.foodType.collectAsState().value,
       setFoodType= viewModel::setFoodType,)
}

@Composable
private fun CardsChoiceScreen(
     navigateTo: (route: String) -> Unit,
     foodType: EFoodType,
){
     setFoodType: (EFoodType) -> Unit,
   Box(
       modifier = Modifier.fillMaxSize(),
       contentAlignment = Alignment.Center
   )  {
    Text(stringResource(id = R.string.pick), style = MaterialTheme.typography.titleMedium,
    textAlign = TextAlign.Center)
    MyPolygonLayout(Modifier.padding(horizontal = 60.dp)) {
        MyChoiceCard(drawableId = R.drawable.ic_food_breakfast, R.string.type_food_breakfast) {navigateTo("")
        setFoodType(EFoodType.BREAKFAST)}
        MyChoiceCard(drawableId = R.drawable.ic_food_dinner, R.string.type_food_dinner) {navigateTo("")
        setFoodType(EFoodType.DINNER)}
        MyChoiceCard(drawableId = R.drawable.ic_food_lunch, R.string.type_food_snack2) {navigateTo("")
        setFoodType(EFoodType.SNACK2)}
        MyChoiceCard(drawableId = R.drawable.ic_food_lunch, R.string.type_food_snack1) {navigateTo("")
        setFoodType(EFoodType.SNACK1)}
        MyChoiceCard(drawableId = R.drawable.ic_food_supper, R.string.type_food_supper) {navigateTo("")
        setFoodType(EFoodType.SUPPER)}
    }
}

@Composable
fun ChoicePrev() {
    FoodMoodTheme() {
        CardsChoiceScreen(
            navigateTo = {},
            foodType = EFoodType.DINNER,
            setFoodType = {}
        )
    }
}