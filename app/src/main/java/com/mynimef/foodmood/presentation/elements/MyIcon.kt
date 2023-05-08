package com.mynimef.foodmood.presentation.elements

import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

@Composable
fun MyIcon(
    modifier: Modifier = Modifier,
    drawableId: Int,
    tint: Color = LocalContentColor.current
) {
    Icon (
        modifier = modifier,
        painter = painterResource(id = drawableId),
        contentDescription = null
    )
}