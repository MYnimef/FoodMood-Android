package com.mynimef.foodmood.presentation.screens.client

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mynimef.foodmood.R
import com.mynimef.data.Notification
import com.mynimef.foodmood.presentation.elements.MyIcon
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun SetUpNotificationsScreen() {
    val notifications = remember {
        listOf(
            Notification(1, "09:00", "Time to drink water"),
            Notification(1, "14:00", "Time to eat"),
            Notification(1, "18:00", "Some very very very big text")
        )
    }
    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        CenterElements(
            notifications = notifications
        )
    }
}
@Composable
private fun CenterElements(
    notifications: List<Notification>,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp)
        ) {
            MyIcon(
                drawableId = R.drawable.ic_set_up_notifications,
                modifier = Modifier
                    .padding(bottom = 30.dp, top = 70.dp)
                    .align(Alignment.CenterVertically)
            )
            Text(
                stringResource(R.string.notifications),
                modifier = Modifier
                    .padding(bottom = 20.dp, top = 70.dp, start = 15.dp)
                    .align(Alignment.CenterVertically),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        LazyColumn(

        ) {
            items(
                items = notifications,
                itemContent = {
                    NotificationsListItem(notification = it)
                })
        }
    }
}

@Preview
@Composable
private fun SetUpNotificationsScreenPreview() {
    FoodMoodTheme {
        SetUpNotificationsScreen()
    }
}