package com.example.flights.presentation.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.flights.R
import com.example.flights.domain.models.Flight

class MessageDialog(
    private val message: String, private val deleteFlight: () -> Unit
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle(getString(R.string.warning))
                .setMessage(message)
                .setPositiveButton(getString(R.string.delete)) { dialog, _ ->
                    deleteFlight()
                    dialog.cancel()
                }.setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                    dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException(getString(R.string.null_activity))
    }
}