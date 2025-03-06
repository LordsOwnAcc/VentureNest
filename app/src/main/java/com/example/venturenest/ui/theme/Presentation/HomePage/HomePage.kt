package com.example.venturenest.ui.theme.Presentation.HomePage

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.venturenest.R
import com.example.venturenest.ui.theme.DaggerHilt.States.HomePageCompanion
import com.example.venturenest.ui.theme.DaggerHilt.ViewModels.HomeViewModel
import com.example.venturenest.ui.theme.Navigation.AboutECell
import com.example.venturenest.ui.theme.Navigation.AboutVentureNest
import com.example.venturenest.ui.theme.Presentation.helper.ChangeStatusBarColorEdgeToEdge
import com.example.venturenest.ui.theme.Presentation.helper.ShimmerEffect
import com.example.venturenest.ui.theme.bg
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    window: WindowInsets, navController: NavController
) {
    ChangeStatusBarColorEdgeToEdge(color = bg)

    val homeViewModel:HomeViewModel= hiltViewModel()
    val state by homeViewModel.state.collectAsState()

    val schroll = rememberScrollState()
    val infinite = rememberInfiniteTransition()
    val transition by infinite.animateFloat(
        initialValue = 0f, targetValue = 360f, animationSpec = InfiniteRepeatableSpec(
            tween(5000, easing = LinearEasing)
        ), label = ""
    )


    Scaffold(contentWindowInsets = window, modifier.fillMaxSize(),
        floatingActionButton = {
            if (schroll.value > 100) {

                Row(
                    modifier = modifier
                        .padding(bottom = 55.dp)
                        .fillMaxWidth()
                        .height(40.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier
                            .wrapContentWidth()
                            .fillMaxHeight()
                            .clip(
                                RoundedCornerShape(25f)
                            )
                            .background(bg),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text("VentureNest",
                            modifier
                                .padding(start = 10.dp, end = 10.dp)
                                .clickable {
                                    navController.navigate(AboutVentureNest)
                                }, color = Color.White)
                        Spacer(modifier = modifier
                            .height(25.dp)
                            .width(1.dp)
                            .background(Color.White))
                        Text("E-cell",
                            modifier
                                .padding(start = 10.dp, end = 10.dp)
                                .clickable {
                                    navController.navigate(AboutECell)
                                }, color = Color.White)
                        Spacer(modifier = modifier
                            .height(25.dp)
                            .width(1.dp)
                            .background(Color.White))
                        Text(text = "Incubation",
                            modifier
                                .padding(start = 10.dp, end = 10.dp)
                                .clickable {

                                }, color = Color.White)


                    }
                }
            }
        }) {


        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White), contentAlignment = Alignment.TopCenter
        ) {

            Image(
                painter = painterResource(id = R.drawable.whatsapp), contentDescription = "",
                modifier
                    .fillMaxWidth()
                    .height(200.dp), contentScale = ContentScale.FillBounds
            )
            Column(
                modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.End
            ) {

            }

            Column(
                modifier
                    .windowInsetsPadding(window)
                    .fillMaxSize(1f),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier
                        .padding(top = 0.dp, bottom = 0.dp)
                        .fillMaxWidth(0.98f)
                        .fillMaxHeight(0.1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 0.dp),
                        text = "CGC VentureNest",
                        fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center, color = Color.White
                    )


                }
                
                Column(
                    modifier
                        .padding(bottom = 20.dp, top = 0.dp)
                        .fillMaxWidth(0.9f)
                        .height(200.dp)
                ) {
                    CoulageElement(modifier.height(200.dp))

                }



                Column(
                    modifier
                        .padding(top = 0.dp, bottom = 60.dp)
                        //  .clip(RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp))
                        .fillMaxWidth(1f)
                        .fillMaxHeight()
                        .verticalScroll(schroll),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = modifier.height(10.dp))
                    
                    Box(
                        modifier = modifier
                            .padding(top = 10.dp, bottom = 10.dp)
                            .fillMaxWidth(0.9f)
                            .clip(RoundedCornerShape(25f))
                            .wrapContentHeight()
                    ) {

                        Column(
                            modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Row(
                                modifier
                                    .padding(bottom = 10.dp)
                                    .fillMaxWidth()
                                    .wrapContentHeight(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.cgc),
                                    contentDescription = "",
                                    modifier
                                        .width(50.dp)
                                        .height(40.dp)
                                        .padding(end = 10.dp),
                                    contentScale = ContentScale.FillBounds
                                )
                                Text(
                                    modifier = Modifier.padding(bottom = 0.dp),
                                    fontSize = MaterialTheme.typography.bodyLarge.fontSize.times(1.5),
                                    textAlign = TextAlign.Center,
                                    color = bg,
                                    fontWeight = FontWeight.Medium,
                                    maxLines = 1,
                                    text = "About VentureNest",
                                    overflow = TextOverflow.Ellipsis
                                )

                            }
                            Text(
                                modifier = Modifier.fillMaxWidth(0.97f),
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                lineHeight = MaterialTheme.typography.bodyMedium.lineHeight,
                                letterSpacing = MaterialTheme.typography.bodySmall.letterSpacing,
                                textAlign = TextAlign.Justify,
                                color = Color.Black,

                                text = "Welcome to CGC VentureNest, the premier business incubator at CGC Jhanjeri, dedicated to fostering innovation, entrepreneurship, and sustainable growth." +


                                        " VentureNest serves as a dynamic hub for startups and aspiring entrepreneurs, providing a supportive ecosystem and comprehensive resources to transform ideas into successful ventures." +
                                        "\n" +
                                        "CGC VentureNest, a flagship initiative of CGC Jhanjeri, is committed to nurturing the spirit of entrepreneurship and innovation among students, faculty, and the wider community. Our incubator offers state-of-the-art facilities, expert mentorship, networking opportunities, and access to funding to accelerate the growth of startups across various sectors."
                            )
                        }
                    }
