package com.example.randomfacts

import android.app.Application
import com.example.randomfacts.di.AppComponent
import com.example.randomfacts.di.DaggerAppComponent
import com.example.randomfacts.di.module.ApiModule
import com.example.randomfacts.di.module.RoomModule

class App : Application() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .apiModule(ApiModule())
            .roomModule(RoomModule(this))
            .build()
    }

    fun getAppComponent(): AppComponent = appComponent
}