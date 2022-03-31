package com.example.flights.di

import com.example.flights.data.repository.FlightsRepository
import com.example.flights.data.repository.FlightsRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun provideBindFlightsRepository(flightsRepositoryImpl: FlightsRepositoryImpl): FlightsRepository
}