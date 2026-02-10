package com.example.venturenest.ui.theme.Presentation.HomePage

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.venturenest.MeetTheBoardCard
import com.example.venturenest.OurJourneyCard
import com.example.venturenest.R
import com.example.venturenest.StatCard
import com.example.venturenest.stats
import com.example.venturenest.ui.theme.DaggerHilt.SuccessStories
import com.example.venturenest.ui.theme.DaggerHilt.ViewModels.LoadingStateViewmodel
import com.example.venturenest.ui.theme.Navigation.AboutECell
import com.example.venturenest.ui.theme.Navigation.AboutVentureNest
import com.example.venturenest.ui.theme.Navigation.CouncilScreen
import com.example.venturenest.ui.theme.Navigation.Profile
import com.example.venturenest.ui.theme.Navigation.partnerScreen
import com.example.venturenest.ui.theme.Presentation.helper.ChangeStatusBarColorEdgeToEdge
import com.example.venturenest.ui.theme.DaggerHilt.Events
import com.example.venturenest.ui.theme.Presentation.EventPage.Dialog as EventDialog
import com.example.venturenest.ui.theme.Presentation.helper.HideSystemBars
import com.example.venturenest.ui.theme.background
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, androidx.compose.foundation.ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    window: WindowInsets,
    navController: NavController
    , homeViewModel: LoadingStateViewmodel
) {
    val scope = rememberCoroutineScope()
    val animationState = remember { MutableTransitionState(false) }
    var showSuccess by remember { mutableStateOf(false) }
    HideSystemBars()
    ChangeStatusBarColorEdgeToEdge(Color.Transparent)
    val context  = LocalContext.current
    val image= remember { mutableStateOf("") }
    val name = remember { mutableStateOf("") }
    var isVisible = remember {
        mutableStateOf(false)
    }
    val activity = context as? Activity
    var backPressedTime by remember { mutableStateOf(0L) }
    val toast = remember { Toast.makeText(context, "Press back again to exit", Toast.LENGTH_SHORT) }
    var isNavOpen by remember { mutableStateOf(false) }
    val state by homeViewModel.state.collectAsState()
    var show = remember { mutableStateOf(false) }

    // New variables for Event Dialog
    var selectedEvent by remember { mutableStateOf(Events("", "", "", "", "", false)) }
    var showEventDialog = remember { mutableStateOf(false) }
    var selectedEventColor by remember { mutableStateOf(0xff000000) }
    val colorlist = listOf(
        0xFF22A699,
        0xFFF29727,
        0xFFF24C3D,
        0xFFB349D2,
        0xFFF2BE22
    )
    val schroll = rememberScrollState()
    val infinite = rememberInfiniteTransition()


    Scaffold(
        contentWindowInsets = window, modifier
            .padding(bottom = 64.dp)
            .fillMaxSize(),
        floatingActionButton = {
            if (!showSuccess){


            Row(verticalAlignment = Alignment.CenterVertically) {
                ExtendedFloatingActionButton(onClick = {
                    show.value = true
                }, containerColor = background) {
                    Image(
                        painter = painterResource(R.drawable.aing),
                        contentDescription = null,
                        modifier
                            .size(25.dp)
                            .offset(x = -5.dp)

                    )
                    Text(
                        "Chat with VentureBot",
                        modifier.padding(start = 5.dp),
                        fontWeight = FontWeight.SemiBold
                        , color = Color.Black
                    )
                }
            }}
        }
    ) {


        Box(modifier = Modifier.fillMaxSize()) {





BackHandler {
    val currentTime = System.currentTimeMillis()
    if (currentTime - backPressedTime < 2000) {
        toast.cancel()
        activity?.finish()
    } else {
        backPressedTime = currentTime
        toast.show()
    }
}

            Box(
                modifier = modifier
                    .fillMaxSize(1f)
                    .background(Color.White)
                    .verticalScroll(schroll), contentAlignment = Alignment.TopCenter
            ) {


//            Image(
//                painter = painterResource(id = R.drawable.whatsapp), contentDescription = "",
//                modifier
//                    .fillMaxWidth()
//                    .height(200.dp), contentScale = ContentScale.FillBounds
//            )
            Column(
                modifier
                    .fillMaxSize()
                    .windowInsetsPadding(window),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

        Spacer(modifier
            .padding(top = 15.dp)
            .height(80.dp))
        


//                Text(
//                    "Hello ${Firebase.auth.currentUser?.displayName} !", fontWeight = FontWeight.W600,
//                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
//                    color = Color.Black,
//                    modifier = modifier.fillMaxWidth(0.93f)
//                )
//                Text(
//                    "Ready to Crush it Today ?", fontWeight = FontWeight.W600,
//                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
//                    color = Color.Black,
//                    modifier = modifier.fillMaxWidth(0.93f)
//                )


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


                Box(modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth()
                    .height(250.dp)) {
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
//                ElevatedCard(
//                    modifier = modifier
//                        .padding(top = 10.dp, bottom = 5.dp)
//                        .fillMaxWidth(0.9f)
//                        .height(60.dp)
//                        // .border(1.dp, Color.Black,RoundedCornerShape(25f))
//                        .clickable {
//
//
//                            val intent = Intent(
//                                Intent.ACTION_VIEW,
//                                Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLScH3XNhBUvAOoNPsjxd7LzxoNBjTVfasJa5_Iqwq3aFsRWmcA/viewform")
//                            )
//                            context.startActivity(intent)
//
//                        },
//                    shape = RoundedCornerShape(25f),
//                    colors = CardDefaults.elevatedCardColors(
//                        containerColor = Color.White
//
//                    )
//                ) {
//
//
//                    Row(
//                        modifier = modifier
//                            .fillMaxWidth()
//                            .height(60.dp)
//                            .background(Color(0x0FA6A5A5)),
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.SpaceBetween
//                    ) {
//                        Text(
//                            text = "Incubate your Startup",
//                            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
//                            fontWeight = FontWeight.SemiBold,
//                            overflow = TextOverflow.Ellipsis, modifier = modifier
//                                .fillMaxWidth(0.8f)
//                                .padding(start = 10.dp), textAlign = TextAlign.Start
//                            , color = Color.Black
//                        )
//
//                        Icon(
//                            imageVector = Icons.Default.ArrowForwardIos, contentDescription = "",
//                            modifier = modifier
//                                .padding(end = 10.dp)
//                                .scale(0.7f)
//
//                        )
//
//                    }
//
//                }

                Row(modifier = modifier
                    .padding(top = 5.dp)
                    .fillMaxWidth(0.9f)) {
                    ElevatedCard(
                        modifier = modifier
                            .weight(0.5f)
                            .fillMaxWidth(0.4f)
                            .height(100.dp)
                            .padding(end = 5.dp)
                            // .border(1.dp, Color.Black,RoundedCornerShape(25f))
                            .clickable {


                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLScvFkGdQcnDYvt0OsJi-27FlAXjslt-48RC5wP6JRxAI4oSMg/viewform")
                                )
                                context.startActivity(intent)

                            },
                        shape = RoundedCornerShape(25f),
                        colors = CardDefaults.elevatedCardColors(
                            containerColor = Color.White

                        )
                    ) {


                        Column(
                            modifier = modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .background(Color(0x0FA6A5A5)),
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(
                                text = "Join Us",
                                fontSize = MaterialTheme.typography.labelSmall.fontSize,
                                fontWeight = FontWeight.SemiBold,
                                overflow = TextOverflow.Ellipsis,
                                modifier = modifier
                                    .fillMaxWidth(0.8f)
                                    .padding(start = 10.dp),
                                textAlign = TextAlign.Start,
                                color = Color.Gray
                            )
                            Text(
                                text = "E-Cell Club",
                                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                                fontWeight = FontWeight.SemiBold,
                                overflow = TextOverflow.Ellipsis,
                                modifier = modifier
                                    .fillMaxWidth(0.8f)
                                    .padding(start = 10.dp),
                                textAlign = TextAlign.Start
                                , color = Color.Black
                            )
                            Text(
                                text = "Click here to join us",
                                fontSize = MaterialTheme.typography.labelSmall.fontSize,
                                fontWeight = FontWeight.SemiBold,
                                overflow = TextOverflow.Ellipsis,
                                modifier = modifier
                                    .fillMaxWidth(0.8f)
                                    .padding(start = 10.dp, top = 0.dp),
                                textAlign = TextAlign.Start,
                                color = Color.Gray
                            )


                        }

                    }

                    ElevatedCard(
                        modifier = modifier
                            .weight(0.5f)
                            .fillMaxWidth(0.4f)
                            .height(100.dp)
                            .padding(start = 5.dp)
                            // .border(1.dp, Color.Black,RoundedCornerShape(25f))
                            .clickable {


                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLScH3XNhBUvAOoNPsjxd7LzxoNBjTVfasJa5_Iqwq3aFsRWmcA/viewform")
                                )
                                context.startActivity(intent)

                            },
                        shape = RoundedCornerShape(25f),
                        colors = CardDefaults.elevatedCardColors(
                            containerColor = Color.White

                        )
                    ) {


                        Column(
                            modifier = modifier
                                .fillMaxWidth()
                                .fillMaxHeight(), verticalArrangement = Arrangement.SpaceEvenly

                        ) {
                            Text(
                                text = "Join Us",
                                fontSize = MaterialTheme.typography.labelSmall.fontSize,
                                fontWeight = FontWeight.SemiBold,
                                overflow = TextOverflow.Ellipsis,
                                modifier = modifier
                                    .fillMaxWidth(0.8f)
                                    .padding(start = 10.dp, top = 0.dp),
                                textAlign = TextAlign.Start,
                                color = Color.Gray
                            )

                            Text(
                                text = "Venture Club",
                                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                                fontWeight = FontWeight.SemiBold,
                                overflow = TextOverflow.Ellipsis,
                                modifier = modifier
                                    .fillMaxWidth(0.8f)
                                    .padding(start = 10.dp),
                                textAlign = TextAlign.Start
                                , color = Color.Black
                            )
                            Text(
                                text = "Click here to join us",
                                fontSize = MaterialTheme.typography.labelSmall.fontSize,
                                fontWeight = FontWeight.SemiBold,
                                overflow = TextOverflow.Ellipsis,
                                modifier = modifier
                                    .fillMaxWidth(0.8f)
                                    .padding(start = 10.dp, top = 0.dp),
                                textAlign = TextAlign.Start,
                                color = Color.Gray
                            )


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
                OurJourneyCard(
                    imageRes = "https://plus.unsplash.com/premium_photo-1706061121842-7ba956c57670?q=80&w=1171&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                )

                Row (modifier
                    .fillMaxWidth(1f)
                    .padding(top = 15.dp, bottom = 15.dp)
                    .horizontalScroll(rememberScrollState())
                    ){
                    Spacer(modifier.width(10.dp))
                    stats.forEach {
                        StatCard(
                            it
                        )
                    }

                }
                MeetTheBoardCard(
members = state.Data.councilmembers.take(4)
               , onclick = {
                        navController.navigate(
                                    CouncilScreen(
                                        search = "", 0xFFF29727, ""
                                    )
                                )
                    } )
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

                            .padding(
                                bottom = 0.dp, top = 20.dp
                            )
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier
                                .fillMaxWidth(0.88f)
                                .height(40.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Text(
                                "Partners", fontWeight = FontWeight.W600,
                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                color = Color.Black,
                                modifier = modifier.fillMaxWidth(0.5f)
                            )
                            TextButton(onClick = {
                                navController.navigate(
                                    partnerScreen(
                                        search = "",
                                        0xFFF29727, ""
                                    )
                                )
                            }) {
                                Text("view all", color = Color.Gray)
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
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 5.dp, bottom = 0.dp)
                                .wrapContentHeight()

                                .clip(androidx.compose.foundation.shape.RoundedCornerShape(8.dp))
                                .background(androidx.compose.ui.graphics.Color.Transparent)
                            , contentAlignment = Alignment.Center
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding()
                                    .fillMaxWidth()
                                    .horizontalScroll(rememberScrollState())

//                .basicMarquee(
//                    500, MarqueeAnimationMode.Immediately, velocity = 10.dp,
//                    repeatDelayMillis = 0, spacing = MarqueeSpacing(0.dp)
//                )
                                , horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically



                            ) {
                                Spacer(modifier = modifier.width(16.dp))
                                val context = LocalContext.current
                                state.Data.partner.forEach { iconUrl ->
                                    ElevatedCard( modifier = Modifier
                                        .padding(start = 10.dp, top = 5.dp, bottom = 5.dp
                                            , end = 5.dp)
                                        // .border(0.1.dp, Color.Black, shape = RoundedCornerShape(25f))
                                        ,
                                        colors = CardDefaults.cardColors(containerColor = Color.White)) {


                                        AsyncImage(
                                            model = iconUrl.imgpath,
                                            contentDescription = null,
                                            contentScale = ContentScale.FillWidth,
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(20f))
                                                .width(150.dp)
                                                .height(180.dp)
                                                .clickable {
                                                    image.value = iconUrl.imgpath
                                                    name.value = iconUrl.Name
                                                    isVisible.value = true
                                                }


                                        )


                                    }
                                }
                                Spacer(modifier = modifier.width(16.dp))
                            }
                        }

                    }
                }



                // Starred Events Section
                val starredEvents = state.Data.events.filter { it.isStarred }
                if (starredEvents.isNotEmpty()) {
                    Column(
                        modifier = Modifier
                            .padding(top = 20.dp, bottom = 20.dp)
                            .fillMaxWidth()
                    ) {
                        // Header without "Scroll all" button
                        Text(
                            text = "Featured Events",
                            fontWeight = FontWeight.W600,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            color = Color.Black,
                            modifier = Modifier.padding(start = 16.dp, bottom = 10.dp)
                        )

                        // Auto-scrolling Featured Event Cards using HorizontalPager
                        val pagerState = rememberPagerState(pageCount = { starredEvents.size })
                        
                        // Auto-scroll effect
                        LaunchedEffect(pagerState) {
                            while (true) {
                                kotlinx.coroutines.delay(3000) // 3 seconds delay
                                val nextPage = (pagerState.currentPage + 1) % starredEvents.size
                                pagerState.animateScrollToPage(nextPage)
                            }
                        }

                        androidx.compose.foundation.pager.HorizontalPager(
                            state = pagerState,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .padding(horizontal = 16.dp)
                        ) { page ->
                            val event = starredEvents[page]
                            ElevatedCard(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .clickable {
                                        selectedEvent = event
                                        selectedEventColor = colorlist[state.Data.events.indexOf(event) % 4]
                                        showEventDialog.value = true
                                    },
                                shape = RoundedCornerShape(16.dp),
                                colors = CardDefaults.elevatedCardColors(containerColor = Color.White)
                            ) {
                                Box(modifier = Modifier.fillMaxSize()) {
                                    AsyncImage(
                                        model = event.imageUrl,
                                        contentDescription = event.eventName,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier.fillMaxSize()
                                    )
                                    // Gradient overlay
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(
                                                Brush.verticalGradient(
                                                    colors = listOf(
                                                        Color.Transparent,
                                                        Color.Black.copy(alpha = 0.7f)
                                                    )
                                                )
                                            )
                                    )
                                    // Event details overlay
                                    Column(
                                        modifier = Modifier
                                            .align(Alignment.BottomStart)
                                            .padding(16.dp)
                                    ) {
                                        Text(
                                            text = event.eventName,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 18.sp,
                                            color = Color.White,
                                            maxLines = 2,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier.padding(top = 4.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.DateRange,
                                                contentDescription = null,
                                                tint = Color.White,
                                                modifier = Modifier.size(16.dp)
                                            )
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Text(
                                                text = event.eventDate.take(10),
                                                fontSize = 12.sp,
                                                color = Color.White
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        // Page indicator dots
                        if (starredEvents.size > 1) {
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                repeat(starredEvents.size) { iteration ->
                                    val color = if (pagerState.currentPage == iteration) 
                                        Color.Black 
                                    else 
                                        Color.LightGray
                                    Box(
                                        modifier = Modifier
                                            .padding(4.dp)
                                            .clip(CircleShape)
                                            .background(color)
                                            .size(8.dp)
                                    )
                                }
                            }
                        }


                        // Grid Layout for Event Cards (2 columns) - matching reference image
                        if (starredEvents.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(16.dp))
                            
                            // Show all starred events to ensure at least 4 are displayed
                            val gridEvents = starredEvents.take(4)
                            
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                            ) {
                                // Create rows of 2 items each
                                gridEvents.chunked(2).forEach { rowEvents ->
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(bottom = 12.dp),
                                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                                    ) {
                                        rowEvents.forEach { event ->
                                            // Each card takes equal space
                                            ElevatedCard(
                                                modifier = Modifier
                                                    .weight(1f)
                                                    .clickable {
                                                        selectedEvent = event
                                                        selectedEventColor = colorlist[state.Data.events.indexOf(event) % 4]
                                                        showEventDialog.value = true
                                                    },
                                                shape = RoundedCornerShape(12.dp),
                                                colors = CardDefaults.elevatedCardColors(containerColor = Color.White),
                                                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
                                            ) {
                                                Column(
                                                    modifier = Modifier.fillMaxWidth()
                                                ) {
                                                    // Event Image
                                                    AsyncImage(
                                                        model = event.imageUrl,
                                                        contentDescription = event.eventName,
                                                        contentScale = ContentScale.Crop,
                                                        modifier = Modifier
                                                            .fillMaxWidth()
                                                            .height(120.dp)
                                                            .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                                                    )
                                                    
                                                    // Event Details
                                                    Column(
                                                        modifier = Modifier
                                                            .fillMaxWidth()
                                                            .padding(12.dp)
                                                    ) {
                                                        // Event Name
                                                        Text(
                                                            text = event.eventName,
                                                            fontWeight = FontWeight.SemiBold,
                                                            fontSize = 14.sp,
                                                            color = Color.Black,
                                                            maxLines = 1,
                                                            overflow = TextOverflow.Ellipsis
                                                        )
                                                        
                                                        Spacer(modifier = Modifier.height(6.dp))
                                                        
                                                        // Date
                                                        Row(
                                                            verticalAlignment = Alignment.CenterVertically
                                                        ) {
                                                            Icon(
                                                                imageVector = Icons.Default.DateRange,
                                                                contentDescription = null,
                                                                tint = Color.Gray,
                                                                modifier = Modifier.size(12.dp)
                                                            )
                                                            Spacer(modifier = Modifier.width(4.dp))
                                                            Text(
                                                                text = event.eventDate.take(10),
                                                                fontSize = 11.sp,
                                                                color = Color.Gray,
                                                                maxLines = 1
                                                            )
                                                        }
                                                        
                                                        Spacer(modifier = Modifier.height(4.dp))
                                                        
                                                        // Location
                                                        Row(
                                                            verticalAlignment = Alignment.CenterVertically
                                                        ) {
                                                            Icon(
                                                                imageVector = Icons.Default.LocationOn,
                                                                contentDescription = null,
                                                                tint = Color.Gray,
                                                                modifier = Modifier.size(12.dp)
                                                            )
                                                            Spacer(modifier = Modifier.width(4.dp))
                                                            Text(
                                                                text = "CGC Mohali",
                                                                fontSize = 11.sp,
                                                                color = Color.Gray,
                                                                maxLines = 1,
                                                                overflow = TextOverflow.Ellipsis
                                                            )
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        
                                        // If row has only 1 item, add empty spacer for balance
                                        if (rowEvents.size == 1) {
                                            Spacer(modifier = Modifier.weight(1f))
                                        }
                                    }
                                }
                            }
                        }

                        // "See All" Button at the bottom
                        OutlinedButton(
                            onClick = {
                                navController.navigate(
                                    com.example.venturenest.ui.theme.Navigation.EventsPage
                                ) {
                                    launchSingleTop = true
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 12.dp),
                            shape = RoundedCornerShape(8.dp),
                            border = BorderStroke(1.dp, Color.Gray)
                        ) {
                            Text(
                                text = "See All Events",
                                color = Color.Black,
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp
                            )
                        }
                    }
                }

        // Wall of Fame Carousel - Below Events section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Wall of Fame",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFB30D2F),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            val pagerState = rememberPagerState(
                initialPage = 1,
                pageCount = { 4 }
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(380.dp)
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 60.dp),
                    pageSpacing = 16.dp
                ) { page ->
                    val pageOffset = ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction)
                    val scale = 1f - (kotlin.math.abs(pageOffset) * 0.15f)

                    val imageRes = when (page) {
                        0 -> R.drawable.escape_founder
                        1 -> R.drawable.techhealth_founder
                        2 -> R.drawable.edswagon_founder
                        else -> R.drawable.vidyutam_founder
                    }
                    val founderName = when (page) {
                        0 -> "Shivang Tiwari"
                        1 -> "Ashutosh Saxena"
                        2 -> "Ashish Chabra"
                        else -> "Anshul Bhati"
                    }
                    val companyName = when (page) {
                        0 -> "escapekar"
                        1 -> "Techealth Apex Private Limited"
                        2 -> "EDS Wagon Tech"
                        else -> "Vidyutam Verde"
                    }
                    val logoRes = when (page) {
                        0 -> R.drawable.escapekarlogo
                        1 -> R.drawable.techealth_logo
                        2 -> R.drawable.edslogo
                        else -> R.drawable.vidyutamlogo
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp) // Increased height slightly to accommodate the layout comfortably
                            .graphicsLayer {
                                scaleX = scale
                                scaleY = scale
                                alpha = 0.5f + (scale - 0.85f) * 3.33f
                            },
                        shape = RoundedCornerShape(24.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF2C2C3E))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            // Header: Logo and Funding
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.Top
                            ) {
                                // Logo Box
                                Card(
                                    shape = RoundedCornerShape(12.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color(0xFF3E3E55)),
                                    elevation = CardDefaults.cardElevation(0.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(50.dp)
                                            .padding(8.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Image(
                                            painter = painterResource(id = logoRes),
                                            contentDescription = "Logo",
                                            modifier = Modifier.fillMaxSize(),
                                            contentScale = ContentScale.Fit
                                        )
                                    }
                                }

                                // Funding Tag
                                Card(
                                    shape = RoundedCornerShape(topEnd = 12.dp, topStart = 4.dp, bottomStart = 12.dp, bottomEnd = 4.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color(0xFFB30D2F))
                                ) {
                                    Column(
                                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                                        horizontalAlignment = Alignment.End
                                    ) {
                                        Text(
                                            text = "Funding",
                                            fontSize = 11.sp,
                                            color = Color.White.copy(alpha = 0.9f),
                                            fontWeight = FontWeight.Medium
                                        )
                                        HorizontalDivider(thickness = 0.dp,
                                            color = Color.White,
                                            modifier = Modifier.width(60.dp))

                                        Text(
                                            text = "3 Lakhs",
                                            fontSize = 16.sp,
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            // Main Founder Image
                            Image(
                                painter = painterResource(id = imageRes),
                                contentDescription = companyName,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(16.dp)),
                                contentScale = ContentScale.Crop,
                                alignment = Alignment.Center
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            // Footer: Names
                            Column(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = founderName,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "~$companyName",
                                    fontSize = 16.sp,
                                    color = Color.White.copy(alpha = 0.8f),
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }

                // Left Arrow
                IconButton(
                    onClick = {
                        scope.launch {
                            if (pagerState.currentPage > 0) {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        } 
                    },
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 16.dp)
                        .background(Color.White.copy(alpha = 0.5f), CircleShape)
                        .size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Previous",
                        tint = Color.Black
                    )
                }

                // Right Arrow
                IconButton(
                    onClick = {
                        scope.launch {
                            if (pagerState.currentPage < 3) {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 16.dp)
                        .background(Color.White.copy(alpha = 0.5f), CircleShape)
                        .size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowForward,
                        contentDescription = "Next",
                        tint = Color.Black
                    )
                }
            }

            // Page Indicators
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(4) { iteration ->
                    val color = if (pagerState.currentPage == iteration)
                        Color(0xFF2F2F2F) else Color.LightGray
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(if (pagerState.currentPage == iteration) 10.dp else 8.dp)
                    )
                }
            }
        }

        // Startup Success Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp)
        ) {
            Text(
                text = "Startup Success",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFB30D2F),
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .align(Alignment.CenterHorizontally)
            )

            var showAllStories by remember { mutableStateOf(false) }

            val successStories = listOf(
                Triple("Pulkesh Gautam", "Vidyutam Verde NCESOL Pvt. Ltd.", "The networking opportunities at VentureNest helped us secure our first three major clients. It's the perfect launchpad for digital ventures."),
                Triple("Navneet Yaduvanshi", "Aasyra", "From lab access to market connect, VentureNest supported our organic product journey every step of the way. Truly a transformative experience."),
                Triple("Mr. RAJAT SONI", "V2R AUTOINFINITE PRIVATE LIMITED", "At V2R, we’re not just disrupting the automotive sector; we’re revolutionizing it. Our mission is to empower both vehicle owners and automotive service businesses through advanced, technology-driven solutions that streamline operations, enhance efficiency, and drive sustainable growth."),
                Triple("Mr Harrish Babber", "Escapekar", "Escapekar is a travel guidance platform that helps people become better travelers—from exploring and planning to taking a trip. Travelers across the globe use the Escapekar app to discover hidden places, find where to stay, what to do, and where to eat—all recommended by an algorithm that selects the best options for them."),
                Triple("Mr. Narinder Singh", "Nhanks Waste Recyclers Pvt.Ltd", "Sustainable solutions need specialized support. VentureNest's focus on social impact startups gave us the push we needed."),
                Triple("Mr Karan Kumar Aggrawal", "Indi Tech", "The technical mentorship here is unmatched. We scaled our tech stack and team efficiently under the guidance of industry experts."),
                Triple("Mr. Jaskaranpreet Singh", "Juniva Organics", "Juniva Organics found its footing here. The incubation support helped us navigate regulatory challenges and reach the market faster."),
                Triple("Aditi Sharma", "GreenEarth Solutions", "VentureNest provided the critical sustainability mentorship we needed to refine our business model and pitch to impact investors successfully."),
                Triple("Rohan Mehta", "CryptoSecure", "The blockchain expertise available at VentureNest is world-class. We were able to architect a secure and scalable solution that investors loved."),
                Triple("Sanya Kapoor", "AI Edutech", "We scaled our AI models with the infrastructure support provided here. The access to cloud credits and technical mentors was a game changer.")
            )

            val visibleStories = if (showAllStories) successStories else successStories.take(4)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
            visibleStories.chunked(2).forEach { rowStories ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    rowStories.forEach { story ->
                        Card(
                            modifier = Modifier
                                .weight(1f)
                                .height(IntrinsicSize.Min), // Allow dynamic height
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFF9FAFB)),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(16.dp) // Reduced padding slightly for 2-column
                                    .fillMaxWidth(),
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    Text(
                                        text = "“",
                                        fontSize = 48.sp, // scaled down from 60
                                        color = Color(0xFFE5E7EB),
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.offset(x = (-4).dp, y = (-8).dp)
                                    )
                                    Text(
                                        text = story.third,
                                        fontSize = 13.sp, // scaled down for 2-column
                                        color = Color(0xFF374151),
                                        lineHeight = 20.sp,
                                        modifier = Modifier.offset(y = (-16).dp)
                                    )
                                }

                                Column {
                                    HorizontalDivider(
                                        modifier = Modifier.padding(bottom = 12.dp),
                                        color = Color(0xFFE5E7EB)
                                    )
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Box(
                                            modifier = Modifier
                                                .size(36.dp) // scaled down
                                                .clip(CircleShape)
                                                .background(Color(0xFFE5E7EB)),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = story.first.first().toString(),
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 16.sp,
                                                color = Color(0xFF6B7280)
                                            )
                                        }

                                        Spacer(modifier = Modifier.width(8.dp))

                                        Column {
                                            Text(
                                                text = story.first,
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 12.sp,
                                                color = Color(0xFF111827),
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis
                                            )
                                            Text(
                                                text = story.second,
                                                fontSize = 10.sp,
                                                color = Color(0xFF6B7280),
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                    // If row has only 1 item, add spacer to push it to left
                    if (rowStories.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
                
                // Show More / Show Less Button
                OutlinedButton(
                    onClick = { showAllStories = !showAllStories },
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, Color(0xFFB30D2F))
                ) {
                    Text(
                        text = if (showAllStories) "Show Less" else "Show More (${successStories.size - 4} more)",
                        color = Color(0xFFB30D2F),
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }


//                Box(
//                    modifier = modifier
//                        .padding(top = 20.dp, bottom = 55.dp)
//                        .fillMaxWidth()
//
//                        .height(280.dp)
//                ) {
//
//                    Column(
//                        modifier
//                            .padding(
//                                bottom = 0.dp, top = 0.dp
//                            )
//                            .fillMaxWidth()
//                            .fillMaxHeight(),
//                        verticalArrangement = Arrangement.SpaceEvenly,
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        Row(
//                            modifier
//                                .fillMaxWidth(0.88f)
//                                .height(40.dp),
//                            verticalAlignment = Alignment.CenterVertically,
//                            horizontalArrangement = Arrangement.SpaceBetween
//                        ) {
//
//                            Text(
//
//
//                                "Council Members", fontWeight = FontWeight.SemiBold
//
//
//
//
//
//
//
//
//
//
//                                ,
//                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
//                                color = Color.Black,
//                                modifier = modifier.fillMaxWidth(0.5f)
//                            )
//
//                            TextButton(onClick = {
//                                navController.navigate(
//                                    CouncilScreen(
//                                        search = "", 0xFFF29727, ""
//                                    )
//                                )
//                            }) {
//                                Text("view all", color = Color.Gray)
//                            }
//                        }
//
//                        val scrollState = rememberScrollState()
////                        Row(
////                            modifier
////                                .padding(top = 0.dp)
////                                .fillMaxWidth(0.94f),
////                            verticalAlignment = Alignment.CenterVertically,
////                            horizontalArrangement = Arrangement.SpaceBetween
////                        ) {
////
////                            Box(
////                                modifier = Modifier
////                                    .fillMaxWidth()
////                                    .padding(top = 5.dp, bottom = 0.dp)
////                                    .wrapContentHeight()
////
////                                    .clip(androidx.compose.foundation.shape.RoundedCornerShape(8.dp))
////                                    .background(androidx.compose.ui.graphics.Color.Transparent)
////                                , contentAlignment = Alignment.Center
////                            ) {
////                                Row(
////                                    modifier = Modifier
////                                        .padding()
////                                        .fillMaxWidth().horizontalScroll(rememberScrollState())
//////                .basicMarquee(
//////                    500, MarqueeAnimationMode.Immediately, velocity = 25.dp,
//////                    repeatDelayMillis = 0, spacing = MarqueeSpacing(0.dp)
//////                )
////                                    , horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically
////
////
////
////                                ) {
////
////                                    val context = LocalContext.current
////                                    state.Data.councilmembers.forEach { it ->
////                                        ElevatedCard (modifier.padding(10.dp).height(180.dp).width(150.dp)
////                                            , shape = RoundedCornerShape(topStart = 15f, topEnd = 15f)) {
////                                            Column(modifier.height(180.dp).width(150.dp)) {
////                                                AsyncImage(
////                                                    model = it.imgpath,
////                                                    contentDescription = it.imgName,
////                                                    modifier.fillMaxWidth()
////                                                        .height(150.dp)
////                                                        .background(Color.White)
////                                                        .clickable{
////                                                            name.value = it.name
////                                                            image.value=it.imgpath
////                                                            isVisible.value=true
////                                                        }, contentScale = ContentScale.Fit
////                                                )
////
////                                                Row(
////                                                    modifier.fillMaxWidth(1f).fillMaxWidth().fillMaxHeight()
////                                                        .background(Color(0xFFF29727))
////                                                        .horizontalScroll(rememberScrollState()),
////                                                    verticalAlignment = Alignment.CenterVertically,
////                                                    horizontalArrangement = Arrangement.Center
////                                                ) {
////                                                    Text(
////                                                       it.name,
////                                                        maxLines = 1,
////                                                        modifier = modifier.padding(start = 10.dp, end = 10.dp),
////                                                        color = Color.White
////                                                    )
////                                                }
////                                            }
////                                        }
////                                    }
////
////                                }
////                            }
////
////                        }
//                    }
//                }
                Text("@all rights reserved to VentureNest ", color = Color.LightGray)


            }


            LaunchedEffect(isVisible.value) {
                animationState.targetState = isVisible.value
            }

            // Only show the Dialog when the animation state is visible or was visible
            if ( isVisible.value==true) {
                Dialog(
                    onDismissRequest = { isVisible.value=false

                    },
                    properties = DialogProperties(
                        dismissOnBackPress = true,
                        dismissOnClickOutside = true,
                        usePlatformDefaultWidth = true
                    )
                ) {
                    // Background scrim with fade animation
                    AnimatedVisibility(
                        visibleState = animationState,
                        enter = fadeIn(tween(300)),
                        exit = fadeOut(tween(300))
                    ) {
                        Box(
                            modifier = Modifier
                                .wrapContentHeight()
                                .wrapContentWidth()
                                ,
                            contentAlignment = Alignment.Center
                        ) {
                            // Dialog content with scale animation
                            AnimatedVisibility(
                                visibleState = animationState,
                                enter = scaleIn(tween(300)) + fadeIn(tween(300)),
                                exit = scaleOut(tween(300)) + fadeOut(tween(300))
                            ) {
                                Card(
                                    modifier = Modifier

                                        .fillMaxWidth(0.85f),
                                    shape = RoundedCornerShape(24.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color.White
                                    ),
                                    elevation = CardDefaults.cardElevation(
                                        defaultElevation = 8.dp
                                    )
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(24.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        AsyncImage(
                                            model = image.value,
                                            contentDescription = null,modifier
                                                .height(300.dp)
                                                .fillMaxWidth()
, contentScale = ContentScale.FillWidth
                                        )
                                        if (name.value!=null){
                                            Text(name.value
                                                ,color= Color.Black
                                                , fontWeight = FontWeight.Bold,
                                                fontSize = androidx.compose.material.MaterialTheme.typography.h6.fontSize
                                                , modifier = modifier.fillMaxWidth()
                                                , textAlign = TextAlign.Center)
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier.height(40.dp))


        }
        Column (modifier = modifier.fillMaxSize()
            ,
            verticalArrangement = Arrangement.Top
        , horizontalAlignment = Alignment.CenterHorizontally){
            Row(
                modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(bottom = 20.dp, start = 15.dp, end = 15.dp)
                    .background(Color.White)
                    .padding(bottom = 10.dp)
                    .windowInsetsPadding(window)


                    ,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ElevatedCard(
                    modifier.size(55.dp),
                    shape = CircleShape,
                    colors = CardDefaults.elevatedCardColors(containerColor = Color.White)
                ) {
                    Image(
                        painter = painterResource(R.drawable.img),
                        contentDescription = null,
                        modifier
                            .size(100.dp)
                            .clickable {

                                isNavOpen = !isNavOpen

                            }, contentScale = ContentScale.Crop
                    )
                }

                Row(
                    modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ElevatedCard(
                        modifier
                            .size(40.dp)
                            .clickable { navController.navigate(Profile) },
                        shape = RectangleShape,
                        colors = CardDefaults.elevatedCardColors(containerColor = Color.White)
                    ) {
                        Box(
                            Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
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
        }
            AnimatedVisibility(showSuccess
            , enter = slideInVertically(
                    initialOffsetY = { it },   // from bottom
                    animationSpec = tween(
                        durationMillis = 350,
                        easing = FastOutSlowInEasing
                    )

                )
            ,  exit = slideOutVertically(
                    targetOffsetY = { it },    // to bottom
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    ))) {



                    SuccessStoriesReels(  state.Data.sucessStories
                    ,{showSuccess=false})





            }

        AiDialog(
            show,
            modifier
        )


        EventDialog(show = showEventDialog, event = selectedEvent, modifier = modifier, color = selectedEventColor)

            }  // Close Box
        
    
    
        // ðŸ”¹ OVERLAY (click to close)
        if (isNavOpen) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.4f))
                    .clickable { isNavOpen = false }
            )
        }

        // ðŸ”¹ SLIDING NAV COLUMN
        AnimatedVisibility(
            visible = isNavOpen,
            enter = slideInHorizontally(
                initialOffsetX = { -it }
            ),
            exit = slideOutHorizontally(
                targetOffsetX = { -it }
            )
        ) {
            SideNavigation(
                onClose = { isNavOpen = false }
                , onE = {
                    navController.navigate(AboutECell)
                }
                , onM = {
                    isNavOpen=false
                    showSuccess=true},
                onV = {
                    navController.navigate(AboutVentureNest)
                }
            )
        }
    }
}


@Composable
fun ChatBubbleWithArrow(message: String, isUser: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 16.dp),
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
    ) {
        var displayedText by remember { mutableStateOf("") }

        LaunchedEffect(message) {
            if (!isUser) {
                displayedText = ""
                message.forEachIndexed { index, _ ->
                    displayedText = message.substring(0, index + 1)
                    delay(5) 
                }
            } else {
                displayedText = message
            }
        }

        Box(
            modifier = Modifier
                .widthIn(max = 280.dp)
                .clip(
                    if (isUser) RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp, bottomStart = 20.dp, bottomEnd = 5.dp)
                    else RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp, bottomStart = 5.dp, bottomEnd = 20.dp)
                )
                .background(
                    color = if (isUser) Color.Black else Color(0xFFF3F3F3) 
                )
                .padding(16.dp)
        ) {
            Text(
                text = if (!isUser && message.length > displayedText.length) displayedText else message,
                color = if (isUser) Color.White else Color.Black,
                fontSize = 15.sp,
                lineHeight = 22.sp
            )
        }
    }
}


@Composable
fun SideNavigation(
    onClose: () -> Unit
    ,onV:()-> Unit,
    onE:()-> Unit,
    onM:()-> Unit
) {
    Column(
        modifier = Modifier

            .fillMaxHeight()
            .width(260.dp)
            .background(Color.Transparent)
            .padding(top = 50.dp, bottom = 16.dp, start = 0.dp, end = 16.dp)
    ) {

//        Text(
//            text = "VentureNest",
//            fontSize = 20.sp,
//            fontWeight = FontWeight.Bold
//            , modifier = Modifier.padding(start = 16.dp)
//        )

        Spacer(modifier = Modifier.height(50.dp))

        NavItem("About E-Cell",onE=onE)
        NavItem("About VentureNest",
        onE=onV)
        NavItem("Success Stories",onE=onM)


        Spacer(modifier = Modifier.weight(1f))


    }
}

@Composable
fun NavItem(title: String,onE : () -> Unit) {
    Card (modifier = Modifier
        .padding(top = 15.dp)
        .fillMaxWidth(1f)
        .clickable{
            onE.invoke()
        }
        , shape =RoundedCornerShape(topStart = 0.dp, bottomEnd = 15.dp,
            topEnd = 5.dp, bottomStart = 0.dp)
    , colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.9f))){
        Row(modifier = Modifier.padding(start = 10.dp)
        ) {
    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onE.invoke()
            }
            .padding(vertical = 12.dp),
        fontSize = 16.sp
        , color = Color.DarkGray
        , fontWeight = FontWeight.SemiBold
    )}
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SuccessStoriesReels(
    stories: List<SuccessStories>
    ,onBack: () -> Unit
) {
    val pagerstate = rememberPagerState(
   0,0f,{stories.size}
    )
    VerticalPager(
     state = pagerstate  ,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        SuccessStoryReelItem(story = stories[page]
        , onBack = onBack)
    }
}
@Composable
fun SuccessStoryReelItem(
    story: SuccessStories
    ,onBack:()-> Unit
) {
    BackHandler {
onBack.invoke()
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()

            .fillMaxHeight()

        , contentAlignment = Alignment.Center
    ) {
        Box (modifier = Modifier.fillMaxWidth(

        ).fillMaxHeight(1f)){

        // ðŸ”¹ Background Founder Image
        AsyncImage(
            model = story.FounderImg,
            contentDescription = story.StartupName,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // ðŸ”¹ Dark Gradient Overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.7f)
                        ),
                        startY = 300f
                    )
                )
        )

        // ðŸ”¹ Bottom Content
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            var textShow by remember { mutableStateOf(false) }

            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = story.FounderLogoImg,
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                    , contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = story.StartupName,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = story.StartupAbout,
                color = Color.White,
                fontSize = 14.sp,
                maxLines =if (textShow) 10 else 3,
                overflow = TextOverflow.Ellipsis
                , modifier = Modifier.clickable{
                    textShow= !textShow
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Founded by ${story.FounderImgName}",
                color = Color.LightGray,
                fontSize = 12.sp
            )
        }

        // ðŸ”¹ Right Side Actions (Like Insta)
//        Column(
//            modifier = Modifier
//                .align(Alignment.BottomEnd)
//                .padding(16.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//
//            IconButton(onClick = { /* like */ }) {
//                Icon(
//                    imageVector = if (story.isStarred)
//                        Icons.Filled.Favorite
//                    else Icons.Outlined.FavoriteBorder,
//                    contentDescription = "Like",
//                    tint = Color.White
//                )
//            }
//
//            Spacer(modifier = Modifier.height(12.dp))
//
//            IconButton(onClick = { /* share */ }) {
//                Icon(
//                    imageVector = Icons.Default.Share,
//                    contentDescription = "Share",
//                    tint = Color.White
//                )
//            }
//        }
        }
    }
}

