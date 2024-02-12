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

fun AppRepository.getActualAccountId(scope: CoroutineScope) = getActualAccountId().stateIn(
    scope = scope,
    started = SharingStarted.WhileSubscribed(),
    initialValue = INITIAL_ID
)

class UpdatableAccount(
    private val repository: AppRepository,
    private val scope: CoroutineScope
) {

    val actualAccountId = repository.getActualAccountId(scope)

    private val updateFlow = MutableSharedFlow<Unit>(replay = 1)

    init {
        scope.launch(Dispatchers.Main) {
            update()
        }
    }

    suspend fun update() {
        updateFlow.emit(Unit)
    }

    /*
    fun getTrainer() = actualAccountId
        .filter {
            it != INITIAL_ID
        }
        .combine(updateFlow) { id, _ ->
            repository.storage.getTrainer(accountId = id)
        }
        .stateIn(
            scope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = null
        )
     */

    fun getClient() = actualAccountId
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