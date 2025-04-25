package com.example.venturenest.ui.theme.Presentation.EventPage


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.BottomSheetScaffold

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberStandardBottomSheetState


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aay.compose.baseComponents.model.LegendPosition
import com.aay.compose.donutChart.DonutChart
import com.aay.compose.donutChart.PieChart
import com.aay.compose.donutChart.model.PieChartData
import com.example.venturenest.ui.theme.DaggerHilt.States.AchievementstateCompanion
import com.example.venturenest.ui.theme.DaggerHilt.ViewModels.AchievementViewModel
import com.example.venturenest.ui.theme.DaggerHilt.ViewModels.LoadingStateViewmodel
import com.example.venturenest.ui.theme.DaggerHilt.councilmembers
import com.example.venturenest.ui.theme.Navigation.CouncilScreen
import com.example.venturenest.ui.theme.Navigation.partnerScreen
import com.example.venturenest.ui.theme.Navigation.startupsScreen
import com.example.venturenest.ui.theme.Presentation.helper.ChangeStatusBarColorEdgeToEdge
import com.example.venturenest.ui.theme.Presentation.helper.HideSystemBars
import com.example.venturenest.ui.theme.background

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.collections.filter
import kotlin.text.contains

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ReEvent(
    windowInsets: WindowInsets, modifier: Modifier, navController: NavController
) {

    HideSystemBars()
    ChangeStatusBarColorEdgeToEdge(background)
    var search by rememberSaveable {
        mutableStateOf("")
    }
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val minOffset = screenHeight * 0.18f
    val maxOffset = screenHeight * 0.45f
    val offsetY = remember { mutableStateOf(maxOffset) }
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(0, 0f, { 4 })
    val pagerState2 = rememberPagerState(0, 0f, { 4 })


    val achievementViewModel: LoadingStateViewmodel = hiltViewModel()
    val state by achievementViewModel.state.collectAsState()
    Box(
        modifier = Modifier

            .fillMaxSize(1f)
            .background(background)
    ) {
        Column(
            modifier
                .windowInsetsPadding(windowInsets)
                .fillMaxWidth()
                .fillMaxHeight(0.3f)

        ) {

//Corouel part here

            Box(modifier   .fillMaxWidth()
                .wrapContentHeight()) {


                Column(
                    modifier
                        .padding(20.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clip(RoundedCornerShape(50f)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TarEventCorouel(
                        modifier.fillMaxWidth()
                            .height(250.dp)

                    )
                }

//                Column (modifier .padding(20.dp).fillMaxWidth()
//                    .height(250.dp)
//                    .clip(RoundedCornerShape(25f))
//                    .background(Brush.verticalGradient(
//                        listOf(
//                            Color.Transparent,
//                            Color.Black.copy(
//                                alpha = 0.8f
//                            )
//                        )
//                    )), horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Bottom){
//                    Text("cdchfdvv vfbvbn", color = Color.White,
//                        fontSize = 30.sp)
//
//
//                }
            }
        }


    }











    Box(
        modifier = Modifier
            .offset(y = -100.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .offset { IntOffset(0, offsetY.value.roundToPx()) }
            .background(Color.White, shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .pointerInput(Unit) {
                detectVerticalDragGestures(
                    onVerticalDrag = { _, dragAmount ->
                        offsetY.value =
                            (offsetY.value + dragAmount.dp).coerceIn(minOffset, maxOffset)
                    },
                    onDragEnd = {
                        coroutineScope.launch {
                            if (offsetY.value > (minOffset + maxOffset) / 2) {
                                offsetY.value = maxOffset // Collapse
                            } else {
                                offsetY.value = minOffset // Expand
                            }
                        }
                    },

                    )
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(1f)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(5.dp)
                    .background(Color.Gray, RoundedCornerShape(50))
            )
            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize(1f)
                    .verticalScroll(rememberScrollState())
            ) {


                Row(
                    modifier
                        .padding(bottom = 10.dp)
                        .fillMaxWidth()
                        .height(50.dp), horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier
                            .clip(RoundedCornerShape(15f))
                            .fillMaxWidth(0.95f)
                            .fillMaxHeight()
                            .border(
                                1.dp, Color.Black,
                                RoundedCornerShape(15f)
                            ),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        TextField(
                            value = search,
                            onValueChange = { search = it },
                            modifier
                                .clip(RoundedCornerShape(15f))
                                .fillMaxSize(),
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.White,


                                unfocusedIndicatorColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent
                            ),
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                            placeholder = { Text(text = "Search",modifier.offset(y = -4.dp)
                                , color = Color.Gray) },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = ""
                                    ,modifier.scale(0.8f)

                                )
                            },
                            maxLines = 1,
                            trailingIcon = {
                                if (search != "") {

                                    Icon(
                                        imageVector = Icons.Default.Clear,
                                        contentDescription = "",
                                        modifier.clickable {
                                            search = ""

                                        }.scale(0.8f),
                                        tint = Color.Gray
                                    )
                                }
                            })
                    }

                }




                AnimatedVisibility(
                    visible = true,
                    modifier = modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),

                    ) {


                    //part here

                }


            }
        }


    }
}




