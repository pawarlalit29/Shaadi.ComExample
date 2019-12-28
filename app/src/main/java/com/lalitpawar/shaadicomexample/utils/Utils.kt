package com.lalitpawar.shaadicomexample.utils

import android.content.Context
import android.widget.Toast

object Utils {
    @JvmStatic
    fun makeUnknownErrorToast(context: Context, message: Int) {
        Toast.makeText(context, context.getString(message), Toast.LENGTH_LONG)
            .show()
    }
}