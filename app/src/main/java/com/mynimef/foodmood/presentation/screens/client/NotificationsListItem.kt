package com.mynimef.foodmood.presentation.screens.client

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mynimef.foodmood.data.models.Notification
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun NotificationsListItem(notification: Notification) {
    Row(
        modifier = Modifier.padding(bottom = 20.dp)
    ) {
            Text(text = notification.time,
                modifier = Modifier.align(Alignment.CenterVertically),
                color = MaterialTheme.colorScheme.primaryContainer)
            Text(text = notification.message,
                modifier = Modifier.padding(start = 10.dp)
                    .align(Alignment.CenterVertically))
    }
}


@Preview
@Composable
fun NotifItemPrev() {
    FoodMoodTheme {
        NotificationsListItem(Notification(1, "9:00", "Time to drink water"))
    }
}