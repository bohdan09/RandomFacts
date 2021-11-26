package com.example.randomfacts.di.module

import com.example.randomfacts.data.api.ApiService
import com.example.randomfacts.data.api.RetrofitImpl
import com.example.randomfacts.data.data.source.remote.RemoteDataSourceImpl
import com.example.randomfacts.data.repository.NumberFactRepository
import dagger.Module
import dagger.Provides

@Module
class ApiModule {
    @Provides
    fun provideApiService(): ApiService {
        return RetrofitImpl.getRetrofit()
    }

    @Provides
    fun provideRemoteDataSourceImpl(
        apiService: ApiService
    ): RemoteDataSourceImpl {
        return RemoteDataSourceImpl(apiService = apiService)
    }

    @Provides
    fun provideNumberFactRepository(
        remoteDataSourceImpl: RemoteDataSourceImpl
    ): NumberFactRepository {
        return NumberFactRepository(remoteDataSourceImpl)
    }
}