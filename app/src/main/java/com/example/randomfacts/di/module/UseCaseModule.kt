package com.example.randomfacts.di.module

import com.example.randomfacts.data.repository.HistoryRepository
import com.example.randomfacts.data.repository.NumberFactRepository
import com.example.randomfacts.domain.usecase.*
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {
    @Provides
    fun provideGetFactByRandomNumberUseCase(
        numberFactRepository: NumberFactRepository
    ): GetFactByRandomNumberUseCase {
        return GetFactByRandomNumberUseCase(numberFactRepository)
    }

    @Provides
    fun provideGetFactByUserNumberUseCase(
        numberFactRepository: NumberFactRepository
    ): GetFactByUserNumberUseCase {
        return GetFactByUserNumberUseCase(numberFactRepository)
    }

    @Provides
    fun provideGetAllNumberFactsUseCase(
        historyRepository: HistoryRepository
    ): GetAllNumberFactsUseCase {
        return GetAllNumberFactsUseCase(historyRepository = historyRepository)
    }

    @Provides
    fun provideGetFactByIdUseCase(
        historyRepository: HistoryRepository
    ): GetFactByNumberUseCase {
        return GetFactByNumberUseCase(historyRepository = historyRepository)
    }

    @Provides
    fun provideInsertNumberFactUseCase(
        historyRepository: HistoryRepository
    ): InsertNumberFactToDbUseCase {
        return InsertNumberFactToDbUseCase(historyRepository = historyRepository)
    }

}