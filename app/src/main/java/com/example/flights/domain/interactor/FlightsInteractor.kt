package com.example.flights.domain.interactor

import com.example.flights.domain.models.enums.AgeCategory
import com.example.flights.domain.models.Flight

interface FlightsInteractor {

    suspend fun getAllFlights(): List<Flight>

    suspend fun saveFlight(flight: Flight)

    suspend fun deleteFlight(flight: Flight)

    fun createFlightObject(
        departureCity: String,
        arrivalCity: String,
        departureTime: String,
        arrivalTime: String,
        passportNumber: String,
        passengerName: String,
        ageCategory: AgeCategory
    ): Flight
}