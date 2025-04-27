package com.example.venturenest.ui.theme.Presentation.HomePage

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.venturenest.R
import com.example.venturenest.ui.theme.DaggerHilt.States.AiStatesCompanion
import com.example.venturenest.ui.theme.DaggerHilt.States.HomePageCompanion
import com.example.venturenest.ui.theme.DaggerHilt.ViewModels.AiViewModel
import com.example.venturenest.ui.theme.DaggerHilt.ViewModels.HomeViewModel
import com.example.venturenest.ui.theme.DaggerHilt.ViewModels.LoadingStateViewmodel
import com.example.venturenest.ui.theme.Navigation.AboutECell
import com.example.venturenest.ui.theme.Navigation.AboutVentureNest
import com.example.venturenest.ui.theme.Navigation.ContactPage
import com.example.venturenest.ui.theme.Navigation.CouncilScreen
import com.example.venturenest.ui.theme.Navigation.Profile
import com.example.venturenest.ui.theme.Navigation.SettingPage
import com.example.venturenest.ui.theme.Navigation.partnerScreen
import com.example.venturenest.ui.theme.Presentation.helper.ChangeStatusBarColorEdgeToEdge
import com.example.venturenest.ui.theme.Presentation.helper.HideSystemBars
import com.example.venturenest.ui.theme.Presentation.helper.ShimmerEffect
import com.example.venturenest.ui.theme.background
import com.example.venturenest.ui.theme.bg
import com.example.venturenest.ui.theme.foreground
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.delay
import kotlin.io.path.Path
import kotlin.io.path.moveTo

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    window: WindowInsets, navController: NavController
) {
    HideSystemBars()
    ChangeStatusBarColorEdgeToEdge(Color.Transparent)

    val homeViewModel: LoadingStateViewmodel= hiltViewModel()
    val state by homeViewModel.state.collectAsState()
   var show = remember { mutableStateOf(false) }

    val schroll = rememberScrollState()
    val infinite = rememberInfiniteTransition()
    val transition by infinite.animateFloat(
        initialValue = 0f, targetValue = 360f, animationSpec = InfiniteRepeatableSpec(
            tween(5000, easing = LinearEasing)
        ), label = ""
    )


    Scaffold(contentWindowInsets = window, modifier
        .padding(bottom = 64.dp).fillMaxSize(),
        floatingActionButton = {
            Row(verticalAlignment = Alignment.CenterVertically) {
            ExtendedFloatingActionButton(onClick = {
                show.value=true
            }
            , containerColor = background) {
                Image(painter = painterResource(R.drawable.aing)
                , contentDescription = null
                ,modifier.size(25.dp).offset(x = -5.dp))
                Text("Chat with VentureBot"
                ,modifier.padding(start = 5.dp)
                , fontWeight = FontWeight.SemiBold)
            }
            }
            }
        ) {


        Box(
            modifier = modifier
                .fillMaxSize(1f)
                .background(Color.White)
                .verticalScroll(rememberScrollState()), contentAlignment = Alignment.TopCenter
        ) {


//            Image(
//                painter = painterResource(id = R.drawable.whatsapp), contentDescription = "",
//                modifier
//                    .fillMaxWidth()
//                    .height(200.dp), contentScale = ContentScale.FillBounds
//            )
            Column(
                modifier.fillMaxSize()
                    .windowInsetsPadding(window),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row (modifier.padding(bottom = 20.dp).fillMaxWidth(0.92f).wrapContentHeight()
                , verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
                    ElevatedCard(
                        modifier.size(60.dp)
                         ,shape=CircleShape
                        , colors = CardDefaults.elevatedCardColors(containerColor = Color.White)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.img),
                            contentDescription = null,
                            modifier.size(80.dp)
, contentScale = ContentScale.Crop                        )
                    }

                    Row (modifier.fillMaxWidth().wrapContentHeight()
                    , horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically){
                        ElevatedCard(
                            modifier .padding(end = 10.dp)
                                .size(40.dp)
                                .clickable{navController.navigate(ContactPage)}

                            ,shape=RectangleShape
                            , colors = CardDefaults.elevatedCardColors(containerColor = Color.White)
                        ) {
                            Box(Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Outlined.Settings,
                                    contentDescription = null,
                                    modifier.size(20.dp)
                                        ,
                                    tint = Color.Black
                                )
                            }
                            }
                        ElevatedCard(
                            modifier.size(40.dp)
                                .clickable{navController.navigate(Profile)}
                            ,shape= RectangleShape
                            , colors = CardDefaults.elevatedCardColors(containerColor = Color.White)
                        ) {
                            Box(Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Outlined.Person,
                                    contentDescription = null,
                                    modifier.size(20.dp),
                                    tint = Color.Black
                                )
                            }
                        }
                    }

                }
                Text("Hello Entrepreneur!"

                    ,      fontWeight = FontWeight.W600,
                    fontSize =MaterialTheme.typography.bodyMedium.fontSize,
                    color = Color.Black,
                    modifier = modifier.fillMaxWidth(0.93f))
        Text("Ready to Crush it Today ?"

          ,      fontWeight = FontWeight.W600,
            fontSize =MaterialTheme.typography.titleLarge.fontSize,
            color = Color.Black,
            modifier = modifier.fillMaxWidth(0.93f))


