package com.example.flights.di

import com.example.flights.domain.interactor.FlightsInteractor
import com.example.flights.domain.interactor.FlightsInteractorImpl
import dagger.Binds
import dagger.Module

@Module
interface InteractorModule {

    @Binds
    fun provideBindFlightsInteractor(flightsInteractorImpl: FlightsInteractorImpl): FlightsInteractor
}