//


                    Box(
                        modifier = modifier
                            .padding(top = 10.dp, bottom = 10.dp)
                            .fillMaxWidth()

                            .height(250.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.whatsapp),
                            contentDescription = "",
                            modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            contentScale = ContentScale.FillBounds
                        )
                        Column(
                            modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp, bottom = 5.dp),
                                text = "Technology Business Incubator (TBI)",
                                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center, color = Color.White
                            )

                            Text(
                                modifier = Modifier.fillMaxWidth(0.9f),
                                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                textAlign = TextAlign.Justify,
                                color = Color.White,
                                overflow = TextOverflow.Ellipsis,
                                text = "As a recognized Technology Business Incubator (TBI), CGC VentureNest offers tailored support and cutting-edge infrastructure designed for technology-driven startups. Our TBI status provides exclusive access to government grants, funding opportunities, and additional benefits, empowering startups to innovate and thrive."
                            )

                        }
                    }
//                    Column(
//                        modifier
//                            .padding(top = 10.dp, bottom = 0.dp)
//                            .wrapContentHeight()
//                            .fillMaxWidth(),
//                        // .border(0.1.dp, Color.Black, shape = RoundedCornerShape(50f))
//
//                    ) {
//                        Column(
//                            modifier
//                                .wrapContentHeight()
//                                .padding(top = 0.dp, bottom = 20.dp),
//                            horizontalAlignment = Alignment.CenterHorizontally,
//                            verticalArrangement = Arrangement.SpaceEvenly
//                        ) {
//
//                            Box(
//                                modifier
//                                    .fillMaxWidth()
//                                    .padding(bottom = 10.dp),
//
//                                ) {
//                                Text(
//                                    modifier = modifier
//                                        .fillMaxWidth()
//                                        .padding(top = 20.dp, bottom = 20.dp),
//                                    text = "Continously Improving",
//                                    textAlign = TextAlign.Center,
//                                    fontSize = MaterialTheme.typography.headlineSmall.fontSize,
//                                    fontWeight = FontWeight.SemiBold
//                                )
//
//                            }
//
//                            Row(
//                                modifier
//                                    .fillMaxWidth()
//                                    .padding(bottom = 10.dp)
//                                    .wrapContentHeight(),
//                                verticalAlignment = Alignment.CenterVertically,
//                                horizontalArrangement = Arrangement.SpaceEvenly
//                            ) {
//                                Column(
//                                    modifier
//                                        .fillMaxWidth()
//                                        .weight(1f),
//                                    horizontalAlignment = Alignment.CenterHorizontally
//                                ) {
//                                    Text(
//                                        text = "Startups Incubated",
//                                        fontSize = MaterialTheme.typography.labelSmall.fontSize
//                                    )
//                                    Text(
//                                        text = "150+",
//                                        textAlign = TextAlign.Center,
//                                        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
//                                        fontWeight = FontWeight.SemiBold, color = Color(0xffA30D33)
//                                    )
//                                }
//                                Column(
//                                    modifier
//                                        .fillMaxWidth()
//                                        .weight(1f),
//                                    horizontalAlignment = Alignment.CenterHorizontally
//                                ) {
//                                    Text(
//                                        text = "Startups Mentored",
//                                        fontSize = MaterialTheme.typography.labelSmall.fontSize
//                                    )
//                                    Text(
//                                        text = "500+",
//                                        textAlign = TextAlign.Center,
//                                        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
//                                        fontWeight = FontWeight.SemiBold, color = Color(0xffA30D33)
//                                    )
//                                }
//
//                            }
//                            Row(
//                                modifier
//                                    .fillMaxWidth()
//                                    .padding(bottom = 10.dp)
//                                    .wrapContentHeight(),
//                                verticalAlignment = Alignment.CenterVertically,
//                                horizontalArrangement = Arrangement.SpaceEvenly
//                            ) {
//                                Column(
//                                    modifier
//                                        .fillMaxWidth()
//                                        .weight(1f),
//                                    horizontalAlignment = Alignment.CenterHorizontally
//                                ) {
//                                    Text(
//                                        text = "Key Mentors",
//                                        fontSize = MaterialTheme.typography.labelSmall.fontSize
//                                    )
//                                    Text(
//                                        text = "100+",
//                                        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
//                                        fontWeight = FontWeight.SemiBold, color = Color(0xffA30D33)
//                                    )
//                                }
//                                Column(
//                                    modifier
//                                        .fillMaxWidth()
//                                        .weight(1f),
//                                    horizontalAlignment = Alignment.CenterHorizontally
//                                ) {
//                                    Text(
//                                        text = "Patents Filed",
//                                        fontSize = MaterialTheme.typography.labelSmall.fontSize
//                                    )
//                                    Text(
//                                        text = "400+",
//                                        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
//                                        fontWeight = FontWeight.SemiBold, color = Color(0xffA30D33)
//                                    )
//                                }
//
//                            }
//
//                            Row(
//                                modifier
//                                    .fillMaxWidth()
//                                    .padding(bottom = 10.dp)
//                                    .wrapContentHeight(),
//                                verticalAlignment = Alignment.CenterVertically,
//                                horizontalArrangement = Arrangement.SpaceEvenly
//                            ) {
//
//                                Column(
//                                    modifier
//                                        .fillMaxWidth()
//                                        .weight(1f),
//                                    horizontalAlignment = Alignment.CenterHorizontally,
//                                    verticalArrangement = Arrangement.Center
//                                ) {
//                                    Text(
//                                        text = "Funding Raised By Startups",
//                                        fontSize = MaterialTheme.typography.labelSmall.fontSize
//                                    )
//                                    Text(
//                                        text = "1400 Lakh",
//                                        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
//                                        fontWeight = FontWeight.SemiBold, color = bg
//                                    )
//                                }
//                                Column(
//                                    modifier
//                                        .fillMaxWidth()
//                                        .weight(1f),
//                                    horizontalAlignment = Alignment.CenterHorizontally,
//                                    verticalArrangement = Arrangement.Center
//                                ) {
//                                    Text(
//                                        text = "Investment By Incubator",
//                                        fontSize = MaterialTheme.typography.labelSmall.fontSize
//                                    )
//                                    Text(
//                                        text = "140 Lakhs",
//                                        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
//                                        fontWeight = FontWeight.SemiBold, color = bg
//                                    )
//                                }
//
//                            }
//                        }
//                    }

                   when(state.state){
                       is HomePageCompanion.Loading->{
                           Box(
                               modifier = modifier
                                   .padding(top = 10.dp, bottom = 10.dp)
                                   .fillMaxWidth()
                                   .height(400.dp)
                           ) {
                              // Image(painter = painterResource(id = R.drawable.whatsapp), contentDescription = "",modifier.fillMaxSize(), contentScale = ContentScale.FillBounds)

                               Column(
                                   modifier
                                       .padding(bottom = 20.dp)
                                       .fillMaxWidth()
                                       .fillMaxHeight(),
                                   verticalArrangement = Arrangement.SpaceEvenly,
                                   horizontalAlignment = Alignment.CenterHorizontally
                               ) {

                                   Row(
                                       modifier
                                           .padding(top = 0.dp)
                                           .fillMaxWidth(),
                                       verticalAlignment = Alignment.CenterVertically,
                                       horizontalArrangement = Arrangement.SpaceBetween
                                   ) {


                                       Column(
                                           modifier = Modifier
                                               .fillMaxWidth(0.9f)

                                               .padding(top = 20.dp, bottom = 15.dp, start = 16.dp)
                                               .height(20.dp)
                                               .background(ShimmerEffect())
                                       ){}

                                   }
                                  Row (
                                      modifier
                                          .fillMaxWidth(1f)
                                          .height(200.dp)
                                          .horizontalScroll(
                                              rememberScrollState()
                                          )){
                                      repeat(6) {


                                          Column(
                                              modifier
                                                  .padding(start = 10.dp, end = 10.dp)
                                                  .fillMaxHeight()
                                                  .width(150.dp)
                                                  .background(ShimmerEffect())
                                          ) {

                                          }
                                      }
                                  }
                               }
                           }


                       }
                       is HomePageCompanion.Result->{

                           Box(
                               modifier = modifier
                                   .padding(top = 10.dp, bottom = 10.dp)
                                   .fillMaxWidth()

                                   .height(300.dp)
                           ) {

                               Column(
                                   modifier
                                       .padding(bottom = 20.dp)
                                       .fillMaxWidth()
                                       .fillMaxHeight(),
                                   verticalArrangement = Arrangement.SpaceEvenly,
                                   horizontalAlignment = Alignment.CenterHorizontally
                               ) {
                                   val scrollState = rememberScrollState()
                                   Row(
                                       modifier
                                           .padding(top = 0.dp)
                                           .fillMaxWidth(0.94f),
                                       verticalAlignment = Alignment.CenterVertically,
                                       horizontalArrangement = Arrangement.SpaceBetween
                                   ) {


                                       Text(
                                           modifier = Modifier
                                               .fillMaxWidth(0.8f)
                                               .padding(top = 20.dp, bottom = 15.dp, start = 16.dp),
                                           text = "Partners",
                                           fontSize = MaterialTheme.typography.bodyLarge.fontSize.times(1.5),
                                           lineHeight = MaterialTheme.typography.bodyLarge.lineHeight
                                           ,
                                           letterSpacing = MaterialTheme.typography.bodyLarge.letterSpacing,
                                           fontWeight = FontWeight.SemiBold,
                                           textAlign = TextAlign.Start, color = Color.Black
                                       )
                                       if (scrollState.value>80) {


                                           Icon(
                                               imageVector = Icons.Default.ArrowForwardIos,
                                               contentDescription = "",
                                               tint = Color.Black,
                                               modifier = modifier.padding(end = 16.dp)
                                           )
                                       }
                                   }
                                   PartnerPages(schroll = scrollState, list = state.result.partnersResult)
                               }
                           }


//
//                           Box(
//                               modifier = modifier
//                                   .padding(top = 10.dp, bottom = 10.dp)
//                                   .fillMaxWidth()
//
//                                   .height(220.dp)
//                           ) {
//                               Column(
//                                   modifier
//                                       .fillMaxWidth()
//                                       .fillMaxHeight(),
//                                   verticalArrangement = Arrangement.SpaceEvenly,
//                                   horizontalAlignment = Alignment.CenterHorizontally
//                               ) {
//                                   val scrollState = rememberScrollState()
//                                   Row(
//                                       modifier
//                                           .padding(top = 0.dp)
//                                           .fillMaxWidth(0.9f),
//                                       verticalAlignment = Alignment.CenterVertically,
//                                       horizontalArrangement = Arrangement.SpaceBetween
//                                   ) {
//
//
//                                       Text(
//                                           modifier = Modifier
//                                               .padding(start = 0.dp)
//                                               .fillMaxWidth(0.8f)
//                                               .padding(top = 10.dp, bottom = 0.dp),
//                                           text = "Associate and Partners",
//                                           fontSize = MaterialTheme.typography.bodyLarge.fontSize.times(1.3),
//                                           lineHeight = MaterialTheme.typography.bodyLarge.lineHeight,
//                                           letterSpacing = MaterialTheme.typography.bodyLarge.letterSpacing,
//                                           fontWeight = FontWeight.SemiBold,
//                                           textAlign = TextAlign.Start, color = Color.Black
//                                       )
//                                       if (scrollState.value>80) {
//
//
//                                           Icon(
//                                               imageVector = Icons.Default.ArrowForwardIos,
//                                               contentDescription = "",
//                                               tint = Color.Black,
//                                               modifier = modifier.scale(0.7f)
//                                           )
//                                       }
//                                   }
//                                   PartnerPage(schroll = scrollState)
//                               }
//                           }
//
//                           Box(
//                               modifier = modifier
//                                   .padding(top = 10.dp, bottom = 10.dp)
//                                   .fillMaxWidth()
//
//                                   .height(220.dp)
//                           ) {
//                               val scrollState = rememberScrollState()
//
//                               Column(
//                                   modifier
//                                       .fillMaxWidth()
//                                       .fillMaxHeight(),
//                                   verticalArrangement = Arrangement.SpaceEvenly,
//                                   horizontalAlignment = Alignment.CenterHorizontally
//                               ) {
//                                   Row(
//                                       modifier
//                                           .padding(top = 0.dp)
//                                           .fillMaxWidth(0.9f),
//                                       verticalAlignment = Alignment.CenterVertically,
//                                       horizontalArrangement = Arrangement.SpaceBetween
//                                   ) {
//
//
//                                       Text(
//                                           modifier = Modifier
//                                               .fillMaxWidth(0.8f)
//                                               .padding(top = 10.dp, bottom = 0.dp),
//                                           text = "Council and members",
//                                           fontSize = MaterialTheme.typography.bodyLarge.fontSize.times(1.5),
//                                           lineHeight = MaterialTheme.typography.bodyLarge.lineHeight,
//                                           letterSpacing = MaterialTheme.typography.bodyLarge.letterSpacing,
//                                           fontWeight = FontWeight.SemiBold,
//                                           textAlign = TextAlign.Start, color = Color.Black
//                                       )
//                                       if (scrollState.value>80) {
//
//
//                                           Icon(
//                                               imageVector = Icons.Default.ArrowForwardIos,
//                                               contentDescription = "",
//                                               tint = Color.Black,
//                                               modifier = modifier.scale(0.7f)
//                                           )
//                                       }
//                                   }
//                                   PartnerPage(schroll = scrollState)
//                               }
//                           }
                           Box(
                               modifier = modifier
                                   .padding(top = 10.dp, bottom = 10.dp)
                                   .fillMaxWidth()

                                   .height(300.dp)
                           ) {
                               Image(painter = painterResource(id = R.drawable.whatsapp), contentDescription = "",modifier.fillMaxSize(), contentScale = ContentScale.FillBounds)

                               Column(
                                   modifier
                                       .padding(bottom = 20.dp)
                                       .fillMaxWidth()
                                       .fillMaxHeight(),
                                   verticalArrangement = Arrangement.SpaceEvenly,
                                   horizontalAlignment = Alignment.CenterHorizontally
                               ) {
                                   val scrollState = rememberScrollState()
                                   Row(
                                       modifier
                                           .padding(top = 0.dp)
                                           .fillMaxWidth(),
                                       verticalAlignment = Alignment.CenterVertically,
                                       horizontalArrangement = Arrangement.SpaceBetween
                                   ) {


                                       Text(
                                           modifier = Modifier
                                               .fillMaxWidth(0.8f)
                                               .padding(top = 20.dp, bottom = 15.dp, start = 16.dp),
                                           text = "Inspirational Stories",
                                           fontSize = MaterialTheme.typography.bodyLarge.fontSize.times(1.5),
                                           lineHeight = MaterialTheme.typography.bodyLarge.lineHeight
                                           ,
                                           letterSpacing = MaterialTheme.typography.bodyLarge.letterSpacing,
                                           fontWeight = FontWeight.SemiBold,
                                           textAlign = TextAlign.Start, color = Color.White
                                       )
                                       if (scrollState.value>80) {


                                           Icon(
                                               imageVector = Icons.Default.ArrowForwardIos,
                                               contentDescription = "",
                                               tint = Color.White,
                                               modifier = modifier.padding(end = 16.dp)
                                           )
                                       }
                                   }
                                   PartnerPage(schroll = scrollState, list = state.result.Successresult)
                               }
                           }

                       }is HomePageCompanion.Error->{
                       LaunchedEffect(key1 = Unit) {
                           while (true) {


                               delay(5000)
                               homeViewModel.fetchSuccess()
                           }}

                       Column(
                           modifier
                               .fillMaxWidth()
                               .height(400.dp), verticalArrangement = Arrangement.Center,
                           horizontalAlignment = Alignment.CenterHorizontally) {
                           Image(painter = painterResource(id = R.drawable.nothingfound), contentDescription = "",
                               modifier
                                   .padding(bottom = 0.dp)
                                   .size(250.dp)
                               , contentScale = ContentScale.FillBounds)
                           Text(text = "Oops! an error occured ",
                               modifier
                                   .padding(bottom = 10.dp)
                                   .fillMaxWidth(0.8f)
                               , maxLines = 3, overflow = TextOverflow.Ellipsis, textAlign = TextAlign.Center)
                           Button(onClick = {
                               homeViewModel.fetchSuccess()

                           }, shape = RoundedCornerShape(25f), colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = Color.Black)) {
                               Text(text = "Reload", color = Color.White)        }

//                           Text(
//                               text =if (state.error!!.contains("Unable to resolve host")) "Oops! unable to connect to server , please check your internet connection " else "Unknown Error Occurred : We are trying to resolve it",
//                               modifier
//                                   .fillMaxWidth(0.8f)
//                                   .padding(bottom = 15.dp, top = 0.dp)
//                               , textAlign = TextAlign.Center, maxLines = 2, overflow = TextOverflow.Ellipsis)
//                           Button(onClick = { homeViewModel.fetchSuccess() },
//                               shape = RoundedCornerShape(25f)
//                               , border = BorderStroke(1.dp,Color.Black)
//                               , colors = ButtonDefaults.buttonColors(containerColor = Color.White)
//                           ) {
//                               Text(text = "Try again", color = Color.Black)
//                           }

                       }
                       }
                   }



Spacer(modifier = modifier.height(50.dp))


                }


            }


        }
    }
}
