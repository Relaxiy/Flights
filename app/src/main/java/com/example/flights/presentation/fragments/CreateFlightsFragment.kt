package com.example.flights.presentation.fragments

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.flights.R
import com.example.flights.databinding.FragmentCreateFlightsBinding
import com.example.flights.domain.models.enums.AgeCategory
import com.example.flights.presentation.viewModels.CreateFlightsFragmentViewModel
import com.example.flights.utils.ext.appComponent
import com.example.flights.utils.ext.openFragment
import com.example.flights.utils.actionSelector.ActionSelector.OpenFragment
import com.example.flights.utils.actionSelector.ActionSelector.ShowSnackBar
import com.example.flights.utils.ext.showSnackBar
import com.example.flights.utils.mapper.toHours
import com.example.flights.utils.mapper.toMinutes
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat.CLOCK_12H
import kotlinx.android.synthetic.main.fragment_create_flights.*
import kotlinx.android.synthetic.main.fragment_create_flights.view.*

class CreateFlightsFragment : Fragment(R.layout.fragment_create_flights) {

    private val binding: FragmentCreateFlightsBinding by viewBinding()

    private val createFlightsFragmentViewModel by viewModels<CreateFlightsFragmentViewModel> {
        requireActivity().appComponent.viewModelsFactory()
    }

    companion object {
        const val TAG = "CreateFlightsFragment"
        fun newInstance() = CreateFlightsFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDatePicker(binding.bindDepartureDate)
        initDatePicker(binding.bindArrivalDate)
        initTimePicker(binding.bindDepartureTime)
        initTimePicker(binding.bindArrivalTime)
        initObserver()
    }


    private fun initObserver() {
        createFlight.setOnClickListener {
            val ageCategory = createFlightsFragmentViewModel.bindAgeCategory(binding.viewSwitcher.isChecked)
            createFlightsFragmentViewModel.saveFlight(
                binding.bindDepartureCity.text.toString(),
                binding.bindArrivalCity.text.toString(),
                "${binding.bindDepartureDate.text} ${binding.bindDepartureTime.text}",
                "${binding.bindArrivalDate.text} ${binding.bindArrivalTime.text}",
                binding.bindPassportNumber.text.toString(),
                binding.bindPassengerName.text.toString(),
                ageCategory
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
                is ShowSnackBar -> showSnackBar(getString(R.string.wrong))
            }
        }
    }

    private fun initDatePicker(editText: EditText) {
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText(getString(R.string.select_date))
                .build()
        editText.setOnClickListener {
            datePicker.show(childFragmentManager, getString(R.string.tag))
        }
        datePicker.addOnPositiveButtonClickListener {
            editText.setText(datePicker.headerText)
        }
    }

    private fun initTimePicker(editText: EditText) {
        val timePicker = MaterialTimePicker.Builder().setTimeFormat(CLOCK_12H).build()
        editText.setOnClickListener {
            timePicker.show(childFragmentManager, getString(R.string.tag))
        }
        timePicker.addOnPositiveButtonClickListener {
            editText.setText(timePicker.hour.toHours() + ":" + timePicker.minute.toMinutes())
        }
    }
}