package com.example.sampleappmvvmhilt

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.sampleappmvvmhilt.ui.theme.SampleAppMvvmHiltTheme
import com.example.sampleappmvvmhilt.ui.theme.navigation.AppNavigationGraph
import com.example.sampleappmvvmhilt.utils.NetworkUtility
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    /**
     * API_KEY = eb9aefc91b6d471ea65853e0f8ce1d0b
     */
    @Inject
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            SampleAppMvvmHiltTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    if (NetworkUtility.isNetworkAvailable(context)) {
                        AppEntryPoint()
                    } else {
                        Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    @Composable
    fun AppEntryPoint() {
        AppNavigationGraph()
    }
}
