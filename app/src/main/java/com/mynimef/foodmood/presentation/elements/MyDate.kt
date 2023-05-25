package com.mynimef.foodmood.presentation.elements

import android.content.res.Resources
import android.text.format.DateFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.os.ConfigurationCompat
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun MyDate() {
    val locale = ConfigurationCompat.getLocales(Resources.getSystem().configuration)
    val date = DateFormat.format(
        if (locale.toLanguageTags() == "ru-RU") "d MMMM" else "MMMM, d",
        System.currentTimeMillis()
    )

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(bottomEnd = 10.dp))
            .width(150.dp)
            .height(45.dp)
            .background(color = MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(0.7f),
            text = date.toString(),
            color = MaterialTheme.colorScheme.onPrimary,
            maxLines = 1,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyDatePreview() {
    FoodMoodTheme {
        MyDate()
    }
}