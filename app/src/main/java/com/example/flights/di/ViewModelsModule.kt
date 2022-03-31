package com.example.flights.di

import androidx.lifecycle.ViewModel
import com.example.flights.presentation.viewModels.CreateFlightsFragmentViewModel
import com.example.flights.presentation.viewModels.HistoryOfFlightsFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(CreateFlightsFragmentViewModel::class)
    fun provideCreateFlightsFragmentViewModel(
        createFlightsFragmentViewModel: CreateFlightsFragmentViewModel
    ): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HistoryOfFlightsFragmentViewModel::class)
    fun provideHistoryOfFlightsFragmentViewModel(
        historyOfFlightsFragmentViewModel: HistoryOfFlightsFragmentViewModel
    ): ViewModel

}