//            }

//            Column(
//                modifier
//                    .windowInsetsPadding(window)
//                    .fillMaxSize(1f),
//                verticalArrangement = Arrangement.Top,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Row(
//                    modifier
//                        .padding(top = 0.dp, bottom = 0.dp)
//                        .fillMaxWidth(0.98f)
//                        .fillMaxHeight(0.1f),
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.Start
//                ) {
//
//                    Text(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(top = 0.dp),
//                        text = "CGC VentureNest",
//                        fontSize = MaterialTheme.typography.headlineLarge.fontSize,
//                        fontWeight = FontWeight.SemiBold,
//                        textAlign = TextAlign.Center, color = Color.White
//                    )
//
//
//                }


Box(modifier.padding(top = 10.dp).fillMaxWidth().height(250.dp)){
    Column(
        modifier
            .padding(15.dp)
            .fillMaxWidth(1f)
            .wrapContentHeight()
            .clip(RoundedCornerShape(30f))
    ) {
        CoulageElement(modifier.height(250.dp))

    }


}
                ElevatedCard(  modifier = modifier.padding(top = 10.dp, bottom = 5.dp)
                    .fillMaxWidth(0.9f)
                    .height(60.dp)
                    // .border(1.dp, Color.Black,RoundedCornerShape(25f))
                    .clickable {  }, shape = RoundedCornerShape(25f)
                    , colors = CardDefaults.elevatedCardColors(
                        containerColor = Color.White

                    )
                ) {


                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .background(Color(0x0FA6A5A5)), verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Incubate your Startup",
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                            fontWeight = FontWeight.SemiBold,
                            overflow = TextOverflow.Ellipsis, modifier = modifier.fillMaxWidth(0.8f)
                                .padding(start = 10.dp), textAlign = TextAlign.Start
                        )

                        Icon(
                            imageVector = Icons.Default.ArrowForwardIos, contentDescription = "",
                            modifier = modifier
                                .padding(end = 10.dp)
                                .scale(0.7f)

                        )

                    }

                }

                Row (modifier = modifier.padding(top = 5.dp).fillMaxWidth(0.9f)){
                    ElevatedCard(  modifier = modifier.weight(0.5f)
                        .fillMaxWidth(0.4f)
                        .height(100.dp)
                        .padding(end = 5.dp)
                        // .border(1.dp, Color.Black,RoundedCornerShape(25f))
                        .clickable {  }, shape = RoundedCornerShape(25f)
                        , colors = CardDefaults.elevatedCardColors(
                            containerColor = Color.White

                        )
                    ) {


                        Column (
                            modifier = modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .background(Color(0x0FA6A5A5))
                            , verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(
                                text = "Join Us",
                                fontSize = MaterialTheme.typography.labelSmall.fontSize,
                                fontWeight = FontWeight.SemiBold,
                                overflow = TextOverflow.Ellipsis, modifier = modifier.fillMaxWidth(0.8f)
                                    .padding(start = 10.dp), textAlign = TextAlign.Start
                          , color = Color.Gray  )
                            Text(
                                text = "E-Cell Club",
                                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                                fontWeight = FontWeight.SemiBold,
                                overflow = TextOverflow.Ellipsis, modifier = modifier.fillMaxWidth(0.8f)
                                    .padding(start = 10.dp), textAlign = TextAlign.Start
                            )
                            Text(
                                text = "Click here to join us",
                                fontSize = MaterialTheme.typography.labelSmall.fontSize,
                                fontWeight = FontWeight.SemiBold,
                                overflow = TextOverflow.Ellipsis, modifier = modifier.fillMaxWidth(0.8f)
                                    .padding(start = 10.dp, top = 0.dp), textAlign = TextAlign.Start
                                , color = Color.Gray  )


                        }

                    }

                    ElevatedCard(  modifier = modifier.weight(0.5f)
                        .fillMaxWidth(0.4f)
                        .height(100.dp)
                        .padding(start = 5.dp)
                        // .border(1.dp, Color.Black,RoundedCornerShape(25f))
                        .clickable {  }, shape = RoundedCornerShape(25f)
                        , colors = CardDefaults.elevatedCardColors(
                            containerColor = Color.White

                        )
                    ) {



                        Column (
                            modifier = modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                            , verticalArrangement = Arrangement.SpaceEvenly

                        ) {
                            Text(
                                text = "Join Us",
                                fontSize = MaterialTheme.typography.labelSmall.fontSize,
                                fontWeight = FontWeight.SemiBold,
                                overflow = TextOverflow.Ellipsis, modifier = modifier.fillMaxWidth(0.8f)
                                    .padding(start = 10.dp, top = 0.dp), textAlign = TextAlign.Start
                                , color = Color.Gray  )

                            Text(
                                text = "Venture Club",
                                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                                fontWeight = FontWeight.SemiBold,
                                overflow = TextOverflow.Ellipsis, modifier = modifier.fillMaxWidth(0.8f)
                                    .padding(start = 10.dp), textAlign = TextAlign.Start
                            )
                            Text(
                                text = "Click here to join us",
                                fontSize = MaterialTheme.typography.labelSmall.fontSize,
                                fontWeight = FontWeight.SemiBold,
                                overflow = TextOverflow.Ellipsis, modifier = modifier.fillMaxWidth(0.8f)
                                    .padding(start = 10.dp, top = 0.dp), textAlign = TextAlign.Start
                                , color = Color.Gray  )


                        }

                    }


                }




