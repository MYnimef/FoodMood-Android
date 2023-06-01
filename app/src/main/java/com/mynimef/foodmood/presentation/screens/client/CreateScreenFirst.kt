package com.mynimef.foodmood.presentation.screens.client

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mynimef.foodmood.R
import com.mynimef.foodmood.data.models.enums.ETypeMeal
import com.mynimef.foodmood.presentation.elements.MyChoiceCard
import com.mynimef.foodmood.presentation.elements.MyPolygonLayout
import com.mynimef.foodmood.presentation.elements.MyWeight
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun CreateScreenFirst(
    viewModel: CreateViewModel
) {
    CreateScreenFirst(
        setMealType = viewModel::setMealType,
        weight = viewModel.weight.collectAsState().value,
        setWeight = viewModel::setWeight,
        isDialogShown = viewModel.isDialogShown.collectAsState().value,
        triggerDialogShown = viewModel::triggerDialogShown,
    )
}

@Composable
private fun CreateScreenFirst(
    setMealType: (ETypeMeal) -> Unit,
    weight: Float,
    setWeight: (Float) -> Unit,
    isDialogShown: Boolean,
    triggerDialogShown: () -> Unit,
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

        var scale by remember { mutableStateOf(0.5f) }

        val scaleAnimate = animateFloatAsState(
            targetValue = scale,
            animationSpec = tween(
                durationMillis = 500
            )
        )

        LaunchedEffect(Unit) {
            scale = 1f
        }

        MyPolygonLayout(
            modifier = Modifier
                .scale(scaleAnimate.value)
                .padding(60.dp)
        ) {
            MyChoiceCard(
                drawableId = R.drawable.ic_food_breakfast,
                stringId = R.string.type_food_breakfast,
            ) {
                setMealType(ETypeMeal.BREAKFAST)
            }
            MyChoiceCard(
                drawableId = R.drawable.ic_food_dinner,
                stringId = R.string.type_food_dinner,
            ) {
                setMealType(ETypeMeal.DINNER)
            }
            MyChoiceCard(
                drawableId = R.drawable.ic_food_lunch,
                stringId = R.string.type_food_snack2,
            ) {
                setMealType(ETypeMeal.LUNCH)
            }

            MyChoiceCard(
                drawableId = R.drawable.ic_weight,
                stringId = R.string.weight,
            ) {
                triggerDialogShown()
            }

            MyChoiceCard(
                drawableId = R.drawable.ic_food_lunch,
                stringId = R.string.type_food_snack1,
            ) {
                setMealType(ETypeMeal.BRUNCH)
            }
            MyChoiceCard(
                drawableId = R.drawable.ic_food_supper,
                stringId = R.string.type_food_supper,
            ) {
                setMealType(ETypeMeal.SUPPER)
            }
        }

        if (isDialogShown) {
            MyWeight(
                onDismiss = triggerDialogShown,
                onConfirm = {
                    //
                },
                weight = weight,
                setWeight = setWeight
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardsChoicePreview() {
    FoodMoodTheme {
        val weight = remember { mutableStateOf(0f) }
        val isDialogShown = remember { mutableStateOf(false) }
        CreateScreenFirst(
            weight = weight.value,
            setWeight = {
                weight.value = it
            },
            setMealType = {},
            isDialogShown = isDialogShown.value,
            triggerDialogShown = {
                isDialogShown.value = !isDialogShown.value
            }
        )
    }
}