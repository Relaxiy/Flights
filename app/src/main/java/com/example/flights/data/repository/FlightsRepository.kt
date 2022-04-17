package com.example.flights.data.repository

import com.example.flights.domain.models.Flight

interface FlightsRepository {

    suspend fun getAllFlights(): List<Flight>

    suspend fun saveFlight(flight: Flight)

    suspend fun deleteFlight(flight: Flight)
}