package com.mynimef.foodmood.presentation.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun MyGradient(
    modifier: Modifier,
    colorTop: Color,
    colorBottom: Color,
) {
    Box (
        modifier = modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colorTop,
                        colorBottom
                    )
                )
            )
        ,
    )
}