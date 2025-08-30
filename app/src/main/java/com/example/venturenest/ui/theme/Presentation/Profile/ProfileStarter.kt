package com.example.venturenest.ui.theme.Presentation.Profile

import android.util.Log
import android.widget.Toast

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.GridOn
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.venturenest.R
import com.example.venturenest.ui.theme.DaggerHilt.States.LoadingPageCompanion
import com.example.venturenest.ui.theme.DaggerHilt.ViewModels.FireStoreViewmodel
import com.example.venturenest.ui.theme.DaggerHilt.ViewModels.LoadingStateViewmodel
import com.example.venturenest.ui.theme.DataBase.DataViewModel
import com.example.venturenest.ui.theme.DataBase.Users
import com.example.venturenest.ui.theme.Navigation.HomePage
import com.example.venturenest.ui.theme.Presentation.helper.ChangeStatusBarColorEdgeToEdge
import com.example.venturenest.ui.theme.background
import kotlin.math.truncate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileStarter(
    navController: NavController, modifier: Modifier = Modifier,
    windowInsets: WindowInsets
) {
    ChangeStatusBarColorEdgeToEdge(background)

    val loadingViewmodel: LoadingStateViewmodel = hiltViewModel()
    val state by loadingViewmodel.state.collectAsState()

    Column(
        modifier.fillMaxSize()
            .background(background), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        when (state.state) {
            is LoadingPageCompanion.Error -> {
                if (state.Data.events.isEmpty()
                    || state.Data.partner.isEmpty()
                    || state.Data.photo.isEmpty() ||
                    state.Data.councilmembers.isEmpty()
                    || state.Data.heroSection.isEmpty()
                    || state.Data.startUp.isEmpty()
                    || state.Data.sucessStories.isEmpty()
                ) {
                    Log.e( "Netcheck","error not loaded")
                    Log.e( "Netcheck","${state.Data.events}")
                 Text("")
                } else {
                    LaunchedEffect(Unit) {
                        navController.navigate(HomePage)
                    }

                }
            }

            is LoadingPageCompanion.Result -> {
                LaunchedEffect(Unit) {
                    navController.navigate(HomePage){
                        popUpTo(HomePage){
                            inclusive=true
                        }
                    }
                }
            }

            is LoadingPageCompanion.Loading -> {
                Column (modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally){


                    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
                    val progress by animateLottieCompositionAsState(composition, iterations = 500)
                    LottieAnimation(
                        composition = composition,
                        progress = { progress },
                        modifier = Modifier.fillMaxWidth(0.7f).padding(bottom = 10.dp)
                    )

                }
            }

        }


    }


}