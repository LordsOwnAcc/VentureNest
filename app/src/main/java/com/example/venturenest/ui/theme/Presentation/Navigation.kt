//package com.example.venturenest.ui.theme.Presentation
//
//import androidx.compose.animation.core.tween
//import androidx.compose.animation.fadeIn
//import androidx.compose.animation.fadeOut
//import androidx.compose.animation.slideInHorizontally
//import androidx.compose.animation.slideOutHorizontally
//import androidx.compose.foundation.layout.WindowInsets
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.example.venturenest.ui.theme.DaggerHilt.AuthViewModel
//import com.example.venturenest.ui.theme.Navigation.BottomNavItem
//import com.example.venturenest.ui.theme.Presentation.Main.AboutPage
//import com.example.venturenest.ui.theme.Presentation.Main.Achievement
//import com.example.venturenest.ui.theme.Presentation.Main.EventsPage
//import com.example.venturenest.ui.theme.Presentation.Main.ExtraPages.StatisticsPage
//import com.example.venturenest.ui.theme.Presentation.Main.ExtraPages.SuccessStoriesPage
//import com.example.venturenest.ui.theme.Presentation.Main.GalaryScreen
//import com.example.venturenest.ui.theme.Presentation.Main.HomePage
//import com.example.venturenest.ui.theme.Presentation.Main.OnBoarding
//
//@Composable
//fun BottomBarNavigation(navController: NavHostController, windowInsets: WindowInsets,parentController: NavHostController) {
//
//    NavHost(navController = navController, startDestination = "Home") {
//
//        composable(route = BottomNavItem.Home.route, enterTransition = {
//            slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(700)) + fadeIn(
//                animationSpec = tween(100)
//            )
//        }, exitTransition = {
//            slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(700)) + fadeOut(
//                animationSpec = tween(2000)
//            )
//        }) {
//            HomePage(
//                window = windowInsets
//            )
//
//        }
//        composable(route = BottomNavItem.event.route, enterTransition = {
//            slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(700)) + fadeIn(
//                animationSpec = tween(100)
//            )
//        }, exitTransition = {
//            slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(700)) + fadeOut(
//                animationSpec = tween(2000)
//            )
//        }) {
//            EventsPage(
//                window = windowInsets
//            )
//        }
//        composable(route = BottomNavItem.Galary.route, enterTransition = {
//            slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(700)) + fadeIn(
//                animationSpec = tween(100)
//            )
//        }, exitTransition = {
//            slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(700)) + fadeOut(
//                animationSpec = tween(2000)
//            )
//        }) {
//           GalaryScreen(
//               window = windowInsets
//           )
//        }
//        composable(route = BottomNavItem.achievement.route, enterTransition = {
//            slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(700)) + fadeIn(
//                animationSpec = tween(100)
//            )
//        }, exitTransition = {
//            slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(700)) + fadeOut(
//                animationSpec = tween(2000)
//            )
//        }) {
//            Achievement(
//                windowInsets = windowInsets
//                , navController = parentController
//            )
//        }
//        composable(
//            route = BottomNavItem.Contact.route, enterTransition = {
//                slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(700)) + fadeIn(
//                    animationSpec = tween(100)
//                )
//            }, exitTransition = {
//                slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(700)) + fadeOut(
//                    animationSpec = tween(2000)
//                )
//            }) {
//            AboutPage(window = windowInsets)
//
//        }
//
//    }
//}
//@Composable
//fun NoBottomBar(navController: NavHostController,
//                windowInsets: WindowInsets,
//                viewModel: AuthViewModel) {
//
//    var isSignedIn by remember {
//        mutableStateOf(viewModel.isUserSignedIn())
//    }
//    var BottomBarNavContoller = rememberNavController()
//    NavHost(navController = navController, startDestination = if (isSignedIn)"BottomBarNav" else "OnBoarding") {
//
//        composable(route = "BottomBarNav", enterTransition = {
//            slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(700)) + fadeIn(
//                animationSpec = tween(100)
//            )
//        }, exitTransition = {
//            slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(700)) + fadeOut(
//                animationSpec = tween(2000)
//            )
//        }) {
//
//           MainPage(parentController = navController,
//               BottomController = BottomBarNavContoller,
//               windowInsets = windowInsets)
//
//        }
//        composable(route = "SuccessStories", enterTransition = {
//            slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(700)) + fadeIn(
//                animationSpec = tween(100)
//            )
//        }, exitTransition = {
//            slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(700)) + fadeOut(
//                animationSpec = tween(2000)
//            )
//        }) {
//            SuccessStoriesPage(navHostController = navController, windowInsets = windowInsets)
//        }
//        composable(route = "Statistics", enterTransition = {
//            slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(700)) + fadeIn(
//                animationSpec = tween(100)
//            )
//        }, exitTransition = {
//            slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(700)) + fadeOut(
//                animationSpec = tween(2000)
//            )
//        }) {
//           StatisticsPage(navHostController = navController, windowInsets = windowInsets)
//        }
//        composable(route = "OnBoarding", enterTransition = {
//            slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(700)) + fadeIn(
//                animationSpec = tween(100)
//            )
//        }, exitTransition = {
//            slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(700)) + fadeOut(
//                animationSpec = tween(2000)
//            )
//        }) {
//              OnBoarding(authViewModel = viewModel , navController = navController)
//        }
//
//
//    }
//}