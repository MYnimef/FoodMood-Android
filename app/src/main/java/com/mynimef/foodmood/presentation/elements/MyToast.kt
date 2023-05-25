package com.mynimef.foodmood.presentation.elements

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun MyToast(flow: SharedFlow<String>) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        flow.collect { message ->
            Toast.makeText(
                context,
                message,
                Toast.LENGTH_SHORT,
            ).show()
        }
    }
}