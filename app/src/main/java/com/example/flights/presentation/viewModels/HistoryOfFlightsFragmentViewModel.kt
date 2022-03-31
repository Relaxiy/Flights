package com.example.flights.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flights.domain.interactor.FlightsInteractor
import com.example.flights.domain.models.Flight
import kotlinx.coroutines.launch
import javax.inject.Inject

class HistoryOfFlightsFragmentViewModel @Inject constructor(
    private val flightsInteractor: FlightsInteractor
) : ViewModel() {

    val flights: LiveData<List<Flight>> get() = _flights
    private val _flights = MutableLiveData<List<Flight>>()

    init {
        getFlights()
    }

    private fun getFlights() {
        viewModelScope.launch {
            _flights.postValue(flightsInteractor.getAllFlights())
        }
    }

    fun deleteFlight(flight: Flight) {
        viewModelScope.launch {
            viewModelScope.launch {
                flightsInteractor.deleteFlight(flight)
            }.onJoin
            viewModelScope.launch {
                getFlights()
            }
        }
    }
}