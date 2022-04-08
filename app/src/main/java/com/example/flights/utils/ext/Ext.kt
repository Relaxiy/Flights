package com.example.flights.utils.ext

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.flights.FlightsApplication
import com.example.flights.di.AppComponent
import com.google.android.material.snackbar.Snackbar

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

fun String.isPassportNumber(): Boolean {
    return Regex("[A-Z]{2}[0-9]{5}").matches(this)
}
