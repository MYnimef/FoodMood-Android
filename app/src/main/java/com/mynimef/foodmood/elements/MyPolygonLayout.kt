package com.mynimef.foodmood.elements

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mynimef.foodmood.theme.FoodMoodTheme
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.roundToInt
import kotlin.math.sin

@Composable
fun MyPolygonLayout(
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        val number = measurables.size
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        val centerX = width / 2f
        val centerY = height / 2f
        val radius = min(centerX, centerY)
        val angle = 360f / number
        val vertices = (0 until number).map { i ->
            val x = centerX + radius * cos(Math.toRadians(270 + i * angle.toDouble())).toFloat()
            val y = centerY + radius * sin(Math.toRadians(270 + i * angle.toDouble())).toFloat()
            Offset(x, y)
        }

        val placeables = measurables.map { measurable ->
            val index = measurables.indexOf(measurable)
            val vertex = vertices[index]
            val placeable = measurable.measure(constraints)
            val x = vertex.x - placeable.width / 2
            val y = vertex.y - placeable.height / 2
            placeable to Offset(x, y)
        }
        layout(width, height) {
            placeables.forEach { (placeable, offset) ->
                placeable.place(offset.x.roundToInt(), offset.y.roundToInt())
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MyPolygonLayoutPreview() {
    FoodMoodTheme {
        MyPolygonLayout( modifier = Modifier.padding(horizontal = 30.dp),) {
            Text("first")
            Text("second")
            Text("third")
            Text("fourth")
            Text("fifth")
        }
    }
}