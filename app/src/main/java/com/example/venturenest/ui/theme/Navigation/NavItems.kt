package com.example.venturenest.ui.theme.Navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.Newspaper
import androidx.compose.material.icons.outlined.TrendingUp
import androidx.compose.material.icons.outlined.WineBar
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.venturenest.ui.theme.Presentation.CollageClass
import kotlinx.serialization.Serializable


@Serializable
object HomePage

@Serializable
object LoginPage

@Serializable
object AchievementPage

@Serializable
object ContactPage

@Serializable
object EventsPage

@Serializable
object SettingPage

@Serializable
object GalleryPage

@Serializable
object StatsPage

@Serializable
object StartScreen



@Serializable
object AboutVentureNest

@Serializable
object AboutECell

@Serializable
object Forum

@Serializable
object SignUpPage

enum class NavigationItems(
    val title: String,
    val route :String,
    val icon: ImageVector,
){
    HomePage(
        title = "Home",
        route = "com.example.venturenest.ui.theme.Navigation.HomePage"
        , icon = Icons.Default.Home
    )
    ,
    SearchPage(
        title = "Events",
        route = "com.example.venturenest.ui.theme.Navigation.EventsPage"
        , icon = Icons.Default.Event
    ),
    SavedPage(
        title = "Gallery"
        , route = "com.example.venturenest.ui.theme.Navigation.GalleryPage"
        ,Icons.Default.Image
    ),
    SettinPage(
        title = "Setting"
        , route = "com.example.venturenest.ui.theme.Navigation.ContactPage"
        ,Icons.Default.Settings    )
}