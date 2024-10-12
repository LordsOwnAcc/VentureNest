package com.example.venturenest.ui.theme.Navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.Newspaper
import androidx.compose.material.icons.outlined.TrendingUp
import androidx.compose.material.icons.outlined.WineBar
import androidx.compose.ui.graphics.vector.ImageVector

enum class NavItems(
    val tittle : String,
    val Icon: ImageVector
){
    Home("Home", Icons.Outlined.Home),
    Event("Event",Icons.Outlined.Event),
    Achievement("Achievement",Icons.Outlined.TrendingUp),
    Galary("Galary",Icons.Outlined.Image),
    About("About",Icons.Outlined.Groups)

}
sealed class BottomNavItem(val route: String, val title: String, val icon: ImageVector) {
    object Home : BottomNavItem("home", "Home", Icons.Default.Home)
    object event : BottomNavItem("event", "Event", Icons.Default.Event)
    object achievement : BottomNavItem("Achievement", "Achievement", Icons.Default.TrendingUp)
    object Galary : BottomNavItem("galary", "Galary", Icons.Default.Image)
    object Contact : BottomNavItem("contact", "Contact", Icons.Default.Call)

}
val listofNavItem = listOf<BottomNavItem>(
    BottomNavItem.Home,
    BottomNavItem.event,
    BottomNavItem.achievement
    ,BottomNavItem.Galary,
    BottomNavItem.Contact
)