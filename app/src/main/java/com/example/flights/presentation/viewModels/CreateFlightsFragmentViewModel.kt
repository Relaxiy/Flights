package com.example.flights.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flights.domain.interactor.FlightsInteractor
import com.example.flights.domain.models.Flight
import com.example.flights.domain.models.enums.AgeCategory
import com.example.flights.utils.actionSelector.ActionSelector
import com.example.flights.utils.actionSelector.ActionSelector.OpenFragment
import com.example.flights.utils.actionSelector.ActionSelector.ShowSnackBar
import com.example.flights.utils.ext.isPassportNumber
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreateFlightsFragmentViewModel @Inject constructor(
    private val flightsInteractor: FlightsInteractor
) : ViewModel() {

    val submit: LiveData<ActionSelector> get() = _submit
    private val _submit = MutableLiveData<ActionSelector>()

    fun saveFlight(
        departureCity: String,
        arrivalCity: String,
        departureTime: String,
        arrivalTime: String,
        passportNumber: String,
        passengerName: String,
        ageCategory: AgeCategory
    ) {
        val flight = createFlightObject(
            departureCity,
            arrivalCity,
            departureTime,
            arrivalTime,
            passportNumber,
            passengerName,
            ageCategory
        )
        viewModelScope.launch {
            _submit.value = if (validate(flight)) {
                flightsInteractor.saveFlight(
                    flight = flight
                )
                OpenFragment
            } else {
                ShowSnackBar
            }
        }
    }

    private fun createFlightObject(
        departureCity: String,
        arrivalCity: String,
        departureTime: String,
        arrivalTime: String,
        passportNumber: String,
        passengerName: String,
        ageCategory: AgeCategory
    ): Flight {
        return Flight(
            departureCity,
            arrivalCity,
            departureTime,
            arrivalTime,
            passportNumber,
            passengerName,
            ageCategory
        )
    }

    private fun validate(
        flight: Flight
    ): Boolean {
        return !(flight.departureCity.isEmpty() ||
                flight.arrivalCity.isEmpty() ||
                flight.departureTime.isEmpty() ||
                flight.arrivalTime.isEmpty() ||
                flight.passportNumber.isEmpty() ||
                flight.passengerName.isEmpty() ||
                !flight.passportNumber.isPassportNumber())
    }
}