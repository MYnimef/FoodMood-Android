package com.mynimef.presentation.elements.chart

import android.graphics.Paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mynimef.domain.extensions.toInt


data class AxisLabels(
    val labels: List<String>,
    val paint: Paint,
    val diapason: Boolean,
)


data class AxisVectors(
    val icons: List<VectorPainter>,
    val iconSize: Float,
    val diapason: Boolean,
)


data class AxisLines(
    val color: Color = Color(0x40000000),
    val width: Dp = 1.dp,
    val offset: Dp,
)


fun DrawScope.drawAxisX(
    axis: AxisLabels,
    width: Float,
    start: Float,
    heightPosition: Float,
) {
    val delta = getDelta(axis.labels.size, width, axis.diapason)
    val iterator = getIterator(axis.diapason)

    axis.labels.forEachIndexed { index, value ->
        val x = start + delta * (index + iterator)
        val y = size.height

        drawContext.canvas.nativeCanvas.drawText(
            value,
            x,
            heightPosition,
            axis.paint
        )
    }
}


fun DrawScope.drawAxisX(
    axis: AxisLabels,
    width: Float,
    start: Float,
    heightPosition: Float,
    lines: AxisLines,
) {
    val delta = getDelta(axis.labels.size, width, axis.diapason)
    val iterator = getIterator(axis.diapason)

    val y = size.height - lines.offset.toPx()
    axis.labels.forEachIndexed { index, value ->
        val x = start + delta * (index + iterator)

        drawLine(
            color = lines.color,
            start = Offset(x = x, y = 0f),
            end = Offset(x = x, y = y),
            strokeWidth = lines.width.toPx()
        )

        drawContext.canvas.nativeCanvas.drawText(
            value,
            x,
            heightPosition,
            axis.paint
        )
    }
}


fun DrawScope.drawAxisX(
    axis: AxisVectors,
    width: Float,
    start: Float,
) {
    val delta = getDelta(axis.icons.size, width, axis.diapason)
    val iterator = getIterator(axis.diapason)

    axis.icons.forEachIndexed { index, painter ->
        val x = start + delta * (index + iterator)
        val y = size.height

        translate(left = x, top = y - axis.iconSize / 2) {
            with(painter) { draw(Size(axis.iconSize, axis.iconSize)) }
        }
    }
}


fun DrawScope.drawAxisX(
    axis: AxisVectors,
    width: Float,
    start: Float,
    lines: AxisLines,
) {
    val delta = getDelta(axis.icons.size, width, axis.diapason)
    val iterator = getIterator(axis.diapason)

    axis.icons.forEachIndexed { index, painter ->
        val x = start + delta * (index + iterator)
        val y = size.height

        drawLine(
            color = lines.color,
            start = Offset(x = x, y = 0f),
            end = Offset(x = x, y = y),
            strokeWidth = lines.width.toPx()
        )

        translate(left = x, top = y - axis.iconSize / 2) {
            with(painter) { draw(Size(axis.iconSize, axis.iconSize)) }
        }
    }
}


fun DrawScope.drawAxisY(
    axis: AxisLabels,
    height: Float,
) {
    val delta = getDelta(axis.labels.size, height, axis.diapason)
    val iterator = getIterator(axis.diapason)

    val x = 0f
    axis.labels.forEachIndexed { index, value ->
        val y = height - delta * (index - iterator)

        drawContext.canvas.nativeCanvas.drawText(value, x, y, axis.paint)
    }
}


fun DrawScope.drawAxisY(
    axis: AxisLabels,
    height: Float,
    paddingY: Float = 0f,
    lines: AxisLines,
) {
    val delta = getDelta(axis.labels.size, height, axis.diapason)
    val iterator = getIterator(axis.diapason)

    val x = paddingY/2
    axis.labels.forEachIndexed { index, value ->
        val y = height - delta * (index - iterator)

        drawLine(
            color = lines.color,
            start = Offset(x = lines.offset.toPx(), y = y),
            end = Offset(x = size.width, y = y),
            strokeWidth = lines.width.toPx()
        )

        drawContext.canvas.nativeCanvas.drawText(value, x, y, axis.paint)
    }
}


fun DrawScope.drawAxisY(
    axis: AxisVectors,
    height: Float,
) {
    val delta = getDelta(axis.icons.size, height, axis.diapason)
    val iterator = getIterator(axis.diapason)

    val x = 0f
    axis.icons.forEachIndexed { index, painter ->
        val y = height - delta * (index + iterator)

        translate(left = x, top = y - axis.iconSize / 2) {
            with(painter) { draw(Size(axis.iconSize, axis.iconSize)) }
        }
    }
}


fun DrawScope.drawAxisY(
    axis: AxisVectors,
    height: Float,
    lines: AxisLines,
) {
    val delta = getDelta(axis.icons.size, height, axis.diapason)
    val iterator = getIterator(axis.diapason)

    val x = 0f
    axis.icons.forEachIndexed { index, painter ->
        val y = height - delta * (index + iterator)

        drawLine(
            color = lines.color,
            start = Offset(x = lines.offset.toPx(), y = y),
            end = Offset(x = size.width, y = y),
            strokeWidth = lines.width.toPx()
        )

        translate(left = x, top = y - axis.iconSize / 2) {
            with(painter) { draw(Size(axis.iconSize, axis.iconSize)) }
        }
    }
}


private fun getIterator(diapason: Boolean): Float {
    return if (diapason) 0.5f else 0f
}


private fun getDelta(
    size: Int,
    line: Float,
    diapason: Boolean
): Float {
    return line / (size - (!diapason).toInt())
}