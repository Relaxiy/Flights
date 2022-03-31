package com.example.flights.utils.actionSelector

sealed class ActionSelector{
    object OpenFragment : ActionSelector()
    object ShowSnackBar : ActionSelector()
}
