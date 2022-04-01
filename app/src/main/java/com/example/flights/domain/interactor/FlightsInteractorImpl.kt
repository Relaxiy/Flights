package com.example.flights.domain.interactor

import com.example.flights.data.repository.FlightsRepository
import com.example.flights.domain.models.enums.AgeCategory
import com.example.flights.domain.models.Flight
import javax.inject.Inject

class FlightsInteractorImpl @Inject constructor(
    private val flightsRepository: FlightsRepository
) : FlightsInteractor {

    override suspend fun getAllFlights(): List<Flight> {
        return flightsRepository.getAllFlights()
    }

    override suspend fun saveFlight(flight: Flight) {
        flightsRepository.saveFlight(flight)
    }

    override suspend fun deleteFlight(flight: Flight) {
        flightsRepository.deleteFlight(flight)
    }
}