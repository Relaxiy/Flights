package com.example.flights.data.repository

import com.example.flights.data.room.dao.FlightsDao
import com.example.flights.domain.models.Flight
import com.example.flights.utils.mapper.toDeletedFlightEntity
import com.example.flights.utils.mapper.toFlight
import com.example.flights.utils.mapper.toFlightEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FlightsRepositoryImpl @Inject constructor(
    private val flightsDao: FlightsDao
) : FlightsRepository {

    override suspend fun getAllFlights(): List<Flight> {
        return withContext(Dispatchers.IO) {
            return@withContext flightsDao.getAllFlights().map { flightEntity ->
                flightEntity.toFlight()
            }
        }
    }

    override suspend fun saveFlight(flight: Flight) {
        withContext(Dispatchers.IO) {
            flightsDao.saveFlight(flightEntity = flight.toFlightEntity())
        }
    }

    override suspend fun deleteFlight(flight: Flight) {
        withContext(Dispatchers.IO) {
            flightsDao.deleteFlight(flightEntity = flight.toDeletedFlightEntity())
        }
    }

}