package com.example.sampleappmvvmhilt.utils

import android.content.Context
import android.widget.Toast
import javax.inject.Inject

class AppHelper @Inject constructor(private val context: Context) {


    fun showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, message, duration).show()
    }
}