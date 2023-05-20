package com.mynimef.foodmood.data.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mynimef.foodmood.data.models.database.AccountEntity
import com.mynimef.foodmood.data.repository.dao.AccountDao

@Database(entities = [AccountEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun accountDao(): AccountDao

    companion object {
        fun init(applicationContext: Context): AppDatabase {
            return Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "foodmood-database"
            ).build()
        }
    }
}