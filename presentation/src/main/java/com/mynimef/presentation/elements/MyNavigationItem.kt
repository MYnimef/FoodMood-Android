package com.mynimef.presentation.elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.mynimef.presentation.extensions.noRippleClickable


data class BottomNavigationItem(
    val icon: Int,
    val iconFill: Int = icon,
    val route: String,
    val onClick: () -> Unit
)


@Composable
fun RowScope.MyNavigationItem(
    drawableId: Int,
    drawableFillId: Int,
    selected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(shape = CircleShape)
            .fillMaxHeight(0.7f)
            .aspectRatio(1f)
            .weight(1f)
            .noRippleClickable (onClick = onClick)
        ,
        contentAlignment = Alignment.Center
    ) {
        MyIcon(
            modifier = Modifier
                .fillMaxSize(0.6f)
            ,
            drawableId = if (selected) drawableFillId else drawableId
        )
    }
}