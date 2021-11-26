package com.example.randomfacts.di.module

import android.app.Application
import com.example.randomfacts.data.data.source.local.LocalDataSource
import com.example.randomfacts.data.data.source.local.LocalDataSourceImpl
import com.example.randomfacts.data.repository.HistoryRepository
import com.example.randomfacts.data.room.AppDB
import com.example.randomfacts.data.room.HistoryFactsDao
import dagger.Module
import dagger.Provides

@Module
class RoomModule(private val application: Application) {
    @Provides
    fun provideFactDao(
        roomDataBase: AppDB
    ): HistoryFactsDao {
        return roomDataBase.getHistoryFactsDao()
    }

    @Provides
    fun provideAppDB(): AppDB {
        return AppDB.getAppDBInstance(application = application)
    }

    @Provides
    fun provideLocalDataSourceImpl(
        factsDao: HistoryFactsDao
    ): LocalDataSourceImpl {
        return LocalDataSourceImpl( factsDao)
    }

    @Provides
    fun provideHistoryRepository(
        localDataSource: LocalDataSourceImpl
    ): HistoryRepository {
        return HistoryRepository(localDataSource)
    }
}