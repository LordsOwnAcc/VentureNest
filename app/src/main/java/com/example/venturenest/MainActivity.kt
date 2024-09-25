package com.example.venturenest

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.Button
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.compose.rememberNavController
import com.example.bottombar.AnimatedBottomBar
import com.example.bottombar.components.BottomBarItem
import com.example.bottombar.model.IndicatorStyle
import com.example.bottombar.model.ItemStyle
import com.example.venturenest.ui.theme.DaggerHilt.MainViewModel
import com.example.venturenest.ui.theme.Navigation.NavItems
import com.example.venturenest.ui.theme.Presentation.Main.ExtraPages.SuccessStoriesPage
import com.example.venturenest.ui.theme.Presentation.Main.OnBoarding
import com.example.venturenest.ui.theme.Presentation.Navigation
import com.example.venturenest.ui.theme.VentureNestTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel by viewModels<MainViewModel>()
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.White.toArgb()),
            navigationBarStyle = SystemBarStyle.dark(Color.Transparent.toArgb())
        )
        setContent {
            var selected by remember {
                mutableStateOf(0)
            }

            var windowInsets = androidx.compose.foundation.layout.WindowInsets.statusBars
var navController = rememberNavController()
            VentureNestTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
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

                        NavItems.entries.forEachIndexed { index, navItems ->
                            BottomBarItem(
                                selected = if (selected == navItems.ordinal) true else false,
                                onClick = {

                                    if(selected == navItems.ordinal){

                                    }else{
                                        selected = navItems.ordinal
                                        if(selected==0) {
                                            navController.navigate("Home")

                                        }else if (selected==1){
                                            navController.navigate("Events")
                                        }else if (selected==2){
                                            navController.navigate("Achievement")
                                        }else if (selected==3){
                                            navController.navigate("Galary")
                                        }else if (selected==4){
                                            navController.navigate("About")
                                        }
                                    }

                                          },
                                imageVector = navItems.Icon,
                                label = navItems.tittle,
                                itemStyle = ItemStyle.STYLE4
                            )
                        }
                    }
                }
//                    , floatingActionButton = { FloatingActionButton(onClick = { /*TODO*/ }, containerColor =  Color.White) {
//                        Icon(imageVector = Icons.Default.EditNote, contentDescription = "", tint =  Color.Red)
//                    }}


                ) {


                   Navigation(navController = navController, windowInsets = windowInsets)
//Column(modifier = Modifier.fillMaxSize()) {
//    Button(onClick = {
//
//        viewModel.viewModelScope.launch {
//            try {
//                Toast.makeText(application,viewModel.getSuccessStories().toString(),Toast.LENGTH_LONG).show()
//            }catch (e:Exception){
//                Toast.makeText(application,e.message,Toast.LENGTH_LONG).show()
//            }
//        }
//    }) {
//
//    }
//}

                }
            }
        }
    }
}

