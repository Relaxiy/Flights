package com.example.flights.utils.ext

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.flights.FlightsApplication
import com.example.flights.di.AppComponent
import com.example.flights.domain.models.Flight
import com.example.flights.presentation.dialog.MessageDialog
import com.google.android.material.snackbar.Snackbar
import kotlin.concurrent.thread

fun FragmentActivity.openFragment(tag: String, fragment: Fragment, id: Int) {
    supportFragmentManager
        .beginTransaction()
        .replace(id, fragment, tag)
        .addToBackStack(tag)
        .commit()
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is FlightsApplication -> appComponent
        else -> this.applicationContext.appComponent
    }

fun Fragment.showSnackBar(message: String) {
    Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).show()
}

fun FragmentActivity.dialog(message: String, deleteFlight: () -> Unit) {
    val myDialogFragment = MessageDialog(message) {
        deleteFlight()
    }
    val manager = supportFragmentManager
    myDialogFragment.show(manager, "myDialog")
}

fun String.isPassportNumber(): Boolean {
    return if (this.length == 7) {
        val letters = this.substring(0, 2)
        val numbers = this.substring(2)
        var counter1 = 0
        var counter2 = 0
        for (it in letters) {
            if (it.digitToIntOrNull() != null) {
                counter1++
            }
        }
        for (it in numbers) {
            if (it.digitToIntOrNull() == null) {
                counter2++
            }
        }

        letters.uppercase() == letters && counter1 == 0 && counter2 == 0
    } else {
        false
    }
}
