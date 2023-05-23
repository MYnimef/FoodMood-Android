package com.mynimef.foodmood.presentation.elements

import android.widget.ProgressBar
import android.widget.Toolbar
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mynimef.foodmood.R
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun MyWaterPanel( setWater: (Float) -> Unit) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
            .background(color = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 6.dp)
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.Center
        ) {
            MyWaterIcon(
                drawableId = R.drawable.ic_glass,
                stringId = R.string.ml_100,
                onClick = {
                    setWater(0.05f)
                }
            )
            Spacer(modifier = Modifier.padding(horizontal = 5.dp))
            MyWaterIcon(drawableId = R.drawable.ic_glass, stringId = R.string.ml_200,
                onClick = {
                    setWater(0.1f)
                })
            Spacer(modifier = Modifier.padding(horizontal = 5.dp))
            MyWaterIcon(drawableId = R.drawable.ic_cup, stringId = R.string.ml_300,
                onClick = {
                    setWater(0.15f)
                })
            Spacer(modifier = Modifier.padding(horizontal = 5.dp))
            MyWaterIcon(drawableId = R.drawable.ic_cup, stringId = R.string.ml_400,
                onClick = {
                    setWater(0.2f)
                })
            Spacer(modifier = Modifier.padding(horizontal = 5.dp))
            MyWaterIcon(drawableId = R.drawable.ic_bottle, stringId = R.string.ml_500,
                onClick = {
                    setWater(0.25f)
                })
            Spacer(modifier = Modifier.padding(horizontal = 5.dp))
            MyWaterIcon(drawableId = R.drawable.ic_bottle, stringId = R.string.ml_600,
                onClick = {
                    setWater(0.3f)
                })
        }

    }
}

@Preview
@Composable
fun MyWaterPanelPrev() {
    FoodMoodTheme {
        MyWaterPanel( {})
    }
}