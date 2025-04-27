package com.example.venturenest

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.bottombar.AnimatedBottomBar
import com.example.bottombar.components.BottomBarItem
import com.example.bottombar.model.ItemStyle
import com.example.venturenest.ui.theme.DaggerHilt.ViewModels.AuthViewModel
import com.example.venturenest.ui.theme.DataBase.DataViewModel
import com.example.venturenest.ui.theme.Navigation.AboutECell
import com.example.venturenest.ui.theme.Navigation.AboutVentureNest
import com.example.venturenest.ui.theme.Navigation.AchievementPage
import com.example.venturenest.ui.theme.Navigation.ContactPage
import com.example.venturenest.ui.theme.Navigation.CouncilScreen
import com.example.venturenest.ui.theme.Navigation.EventsPage
import com.example.venturenest.ui.theme.Navigation.GalleryPage
import com.example.venturenest.ui.theme.Navigation.HomePage
import com.example.venturenest.ui.theme.Navigation.LoginPage
import com.example.venturenest.ui.theme.Navigation.NavigationItems
import com.example.venturenest.ui.theme.Navigation.Profile
import com.example.venturenest.ui.theme.Navigation.SignUpPage
import com.example.venturenest.ui.theme.Navigation.Start
import com.example.venturenest.ui.theme.Navigation.StartScreen
import com.example.venturenest.ui.theme.Navigation.partnerScreen
import com.example.venturenest.ui.theme.Navigation.startupsScreen
import com.example.venturenest.ui.theme.Presentation.EventPage.ReEvent
import com.example.venturenest.ui.theme.Presentation.Setting.AboutEcell
import com.example.venturenest.ui.theme.Presentation.Setting.AboutVenturenest
import com.example.venturenest.ui.theme.Presentation.Setting.ProfilePage

import com.example.venturenest.ui.theme.VentureNestTheme
import com.example.venturenest.ui.theme.auth.AuthStateCompanion
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color(0xffA30D33).toArgb()),
            navigationBarStyle = SystemBarStyle.dark(Color(0xffA30D33).toArgb())
        )
        setContent {
            val autViewmodel: AuthViewModel = hiltViewModel()
            val dataViewModel: DataViewModel = hiltViewModel()

            val state by autViewmodel.authState.collectAsState()
            var navController = rememberNavController()
            val windowInsets = androidx.compose.foundation.layout.WindowInsets.statusBars

            VentureNestTheme {

                val bottomBarRoutes = listOf(
                    "com.example.venturenest.ui.theme.Navigation.HomePage",
                    "com.example.venturenest.ui.theme.Navigation.EventsPage",
                    "com.example.venturenest.ui.theme.Navigation.GalleryPage",

                   "com.example.venturenest.ui.theme.Navigation.AchievementPage"
                )
                val currentBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = currentBackStackEntry?.destination
                val shouldShowBottomBar = bottomBarRoutes.contains(currentDestination?.route)



                LaunchedEffect(key1 = Unit) {


                }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (shouldShowBottomBar) {

                            AnimatedBottomBar(
                                containerColor = Color.White,
                                modifier = Modifier, contentColor = Color.Black


                            ) {

                                NavigationItems.entries.forEach { it ->
                                    BottomBarItem(
                                        selected = currentDestination?.route == it.route,
                                        onClick = {
                                            if (currentDestination?.route != it.route) {
                                                navController.navigate(it.route) {
                                                    popUpTo(navController.graph.startDestinationId) {
                                                        saveState = true
                                                    }
                                                    launchSingleTop = true
                                                    restoreState = true
                                                }
                                            }
                                        },
                                        imageVector = it.icon,
                                        label = it.title, contentColor = Color.White,
                                        iconColor = Color.Black,
                                        textColor = Color.White, itemStyle = ItemStyle.STYLE4
, modifier = Modifier.scale(0.8f)

                                    )
                                }

                            }
                        }
                    }) {
                    com.example.venturenest.ui.theme.Presentation.helper.HideSystemBars()
                    NavHost(
                        navController = navController,
                        startDestination = if (state.state == AuthStateCompanion.UserExist) Start else StartScreen,
                        enterTransition = {
                            slideIntoContainer(
                                AnimatedContentTransitionScope.SlideDirection.Left,
                                tween(300)
                            )
                        },
                        exitTransition = {
                            slideOutOfContainer(
                                AnimatedContentTransitionScope.SlideDirection.Left,
                                tween(300)
                            )
                        },
                        popEnterTransition = {
                            slideIntoContainer(
                                AnimatedContentTransitionScope.SlideDirection.Right,
                                tween(300)
                            )
                        },
                        popExitTransition = {
                            slideOutOfContainer(
                                AnimatedContentTransitionScope.SlideDirection.Right,
                                tween(300)
                            )
                        }

                    ) {

                        composable<HomePage> {

                            com.example.venturenest.ui.theme.Presentation.HomePage.HomePage(
                                window = windowInsets, navController = navController
                            )
                        }

                        composable<EventsPage> {
                            ReEvent(windowInsets, Modifier,navController)
                        }
                        composable<AchievementPage> {

                            com.example.venturenest.ui.theme.Presentation.AchievementsPage.ExpandableBottoSheet(
                                windowInsets,
                                Modifier, navController
                            )

                        }
                        composable<ContactPage> {
                            com.example.venturenest.ui.theme.Presentation.Setting.AboutPage(window = windowInsets, navController = navController)
                        }

                        composable<LoginPage> {
                            com.example.venturenest.ui.theme.Presentation.Login.LoginPage(
                                windowInsets,
                                navController
                            )
                        }
                        composable<StartScreen> {
                            com.example.venturenest.ui.theme.Presentation.Login.StartScreen(
                                windowInsets,
                                navController
                            )
                        }
                        composable<SignUpPage> {
                            com.example.venturenest.ui.theme.Presentation.Login.SignUpPage(
                                windowInsets,
                                navController
                            )
                        }
                        composable<GalleryPage> {
                            com.example.venturenest.ui.theme.Presentation.Gallery.GalaryScreen(window = windowInsets)
                        }

                        composable<CouncilScreen> {
                            val args = it.toRoute<CouncilScreen>()
                            com.example.venturenest.ui.theme.Presentation.AchievementsPage.CouncilScreen(
                                windowInsets, Modifier,
                                args.search,
                                category = args.category,
                                color = args.color

                            )
                        }
                        composable<startupsScreen> {
                            val args = it.toRoute<startupsScreen>()
                            com.example.venturenest.ui.theme.Presentation.AchievementsPage.startupsScreen(
                                windowInsets, Modifier, args.search, args.type, args.contain
                            )
                        }
                        composable<partnerScreen> {
                            val args = it.toRoute<partnerScreen>()
                            com.example.venturenest.ui.theme.Presentation.AchievementsPage.partnerScreen(
                                windowInsets, Modifier, args.search, args.color, args.category
                            )
                        }
                        composable<Start> {
                            com.example.venturenest.ui.theme.Presentation.Profile.ProfileStarter(
                                navController, Modifier, windowInsets
                            )
                        }
                        composable<AboutVentureNest> {
                            AboutVenturenest(modifier = Modifier,navController,windowInsets)
                        }
                        composable<AboutECell> {
                            AboutEcell(modifier = Modifier,navController,windowInsets)
                        }

                        composable<Profile> {
                            ProfilePage(Modifier,navController)
                        }

                    }
                }
            }
        }

    }}


