package com.mynimef.presentation.screens.client

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mynimef.presentation.theme.FoodMoodTheme


@Composable
fun CalendarScreen(
) {
    val viewModel: CalendarViewModel = viewModel()
    CalendarScreen(viewModel)
}


@Composable
private fun CalendarScreen(
    viewModel: CalendarViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
            .verticalScroll(rememberScrollState())
        ,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
    }
}


@Preview(showBackground = true)
@Composable
private fun CalendarScreenPreview() = FoodMoodTheme {
    CalendarScreen()
}