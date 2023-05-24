package com.mynimef.foodmood.presentation.elements

import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mynimef.foodmood.R

@Composable
fun MyEmotionButton(
    drawableId: Int,
) {
    IconButton(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .size(60.dp),
        onClick = {  }) {
        MyIcon(
            drawableId =  drawableId,
            modifier = Modifier.size(55.dp)
            )
    }
}