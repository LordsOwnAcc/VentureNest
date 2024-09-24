package com.example.venturenest.ui.theme.Presentation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.venturenest.ui.theme.Presentation.Main.AboutPage
import com.example.venturenest.ui.theme.Presentation.Main.Achievement
import com.example.venturenest.ui.theme.Presentation.Main.EventsPage
import com.example.venturenest.ui.theme.Presentation.Main.GalaryScreen
import com.example.venturenest.ui.theme.Presentation.Main.HomePage

@Composable
fun Navigation(navController: NavHostController, windowInsets: WindowInsets) {

    NavHost(navController = navController, startDestination = "Home") {

        composable(route = "Home", enterTransition = {
            slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(700)) + fadeIn(
                animationSpec = tween(100)
            )
        }, exitTransition = {
            slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(700)) + fadeOut(
                animationSpec = tween(2000)
            )
        }) {
            HomePage(
                window = windowInsets
            )

        }
        composable(route = "Events", enterTransition = {
            slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(700)) + fadeIn(
                animationSpec = tween(100)
            )
        }, exitTransition = {
            slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(700)) + fadeOut(
                animationSpec = tween(2000)
            )
        }) {
            EventsPage(
                window = windowInsets
            )
        }
        composable(route = "Galary", enterTransition = {
            slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(700)) + fadeIn(
                animationSpec = tween(100)
            )
        }, exitTransition = {
            slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(700)) + fadeOut(
                animationSpec = tween(2000)
            )
        }) {
           GalaryScreen(
               window = windowInsets
           )
        }
        composable(route = "Achievement", enterTransition = {
            slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(700)) + fadeIn(
                animationSpec = tween(100)
            )
        }, exitTransition = {
            slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(700)) + fadeOut(
                animationSpec = tween(2000)
            )
        }) {
            Achievement(
                windowInsets = windowInsets
            )
        }
        composable(
            route = "About", enterTransition = {
                slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(700)) + fadeIn(
                    animationSpec = tween(100)
                )
            }, exitTransition = {
                slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(700)) + fadeOut(
                    animationSpec = tween(2000)
                )
            }) {
            AboutPage(window = windowInsets)

        }

    }
}