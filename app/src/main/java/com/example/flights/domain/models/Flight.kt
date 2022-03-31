package com.example.flights.domain.models

import com.example.flights.domain.models.enums.AgeCategory

data class Flight(
    val departureCity: String,
    val arrivalCity: String,
    val departureTime: String,
    val arrivalTime: String,
    val passportNumber: String,
    val passengerName: String,
    val ageCategory: AgeCategory,
    var uid: Long = 0
)
