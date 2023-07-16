package com.mynimef.foodmood.presentation.elements

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.mynimef.foodmood.extensions.noRippleClickable
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@Stable
private class MultiSelectorState(
    options: List<String>,
    selectedOption: Int,
    private val selectedColor: Color,
    private val unselectedColor: Color,
    animationDuration: Int,
) {

    val selectedIndex: Float
        get() = _selectedIndex.value
    val textColors: List<Color>
        get() = _textColors.value

    private var _selectedIndex = Animatable(selectedOption.toFloat())
    private var _textColors: State<List<Color>> = derivedStateOf {
        List(numOptions) { index ->
            lerp(
                start = unselectedColor,
                stop = selectedColor,
                fraction = 1f - (((selectedIndex - index.toFloat()).absoluteValue).coerceAtMost(1f))
            )
        }
    }

    private val numOptions = options.size
    private val animationSpec = tween<Float>(
        durationMillis = animationDuration,
        easing = FastOutSlowInEasing,
    )

    fun selectOption(scope: CoroutineScope, index: Int) {
        scope.launch {
            _selectedIndex.animateTo(
                targetValue = index.toFloat(),
                animationSpec = animationSpec,
            )
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MultiSelectorState

        if (selectedColor != other.selectedColor) return false
        if (unselectedColor != other.unselectedColor) return false
        if (_selectedIndex != other._selectedIndex) return false
        if (numOptions != other.numOptions) return false
        if (animationSpec != other.animationSpec) return false

        return true
    }

    override fun hashCode(): Int {
        var result = selectedColor.hashCode()
        result = 31 * result + unselectedColor.hashCode()
        result = 31 * result + _selectedIndex.hashCode()
        result = 31 * result + numOptions
        result = 31 * result + animationSpec.hashCode()
        return result
    }
}

private enum class MultiSelectorOption {
    Option,
    Background,
}

@Composable
fun MultiSelector(
    options: List<String>,
    selectedOption: Int,
    onOptionSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
    selectedTextColor: Color = MaterialTheme.colorScheme.onPrimary,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    selectedBackgroundColor: Color = MaterialTheme.colorScheme.primary,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    animationDuration: Int = 250,
) {
    require(options.size >= 2) { "This composable requires at least 2 options" }
    require(options.size >= selectedOption) { "Invalid selected option [$selectedOption]" }

    val state = remember { MultiSelectorState(
        options = options,
        selectedOption = selectedOption,
        selectedColor = selectedTextColor,
        unselectedColor = textColor,
        animationDuration = animationDuration,
    ) }

    LaunchedEffect(key1 = options, key2 = selectedOption) {
        state.selectOption(this, selectedOption)
    }

    Layout(
        modifier = modifier
            .clip(shape = RoundedCornerShape(percent = 15))
            .background(backgroundColor)
        ,
        content = {
            val colors = state.textColors
            options.forEachIndexed { index, option ->
                Box(
                    modifier = Modifier
                        .layoutId(MultiSelectorOption.Option)
                        .noRippleClickable { onOptionSelect(index) }
                    ,
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = option,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge,
                        color = colors[index],
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(horizontal = 4.dp),
                    )
                }
            }
            Box(
                modifier = Modifier
                    .layoutId(MultiSelectorOption.Background)
                    .clip(RoundedCornerShape(percent = 15))
                    .background(selectedBackgroundColor)
                ,
            )
        }
    ) { measurables, constraints ->
        val optionWidth = constraints.maxWidth / options.size
        val optionConstraints = Constraints.fixed(
            width = optionWidth,
            height = constraints.maxHeight,
        )
        val optionPlaceables = measurables
            .filter { measurable -> measurable.layoutId == MultiSelectorOption.Option }
            .map { measurable -> measurable.measure(optionConstraints) }
        val backgroundPlaceable = measurables
            .first { measurable -> measurable.layoutId == MultiSelectorOption.Background }
            .measure(optionConstraints)
        layout(
            width = constraints.maxWidth,
            height = constraints.maxHeight,
        ) {
            backgroundPlaceable.placeRelative(
                x = (state.selectedIndex * optionWidth).toInt(),
                y = 0,
            )
            optionPlaceables.forEachIndexed { index, placeable ->
                placeable.placeRelative(
                    x = optionWidth * index,
                    y = 0,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMultiSelector() {
    FoodMoodTheme {
        val options1 = listOf("Lorem", "Ipsum", "Dolor")
        var selectedOption1 by remember { mutableStateOf(0) }
        val options2 = listOf("Sit", "Amet", "Consectetur", "Elit", "Quis")
        var selectedOption2 by remember { mutableStateOf(0) }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            MultiSelector(
                modifier = Modifier
                    .padding(all = 16.dp)
                    .fillMaxWidth()
                    .height(56.dp)
                ,
                options = options1,
                selectedOption = selectedOption1,
                onOptionSelect = { num -> selectedOption1 = num },
            )

            MultiSelector(
                modifier = Modifier
                    .padding(all = 16.dp)
                    .fillMaxWidth()
                    .height(56.dp)
                ,
                options = options2,
                selectedOption = selectedOption2,
                onOptionSelect = { num -> selectedOption2 = num },
            )
        }
    }
}