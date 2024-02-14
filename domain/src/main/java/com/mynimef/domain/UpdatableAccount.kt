package com.mynimef.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.stateIn

private const val INITIAL_ID = -1L

class UpdatableAccount(
    private val repository: AppRepository,
    private val scope: CoroutineScope,
) {

    val actualAccountId = repository.getActualAccountId().stateIn(
        scope = scope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = INITIAL_ID
    )

    val updateFlow = MutableStateFlow(false)

    /*
    fun getAccount() = actualAccountId
        .filter { it != INITIAL_ID }
        .combine(updateFlow.filter { !it }) { id, _ ->
            repository.getClient(accountId = id)
        }
        .stateIn(
            scope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = null
        )
     */

    fun getClient(repository: ClientRepository) = actualAccountId
        .filter {
            it != INITIAL_ID
        }
        .combine(updateFlow) { id, _ ->
            repository.getAccount(accountId = id)
        }
        .stateIn(
            scope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = null
        )

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

}