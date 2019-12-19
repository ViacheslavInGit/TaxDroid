package ua.pesochin.taxdroid.util

import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar

fun showErrorSnackbar(
    view: View,
    errorMessage: String
) {
    val snackbar = Snackbar.make(
        view,
        errorMessage,
        Snackbar.LENGTH_SHORT
    )
    snackbar.animationMode = Snackbar.ANIMATION_MODE_FADE

    val layoutParams = snackbar.view.layoutParams as ViewGroup.MarginLayoutParams
    layoutParams.marginEnd = 144
    layoutParams.marginStart = 144

    snackbar.show()
}