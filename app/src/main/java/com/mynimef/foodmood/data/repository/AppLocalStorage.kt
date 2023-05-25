package com.mynimef.foodmood.data.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.mynimef.foodmood.data.models.database.ClientEntity
import com.mynimef.foodmood.data.models.database.TrainerEntity
import com.mynimef.foodmood.data.models.enums.EAppState
import kotlinx.coroutines.flow.MutableStateFlow

class AppLocalStorage(context: Context) {

    private val sharedPref: SharedPreferences
    val database: AppDatabase

    val appState by lazy {
        MutableStateFlow(EAppState.NONE)
    }
    val client by lazy {
        MutableStateFlow(ClientEntity(id = 0, name = "", trackFood = true, trackWater = true, trackWeight = true))
    }
    val trainer by lazy {
        MutableStateFlow(TrainerEntity(id = 0))
    }

    init {
        sharedPref = context.getSharedPreferences("food_mood", Context.MODE_PRIVATE)
        database =  Room.databaseBuilder(
            context = context,
            klass = AppDatabase::class.java,
            name = "foodmood-database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    fun getSavedState() = EAppState.fromInt(sharedPref.getInt("app_state", 0))
    fun setSavedState(state: Int) = with (sharedPref.edit()) {
        putInt("app_state", state)
        apply()
    }

    fun getSavedId() = sharedPref.getLong("account_id", 0)
    fun setSavedId(id: Long) = with (sharedPref.edit()) {
        putLong("account_id", id)
        apply()
    }

    fun setState(state: EAppState) {
        setSavedState(state.value)
        appState.value = state
    }

}