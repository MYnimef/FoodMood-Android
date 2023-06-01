package com.mynimef.foodmood.presentation.screens.client

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mynimef.foodmood.R
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme
import org.intellij.lang.annotations.JdkConstants.CalendarMonth

@Composable
fun CalendarScreen(
) {
    val viewModel: CalendarViewModel = viewModel()
    CalendarScreen(viewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
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
        Text(
            stringResource(R.string.emotion_chart),
            modifier = Modifier.padding(bottom = 30.dp, top = 70.dp),
            style = MaterialTheme.typography.titleLarge)
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarPrev() {
    FoodMoodTheme {
        CalendarScreen()
    }
    
}