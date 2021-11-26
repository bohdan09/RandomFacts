package com.example.randomfacts.data.room

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.randomfacts.domain.model.NumbersFact

@Database(entities = [NumbersFact::class], version = 1)
abstract class AppDB : RoomDatabase() {
    abstract fun getHistoryFactsDao(): HistoryFactsDao

    companion object {
        const val TABLE_NAME = "historyTable"
        private const val DB_NAME = "appDBpj"
        private var instance: AppDB? = null

        fun getAppDBInstance(application: Application): AppDB {
            if (instance == null) {
                instance =
                    Room.databaseBuilder(application.applicationContext, AppDB::class.java, DB_NAME)
                        .build()
            }
            return instance!!
        }
    }
}