//
//
//                Column(
//                    modifier
//                        .padding(top = 0.dp, bottom = 60.dp)
//                        //  .clip(RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp))
//                        .fillMaxWidth(1f)
//                        .fillMaxHeight()
//                        .verticalScroll(schroll),
//                    verticalArrangement = Arrangement.Top,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Spacer(modifier = modifier.height(10.dp))
//
//                    Box(
//                        modifier = modifier
//                            .padding(top = 10.dp, bottom = 10.dp)
//                            .fillMaxWidth(0.9f)
//                            .clip(RoundedCornerShape(25f))
//                            .wrapContentHeight()
//                    ) {
//
//                        Column(
//                            modifier
//                                .fillMaxWidth()
//                                .wrapContentHeight(),
//                            verticalArrangement = Arrangement.SpaceEvenly,
//                            horizontalAlignment = Alignment.CenterHorizontally
//                        ) {
//
//                            Row(
//                                modifier
//                                    .padding(bottom = 10.dp)
//                                    .fillMaxWidth()
//                                    .wrapContentHeight(),
//                                horizontalArrangement = Arrangement.Center,
//                                verticalAlignment = Alignment.CenterVertically
//                            ) {
//                                Image(
//                                    painter = painterResource(id = R.drawable.cgc),
//                                    contentDescription = "",
//                                    modifier
//                                        .width(50.dp)
//                                        .height(40.dp)
//                                        .padding(end = 10.dp),
//                                    contentScale = ContentScale.FillBounds
//                                )
//                                Text(
//                                    modifier = Modifier.padding(bottom = 0.dp),
//                                    fontSize = MaterialTheme.typography.bodyLarge.fontSize.times(1.5),
//                                    textAlign = TextAlign.Center,
//                                    color = bg,
//                                    fontWeight = FontWeight.Medium,
//                                    maxLines = 1,
//                                    text = "About VentureNest",
//                                    overflow = TextOverflow.Ellipsis
//                                )
//
//                            }
//                            Text(
//                                modifier = Modifier.fillMaxWidth(0.97f),
//                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
//                                lineHeight = MaterialTheme.typography.bodyMedium.lineHeight,
//                                letterSpacing = MaterialTheme.typography.bodySmall.letterSpacing,
//                                textAlign = TextAlign.Justify,
//                                color = Color.Black,
//
//                                text = "Welcome to CGC VentureNest, the premier business incubator at CGC Jhanjeri, dedicated to fostering innovation, entrepreneurship, and sustainable growth." +
//
//
//                                        " VentureNest serves as a dynamic hub for startups and aspiring entrepreneurs, providing a supportive ecosystem and comprehensive resources to transform ideas into successful ventures." +
//                                        "\n" +
//                                        "CGC VentureNest, a flagship initiative of CGC Jhanjeri, is committed to nurturing the spirit of entrepreneurship and innovation among students, faculty, and the wider community. Our incubator offers state-of-the-art facilities, expert mentorship, networking opportunities, and access to funding to accelerate the growth of startups across various sectors."
//                            )
//                        }
//                    }
////
//
//
//                    Box(
//                        modifier = modifier
//                            .padding(top = 10.dp, bottom = 10.dp)
//                            .fillMaxWidth()
//
//                            .height(250.dp)
//                    ) {
//                        Image(
//                            painter = painterResource(id = R.drawable.whatsapp),
//                            contentDescription = "",
//                            modifier
//                                .fillMaxWidth()
//                                .fillMaxHeight(),
//                            contentScale = ContentScale.FillBounds
//                        )
//                        Column(
//                            modifier
//                                .fillMaxWidth()
//                                .fillMaxHeight(),
//                            verticalArrangement = Arrangement.SpaceEvenly,
//                            horizontalAlignment = Alignment.CenterHorizontally
//                        ) {
//
//                            Text(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(top = 10.dp, bottom = 5.dp),
//                                text = "Technology Business Incubator (TBI)",
//                                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
//                                fontWeight = FontWeight.SemiBold,
//                                textAlign = TextAlign.Center, color = Color.White
//                            )
//
//                            Text(
//                                modifier = Modifier.fillMaxWidth(0.9f),
//                                fontSize = MaterialTheme.typography.bodySmall.fontSize,
//                                textAlign = TextAlign.Justify,
//                                color = Color.White,
//                                overflow = TextOverflow.Ellipsis,
//                                text = "As a recognized Technology Business Incubator (TBI), CGC VentureNest offers tailored support and cutting-edge infrastructure designed for technology-driven startups. Our TBI status provides exclusive access to government grants, funding opportunities, and additional benefits, empowering startups to innovate and thrive."
//                            )
//
//                        }
//                    }
////                    Column(
////                        modifier
////                            .padding(top = 10.dp, bottom = 0.dp)
////                            .wrapContentHeight()
////                            .fillMaxWidth(),
////                        // .border(0.1.dp, Color.Black, shape = RoundedCornerShape(50f))
////
////                    ) {
////                        Column(
////                            modifier
////                                .wrapContentHeight()
////                                .padding(top = 0.dp, bottom = 20.dp),
////                            horizontalAlignment = Alignment.CenterHorizontally,
////                            verticalArrangement = Arrangement.SpaceEvenly
////                        ) {
////
////                            Box(
////                                modifier
////                                    .fillMaxWidth()
////                                    .padding(bottom = 10.dp),
////
////                                ) {
////                                Text(
////                                    modifier = modifier
////                                        .fillMaxWidth()
////                                        .padding(top = 20.dp, bottom = 20.dp),
////                                    text = "Continously Improving",
////                                    textAlign = TextAlign.Center,
////                                    fontSize = MaterialTheme.typography.headlineSmall.fontSize,
////                                    fontWeight = FontWeight.SemiBold
////                                )
////
////                            }
////
////                            Row(
////                                modifier
////                                    .fillMaxWidth()
////                                    .padding(bottom = 10.dp)
////                                    .wrapContentHeight(),
////                                verticalAlignment = Alignment.CenterVertically,
////                                horizontalArrangement = Arrangement.SpaceEvenly
////                            ) {
////                                Column(
////                                    modifier
////                                        .fillMaxWidth()
////                                        .weight(1f),
////                                    horizontalAlignment = Alignment.CenterHorizontally
////                                ) {
////                                    Text(
////                                        text = "Startups Incubated",
////                                        fontSize = MaterialTheme.typography.labelSmall.fontSize
////                                    )
////                                    Text(
////                                        text = "150+",
////                                        textAlign = TextAlign.Center,
////                                        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
////                                        fontWeight = FontWeight.SemiBold, color = Color(0xffA30D33)
////                                    )
////                                }
////                                Column(
////                                    modifier
////                                        .fillMaxWidth()
////                                        .weight(1f),
////                                    horizontalAlignment = Alignment.CenterHorizontally
////                                ) {
////                                    Text(
////                                        text = "Startups Mentored",
////                                        fontSize = MaterialTheme.typography.labelSmall.fontSize
////                                    )
////                                    Text(
////                                        text = "500+",
////                                        textAlign = TextAlign.Center,
////                                        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
////                                        fontWeight = FontWeight.SemiBold, color = Color(0xffA30D33)
////                                    )
////                                }
////
////                            }
////                            Row(
////                                modifier
////                                    .fillMaxWidth()
////                                    .padding(bottom = 10.dp)
////                                    .wrapContentHeight(),
////                                verticalAlignment = Alignment.CenterVertically,
////                                horizontalArrangement = Arrangement.SpaceEvenly
////                            ) {
////                                Column(
////                                    modifier
////                                        .fillMaxWidth()
////                                        .weight(1f),
////                                    horizontalAlignment = Alignment.CenterHorizontally
////                                ) {
////                                    Text(
////                                        text = "Key Mentors",
////                                        fontSize = MaterialTheme.typography.labelSmall.fontSize
////                                    )
////                                    Text(
////                                        text = "100+",
////                                        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
////                                        fontWeight = FontWeight.SemiBold, color = Color(0xffA30D33)
////                                    )
////                                }
////                                Column(
////                                    modifier
////                                        .fillMaxWidth()
////                                        .weight(1f),
////                                    horizontalAlignment = Alignment.CenterHorizontally
////                                ) {
////                                    Text(
////                                        text = "Patents Filed",
////                                        fontSize = MaterialTheme.typography.labelSmall.fontSize
////                                    )
////                                    Text(
////                                        text = "400+",
////                                        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
////                                        fontWeight = FontWeight.SemiBold, color = Color(0xffA30D33)
////                                    )
////                                }
////
////                            }
////
////                            Row(
////                                modifier
////                                    .fillMaxWidth()
////                                    .padding(bottom = 10.dp)
////                                    .wrapContentHeight(),
////                                verticalAlignment = Alignment.CenterVertically,
////                                horizontalArrangement = Arrangement.SpaceEvenly
////                            ) {
////
////                                Column(
////                                    modifier
////                                        .fillMaxWidth()
////                                        .weight(1f),
////                                    horizontalAlignment = Alignment.CenterHorizontally,
////                                    verticalArrangement = Arrangement.Center
////                                ) {
////                                    Text(
////                                        text = "Funding Raised By Startups",
////                                        fontSize = MaterialTheme.typography.labelSmall.fontSize
////                                    )
////                                    Text(
////                                        text = "1400 Lakh",
////                                        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
////                                        fontWeight = FontWeight.SemiBold, color = bg
////                                    )
////                                }
////                                Column(
////                                    modifier
////                                        .fillMaxWidth()
////                                        .weight(1f),
////                                    horizontalAlignment = Alignment.CenterHorizontally,
////                                    verticalArrangement = Arrangement.Center
////                                ) {
////                                    Text(
////                                        text = "Investment By Incubator",
////                                        fontSize = MaterialTheme.typography.labelSmall.fontSize
////                                    )
////                                    Text(
////                                        text = "140 Lakhs",
////                                        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
////                                        fontWeight = FontWeight.SemiBold, color = bg
////                                    )
////                                }
////
////                            }
////                        }
////                    }
//
//                   when(state.state){
//                       is HomePageCompanion.Loading->{
//                           Box(
//                               modifier = modifier
//                                   .padding(top = 10.dp, bottom = 10.dp)
//                                   .fillMaxWidth()
//                                   .height(400.dp)
//                           ) {
//                              // Image(painter = painterResource(id = R.drawable.whatsapp), contentDescription = "",modifier.fillMaxSize(), contentScale = ContentScale.FillBounds)
//
//                               Column(
//                                   modifier
//                                       .padding(bottom = 20.dp)
//                                       .fillMaxWidth()
//                                       .fillMaxHeight(),
//                                   verticalArrangement = Arrangement.SpaceEvenly,
//                                   horizontalAlignment = Alignment.CenterHorizontally
//                               ) {
//
//                                   Row(
//                                       modifier
//                                           .padding(top = 0.dp)
//                                           .fillMaxWidth(),
//                                       verticalAlignment = Alignment.CenterVertically,
//                                       horizontalArrangement = Arrangement.SpaceBetween
//                                   ) {
//
//
//                                       Column(
//                                           modifier = Modifier
//                                               .fillMaxWidth(0.9f)
//
//                                               .padding(top = 20.dp, bottom = 15.dp, start = 16.dp)
//                                               .height(20.dp)
//                                               .background(ShimmerEffect())
//                                       ){}
//
//                                   }
//                                  Row (
//                                      modifier
//                                          .fillMaxWidth(1f)
//                                          .height(200.dp)
//                                          .horizontalScroll(
//                                              rememberScrollState()
//                                          )){
//                                      repeat(6) {
//
//
//                                          Column(
//                                              modifier
//                                                  .padding(start = 10.dp, end = 10.dp)
//                                                  .fillMaxHeight()
//                                                  .width(150.dp)
//                                                  .background(ShimmerEffect())
//                                          ) {
//
//                                          }
//                                      }
//                                  }
//                               }
//                           }
//
//
//                       }
//                       is HomePageCompanion.Result->{
//
                           Box(
                               modifier = modifier
                                   .padding(top = 10.dp, bottom = 0.dp)
                                   .fillMaxWidth()

                                   .height(250.dp)
                           ) {
                              //
//
                               Column(
                                   modifier

                                       .padding(bottom = 0.dp
                                       , top = 20.dp)
                                       .fillMaxWidth()
                                       .fillMaxHeight(),
                                   verticalArrangement = Arrangement.SpaceEvenly,
                                   horizontalAlignment = Alignment.CenterHorizontally
                               ) {
                                   Row(modifier.fillMaxWidth(0.88f).height(40.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {

                                       Text(
                                           "Our Partners", fontWeight = FontWeight.W600,
                                           fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                           color = Color.Black,
                                           modifier = modifier.fillMaxWidth(0.5f)
                                       )
                                       TextButton(onClick = {
                                           navController.navigate(partnerScreen(search = "",
                                               0xFFF29727,""))
                                       }) {
                                           Text("See more")
                                       }
                                   }
                                   val scrollState = rememberScrollState()
                                   Row(
                                       modifier
                                           .padding(top = 0.dp)
                                           .fillMaxWidth(0.94f),
                                       verticalAlignment = Alignment.CenterVertically,
                                       horizontalArrangement = Arrangement.SpaceBetween
                                   ) {



                                   }
                                   PartnerPages(schroll = scrollState, list = state.Data.partner)

                               }
                           }


                Box(
                    modifier = modifier
                        .padding(top = 20.dp, bottom = 0.dp)
                        .fillMaxWidth()

                        .height(280.dp)
                ) {

                    Column(
                        modifier
                            .padding(bottom = 0.dp
                                , top = 0.dp)
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                       Row(modifier.fillMaxWidth(0.88f).height(40.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {

                           Text(
                               "Council Members", fontWeight = FontWeight.W600,
                               fontSize = MaterialTheme.typography.titleMedium.fontSize,
                               color = Color.Black,
                               modifier = modifier.fillMaxWidth(0.5f)
                           )

                           TextButton(onClick = {
                               navController.navigate(CouncilScreen(
                                   search = "", 0xFFF29727,""
                               ))
                           }) {
                               Text("See more")
                           }
                       }

                        val scrollState = rememberScrollState()
                        Row(
                            modifier
                                .padding(top = 0.dp)
                                .fillMaxWidth(0.94f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            PartnerPage(modifier, scrollState, state.Data.councilmembers)

                        }
                    }
                }
//
//
////
////                           Box(
////                               modifier = modifier
////                                   .padding(top = 10.dp, bottom = 10.dp)
////                                   .fillMaxWidth()
////
////                                   .height(220.dp)
////                           ) {
////                               Column(
////                                   modifier
////                                       .fillMaxWidth()
////                                       .fillMaxHeight(),
////                                   verticalArrangement = Arrangement.SpaceEvenly,
////                                   horizontalAlignment = Alignment.CenterHorizontally
////                               ) {
////                                   val scrollState = rememberScrollState()
////                                   Row(
////                                       modifier
////                                           .padding(top = 0.dp)
////                                           .fillMaxWidth(0.9f),
////                                       verticalAlignment = Alignment.CenterVertically,
////                                       horizontalArrangement = Arrangement.SpaceBetween
////                                   ) {
////
////
////                                       Text(
////                                           modifier = Modifier
////                                               .padding(start = 0.dp)
////                                               .fillMaxWidth(0.8f)
////                                               .padding(top = 10.dp, bottom = 0.dp),
////                                           text = "Associate and Partners",
////                                           fontSize = MaterialTheme.typography.bodyLarge.fontSize.times(1.3),
////                                           lineHeight = MaterialTheme.typography.bodyLarge.lineHeight,
////                                           letterSpacing = MaterialTheme.typography.bodyLarge.letterSpacing,
////                                           fontWeight = FontWeight.SemiBold,
////                                           textAlign = TextAlign.Start, color = Color.Black
////                                       )
////                                       if (scrollState.value>80) {
////
////
////                                           Icon(
////                                               imageVector = Icons.Default.ArrowForwardIos,
////                                               contentDescription = "",
////                                               tint = Color.Black,
////                                               modifier = modifier.scale(0.7f)
////                                           )
////                                       }
////                                   }
////                                   PartnerPage(schroll = scrollState)
////                               }
////                           }
////
////                           Box(
////                               modifier = modifier
////                                   .padding(top = 10.dp, bottom = 10.dp)
////                                   .fillMaxWidth()
////
////                                   .height(220.dp)
////                           ) {
////                               val scrollState = rememberScrollState()
////
////                               Column(
////                                   modifier
////                                       .fillMaxWidth()
////                                       .fillMaxHeight(),
////                                   verticalArrangement = Arrangement.SpaceEvenly,
////                                   horizontalAlignment = Alignment.CenterHorizontally
////                               ) {
////                                   Row(
////                                       modifier
////                                           .padding(top = 0.dp)
////                                           .fillMaxWidth(0.9f),
////                                       verticalAlignment = Alignment.CenterVertically,
////                                       horizontalArrangement = Arrangement.SpaceBetween
////                                   ) {
////
////
////                                       Text(
////                                           modifier = Modifier
////                                               .fillMaxWidth(0.8f)
////                                               .padding(top = 10.dp, bottom = 0.dp),
////                                           text = "Council and members",
////                                           fontSize = MaterialTheme.typography.bodyLarge.fontSize.times(1.5),
////                                           lineHeight = MaterialTheme.typography.bodyLarge.lineHeight,
////                                           letterSpacing = MaterialTheme.typography.bodyLarge.letterSpacing,
////                                           fontWeight = FontWeight.SemiBold,
////                                           textAlign = TextAlign.Start, color = Color.Black
////                                       )
////                                       if (scrollState.value>80) {
////
////
////                                           Icon(
////                                               imageVector = Icons.Default.ArrowForwardIos,
////                                               contentDescription = "",
////                                               tint = Color.Black,
////                                               modifier = modifier.scale(0.7f)
////                                           )
////                                       }
////                                   }
////                                   PartnerPage(schroll = scrollState)
////                               }
////                           }
//                           Box(
//                               modifier = modifier
//                                   .padding(top = 0.dp, bottom = 10.dp)
//                                   .fillMaxWidth()
//
//                                   .height(300.dp)
//                           ) {
//                               Image(painter = painterResource(id = R.drawable.whatsapp), contentDescription = "",modifier.fillMaxSize(), contentScale = ContentScale.FillBounds)
//
//                               Column(
//                                   modifier
//                                       .padding(bottom = 20.dp)
//                                       .fillMaxWidth()
//                                       .fillMaxHeight(),
//                                   verticalArrangement = Arrangement.SpaceEvenly,
//                                   horizontalAlignment = Alignment.CenterHorizontally
//                               ) {
//                                   val scrollState = rememberScrollState()
//                                   Row(
//                                       modifier
//                                           .padding(top = 0.dp)
//                                           .fillMaxWidth(),
//                                       verticalAlignment = Alignment.CenterVertically,
//                                       horizontalArrangement = Arrangement.SpaceBetween
//                                   ) {
//
//
////                                       Text(
////                                           modifier = Modifier
////                                               .fillMaxWidth(0.8f)
////                                               .padding(top = 20.dp, bottom = 15.dp, start = 16.dp),
////                                           text = "Inspirational Stories",
////                                           fontSize = MaterialTheme.typography.bodyLarge.fontSize.times(1.5),
////                                           lineHeight = MaterialTheme.typography.bodyLarge.lineHeight
////                                           ,
////                                           letterSpacing = MaterialTheme.typography.bodyLarge.letterSpacing,
////                                           fontWeight = FontWeight.SemiBold,
////                                           textAlign = TextAlign.Start, color = Color.White
////                                       )
//
//                                   }
//PartnerPages(modifier,scrollState,
//    state.Data.partner)                               }
//                           }
//
//                       }is HomePageCompanion.Error->{
//                       LaunchedEffect(key1 = Unit) {
//                           while (true) {
//
//
//                               delay(5000)
//                               homeViewModel.fetchSuccess()
//                           }}
//
//                       Column(
//                           modifier
//                               .fillMaxWidth()
//                               .height(400.dp), verticalArrangement = Arrangement.Center,
//                           horizontalAlignment = Alignment.CenterHorizontally) {
//                           Image(painter = painterResource(id = R.drawable.nothingfound), contentDescription = "",
//                               modifier
//                                   .padding(bottom = 0.dp)
//                                   .size(250.dp)
//                               , contentScale = ContentScale.FillBounds)
//                           Text(text = "Oops! an error occured ",
//                               modifier
//                                   .padding(bottom = 10.dp)
//                                   .fillMaxWidth(0.8f)
//                               , maxLines = 3, overflow = TextOverflow.Ellipsis, textAlign = TextAlign.Center)
//                           Button(onClick = {
//                               homeViewModel.fetchSuccess()
//
//                           }, shape = RoundedCornerShape(25f), colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = Color.Black)) {
//                               Text(text = "Reload", color = Color.White)        }
//
////                           Text(
////                               text =if (state.error!!.contains("Unable to resolve host")) "Oops! unable to connect to server , please check your internet connection " else "Unknown Error Occurred : We are trying to resolve it",
////                               modifier
////                                   .fillMaxWidth(0.8f)
////                                   .padding(bottom = 15.dp, top = 0.dp)
////                               , textAlign = TextAlign.Center, maxLines = 2, overflow = TextOverflow.Ellipsis)
////                           Button(onClick = { homeViewModel.fetchSuccess() },
////                               shape = RoundedCornerShape(25f)
////                               , border = BorderStroke(1.dp,Color.Black)
////                               , colors = ButtonDefaults.buttonColors(containerColor = Color.White)
////                           ) {
////                               Text(text = "Try again", color = Color.Black)
////                           }
//
//                       }
//                       }
//                   }
//
//
//
//Spacer(modifier = modifier.height(50.dp))
//
//
//                }
//
//
//            }
//
//
//        }
            }



                Spacer(modifier.height(40.dp))










        }
        AiDialog(
            show,
            modifier
            )

    }}









@Composable
fun ChatBubbleWithArrow(message: String, isUser: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
    ) {
        var displayedText by remember { mutableStateOf("") }

        LaunchedEffect(message) {
            displayedText = "" // Reset
           message.forEachIndexed { index, _ ->
                displayedText = message.substring(0, index + 1)
                delay(5) // Delay between each character
            }
        }


        Box(
            modifier = Modifier
                .background(
                    color = if (!isUser) Color.Transparent else Color.Gray.copy(
                        alpha = 0.2f
                    ),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(12.dp)
        ) {
            Text(text = if (!isUser) displayedText else message, color = Color.Black
                , fontSize = 16.sp)
        }
        Canvas(modifier = Modifier.size(10.dp)) {
            drawPath(
                path = Path().apply {
                    moveTo(0f, 0f)
                    lineTo(10f, 5f)
                    lineTo(0f, 10f)
                    close()
                } as Path,
                color = if (isUser) Color.Blue else Color.Gray
            )
        }
    }
}
