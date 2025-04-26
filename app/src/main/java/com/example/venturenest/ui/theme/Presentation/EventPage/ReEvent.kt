package com.example.venturenest.ui.theme.Presentation.EventPage


import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import com.example.venturenest.ui.theme.DaggerHilt.Events
import com.example.venturenest.ui.theme.DaggerHilt.States.AchievementstateCompanion
import com.example.venturenest.ui.theme.DaggerHilt.States.EventsStateSearching
import com.example.venturenest.ui.theme.DaggerHilt.ViewModels.AchievementViewModel
import com.example.venturenest.ui.theme.DaggerHilt.ViewModels.EventsPageViewModel
import com.example.venturenest.ui.theme.DaggerHilt.ViewModels.LoadingStateViewmodel
import com.example.venturenest.ui.theme.DaggerHilt.councilmembers
import com.example.venturenest.ui.theme.Navigation.CouncilScreen
import com.example.venturenest.ui.theme.Navigation.partnerScreen
import com.example.venturenest.ui.theme.Navigation.startupsScreen
import com.example.venturenest.ui.theme.Presentation.helper.ChangeStatusBarColorEdgeToEdge
import com.example.venturenest.ui.theme.Presentation.helper.ColorPicker
import com.example.venturenest.ui.theme.Presentation.helper.HideSystemBars
import com.example.venturenest.ui.theme.background

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.collections.filter
import kotlin.text.contains

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class,
    ExperimentalLayoutApi::class
)
@Composable
fun ReEvent(
    windowInsets: WindowInsets, modifier: Modifier, navController: NavController
) {

    HideSystemBars()
    ChangeStatusBarColorEdgeToEdge(background)
    var search by rememberSaveable {
        mutableStateOf("")
    }
    val colorlist = listOf(
        0xFF22A699,
        0xFFF29727,
        0xFFF24C3D,
        0xFFB349D2,
        0xFFF2BE22
    )
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val minOffset = screenHeight * 0.18f
    val maxOffset = screenHeight * 0.45f
    val offsetY = remember { mutableStateOf(maxOffset) }
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(0, 0f, { 4 })
    val pagerState2 = rememberPagerState(0, 0f, { 4 })
    var selectedColor by remember {
        mutableStateOf(0xff000000)
    }
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
                   StarEventCorousel(
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
                    val eventViewModel: LoadingStateViewmodel = hiltViewModel()
                    val state by eventViewModel.state.collectAsState()
                    val eventsPageViewModel: EventsPageViewModel = hiltViewModel()
                    val state2 by eventsPageViewModel.state.collectAsState()


                    var filterSelected by remember {
                        mutableStateOf(0)
                    }

                    var selected1 = remember {
                        mutableStateOf(false)
                    }
                    var events by remember {
                        mutableStateOf(Events("", "", "", "", "", false))
                    }

                    Column(
                        modifier


                            .fillMaxSize()
                            .background(Color.White)
                            .padding(bottom = 10.dp)
                            .padding(top = 10.dp), verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
//        Row(
//            modifier
//                .fillMaxWidth(1f)
//                .fillMaxHeight(0.05f)
//                .background(Color(0xffdd1212))
//            , verticalAlignment = Alignment.CenterVertically
//            , horizontalArrangement = Arrangement.Center
//        ) {
//
//
//        Row (
//            modifier
//                .fillMaxWidth(0.9f)
//                .fillMaxHeight(1f)
//
//
//            , verticalAlignment = Alignment.CenterVertically){
//            AsyncImage(model = "https://www.cgc.ac.in/public/course/assets/images/header-footer/cgc-jhanjeri-logo-white.png", contentDescription = "",
//                modifier
//                    .fillMaxHeight()
//                    .fillMaxWidth(0.3f))
//            Row (modifier.fillMaxWidth(0.95f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End){
//                Text(text = "Events", color = Color.White, fontSize = MaterialTheme.typography.titleLarge.fontSize, fontWeight = FontWeight.ExtraBold)
//            }
//
//        }
//
//
//        }


//        Row(
//            modifier = Modifier
//                .fillMaxWidth(0.9f)
//                .fillMaxHeight(0.05f)
//                .padding(bottom = 10.dp)
//            ,
//            horizontalArrangement = Arrangement.Center,
//            verticalAlignment = Alignment.Top
//        ) {
//
////            Text(text = "Events",
////                fontSize = MaterialTheme.typography.titleLarge.fontSize,
////                fontWeight = FontWeight.Bold, color = Color.Black)
//            Row(modifier.fillMaxWidth()  ,
//                horizontalArrangement = Arrangement.End,
//                verticalAlignment = Alignment.Top) {
////                IconButton(onClick = { filterSelected.value = !filterSelected.value },modifier.offset(x=10.dp)) {
////                    Icon(imageVector = Icons.Default.Tune, contentDescription = "", tint =Color.Black)
////                }
//            }
//        }





                        Row(
                            modifier
                                .padding(top = 6.dp, bottom = 10.dp)
                                .fillMaxWidth(1f)
                                .height(50.dp)
                                .horizontalScroll(
                                    rememberScrollState()
                                )
                        ) {
                            Button(
                                modifier = Modifier.padding(start = 16.dp),
                                onClick = {
                                    if (state2.searchState == EventsStateSearching.Starred) {

                                    } else {
                                        eventsPageViewModel.changeToStarred()
                                    }
                                },
                                shape = RoundedCornerShape(25f),
                                border = BorderStroke(
                                    1.dp,
                                    if (state2.searchState != EventsStateSearching.Starred) Color.LightGray.copy(
                                        alpha = 0.2f
                                    ) else Color.Black
                                ),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.1f))
                            ) {
                                Text(
                                    "Starred",
                                    color = if (state2.searchState != EventsStateSearching.Starred) Color.LightGray else Color.Black
                                )
                            }
                            Button(
                                modifier = Modifier.padding(start = 10.dp),
                                onClick = {
                                    if (state2.searchState == EventsStateSearching.Nonstarred) {

                                    } else {
                                        eventsPageViewModel.changeToNonStarred()
                                    }
                                },
                                shape = RoundedCornerShape(25f),
                                border = BorderStroke(
                                    1.dp,
                                    if (state2.searchState != EventsStateSearching.Nonstarred) Color.LightGray.copy(
                                        alpha = 0.2f
                                    ) else Color.Black
                                ),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.1f))
                            ) {
                                Text(
                                    "Non-Starred",
                                    color = if (state2.searchState != EventsStateSearching.Nonstarred) Color.Gray else Color.Black
                                )
                            }
                            Button(
                                modifier = Modifier.padding(start = 10.dp),
                                onClick = {
                                    if (state2.searchState == EventsStateSearching.Latest) {

                                    } else {
                                        eventsPageViewModel.changeToLatest()
                                    }
                                },
                                shape = RoundedCornerShape(25f),
                                border = BorderStroke(
                                    1.dp,
                                    if (state2.searchState != EventsStateSearching.Latest) Color.LightGray.copy(
                                        alpha = 0.2f
                                    ) else Color.Black
                                ),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.1f))
                            ) {
                                Text(
                                    "latest",
                                    color = if (state2.searchState != EventsStateSearching.Latest) Color.Gray else Color.Black
                                )
                            }
                            Button(
                                modifier = Modifier.padding(start = 10.dp, end = 16.dp),
                                onClick = {
                                    if (state2.searchState == EventsStateSearching.AllEvents) {

                                    } else {
                                        eventsPageViewModel.changeToAll()
                                    }
                                },
                                shape = RoundedCornerShape(25f),
                                border = BorderStroke(
                                    1.dp,
                                    if (state2.searchState != EventsStateSearching.AllEvents) Color.LightGray.copy(
                                        alpha = 0.2f
                                    ) else Color.Black
                                ),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.1f))
                            ) {
                                Text(
                                    "All-Events",
                                    color = if (state2.searchState != EventsStateSearching.AllEvents) Color.Gray else Color.Black
                                )
                            }

                        }


                        FlowRow(
                            modifier
                                .padding(bottom = 400.dp)

                                // .clip(RoundedCornerShape(topEnd = 80f, topStart = 80f))
                                .fillMaxWidth(0.9f)
                                .fillMaxHeight(1f)

                                .background(
                                   Color.White
                                ), verticalArrangement = Arrangement.Top,
                            horizontalArrangement = Arrangement.Absolute.SpaceBetween
                        ) {

//               when(state.state){
//                   is EventsStateCompanion.Loading->{
//                       repeat(15){
//                           CardElementLoading()
//                       }
//                   }
//                   is EventsStateCompanion.Error->{
//                       LaunchedEffect(key1 = Unit) {
//                           while (true) {
//
//
//                               delay(5000)
//                               eventViewModel.fetchEvents()
//                           }}

//                       Column(
//                           modifier
//                               .fillMaxWidth()
//                               .height(400.dp), verticalArrangement = Arrangement.Center,
//                           horizontalAlignment = Alignment.CenterHorizontally) {
//                           Image(
//                               painter = painterResource(id = R.drawable.nothingfound),
//                               contentDescription = "",
//                               modifier
//                                   .padding(bottom = 0.dp)
//                                   .size(250.dp),
//                               contentScale = ContentScale.FillBounds
//                           )
//                           Text(
//                               text = "Oops! an error occured ",
//                               modifier
//                                   .padding(bottom = 10.dp)
//                                   .fillMaxWidth(0.8f),
//                               maxLines = 3,
//                               overflow = TextOverflow.Ellipsis,
//                               textAlign = TextAlign.Center,
//                               color = Color.Black
//                           )
//                           Button(
//                               onClick = {
//                                //  eventViewModel.fetchEvents()
//
//                               },
//                               shape = RoundedCornerShape(25f),
//                               colors = androidx.compose.material3.ButtonDefaults.buttonColors(
//                                   containerColor = Color.White
//                               )
//                           ) {
//                               Text(text = "Reload", color = Color.Black)
//                           }
//                       }
//
////                       Column(modifier.fillMaxSize(1f), verticalArrangement = Arrangement.Center,
////                           horizontalAlignment = Alignment.CenterHorizontally) {
////
////                           Text(
////                               text =if (state.error!!.contains("Unable to resolve host")) "Oops! unable to connect to server , please check your internet connection " else "Unknown Error Occurred : We are trying to resolve it",
////                               modifier.fillMaxWidth(0.8f)
////                                   .padding(bottom = 15.dp, top = 150.dp)
////                           , textAlign = TextAlign.Center, maxLines = 2, overflow = TextOverflow.Ellipsis)
////                           Button(onClick = { eventViewModel.fetchEvents() },
////                               shape = RoundedCornerShape(25f)
////                               , border = BorderStroke(1.dp,Color.Black)
////                               , colors = ButtonDefaults.buttonColors(containerColor = Color.White)
////                           ) {
////Text(text = "Try again", color = Color.Black)
////                           }
////
////                       }
//
//                   }
//                   is EventsStateCompanion.Result->{
                            state.Data.events.filter {
                                it.eventName.contains(search,true) || it.eventTitle.contains(
                                    search,true
                                )
                            }.forEach {
                                when (state2.searchState) {
                                    is EventsStateSearching.Starred -> {

                                        if (it.isStarred) {
                                            com.example.venturenest.ui.theme.Presentation.EventPage.CardElement2(onClick = {
                                                selected1.value = true
                                                events = it
                                                selectedColor = colorlist[state.Data.events.indexOf(it)%4]
                                            }, events = it
                                            , color = colorlist[state.Data.events.indexOf(it)%4]
                                            )
                                        }


                                    }

                                    is EventsStateSearching.Nonstarred -> {
                                        if (!it.isStarred) {
                                            com.example.venturenest.ui.theme.Presentation.EventPage.CardElement2(onClick = {
                                                selected1.value = true
                                                events = it
                                                selectedColor = colorlist[state.Data.events.indexOf(it)%4]

                                            }, events = it,
color = colorlist[state.Data.events.indexOf(it)%4])
                                        }


                                    }

                                    is EventsStateSearching.Latest -> {
                                        com.example.venturenest.ui.theme.Presentation.EventPage.CardElement2(onClick = {
                                            selected1.value = true
                                            events = it
                                            selectedColor = colorlist[state.Data.events.indexOf(it)%4]

                                        }, events = it
                                        , color =colorlist[state.Data.events.indexOf(it)%4])


                                    }

                                    is EventsStateSearching.AllEvents -> {
                                        com.example.venturenest.ui.theme.Presentation.EventPage.CardElement2(onClick = {
                                            selected1.value = true
                                            events = it
                                            selectedColor = colorlist[state.Data.events.indexOf(it)%4]

                                        }, events = it
                                             ,   color = colorlist[state.Data.events.indexOf(it)%4])

                                    }
                                }


                            }
                            Dialog(show = selected1, events, modifier, color = selectedColor)

                        }
                    }


                    //part here

                }


            }
        }


    }
}




