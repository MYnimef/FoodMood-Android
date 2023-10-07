package com.mynimef.foodmood.presentation.screens.trainer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mynimef.foodmood.presentation.elements.MyDate
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = viewModel()
    Column {
        MyDate()
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "home",
            maxLines = 1,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun HomeScreenPreview() = FoodMoodTheme {
    HomeScreen()
}