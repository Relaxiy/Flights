package com.example.flights.utils.mapper

import com.example.flights.data.room.entity.FlightEntity
import com.example.flights.domain.models.Flight

fun Int.toHours(): String{
    return when{
        this < 1 -> this.toString() + "0"
        else -> this.toString()
    }
}

fun Int.toMinutes(): String{
    return when{
        this < 10 -> "0$this"
        else -> this.toString()
    }
}

fun Flight.toFlightEntity() = FlightEntity(
    departureCity = departureCity,
    arrivalCity = arrivalCity,
    departureTime = departureTime,
    arrivalTime = arrivalTime,
    passportNumber = passportNumber,
    passengerName = passengerName,
    ageCategory = ageCategory
)

fun Flight.toDeletedFlightEntity() = FlightEntity(
    departureCity = departureCity,
    arrivalCity = arrivalCity,
    departureTime = departureTime,
    arrivalTime = arrivalTime,
    passportNumber = passportNumber,
    passengerName = passengerName,
    ageCategory = ageCategory,
    uid = uid
)

fun FlightEntity.toFlight() = Flight(
    departureCity = departureCity,
    arrivalCity = arrivalCity,
    departureTime = departureTime,
    arrivalTime = arrivalTime,
    passportNumber = passportNumber,
    passengerName = passengerName,
    ageCategory = ageCategory,
    uid = uid
)