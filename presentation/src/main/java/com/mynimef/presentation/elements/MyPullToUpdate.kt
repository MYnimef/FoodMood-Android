package com.mynimef.presentation.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mynimef.presentation.theme.FoodMoodTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPullToUpdate(
    modifier: Modifier,
    state: PullToRefreshState,
    updatingProvider: () -> Boolean,
    onUpdate: () -> Unit
) {
    MyPullToUpdate(
        updatingProvider = updatingProvider,
        state = state,
        onUpdate = onUpdate
    )

    PullToRefreshContainer(
        modifier = modifier,
        state = state
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MyPullToUpdate(
    state: PullToRefreshState,
    updatingProvider: () -> Boolean,
    onUpdate: () -> Unit
) {
    val launchedState = remember { mutableStateOf(false) }

    val updating = updatingProvider()
    val refreshing = state.isRefreshing
    val launched = launchedState.value

    if (updating != refreshing) {
        if (!launched && !updating) {
            onUpdate()
            launchedState.value = true
        } else {
            launchedState.value = false
            state.endRefresh()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun MyPullToUpdatePreview() = FoodMoodTheme {
    val pullRefreshState = rememberPullToRefreshState()
    val updatingState = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .nestedScroll(pullRefreshState.nestedScrollConnection)
    ) {
        LazyColumn(Modifier.fillMaxSize()) {
            items(15) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color(15 * it, 3 * it + 100, 5 * it))
                )
            }
        }
        MyPullToUpdate(
            modifier = Modifier.align(Alignment.TopCenter),
            state = pullRefreshState,
            updatingProvider = { updatingState.value },
            onUpdate = {
                coroutineScope.launch(Dispatchers.Main) {
                    updatingState.value = true
                    delay(5000)
                    updatingState.value = false
                }
            }
        )
    }
}