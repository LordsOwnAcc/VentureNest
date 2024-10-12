package com.example.venturenest.ui.theme.Presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.bottombar.AnimatedBottomBar
import com.example.bottombar.components.BottomBarItem
import com.example.bottombar.model.IndicatorStyle
import com.example.bottombar.model.ItemStyle
import com.example.venturenest.ui.theme.DaggerHilt.AuthViewModel
import com.example.venturenest.ui.theme.DaggerHilt.MainViewModel
import com.example.venturenest.ui.theme.Navigation.listofNavItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "RestrictedApi")
@Composable
fun MainPage(
    modifier: Modifier = Modifier
            ,
    parentController:NavHostController
    ,BottomController: NavHostController
    ,windowInsets: WindowInsets
//    ,authViewModel: AuthViewModel,
//    viewModel: MainViewModel

) {
    Scaffold(modifier.fillMaxSize(),
            bottomBar = {
                    AnimatedBottomBar(
                        modifier = Modifier
                            .shadow(
                                elevation = 500.dp,
                                spotColor = Color.Black,
                                shape = RectangleShape,

                                )
                            //.border(1.dp, Color.Black, RectangleShape)
                                ,
                        containerColor =Color.White,
                        containerShape = RectangleShape,

                        itemSize = 5,
                        indicatorStyle = IndicatorStyle.WORM
                    ) {
                        listofNavItem.forEachIndexed { index, bottomNavItem ->
                            val navBackStackEntry by BottomController.currentBackStackEntryAsState()

                            BottomBarItem(
                                selected = navBackStackEntry?.destination?.route == bottomNavItem.route,
                                onClick = {
                                    if (navBackStackEntry?.destination?.route != bottomNavItem.route) {

                                        BottomController.navigate(bottomNavItem.route)

                                    }
                                          },
                                imageVector = bottomNavItem.icon,
                                label = bottomNavItem.title,
                                itemStyle = ItemStyle.STYLE4
                            )
                        }
                        }

    }) {
        BottomBarNavigation(navController = BottomController,
            windowInsets = windowInsets, parentController = parentController)
    }

}

//@Preview
//@Composable
//private fun PreviewMainPage() {
//    MainPage()
//}