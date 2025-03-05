package com.example.venturenest.ui.theme.Presentation.helper

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.view.View
import android.view.Window
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat


fun hideSystemNavigationBarLegacy(activity: Activity) {
    activity.window.decorView.systemUiVisibility = (
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_FULLSCREEN
            )
}

@SuppressLint("WrongConstant")
fun hideSystemNavigationBar(window: Window) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        // For Android 11 (API 30) and above
        val controller = WindowCompat.getInsetsController(window, window.decorView)
        controller?.apply {
            hide(android.view.WindowInsets.Type.navigationBars()) // Hide navigation bar
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    } else {
        // For Android 10 and below
        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                        View.SYSTEM_UI_FLAG_FULLSCREEN
                )
    }
}


@Composable
fun HideSystemBars() {
    val context = LocalContext.current
    val window = (context as Activity).window

    SideEffect {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            hideSystemNavigationBar(window)
        } else {
            hideSystemNavigationBarLegacy(context)
        }
    }
}