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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mynimef.foodmood.R
import com.mynimef.domain.models.ETypeMeal
import com.mynimef.foodmood.presentation.elements.MyChoiceCard
import com.mynimef.foodmood.presentation.elements.MyPolygonLayout
import com.mynimef.foodmood.presentation.elements.MyWeightCard
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun CreateScreen_1(
    viewModel: CreateViewModel
) {
    val weightState = viewModel.weight.collectAsStateWithLifecycle()
    val dialogShownState = viewModel.isDialogShown.collectAsStateWithLifecycle()

    CreateScreen_1(
        setMealType = viewModel::setMealType,
        weightProvider = { weightState.value },
        setWeight = viewModel::setWeight,
        dialogShownProvider = { dialogShownState.value },
        triggerDialogShown = viewModel::triggerDialogShown,
    )
}

@Composable
private fun CreateScreen_1(
    setMealType: (ETypeMeal) -> Unit,
    weightProvider: () -> Float,
    setWeight: (Float) -> Unit,
    dialogShownProvider: () -> Boolean,
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

        var scale by remember { mutableFloatStateOf(0.5f) }

        val scaleAnimate = animateFloatAsState(
            targetValue = scale,
            animationSpec = tween(
                durationMillis = 500
            ),
            label = "create_animation",
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
                onClick = { setMealType(ETypeMeal.BREAKFAST) }
            )
            MyChoiceCard(
                drawableId = R.drawable.ic_food_dinner,
                stringId = R.string.type_food_dinner,
                onClick = { setMealType(ETypeMeal.DINNER) }
            )
            MyChoiceCard(
                drawableId = R.drawable.ic_food_lunch,
                stringId = R.string.type_food_snack2,
                onClick = { setMealType(ETypeMeal.LUNCH) }
            )
            MyChoiceCard(
                drawableId = R.drawable.ic_weight,
                stringId = R.string.weight,
                onClick = triggerDialogShown
            )
            MyChoiceCard(
                drawableId = R.drawable.ic_food_lunch,
                stringId = R.string.type_food_snack1,
                onClick = { setMealType(ETypeMeal.BRUNCH) }
            )
            MyChoiceCard(
                drawableId = R.drawable.ic_food_supper,
                stringId = R.string.type_food_supper,
                onClick = { setMealType(ETypeMeal.SUPPER) }
            )
        }

        WeightDialog(
            dialogShownProvider = dialogShownProvider,
            triggerDialogShown = triggerDialogShown,
            weightProvider = weightProvider,
            setWeight = setWeight
        )
    }
}

@Composable
private fun WeightDialog(
    dialogShownProvider: () -> Boolean,
    triggerDialogShown: () -> Unit,
    weightProvider: () -> Float,
    setWeight: (Float) -> Unit,
) {
    val dialogShown = dialogShownProvider()

    if (dialogShown) {
        MyWeightCard(
            onDismiss = triggerDialogShown,
            onConfirm = {
                //
            },
            weight = weightProvider(),
            setWeight = setWeight
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CreateScreen_1_Preview() = FoodMoodTheme {
    val weightState = remember { mutableFloatStateOf(0f) }
    val dialogShownState = remember { mutableStateOf(false) }

    CreateScreen_1(
        weightProvider = { weightState.value },
        setWeight = {
            weightState.value = it
        },
        setMealType = { },
        dialogShownProvider = { dialogShownState.value },
        triggerDialogShown = {
            dialogShownState.value = !dialogShownState.value
        }
    )
}