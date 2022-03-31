package com.example.flights.presentation.fragments

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.flights.R
import com.example.flights.domain.models.enums.AgeCategory
import com.example.flights.presentation.viewModels.CreateFlightsFragmentViewModel
import com.example.flights.utils.ext.appComponent
import com.example.flights.utils.ext.openFragment
import com.example.flights.utils.actionSelector.ActionSelector.OpenFragment
import com.example.flights.utils.actionSelector.ActionSelector.ShowSnackBar
import com.example.flights.utils.mapper.toHours
import com.example.flights.utils.mapper.toMinutes
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat.CLOCK_12H
import kotlinx.android.synthetic.main.fragment_create_flights.*
import kotlinx.android.synthetic.main.fragment_create_flights.view.*

class CreateFlightsFragment : Fragment(R.layout.fragment_create_flights) {

    private val createFlightsFragmentViewModel by viewModels<CreateFlightsFragmentViewModel> {
        requireActivity().appComponent.viewModelsFactory()
    }

    companion object {
        const val TAG = "CreateFlightsFragment"
        fun newInstance() = CreateFlightsFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDatePicker(bindDepartureDate)
        initDatePicker(bindArrivalDate)
        initTimePicker(bindDepartureTime)
        initTimePicker(bindArrivalTime)
        initObserver()
    }

    private fun bindAgeCategory(): AgeCategory {
        return if (viewSwitcher.isChecked) {
            AgeCategory.CHILD
        } else {
            AgeCategory.ADULT
        }
    }

    private fun initObserver() {
        createFlight.setOnClickListener {
            createFlightsFragmentViewModel.saveFlight(
                bindDepartureCity.text.toString(),
                bindArrivalCity.text.toString(),
                bindDepartureDate.text.toString() + " " + bindDepartureTime.text.toString(),
                bindArrivalDate.text.toString() + " " + bindArrivalTime.text.toString(),
                bindPassportNumber.text.toString(),
                bindPassengerName.text.toString(),
                bindAgeCategory()
            )
        }
        createFlightsFragmentViewModel.submit.observe(viewLifecycleOwner) { actionSelector ->
            when (actionSelector) {
                is OpenFragment -> requireActivity().apply {
                    openFragment(
                        HistoryOfFlightsFragment.TAG,
                        HistoryOfFlightsFragment.newInstance(),
                        R.id.container
                    )
                }
                is ShowSnackBar -> Snackbar.make(
                    requireView(),
                    "Wrong input!",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun initDatePicker(editText: EditText) {
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .build()
        editText.setOnClickListener {
            datePicker.show(requireActivity().supportFragmentManager, "TAG")
        }
        datePicker.addOnPositiveButtonClickListener {
            editText.setText(datePicker.headerText)
        }
    }

    private fun initTimePicker(editText: EditText) {
        val timePicker = MaterialTimePicker.Builder().setTimeFormat(CLOCK_12H).build()
        editText.setOnClickListener {
            timePicker.show(requireActivity().supportFragmentManager, "TAG")
        }
        timePicker.addOnPositiveButtonClickListener {
            editText.setText(timePicker.hour.toHours() + ":" + timePicker.minute.toMinutes())
        }
    }
}