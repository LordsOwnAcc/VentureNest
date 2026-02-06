package com.example.venturenest.ui.theme.Presentation.HomePage

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.AnimationState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
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
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
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
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.FavoriteBorder
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
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.venturenest.MeetTheBoardCard
import com.example.venturenest.OurJourneyCard
import com.example.venturenest.R
import com.example.venturenest.StatCard
import com.example.venturenest.stats
import com.example.venturenest.ui.theme.DaggerHilt.States.AiStatesCompanion
import com.example.venturenest.ui.theme.DaggerHilt.States.HomePageCompanion
import com.example.venturenest.ui.theme.DaggerHilt.SuccessStories
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
import com.example.venturenest.ui.theme.Presentation.Setting.AboutEcell
import com.example.venturenest.ui.theme.Presentation.helper.ChangeStatusBarColorEdgeToEdge
import com.example.venturenest.ui.theme.Presentation.helper.HideSystemBars
import com.example.venturenest.ui.theme.Presentation.helper.ShimmerEffect
import com.example.venturenest.ui.theme.background
import com.example.venturenest.ui.theme.bg
import com.example.venturenest.ui.theme.foreground
import com.google.ai.client.generativeai.type.content
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.delay
import java.nio.file.WatchEvent
import kotlin.io.path.Path
import kotlin.io.path.moveTo

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    window: WindowInsets,
    navController: NavController
    , homeViewModel: LoadingStateViewmodel
) {
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
    val schroll = rememberScrollState()
    val infinite = rememberInfiniteTransition()
    val transition by infinite.animateFloat(
        initialValue = 0f, targetValue = 360f, animationSpec = InfiniteRepeatableSpec(
            tween(5000, easing = LinearEasing)
        ), label = ""
    )


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


    }
        // 🔹 OVERLAY (click to close)
        if (isNavOpen) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.4f))
                    .clickable { isNavOpen = false }
            )
        }

        // 🔹 SLIDING NAV COLUMN
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
            .padding(8.dp),
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
            Text(
                text = if (!isUser) displayedText else message,
                color = Color.Black,
                fontSize = 16.sp
            )
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

        // 🔹 Background Founder Image
        AsyncImage(
            model = story.FounderImg,
            contentDescription = story.StartupName,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // 🔹 Dark Gradient Overlay
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

        // 🔹 Bottom Content
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

        // 🔹 Right Side Actions (Like Insta)
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

