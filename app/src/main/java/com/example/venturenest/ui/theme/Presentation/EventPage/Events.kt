//package com.example.venturenest.ui.theme.Presentation.EventPage
//
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.horizontalScroll
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.ExperimentalLayoutApi
//import androidx.compose.foundation.layout.FlowColumn
//import androidx.compose.foundation.layout.FlowRow
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.WindowInsets
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.offset
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.windowInsetsPadding
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Clear
//import androidx.compose.material.icons.filled.Search
//import androidx.compose.material.icons.filled.Tune
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonColors
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
//import androidx.compose.material3.TextFieldDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.draw.scale
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.ImeAction
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.text.style.TextOverflow
//import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.example.venturenest.R
//import com.example.venturenest.ui.theme.DaggerHilt.Events
//import com.example.venturenest.ui.theme.DaggerHilt.States.EventsStateCompanion
//import com.example.venturenest.ui.theme.DaggerHilt.States.EventsStateSearching
//import com.example.venturenest.ui.theme.DaggerHilt.ViewModels.EventsPageViewModel
//import com.example.venturenest.ui.theme.DaggerHilt.ViewModels.LoadingStateViewmodel
//import com.example.venturenest.ui.theme.Presentation.backgroundColor
//import com.example.venturenest.ui.theme.Presentation.helper.ChangeStatusBarColorEdgeToEdge
//import com.example.venturenest.ui.theme.Presentation.helper.HideSystemBars
//import com.example.venturenest.ui.theme.background
//import kotlinx.coroutines.delay
//
//@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
//@Composable
//fun EventsPage(
//    modifier: Modifier = Modifier,
//    window: WindowInsets
//
//) {
//
//
//    ChangeStatusBarColorEdgeToEdge(background)
//    HideSystemBars()
//    val eventViewModel: LoadingStateViewmodel = hiltViewModel()
//    val state by eventViewModel.state.collectAsState()
//    val eventsPageViewModel: EventsPageViewModel = hiltViewModel()
//    val state2 by eventsPageViewModel.state.collectAsState()
//
//
//    var filterSelected by remember {
//        mutableStateOf(0)
//    }
//
//    var selected1 = remember {
//        mutableStateOf(false)
//    }
//    var events by remember {
//        mutableStateOf(Events("", "", "", "", "", false))
//    }
//
//    Column(
//        modifier
//            .windowInsetsPadding(window)
//
//            .fillMaxSize()
//            .background(background)
//            .padding(bottom = 64.dp)
//            .padding(top = 30.dp), verticalArrangement = Arrangement.Top,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
////        Row(
////            modifier
////                .fillMaxWidth(1f)
////                .fillMaxHeight(0.05f)
////                .background(Color(0xffdd1212))
////            , verticalAlignment = Alignment.CenterVertically
////            , horizontalArrangement = Arrangement.Center
////        ) {
////
////
////        Row (
////            modifier
////                .fillMaxWidth(0.9f)
////                .fillMaxHeight(1f)
////
////
////            , verticalAlignment = Alignment.CenterVertically){
////            AsyncImage(model = "https://www.cgc.ac.in/public/course/assets/images/header-footer/cgc-jhanjeri-logo-white.png", contentDescription = "",
////                modifier
////                    .fillMaxHeight()
////                    .fillMaxWidth(0.3f))
////            Row (modifier.fillMaxWidth(0.95f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End){
////                Text(text = "Events", color = Color.White, fontSize = MaterialTheme.typography.titleLarge.fontSize, fontWeight = FontWeight.ExtraBold)
////            }
////
////        }
////
////
////        }
//
//
////        Row(
////            modifier = Modifier
////                .fillMaxWidth(0.9f)
////                .fillMaxHeight(0.05f)
////                .padding(bottom = 10.dp)
////            ,
////            horizontalArrangement = Arrangement.Center,
////            verticalAlignment = Alignment.Top
////        ) {
////
//////            Text(text = "Events",
//////                fontSize = MaterialTheme.typography.titleLarge.fontSize,
//////                fontWeight = FontWeight.Bold, color = Color.Black)
////            Row(modifier.fillMaxWidth()  ,
////                horizontalArrangement = Arrangement.End,
////                verticalAlignment = Alignment.Top) {
//////                IconButton(onClick = { filterSelected.value = !filterSelected.value },modifier.offset(x=10.dp)) {
//////                    Icon(imageVector = Icons.Default.Tune, contentDescription = "", tint =Color.Black)
//////                }
////            }
////        }
//
//        var search by rememberSaveable {
//            mutableStateOf("")
//        }
//        Row(
//            modifier
//                .padding(bottom = 10.dp)
//                .fillMaxWidth()
//                .height(50.dp), horizontalArrangement = Arrangement.Center,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Row(
//                modifier
//                    .clip(RoundedCornerShape(15f))
//                    .fillMaxWidth(0.92f)
//                    .fillMaxHeight()
//                    .border(
//                        1.dp, Color.Black,
//                        RoundedCornerShape(15f)
//                    ),
//                horizontalArrangement = Arrangement.Center,
//                verticalAlignment = Alignment.Bottom
//            ) {
//                TextField(
//                    value = search, onValueChange = { search = it },
//                    modifier
//                        .clip(RoundedCornerShape(15f))
//                        .fillMaxSize(),
//                    colors = TextFieldDefaults.textFieldColors(
//                        containerColor = Color.White,
//
//
//                        unfocusedIndicatorColor = Color.Transparent,
//                        focusedIndicatorColor = Color.Transparent
//                    ), keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
//                    placeholder = { Text(text = "Search in Events",modifier.offset(y = -4.dp
//                    ), color = Color.Gray) }, leadingIcon = {
//                        Icon(
//                            imageVector = Icons.Default.Search,
//                            contentDescription = "",
//                            tint = Color.Gray
//                        , modifier = modifier.scale(0.8f))
//                    }, maxLines = 1, trailingIcon = {
//                        if (search != "") {
//
//                            Icon(
//                                imageVector = Icons.Default.Clear,
//                                contentDescription = "",
//                                modifier.clickable {
//                                    search = ""
//                                }.scale(0.8f)
//                                , tint = Color.Gray
//                            )
//                        }
//                    })
//            }
//        }
//
//
//
//
//        Row(
//            modifier
//                .padding(top = 6.dp, bottom = 0.dp)
//                .fillMaxWidth(1f)
//                .height(50.dp)
//                .horizontalScroll(
//                    rememberScrollState()
//                )
//        ) {
//            Button(
//                modifier = Modifier.padding(start = 16.dp),
//                onClick = {
//                    if (state2.searchState == EventsStateSearching.Starred) {
//
//                    } else {
//                        eventsPageViewModel.changeToStarred()
//                    }
//                },
//                shape = RoundedCornerShape(25f),
//                border = BorderStroke(
//                    1.dp,
//                    if (state2.searchState != EventsStateSearching.Starred) Color.LightGray.copy(
//                        alpha = 0.2f
//                    ) else Color.Black
//                ),
//                colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.1f))
//            ) {
//                Text(
//                    "Starred",
//                    color = if (state2.searchState != EventsStateSearching.Starred) Color.LightGray else Color.Black
//                )
//            }
//            Button(
//                modifier = Modifier.padding(start = 10.dp),
//                onClick = {
//                    if (state2.searchState == EventsStateSearching.Nonstarred) {
//
//                    } else {
//                        eventsPageViewModel.changeToNonStarred()
//                    }
//                },
//                shape = RoundedCornerShape(25f),
//                border = BorderStroke(
//                    1.dp,
//                    if (state2.searchState != EventsStateSearching.Nonstarred) Color.LightGray.copy(
//                        alpha = 0.2f
//                    ) else Color.Black
//                ),
//                colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.1f))
//            ) {
//                Text(
//                    "Non-Starred",
//                    color = if (state2.searchState != EventsStateSearching.Nonstarred) Color.Gray else Color.Black
//                )
//            }
//            Button(
//                modifier = Modifier.padding(start = 10.dp),
//                onClick = {
//                    if (state2.searchState == EventsStateSearching.Latest) {
//
//                    } else {
//                        eventsPageViewModel.changeToLatest()
//                    }
//                },
//                shape = RoundedCornerShape(25f),
//                border = BorderStroke(
//                    1.dp,
//                    if (state2.searchState != EventsStateSearching.Latest) Color.LightGray.copy(
//                        alpha = 0.2f
//                    ) else Color.Black
//                ),
//                colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.1f))
//            ) {
//                Text(
//                    "latest",
//                    color = if (state2.searchState != EventsStateSearching.Latest) Color.Gray else Color.Black
//                )
//            }
//            Button(
//                modifier = Modifier.padding(start = 10.dp, end = 16.dp),
//                onClick = {
//                    if (state2.searchState == EventsStateSearching.AllEvents) {
//
//                    } else {
//                        eventsPageViewModel.changeToAll()
//                    }
//                },
//                shape = RoundedCornerShape(25f),
//                border = BorderStroke(
//                    1.dp,
//                    if (state2.searchState != EventsStateSearching.AllEvents) Color.LightGray.copy(
//                        alpha = 0.2f
//                    ) else Color.Black
//                ),
//                colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.1f))
//            ) {
//                Text(
//                    "All-Events",
//                    color = if (state2.searchState != EventsStateSearching.AllEvents) Color.Gray else Color.Black
//                )
//            }
//
//        }
//
//        var schroll = rememberScrollState()
//        FlowRow(
//            modifier
//                .padding(top = 10.dp)
//                // .clip(RoundedCornerShape(topEnd = 80f, topStart = 80f))
//                .fillMaxWidth(0.9f)
//                .fillMaxHeight(1f)
//                .verticalScroll(schroll)
//                .background(
//                    background
//                ), verticalArrangement = Arrangement.Top,
//            horizontalArrangement = Arrangement.Absolute.SpaceBetween
//        ) {
//
////               when(state.state){
////                   is EventsStateCompanion.Loading->{
////                       repeat(15){
////                           CardElementLoading()
////                       }
////                   }
////                   is EventsStateCompanion.Error->{
////                       LaunchedEffect(key1 = Unit) {
////                           while (true) {
////
////
////                               delay(5000)
////                               eventViewModel.fetchEvents()
////                           }}
//
////                       Column(
////                           modifier
////                               .fillMaxWidth()
////                               .height(400.dp), verticalArrangement = Arrangement.Center,
////                           horizontalAlignment = Alignment.CenterHorizontally) {
////                           Image(
////                               painter = painterResource(id = R.drawable.nothingfound),
////                               contentDescription = "",
////                               modifier
////                                   .padding(bottom = 0.dp)
////                                   .size(250.dp),
////                               contentScale = ContentScale.FillBounds
////                           )
////                           Text(
////                               text = "Oops! an error occured ",
////                               modifier
////                                   .padding(bottom = 10.dp)
////                                   .fillMaxWidth(0.8f),
////                               maxLines = 3,
////                               overflow = TextOverflow.Ellipsis,
////                               textAlign = TextAlign.Center,
////                               color = Color.Black
////                           )
////                           Button(
////                               onClick = {
////                                //  eventViewModel.fetchEvents()
////
////                               },
////                               shape = RoundedCornerShape(25f),
////                               colors = androidx.compose.material3.ButtonDefaults.buttonColors(
////                                   containerColor = Color.White
////                               )
////                           ) {
////                               Text(text = "Reload", color = Color.Black)
////                           }
////                       }
////
//////                       Column(modifier.fillMaxSize(1f), verticalArrangement = Arrangement.Center,
//////                           horizontalAlignment = Alignment.CenterHorizontally) {
//////
//////                           Text(
//////                               text =if (state.error!!.contains("Unable to resolve host")) "Oops! unable to connect to server , please check your internet connection " else "Unknown Error Occurred : We are trying to resolve it",
//////                               modifier.fillMaxWidth(0.8f)
//////                                   .padding(bottom = 15.dp, top = 150.dp)
//////                           , textAlign = TextAlign.Center, maxLines = 2, overflow = TextOverflow.Ellipsis)
//////                           Button(onClick = { eventViewModel.fetchEvents() },
//////                               shape = RoundedCornerShape(25f)
//////                               , border = BorderStroke(1.dp,Color.Black)
//////                               , colors = ButtonDefaults.buttonColors(containerColor = Color.White)
//////                           ) {
//////Text(text = "Try again", color = Color.Black)
//////                           }
//////
//////                       }
////
////                   }
////                   is EventsStateCompanion.Result->{
//            state.Data.events.filter {
//                it.eventName.contains(search,true) || it.eventTitle.contains(
//                    search,true
//                )
//            }.forEach {
//                when (state2.searchState) {
//                    is EventsStateSearching.Starred -> {
//
//                        if (it.isStarred) {
//                            CardElement2(onClick = {
//                                selected1.value = true
//                                events = it
//                            }, events = it
//                            , color = 0xff0000)
//                        }
//
//
//                    }
//
//                    is EventsStateSearching.Nonstarred -> {
//                        if (!it.isStarred) {
//                            CardElement2(onClick = {
//                                selected1.value = true
//                                events = it
//                            }, events = it,
//                                color = 0xff0000)
//
//                        }
//
//
//                    }
//
//                    is EventsStateSearching.Latest -> {
//                        CardElement2(onClick = {
//                            selected1.value = true
//                            events = it
//                        }, events = it,
//                            color = 0xff0000)
//
//
//                    }
//
//                    is EventsStateSearching.AllEvents -> {
//                        CardElement2(onClick = {
//                            selected1.value = true
//                            events = it
//                        }, events = it,
//                            color = 0xff0000)
//
//                    }
//                }
//
//
//            }
//        }
//    }
//
//
//
//
//
//    Dialog(show = selected1, events, modifier)
//
//
//}
//
//
////}
//
////@Preview
////@Composable
////private fun PreviewEventsPage() {
////    EventsPage()
////}