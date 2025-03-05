package com.example.venturenest.ui.theme.Presentation.helper

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat

@Composable
fun ChangeStatusBarColorEdgeToEdge(color: Color, useDarkIcons: Boolean = true) {
    val context = LocalContext.current
    val window = (context as Activity).window

    SideEffect {
        // Enable edge-to-edge display
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // Change status bar color
        window.statusBarColor = color.toArgb()

        // Set status bar icons (light/dark)
        val insetsController = WindowInsetsControllerCompat(window, window.decorView)
        insetsController.isAppearanceLightStatusBars = useDarkIcons
    }
}