package com.mynimef.foodmood.presentation.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mynimef.foodmood.data.models.enums.ETypeWater
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun MyWaterPanel(
    setWater: (Float) -> Unit
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
            .background(color = MaterialTheme.colorScheme.primaryContainer)
    ) {
        WaterLine(
            types = listOf(
                ETypeWater.GLASS_SMALL,
                ETypeWater.GLASS_BIG,
                ETypeWater.CUP_SMALL
            ),
            setWater = setWater
        )
        WaterLine(
            types = listOf(
                ETypeWater.CUP_BIG,
                ETypeWater.BOTTLE_SMALL,
                ETypeWater.BOTTLE_BIG
            ),
            setWater = setWater
        )
    }
}

@Composable
private fun WaterIcon(
    type: ETypeWater,
    setWater: (Float) -> Unit,
) {
    MyChoiceCard(
        modifier = Modifier.size(100.dp),
        drawableId = type.icon,
        drawableFraction = 0.4f,
        fontSize = 18.sp,
        stringId = type.text,
        onClick = { setWater(type.amount) }
    )
}

@Composable
private fun WaterLine(
    types: List<ETypeWater>,
    setWater: (Float) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp)
        ,
        horizontalArrangement = Arrangement.Center
    ) {
        types.forEach {
            WaterIcon(type = it, setWater = setWater)
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