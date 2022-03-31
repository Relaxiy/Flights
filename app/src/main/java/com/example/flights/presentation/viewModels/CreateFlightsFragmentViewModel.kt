package com.example.flights.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flights.domain.interactor.FlightsInteractor
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
        viewModelScope.launch {
            if (validation(
                    departureCity,
                    arrivalCity,
                    departureTime,
                    arrivalTime,
                    passportNumber,
                    passengerName
                )
            ) {
                flightsInteractor.saveFlight(
                    flight = flightsInteractor.createFlightObject(
                        departureCity,
                        arrivalCity,
                        departureTime,
                        arrivalTime,
                        passportNumber,
                        passengerName,
                        ageCategory
                    )
                )
                _submit.value = OpenFragment
            } else {
                _submit.value = ShowSnackBar
            }
        }
    }

    private fun validation(
        departureCity: String,
        arrivalCity: String,
        departureTime: String,
        arrivalTime: String,
        passportNumber: String,
        passengerName: String,
    ): Boolean {
        return !(departureCity.isEmpty() ||
                arrivalCity.isEmpty() ||
                departureTime.isEmpty() ||
                arrivalTime.isEmpty() ||
                passportNumber.isEmpty() ||
                passengerName.isEmpty() ||
                !passportNumber.isPassportNumber())
    }
}