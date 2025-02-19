package com.example.venturenest

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.WindowInsets
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.bottombar.AnimatedBottomBar
import com.example.bottombar.components.BottomBarItem
import com.example.bottombar.model.IndicatorStyle
import com.example.bottombar.model.ItemStyle
import com.example.venturenest.ui.theme.DaggerHilt.AuthViewModel
import com.example.venturenest.ui.theme.DaggerHilt.MainViewModel
import com.example.venturenest.ui.theme.DaggerHilt.SuccessStories
import com.example.venturenest.ui.theme.Navigation.AchievementPage
import com.example.venturenest.ui.theme.Navigation.ContactPage
import com.example.venturenest.ui.theme.Navigation.EventsPage
import com.example.venturenest.ui.theme.Navigation.GalleryPage
import com.example.venturenest.ui.theme.Navigation.HomePage
import com.example.venturenest.ui.theme.Navigation.LoginPage
import com.example.venturenest.ui.theme.Navigation.NavigationItems
import com.example.venturenest.ui.theme.Navigation.SettingPage
import com.example.venturenest.ui.theme.Navigation.StatsPage
import com.example.venturenest.ui.theme.Presentation.Main.AboutPage
import com.example.venturenest.ui.theme.Presentation.Main.Achievement
import com.example.venturenest.ui.theme.Presentation.Main.EventsPage
import com.example.venturenest.ui.theme.Presentation.Main.ExtraPages.StatisticsPage
import com.example.venturenest.ui.theme.Presentation.Main.ExtraPages.SuccessStoriesPage
import com.example.venturenest.ui.theme.Presentation.Main.GalaryScreen
import com.example.venturenest.ui.theme.Presentation.Main.HomePage
import com.example.venturenest.ui.theme.Presentation.Main.OnBoarding
import com.example.venturenest.ui.theme.VentureNestTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.White.toArgb()),
            navigationBarStyle = SystemBarStyle.dark(Color.Transparent.toArgb())
        )
        setContent {
            var navController = rememberNavController()
            val windowInsets = androidx.compose.foundation.layout.WindowInsets.statusBars

            VentureNestTheme {

                val bottomBarRoutes = listOf(
                    "com.example.venturenest.ui.theme.Navigation.HomePage",
                    "com.example.venturenest.ui.theme.Navigation.EventsPage",
                    "com.example.venturenest.ui.theme.Navigation.GalleryPage",
                    "com.example.venturenest.ui.theme.Navigation.SettingPage"
                )
                val currentBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = currentBackStackEntry?.destination
                val shouldShowBottomBar = bottomBarRoutes.contains(currentDestination?.route)



                Scaffold(modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (shouldShowBottomBar) {


                            AnimatedBottomBar(
                                containerColor = Color(0xffA30D33),
                                modifier = Modifier
                                , contentColor = Color.White


                            ) {
                                NavigationItems.entries.forEach { it ->
                                    BottomBarItem(
                                        selected = currentDestination?.route == it.route,
                                        onClick = {
                                            if(currentDestination?.route != it.route) {
                                                navController.navigate(route = it.route)
                                            }},
                                        imageVector = it.icon,
                                        label = it.title
                                        , contentColor = Color.White,
                                        iconColor = Color.White,
                                        textColor = Color.White
                                        , itemStyle = ItemStyle.STYLE4

                                    )
                                }

                            }
                        }
                    }) {
                    NavHost(
                        navController = navController,
                        startDestination = HomePage
                    ) {

                        composable<HomePage> {
                           com.example.venturenest.ui.theme.Presentation.Main.HomePage(
                               window = windowInsets)
                        }

                        composable<EventsPage> {
                            com.example.venturenest.ui.theme.Presentation.Main.EventsPage(window =
                            windowInsets)
                        }
                        composable<AchievementPage> {
                           Achievement(windowInsets = windowInsets,
                               navController = navController)
                        }
                        composable<SettingPage> {
                           AboutPage(window = windowInsets)
                        }

                        composable<LoginPage> {
                              OnBoarding( navController = navController)
                        }

                        composable<GalleryPage> {
                          GalaryScreen(window = windowInsets)
                        }
                        composable<StatsPage> {
                           StatisticsPage(navHostController = navController,
                               windowInsets = windowInsets)
                        }
                        composable<com.example.venturenest.ui.theme.Navigation.SuccessStories> {
                           SuccessStoriesPage(navHostController = navController,
                               windowInsets = windowInsets)
                        }


                    }
                }
            }
        }

    }
}

