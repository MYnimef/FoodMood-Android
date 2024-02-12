package com.mynimef.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

private const val INITIAL_ID = -1L

class UpdatableClient(
    repository: AppRepository,
    private val scope: CoroutineScope
) {

    val actualAccountId = repository.getActualAccountId().stateIn(
        scope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = INITIAL_ID
    )

    private val updateFlow = MutableSharedFlow<Unit>(replay = 1)

    init {
        update()
    }

    fun update() {
        scope.launch(Dispatchers.Main) {
            updateFlow.emit(Unit)
        }
    }

    val client = actualAccountId
        .filter {
            it != INITIAL_ID
        }
        .combine(updateFlow) { id, _ ->
            repository.storage.getClient(accountId = id)
        }
        .stateIn(
            scope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = null
        )

}