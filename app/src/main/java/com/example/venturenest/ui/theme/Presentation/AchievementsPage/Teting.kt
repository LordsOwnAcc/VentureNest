package com.example.venturenest.ui.theme.Presentation.AchievementsPage

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Card

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
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

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ExpandableBottoSheet(
    windowInsets: WindowInsets, modifier: Modifier, navController: NavController
) {

    HideSystemBars()
    ChangeStatusBarColorEdgeToEdge(background)
    var search by rememberSaveable {
        mutableStateOf("")
    }
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val minOffset = screenHeight * 0.1f
    val maxOffset = screenHeight * 0.5f
    val offsetY = remember { mutableStateOf(maxOffset) }
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(0, 0f, { 4 })
    val pagerState2 = rememberPagerState(0, 0f, { 4 })
    var searchIn by remember { mutableStateOf("council members") }

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
                .fillMaxHeight(0.4f)

        ) {


            HorizontalPager(
                flingBehavior = PagerDefaults.flingBehavior(
                    pagerState,
                    pagerSnapDistance = PagerSnapDistance.atMost(1)
                ),
                state = pagerState,
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                userScrollEnabled = true,
                pageSpacing = 0.dp, beyondViewportPageCount = 2
            ) { page ->
                if (page == 0) {
                    Column(
                        modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column(
                            modifier
                                .fillMaxWidth(0.9f)
                                .fillMaxHeight()
                        ) {

                            val data1 =
                                state.Data.councilmembers.count { it.category == "advisory" }
                                    .toDouble()
                            val data =
                                state.Data.councilmembers.count { it.category == "mentorship" }
                                    .toDouble()
                            val data2 =
                                state.Data.councilmembers.count { it.category == "advisory" }
                                    .toDouble()
                            val data3 =
                                state.Data.councilmembers.count { it.category == "legalcompl" }
                                    .toDouble()
                            if (data1 != 0.0
                                || data2 != 0.0
                                || data3 != 0.0
                                || data != 0.0
                            ) {


                                val testPieChartData: List<PieChartData> = listOf(
                                    PieChartData(
                                        partName = "Advisory Council",
                                        data = data2,
                                        color = Color(0xFF22A699),
                                    ),
//                                PieChartData(
//                                    partName = "Investment and Funding Council",
//                                    data = state.result.councilmembers.filter { it->it.category=="funding" }.size.toDouble(),//317 funding to be added wrong search
//                                    color = Color(0xFFF2BE22),
//                                ),
                                    PieChartData(
                                        partName = "Technology and Innovation Council",
                                        data = data1, color = Color(0xFFF29727),
                                    ),
                                    PieChartData(
                                        partName = "Mentorship Council",
                                        data = data, color = Color(0xFFDE908A),
                                    ),
                                    PieChartData(
                                        partName = "Legal and Compliance Council",
                                        data = data3, color = Color(0xFFF24C3D),
                                    )

                                )

                                PieChart(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight(),
                                    pieChartData = testPieChartData,
                                    ratioLineColor = Color.LightGray,
                                    textRatioStyle = TextStyle(color = Color.Black),
                                    descriptionStyle = TextStyle(Color.Black),
                                    legendPosition = LegendPosition.TOP

                                )
                            }
                        }
                    }
                } else if (page == 1) {
                    Column(
                        modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column(
                            modifier
                                .fillMaxWidth(0.9f)
                                .fillMaxHeight(),

                            ) {
                            val data1: Double =
                                state.Data.partner.count { it.Category == "government" }.toDouble()
                            val data2 =
                                state.Data.partner.count { it.Category == "ecosystem" }.toDouble()
                            val data3 =
                                state.Data.partner.count { it.Category == "investor" }.toDouble()
                            val data4 =
                                state.Data.partner.count { it.Category == "accelerator" }.toDouble()
                            val data5 =
                                state.Data.partner.count { it.Category == "mentor" }.toDouble()


                            if (data1 != 0.0
                                || data2 != 0.0
                                || data3 != 0.0
                                || data4 != 0.0
                                || data5 != 0.0
                            ) {


                                val testPieChartData: List<PieChartData> = listOf(
                                    PieChartData(
                                        partName = "Government Catalyst",
                                        data = data1, color = Color(0xFFB69F5F),
                                    ),
                                    PieChartData(
                                        partName = "Eco-System",
                                        data = data2,
                                        color = Color(0xFF31D5D5),
                                    ),

                                    PieChartData(
                                        partName = "Investment Partner",
                                        data = data3,
                                        color = Color(0xFFF29727),
                                    ),
                                    PieChartData(
                                        partName = "Accelerator Collaborator",
                                        data = data4,
                                        color = Color(0xFFB349D2),
                                    )

                                )

                                PieChart(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight(),
                                    pieChartData = testPieChartData,
                                    ratioLineColor = Color.LightGray,
                                    textRatioStyle = TextStyle(color = Color.Black),
                                    descriptionStyle = TextStyle(Color.Black),
                                    legendPosition = LegendPosition.TOP
                                )
                            } else {

                            }
                        }
                    }
                } else if (page == 2) {
                    val data1 = state.Data.startUp.count { it.StartupType == "Virtual" }.toDouble()
                    val data = state.Data.startUp.count { it.StartupType == "Physical" }.toDouble()

                    if (data1 != 0.0

                        || data != 0.0
                    ) {

                        Column(
                            modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Column(
                                modifier
                                    .fillMaxWidth(0.9f)
                                    .fillMaxHeight(),
                            ) {
                                val testPieChartData: List<PieChartData> = listOf(

                                    PieChartData(
                                        partName = "PhysicalStartups",
                                        data = data,
                                        color = Color(0xFFF29727),
                                    ),
                                    PieChartData(
                                        partName = "Virtual Startups",
                                        data = data1,
                                        color = Color(0xFF35A29F),
                                    )
                                )

                                DonutChart(
                                    modifier = Modifier.fillMaxSize(),
                                    pieChartData = testPieChartData,
                                    centerTitle = "Startups",
                                    centerTitleStyle = TextStyle(color = Color(0xFF071952)),
                                    outerCircularColor = Color.LightGray,
                                    innerCircularColor = Color.Gray,
                                    ratioLineColor = Color.LightGray,
                                )
                            }
                        }
                    }
                } else if (page == 3) {
                    val data1: Double =
                        state.Data.startUp.count { it.RegistrationStatus == "Not Registered" }
                            .toDouble()
                    val data2 =
                        state.Data.startUp.count { it.RegistrationStatus == "Private Limited" }
                            .toDouble()
                    val data3 =
                        state.Data.startUp.count { it.RegistrationStatus == "Proprietorship" }
                            .toDouble()
                    val data5 = state.Data.startUp.count { it.RegistrationStatus == "Partnership" }
                        .toDouble()


                    Column(
                        modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column(
                            modifier
                                .fillMaxWidth(0.9f)
                                .fillMaxHeight(),

                            ) {
                            if (data1 != 0.0 ||
                                data2 != 0.0 ||
                                data3 != 0.0 ||
                                data5 != 0.0
                            ) {


                                val testPieChartData: List<PieChartData> = listOf(
                                    PieChartData(
                                        partName = "Private Limited",
                                        data = data2,
                                        color = Color(0xFF22A699),
                                    ),
                                    PieChartData(
                                        partName = "Partnership",
                                        data = data5,
                                        color = Color(0xFFF2BE22),
                                    ),
                                    PieChartData(
                                        partName = "Proprietorship",
                                        data = data3,
                                        color = Color(0xFFF29727),
                                    ),
                                    PieChartData(
                                        partName = "Not Registered",
                                        data = data1,
                                        color = Color(0xFFF24C3D),
                                    )

                                )

                                PieChart(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight(),
                                    pieChartData = testPieChartData,
                                    ratioLineColor = Color.LightGray,
                                    textRatioStyle = TextStyle(color = Color.Black),
                                    descriptionStyle = TextStyle(Color.Black),
                                    legendPosition = LegendPosition.TOP

                                )
                            }
                        }

                    }
                }

            }
        }


    }











    Box(
        modifier = Modifier
            .offset(y = -25.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .offset { IntOffset(0, offsetY.value.roundToPx()) }
            .background(background, shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
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
                        .height(55.dp), horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier
                            .clip(RoundedCornerShape(15f))
                            .fillMaxWidth(0.95f)
                            .fillMaxHeight(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        TextField(
                            value = search,
                            onValueChange = { search = it },
                            modifier
                                .clip(RoundedCornerShape(25f))
                                .fillMaxSize(),
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.Transparent,

                                textColor = Color.Gray,
                                unfocusedIndicatorColor = Color.LightGray,
                                focusedIndicatorColor = Color.DarkGray
                            ),
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                            placeholder = {
                                Text(
                                    text = "Search in ${searchIn}", modifier, color = Color.Gray
                                )
                            },
                            textStyle = TextStyle(fontSize = MaterialTheme.typography.body1.fontSize),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "", modifier.scale(0.8f)

                                )
                            },
                            maxLines = 1,
                            trailingIcon = {
                                if (search != "") {

                                    Icon(
                                        imageVector = Icons.Default.Clear,
                                        contentDescription = "",
                                        modifier
                                            .clickable {
                                                search = ""

                                            }
                                            .scale(0.8f)
                                            .offset(y = -4.dp),
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


                    if (pagerState.currentPage == 0) {
                        searchIn = "council members"
                        Column(
                            modifier
                                .fillMaxSize(1f)
                                .padding(horizontal = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Spacer(modifier = Modifier.height(8.dp))
                            
                            // Advisory Council Card
                            androidx.compose.material3.Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(70.dp)
                                    .clickable {
                                        navController.navigate(
                                            CouncilScreen(
                                                search = search,
                                                color = 0xFF00897B,
                                                category = "advisory"
                                            )
                                        )
                                    },
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White
                                ),
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 2.dp
                                )
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    // Left accent bar
                                    Box(
                                        modifier = Modifier
                                            .width(6.dp)
                                            .fillMaxHeight()
                                            .clip(
                                                RoundedCornerShape(
                                                    topStart = 12.dp,
                                                    bottomStart = 12.dp
                                                )
                                            )
                                            .background(Color(0xFF00897B))
                                    )

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(end = 16.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "Advisory Council",
                                            modifier = Modifier
                                                .weight(1f)
                                                .padding(start = 16.dp),
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFF424242),
                                            fontSize = 16.sp
                                        )

                                        Box(
                                            modifier = Modifier
                                                .size(32.dp)
                                                .background(
                                                    color = Color(0xFF00897B).copy(alpha = 0.15f),
                                                    shape = CircleShape
                                                ),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = state.Data.councilmembers.filter { 
                                                    it.category == "advisory" && it.name.contains(search, true)
                                                }.size.toString(),
                                                color = Color(0xFF00897B),
                                                fontWeight = FontWeight.SemiBold,
                                                fontSize = 14.sp
                                            )
                                        }
                                    }
                                }
                            }

                            // Technology & Innovation Card
                            androidx.compose.material3.Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(70.dp)
                                    .clickable {
                                        navController.navigate(
                                            CouncilScreen(
                                                search = search,
                                                color = 0xFFFBC02D,
                                                category = "techinnov"
                                            )
                                        )
                                    },
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White
                                ),
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 2.dp
                                )
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .width(6.dp)
                                            .fillMaxHeight()
                                            .clip(
                                                RoundedCornerShape(
                                                    topStart = 12.dp,
                                                    bottomStart = 12.dp
                                                )
                                            )
                                            .background(Color(0xFFFBC02D))
                                    )

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(end = 16.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "Technology & Innovation",
                                            modifier = Modifier
                                                .weight(1f)
                                                .padding(start = 16.dp),
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFF424242),
                                            fontSize = 16.sp
                                        )

                                        Box(
                                            modifier = Modifier
                                                .size(32.dp)
                                                .background(
                                                    color = Color(0xFFFBC02D).copy(alpha = 0.15f),
                                                    shape = CircleShape
                                                ),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = state.Data.councilmembers.filter { 
                                                    it.category == "techinnov" && it.name.contains(search, true)
                                                }.size.toString(),
                                                color = Color(0xFFFBC02D),
                                                fontWeight = FontWeight.SemiBold,
                                                fontSize = 14.sp
                                            )
                                        }
                                    }
                                }
                            }

                            // Mentorship Council Card
                            androidx.compose.material3.Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(70.dp)
                                    .clickable {
                                        navController.navigate(
                                            CouncilScreen(
                                                search = search,
                                                color = 0xFFF06292,
                                                category = "mentorship"
                                            )
                                        )
                                    },
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White
                                ),
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 2.dp
                                )
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .width(6.dp)
                                            .fillMaxHeight()
                                            .clip(
                                                RoundedCornerShape(
                                                    topStart = 12.dp,
                                                    bottomStart = 12.dp
                                                )
                                            )
                                            .background(Color(0xFFF06292))
                                    )

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(end = 16.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "Mentorship Council",
                                            modifier = Modifier
                                                .weight(1f)
                                                .padding(start = 16.dp),
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFF424242),
                                            fontSize = 16.sp
                                        )

                                        Box(
                                            modifier = Modifier
                                                .size(32.dp)
                                                .background(
                                                    color = Color(0xFFF06292).copy(alpha = 0.15f),
                                                    shape = CircleShape
                                                ),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = state.Data.councilmembers.filter { 
                                                    it.category == "mentorship" && it.name.contains(search, true)
                                                }.size.toString(),
                                                color = Color(0xFFF06292),
                                                fontWeight = FontWeight.SemiBold,
                                                fontSize = 14.sp
                                            )
                                        }
                                    }
                                }
                            }

                            // Legal and Compliance Card
                            androidx.compose.material3.Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(70.dp)
                                    .clickable {
                                        navController.navigate(
                                            CouncilScreen(
                                                search = search,
                                                color = 0xFFE91E63,
                                                category = "legalcompl"
                                            )
                                        )
                                    },
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White
                                ),
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 2.dp
                                )
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .width(6.dp)
                                            .fillMaxHeight()
                                            .clip(
                                                RoundedCornerShape(
                                                    topStart = 12.dp,
                                                    bottomStart = 12.dp
                                                )
                                            )
                                            .background(Color(0xFFE91E63))
                                    )

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(end = 16.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "Legal and Compliance",
                                            modifier = Modifier
                                                .weight(1f)
                                                .padding(start = 16.dp),
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFF424242),
                                            fontSize = 16.sp
                                        )

                                        Box(
                                            modifier = Modifier
                                                .size(32.dp)
                                                .background(
                                                    color = Color(0xFFE91E63).copy(alpha = 0.15f),
                                                    shape = CircleShape
                                                ),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = state.Data.councilmembers.filter { 
                                                    it.category == "legalcompl" && it.name.contains(search, true)
                                                }.size.toString(),
                                                color = Color(0xFFE91E63),
                                                fontWeight = FontWeight.SemiBold,
                                                fontSize = 14.sp
                                            )
                                        }
                                    }
                                }
                            }

                        }

                    } else if (pagerState.currentPage == 1) {
                        searchIn = "partners"
                        Column(
                            modifier
                                .fillMaxSize(1f)
                                .padding(horizontal = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Spacer(modifier = Modifier.height(8.dp))
                            
                            // Government Catalyst Card
                            androidx.compose.material3.Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(70.dp)
                                    .clickable {
                                        navController.navigate(
                                            partnerScreen(
                                                search = search,
                                                color = 0xFFB69F5F,
                                                category = "government"
                                            )
                                        )
                                    },
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White
                                ),
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 2.dp
                                )
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .width(6.dp)
                                            .fillMaxHeight()
                                            .clip(
                                                RoundedCornerShape(
                                                    topStart = 12.dp,
                                                    bottomStart = 12.dp
                                                )
                                            )
                                            .background(Color(0xFFB69F5F))
                                    )

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(end = 16.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "Government Catalyst",
                                            modifier = Modifier
                                                .weight(1f)
                                                .padding(start = 16.dp),
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFF424242),
                                            fontSize = 16.sp
                                        )

                                        Box(
                                            modifier = Modifier
                                                .size(32.dp)
                                                .background(
                                                    color = Color(0xFFB69F5F).copy(alpha = 0.15f),
                                                    shape = CircleShape
                                                ),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = state.Data.partner.filter { 
                                                    it.Category == "government" && it.Name.contains(search, true)
                                                }.size.toString(),
                                                color = Color(0xFFB69F5F),
                                                fontWeight = FontWeight.SemiBold,
                                                fontSize = 14.sp
                                            )
                                        }
                                    }
                                }
                            }

                            // Eco-system Partners Card
                            androidx.compose.material3.Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(70.dp)
                                    .clickable {
                                        navController.navigate(
                                            partnerScreen(
                                                search = search,
                                                color = 0xFF32BBBB,
                                                category = "ecosystem"
                                            )
                                        )
                                    },
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White
                                ),
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 2.dp
                                )
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .width(6.dp)
                                            .fillMaxHeight()
                                            .clip(
                                                RoundedCornerShape(
                                                    topStart = 12.dp,
                                                    bottomStart = 12.dp
                                                )
                                            )
                                            .background(Color(0xFF32BBBB))
                                    )

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(end = 16.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "Eco-system Partners",
                                            modifier = Modifier
                                                .weight(1f)
                                                .padding(start = 16.dp),
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFF424242),
                                            fontSize = 16.sp
                                        )

                                        Box(
                                            modifier = Modifier
                                                .size(32.dp)
                                                .background(
                                                    color = Color(0xFF32BBBB).copy(alpha = 0.15f),
                                                    shape = CircleShape
                                                ),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = state.Data.partner.filter { 
                                                    it.Category == "ecosystem" && it.Name.contains(search, true)
                                                }.size.toString(),
                                                color = Color(0xFF32BBBB),
                                                fontWeight = FontWeight.SemiBold,
                                                fontSize = 14.sp
                                            )
                                        }
                                    }
                                }
                            }

                            // Investment Partner Card
                            androidx.compose.material3.Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(70.dp)
                                    .clickable {
                                        navController.navigate(
                                            partnerScreen(
                                                search = search,
                                                color = 0xFFF29727,
                                                category = "investor"
                                            )
                                        )
                                    },
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White
                                ),
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 2.dp
                                )
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .width(6.dp)
                                            .fillMaxHeight()
                                            .clip(
                                                RoundedCornerShape(
                                                    topStart = 12.dp,
                                                    bottomStart = 12.dp
                                                )
                                            )
                                            .background(Color(0xFFF29727))
                                    )

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(end = 16.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "Investment Partner",
                                            modifier = Modifier
                                                .weight(1f)
                                                .padding(start = 16.dp),
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFF424242),
                                            fontSize = 16.sp
                                        )

                                        Box(
                                            modifier = Modifier
                                                .size(32.dp)
                                                .background(
                                                    color = Color(0xFFF29727).copy(alpha = 0.15f),
                                                    shape = CircleShape
                                                ),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = state.Data.partner.filter { 
                                                    it.Category == "investor" && it.Name.contains(search, true)
                                                }.size.toString(),
                                                color = Color(0xFFF29727),
                                                fontWeight = FontWeight.SemiBold,
                                                fontSize = 14.sp
                                            )
                                        }
                                    }
                                }
                            }

                            // Accelerator Collaborator Card
                            androidx.compose.material3.Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(70.dp)
                                    .clickable {
                                        navController.navigate(
                                            partnerScreen(
                                                search = search,
                                                color = 0xFFB349D2,
                                                category = "accelerator"
                                            )
                                        )
                                    },
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White
                                ),
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 2.dp
                                )
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .width(6.dp)
                                            .fillMaxHeight()
                                            .clip(
                                                RoundedCornerShape(
                                                    topStart = 12.dp,
                                                    bottomStart = 12.dp
                                                )
                                            )
                                            .background(Color(0xFFB349D2))
                                    )

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(end = 16.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "Accelerator Collaborator",
                                            modifier = Modifier
                                                .weight(1f)
                                                .padding(start = 16.dp),
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFF424242),
                                            fontSize = 16.sp
                                        )

                                        Box(
                                            modifier = Modifier
                                                .size(32.dp)
                                                .background(
                                                    color = Color(0xFFB349D2).copy(alpha = 0.15f),
                                                    shape = CircleShape
                                                ),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = state.Data.partner.filter { 
                                                    it.Category == "accelerator" && it.Name.contains(search, true)
                                                }.size.toString(),
                                                color = Color(0xFFB349D2),
                                                fontWeight = FontWeight.SemiBold,
                                                fontSize = 14.sp
                                            )
                                        }
                                    }
                                }
                            }

                        }

                    } else if (pagerState.currentPage == 2) {
                        searchIn = "startup type"
                        val data = state.Data.startUp.count {
                            it.StartupType == "Virtual" &&
                                    (it.StartupName.contains(search, ignoreCase = true) ||
                                            it.ProductName.contains(search, ignoreCase = true) ||
                                            it.FounderName.contains(search, ignoreCase = true))
                        }

                        val data1 = state.Data.startUp.count {
                            it.StartupType == "Physical" &&
                                    (it.StartupName.contains(search, ignoreCase = true) ||
                                            it.ProductName.contains(search, ignoreCase = true) ||
                                            it.FounderName.contains(search, ignoreCase = true))
                        }

                        Column(
                            modifier
                                .fillMaxSize(1f)
                                .padding(horizontal = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Spacer(modifier = Modifier.height(8.dp))
                            
                            // Virtual Startups Card
                            androidx.compose.material3.Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(70.dp)
                                    .clickable {
                                        navController.navigate(
                                            startupsScreen(
                                                search = search,
                                                type = "type", 
                                                contain = "Virtual"
                                            )
                                        )
                                    },
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White
                                ),
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 2.dp
                                )
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .width(6.dp)
                                            .fillMaxHeight()
                                            .clip(
                                                RoundedCornerShape(
                                                    topStart = 12.dp,
                                                    bottomStart = 12.dp
                                                )
                                            )
                                            .background(Color(0xFF00897B))
                                    )

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(end = 16.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "Virtual",
                                            modifier = Modifier
                                                .weight(1f)
                                                .padding(start = 16.dp),
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFF424242),
                                            fontSize = 16.sp
                                        )

                                        Box(
                                            modifier = Modifier
                                                .size(32.dp)
                                                .background(
                                                    color = Color(0xFF00897B).copy(alpha = 0.15f),
                                                    shape = CircleShape
                                                ),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = data.toString(),
                                                color = Color(0xFF00897B),
                                                fontWeight = FontWeight.SemiBold,
                                                fontSize = 14.sp
                                            )
                                        }
                                    }
                                }
                            }

                            // Physical Startups Card
                            androidx.compose.material3.Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(70.dp)
                                    .clickable {
                                        navController.navigate(
                                            startupsScreen(
                                                search = search,
                                                type = "type", 
                                                contain = "Physical"
                                            )
                                        )
                                    },
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White
                                ),
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 2.dp
                                )
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .width(6.dp)
                                            .fillMaxHeight()
                                            .clip(
                                                RoundedCornerShape(
                                                    topStart = 12.dp,
                                                    bottomStart = 12.dp
                                                )
                                            )
                                            .background(Color(0xFFF29727))
                                    )

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(end = 16.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "Physical",
                                            modifier = Modifier
                                                .weight(1f)
                                                .padding(start = 16.dp),
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFF424242),
                                            fontSize = 16.sp
                                        )

                                        Box(
                                            modifier = Modifier
                                                .size(32.dp)
                                                .background(
                                                    color = Color(0xFFF29727).copy(alpha = 0.15f),
                                                    shape = CircleShape
                                                ),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = data1.toString(),
                                                color = Color(0xFFF29727),
                                                fontWeight = FontWeight.SemiBold,
                                                fontSize = 14.sp
                                            )
                                        }
                                    }
                                }
                            }

                        }

                    } else if (pagerState.currentPage == 3) {
                        searchIn = "startup status"
                        val data1 = state.Data.startUp.count {
                            it.RegistrationStatus == "Not Registered" &&
                                    (it.StartupName.contains(search, ignoreCase = true) ||
                                            it.ProductName.contains(search, ignoreCase = true) ||
                                            it.FounderName.contains(search, ignoreCase = true))
                        }

                        val data2 = state.Data.startUp.count {
                            it.RegistrationStatus == "Private Limited" &&
                                    (it.StartupName.contains(search, ignoreCase = true) ||
                                            it.ProductName.contains(search, ignoreCase = true) ||
                                            it.FounderName.contains(search, ignoreCase = true))
                        }

                        val data3 = state.Data.startUp.count {
                            it.RegistrationStatus == "Proprietorship" &&
                                    (it.StartupName.contains(search, ignoreCase = true) ||
                                            it.ProductName.contains(search, ignoreCase = true) ||
                                            it.FounderName.contains(search, ignoreCase = true))
                        }

                        val data5 = state.Data.startUp.count {
                            it.RegistrationStatus == "Partnership" &&
                                    (it.StartupName.contains(search, ignoreCase = true) ||
                                            it.ProductName.contains(search, ignoreCase = true) ||
                                            it.FounderName.contains(search, ignoreCase = true))
                        }

                        Column(
                            modifier
                                .fillMaxSize(1f)
                                .padding(horizontal = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Spacer(modifier = Modifier.height(8.dp))
                            
                            // Not Registered Card
                            androidx.compose.material3.Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(70.dp)
                                    .clickable {
                                        navController.navigate(
                                            startupsScreen(
                                                search = search,
                                                type = "reg", 
                                                contain = "Not Registered"
                                            )
                                        )
                                    },
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White
                                ),
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 2.dp
                                )
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .width(6.dp)
                                            .fillMaxHeight()
                                            .clip(
                                                RoundedCornerShape(
                                                    topStart = 12.dp,
                                                    bottomStart = 12.dp
                                                )
                                            )
                                            .background(Color(0xFFE91E63))
                                    )

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(end = 16.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "Not Registered",
                                            modifier = Modifier
                                                .weight(1f)
                                                .padding(start = 16.dp),
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFF424242),
                                            fontSize = 16.sp
                                        )

                                        Box(
                                            modifier = Modifier
                                                .size(32.dp)
                                                .background(
                                                    color = Color(0xFFE91E63).copy(alpha = 0.15f),
                                                    shape = CircleShape
                                                ),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = data1.toString(),
                                                color = Color(0xFFE91E63),
                                                fontWeight = FontWeight.SemiBold,
                                                fontSize = 14.sp
                                            )
                                        }
                                    }
                                }
                            }

                            // Private Limited Card
                            androidx.compose.material3.Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(70.dp)
                                    .clickable {
                                        navController.navigate(
                                            startupsScreen(
                                                search = search,
                                                type = "reg", 
                                                contain = "Private Limited"
                                            )
                                        )
                                    },
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White
                                ),
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 2.dp
                                )
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .width(6.dp)
                                            .fillMaxHeight()
                                            .clip(
                                                RoundedCornerShape(
                                                    topStart = 12.dp,
                                                    bottomStart = 12.dp
                                                )
                                            )
                                            .background(Color(0xFF00897B))
                                    )

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(end = 16.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "Private Limited",
                                            modifier = Modifier
                                                .weight(1f)
                                                .padding(start = 16.dp),
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFF424242),
                                            fontSize = 16.sp
                                        )

                                        Box(
                                            modifier = Modifier
                                                .size(32.dp)
                                                .background(
                                                    color = Color(0xFF00897B).copy(alpha = 0.15f),
                                                    shape = CircleShape
                                                ),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = data2.toString(),
                                                color = Color(0xFF00897B),
                                                fontWeight = FontWeight.SemiBold,
                                                fontSize = 14.sp
                                            )
                                        }
                                    }
                                }
                            }

                            // Proprietorship Card
                            androidx.compose.material3.Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(70.dp)
                                    .clickable {
                                        navController.navigate(
                                            startupsScreen(
                                                search = search,
                                                type = "reg", 
                                                contain = "Proprietorship"
                                            )
                                        )
                                    },
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White
                                ),
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 2.dp
                                )
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .width(6.dp)
                                            .fillMaxHeight()
                                            .clip(
                                                RoundedCornerShape(
                                                    topStart = 12.dp,
                                                    bottomStart = 12.dp
                                                )
                                            )
                                            .background(Color(0xFFF29727))
                                    )

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(end = 16.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "Proprietorship",
                                            modifier = Modifier
                                                .weight(1f)
                                                .padding(start = 16.dp),
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFF424242),
                                            fontSize = 16.sp
                                        )

                                        Box(
                                            modifier = Modifier
                                                .size(32.dp)
                                                .background(
                                                    color = Color(0xFFF29727).copy(alpha = 0.15f),
                                                    shape = CircleShape
                                                ),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = data3.toString(),
                                                color = Color(0xFFF29727),
                                                fontWeight = FontWeight.SemiBold,
                                                fontSize = 14.sp
                                            )
                                        }
                                    }
                                }
                            }

                            // Partnership Card
                            androidx.compose.material3.Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(70.dp)
                                    .clickable {
                                        navController.navigate(
                                            startupsScreen(
                                                search = search,
                                                type = "reg", 
                                                contain = "Partnership"
                                            )
                                        )
                                    },
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White
                                ),
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 2.dp
                                )
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .width(6.dp)
                                            .fillMaxHeight()
                                            .clip(
                                                RoundedCornerShape(
                                                    topStart = 12.dp,
                                                    bottomStart = 12.dp
                                                )
                                            )
                                            .background(Color(0xFFFBC02D))
                                    )

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(end = 16.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "Partnership",
                                            modifier = Modifier
                                                .weight(1f)
                                                .padding(start = 16.dp),
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFF424242),
                                            fontSize = 16.sp
                                        )

                                        Box(
                                            modifier = Modifier
                                                .size(32.dp)
                                                .background(
                                                    color = Color(0xFFFBC02D).copy(alpha = 0.15f),
                                                    shape = CircleShape
                                                ),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = data5.toString(),
                                                color = Color(0xFFFBC02D),
                                                fontWeight = FontWeight.SemiBold,
                                                fontSize = 14.sp
                                            )
                                        }
                                    }
                                }
                            }

                        }


                    }
                }


            }
        }


    }
}





