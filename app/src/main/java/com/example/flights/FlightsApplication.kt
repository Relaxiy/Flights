package com.example.flights

import android.app.Application
import com.example.flights.di.AppComponent
import com.example.flights.di.DaggerAppComponent

class FlightsApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .context(applicationContext)
            .build()
    }
}