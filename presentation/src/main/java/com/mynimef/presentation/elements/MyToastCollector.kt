package com.mynimef.presentation.elements

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.mynimef.data.enums.EMessage
import com.mynimef.presentation.extensions.getText
import kotlinx.coroutines.flow.SharedFlow


@Composable
fun MyToastCollector(
    messagesFlow: SharedFlow<EMessage>,
    duration: Int = Toast.LENGTH_SHORT
) {
    val context = LocalContext.current
    LaunchedEffect(null) {
        messagesFlow.collect {
            Toast.makeText(context, it.getText(), Toast.LENGTH_SHORT).show()
        }
    }
}