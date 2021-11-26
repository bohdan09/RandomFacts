package com.example.randomfacts.di

import com.example.randomfacts.di.module.ApiModule
import com.example.randomfacts.di.module.RoomModule
import com.example.randomfacts.di.module.UseCaseModule
import com.example.randomfacts.ui.MainActivity
import com.example.randomfacts.viewmodel.factory.MainActivityViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ApiModule::class, UseCaseModule::class, RoomModule::class]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun mainActivityFactory(): MainActivityViewModelFactory
}