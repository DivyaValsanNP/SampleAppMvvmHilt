package com.example.sampleappmvvmhilt

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.sampleappmvvmhilt.utils.NetworkUtility
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class NewsApplication : Application() {

    @Inject
    lateinit var context: Context
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: Application class is launched")
        if (!NetworkUtility.isNetworkAvailable(context)) {
            Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val TAG = "NewsApplication"
    }
}