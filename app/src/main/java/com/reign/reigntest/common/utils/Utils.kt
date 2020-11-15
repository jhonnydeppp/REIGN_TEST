package com.reign.reigntest.common.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

object Utils {

    fun showSnackMessage(view: View, message: String) {
         Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }
}