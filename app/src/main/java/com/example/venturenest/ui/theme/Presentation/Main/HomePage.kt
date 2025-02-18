package com.example.venturenest.ui.theme.Presentation.Main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.venturenest.R
import com.example.venturenest.ui.theme.Presentation.CoulageElement
import com.example.venturenest.ui.theme.Presentation.NewHomePage
import com.example.venturenest.ui.theme.Presentation.ServicesElement

@Composable
fun HomePage(
    modifier: Modifier = Modifier
    ,
    window: WindowInsets
) {
    val items = listOf(
        "MENTORING",
        "CONNECT TO GRANTS",
        "INCUBATION SPACE",
        "INDUSTRY/INVESTOR CONNECTS"
    )
    val programList = listOf(
        "Structured Mentoring Program" to "Mentoring increases companies' odds of success. We understand the journey and advise accordingly.",
        "Focus on Student Startups" to "The idea is to encourage innovation and creativity. There is no time and age to build an idea, so you can start early.",
        "Focus on Industry Network" to "The notion is to foster continuous and sustained engagement between large corporates and innovative technology ventures.",
        "Provision of Seed Fund" to "Incubator provides seed funding to help startups in developing their business idea, create a prototype, conduct market research, and cover initial expenses.",
        "Subsidized Incubation Space" to "We help your startup to create, develop, and design by providing an office space, where you and your team can brainstorm and work together as one unit.",
        "Focused Acceleration Program" to "We help the growth and development of entrepreneurial talent in youth. The acceleration program equips young startups with the tools and skills."
    )

    val schroll = rememberScrollState()
//    var reverse by remember {
//        mutableStateOf(false)
//    }
//    var value by remember {
//        mutableStateOf(0)
//    }
    // made to increment value variable from 0-10 with 2s delay
//    LaunchedEffect(Unit) {
//        while (true) {
//            if (value == 0) {
//                reverse = true
//            }
//            if (value < 10 && reverse) {
//                delay(2000)
//                value++
//            } else {
//                reverse = false
//                delay(2000)
//                value--
//            }
//
//        }
//    }
//    var selected by remember {
//        mutableStateOf(0)
//    }
    var selected1 = remember {
        mutableStateOf(false)
    }

    Column(
        modifier
            .windowInsetsPadding(window)
            .fillMaxSize(1f)
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier
                .padding(top = 10.dp, bottom = 10.dp)
                .fillMaxWidth()
                .fillMaxHeight(0.1f)
               ,
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center
        ) {


            Row(
                modifier
                    .fillMaxWidth(0.9f)

                    , verticalAlignment = Alignment.CenterVertically
            ) {
                
Row(modifier = modifier
    .fillMaxHeight()
    .padding(start = 0.dp),
    horizontalArrangement = Arrangement.Start,
    verticalAlignment = Alignment.CenterVertically) {
    Box(modifier = modifier.fillMaxHeight(), contentAlignment = Alignment.BottomCenter) {

Column(modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {


    Image(
        painter = painterResource(id = R.drawable.preview),
        contentDescription = "",
        modifier

            .size(0.dp)
            .clip(CircleShape)
            .border(2.dp, Color.Black, CircleShape), contentScale = ContentScale.Crop
    )
}

    }
    Column (modifier = modifier
        .padding(start = 5.dp)
        .fillMaxHeight()
        .fillMaxWidth(0.6f),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center){


        Text(
            text = "Pragyansh ",
            color = Color.Black,
            lineHeight = MaterialTheme.typography.bodyMedium.lineHeight,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            fontWeight = FontWeight.ExtraLight
        )
        Text(
            text = "Technical",
            color = Color.Black,
            lineHeight = MaterialTheme.typography.bodySmall.lineHeight,

            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            letterSpacing = MaterialTheme.typography.bodyLarge.letterSpacing,
            fontWeight = FontWeight.Medium
        )
    }
//    Text(text = "DashBoard", modifier.padding(top = 10.dp), color = Color.Black,
//    lineHeight = MaterialTheme.typography.bodyMedium.lineHeight,
//    fontSize = MaterialTheme.typography.titleLarge.fontSize,
//    fontWeight = FontWeight.SemiBold
//    )
}

//                Image(painter = painterResource(id = R.drawable.img), contentDescription ="",    modifier
//                    .fillMaxHeight()
//                    .graphicsLayer {
//                        scaleX = 1.5f
//                        scaleY=1.5f
//                    }
//                    .fillMaxWidth(0.3f) )
                Row(
                    modifier.fillMaxWidth(0.95f),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.End
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.img),
                        contentDescription = "",
                        modifier

                            .size(80.dp)
                            .offset(20.dp)





                        , contentScale = ContentScale.Crop
                    )
                }

            }


        }
        Column(
            modifier
                .padding(bottom = 20.dp)
                .fillMaxWidth(0.91f)
                .height(200.dp)) {
            CoulageElement(modifier.height(200.dp))

        }



        Column(
            modifier
                .padding(top = 0.dp, bottom = 60.dp)
                .clip(RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp))
                .fillMaxWidth(1f)
                .fillMaxHeight()
                .verticalScroll(schroll)
                .background(Color.White)

              ,
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //how we work
            Column(
                modifier
                    .padding(top = 0.dp)
                    .fillMaxWidth()
                    .wrapContentHeight() ,
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally) {

                Column(
                    modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(Color.White),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 0.dp),
                        text = "About Us",
                        fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(0.9f),
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        textAlign = TextAlign.Center,
                        text = "Welcome to CGC VentureNest, the premier incubator at CGC Jhanjeri, dedicated to fostering innovation, entrepreneurship," +
                                " and sustainable growth. We provide startups and aspiring entrepreneurs with resources, mentorship, networking, and funding to" +
                                " transform ideas into successful ventures."
                    )


                }
            }

//
//                Text(modifier = Modifier
//                    .padding(start = 15.dp)
//                    .fillMaxWidth(),
//                     text = "How We Work" ,
//                    textAlign = TextAlign.Center,
//                    fontSize = MaterialTheme.typography.headlineSmall.fontSize,
//                    fontWeight = FontWeight.SemiBold)
//
//
//
//
//            Row(
//                modifier
//                    .clip(RoundedCornerShape(topEnd = 50f, topStart = 50f))
//                    .background(Color.White)
//                    .padding(top = 10.dp, bottom = 0.dp)
//                    .fillMaxWidth(0.95f)
//
//                    .height(150.dp), horizontalArrangement = Arrangement.SpaceAround,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//
//                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.successs))
//                val progress by animateLottieCompositionAsState(
//                    composition,
//                    iterations = 500,
//                    clipSpec = LottieClipSpec.Progress(0.3f, 1f)
//                )
//
//
//                ElevatedCard(
//                    modifier
//                        .fillMaxHeight(0.9f)
//                        .shadow(elevation = 5.dp, shape = RectangleShape, spotColor = Color.Black)
//                        .fillMaxWidth(0.28f),
//                    shape = RectangleShape,
//                    colors = CardDefaults.cardColors(containerColor = Color.White)
//                ) {
////                    Box() {
////                        Column(modifier.fillMaxSize()) {
////                            LottieAnimation(
////                                composition = composition,
////                                progress = { progress },
////                                modifier
////                                    .fillMaxWidth()
////                                    .fillMaxHeight()
////                                    .alpha(0.3f)
////
////                            )
////                        }
//
//                    Column(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .fillMaxWidth()
//                        //  .background(Color(255, 26, 29, 50))
//                        ,
//                        verticalArrangement = Arrangement.SpaceEvenly,
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        Icon(
//                            painter = painterResource(id = R.drawable.teacher_professor_avatar_svgrepo_com),
//                            contentDescription = "", tint = Color.Red,
//                            modifier = Modifier
//
//                                .fillMaxHeight(0.4f)
//                                .fillMaxWidth(0.6f)
//                                .padding(top = 5.dp)
//                        )
//
//
//                        Text(
//                            text = "STRUCTURED MENTORING PROGRAM",
//                            fontSize = MaterialTheme.typography.titleSmall.fontSize,
//                            color = Color.Black,
//                            fontWeight = FontWeight.ExtraLight,
//                            textAlign = TextAlign.Center,
//                            lineHeight = MaterialTheme.typography.bodyMedium.lineHeight,
//                            modifier = Modifier.padding(bottom = 0.dp),
//                            fontFamily = FontFamily.Serif
//                        )
////                                                        Text(modifier = modifier.fillMaxWidth(0.9f) ,text = "We help your startup to create, develop and design by providing an office space, where you and your team can brainstorm and work together as one unit", textAlign = TextAlign.Center, fontSize = MaterialTheme.typography.labelSmall.fontSize, lineHeight = MaterialTheme.typography.bodySmall.lineHeight)
//
//
//                    }
//
//
//                    //}
//
//                }
//
//                val composition2 by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.partner2))
//                val progress2 by animateLottieCompositionAsState(composition2, iterations = 500)
//
//                ElevatedCard(
//                    modifier
//                        .fillMaxHeight(0.9f)
//                        .shadow(elevation = 5.dp, shape = RectangleShape, spotColor = Color.Black)
//
//                        .fillMaxWidth(0.42f),
//                    shape = RectangleShape,
//                    colors = CardDefaults.cardColors(containerColor = Color.White)
//
//                ) {
////                    Box() {
////                        Column(modifier.fillMaxSize()) {
////                            LottieAnimation(
////                                composition = composition2,
////                                progress = { progress2 },
////                                modifier
////                                    .fillMaxWidth()
////                                    .fillMaxHeight()
////                                    .alpha(0.2f)
////
////                            )
////                        }
//                    Column(
//                        modifier = Modifier
//                            .fillMaxSize()
//                        //    .background(Color(255, 26, 29, 50))
//                        ,
//                        verticalArrangement = Arrangement.SpaceEvenly,
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        Icon(
//                            painter = painterResource(id = R.drawable.campaign_launch_startup_rocket_svgrepo_com),
//                            contentDescription = "", tint = Color.Red,
//                            modifier = Modifier
//                                .fillMaxHeight(0.4f)
//                                .fillMaxWidth(0.6f)
//                                .padding(top = 5.dp)
//                        )
//
//
//                        Text(
//                            text = "FOCUS ON STUDENT STARTUPS",
//                            fontSize = MaterialTheme.typography.titleSmall.fontSize,
//                            color = Color.Black,
//                            fontWeight = FontWeight.ExtraLight,
//                            textAlign = TextAlign.Center,
//                            lineHeight = MaterialTheme.typography.bodyMedium.lineHeight,
//                            modifier = Modifier.padding(bottom = 0.dp),
//                            fontFamily = FontFamily.Serif
//                        )
////                            Text(modifier = modifier.fillMaxWidth(0.9f), text = "The idea is to encourage innovation and creativity there is no time and age to build an idea, so you can start early.", textAlign = TextAlign.Center, fontSize = MaterialTheme.typography.labelSmall.fontSize, lineHeight = MaterialTheme.typography.bodySmall.lineHeight)
//
//
//                        //}
//                    }
//                }
//
//                ElevatedCard(
//                    modifier
//                        .fillMaxHeight(0.9f)
//                        .shadow(elevation = 5.dp, shape = RectangleShape, spotColor = Color.Black)
//
//                        .fillMaxWidth(0.7f),
//                    shape = RectangleShape,
//                    colors = CardDefaults.cardColors(containerColor = Color.White)
//                ) {
////                    Box() {
////                        Column(modifier.fillMaxSize()) {
////                            LottieAnimation(
////                                composition = composition2,
////                                progress = { progress2 },
////                                modifier
////                                    .fillMaxWidth()
////                                    .fillMaxHeight()
////                                    .alpha(0.2f)
////
////                            )
////                        }
//                    Column(
//                        modifier = Modifier
//                            .fillMaxSize()
//                        //    .background(Color(255, 26, 29, 50))
//                        ,
//                        verticalArrangement = Arrangement.SpaceEvenly,
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        Icon(
//                            painter = painterResource(id = R.drawable.speed_svgrepo_com),
//                            contentDescription = "", tint = Color.Red,
//                            modifier = Modifier
//                                .fillMaxHeight(0.4f)
//                                .fillMaxWidth(0.6f)
//                        )
//
//
//                        Text(
//                            text = "FOCUSED ACCELERATION PROGRAM",
//                            fontSize = MaterialTheme.typography.titleSmall.fontSize,
//                            color = Color.Black,
//                            fontWeight = FontWeight.ExtraLight,
//                            textAlign = TextAlign.Center,
//                            lineHeight = MaterialTheme.typography.bodyMedium.lineHeight,
//                            modifier = Modifier.padding(bottom = 0.dp),
//                            fontFamily = FontFamily.Serif
//                        )
////                            Text(modifier = modifier.fillMaxWidth(0.9f), text = "The idea is to encourage innovation and creativity there is no time and age to build an idea, so you can start early.", textAlign = TextAlign.Center, fontSize = MaterialTheme.typography.labelSmall.fontSize, lineHeight = MaterialTheme.typography.bodySmall.lineHeight)
//
//
//                        //}
//                    }
//                }
//            }
//            Row(
//                modifier
//                    .clip(RoundedCornerShape(topEnd = 50f, topStart = 50f))
//                    .background(Color.White)
//                    .padding(top = 0.dp)
//                    .fillMaxWidth(0.95f)
//
//                    .height(150.dp), horizontalArrangement = Arrangement.SpaceAround,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//
//                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.successs))
//                val progress by animateLottieCompositionAsState(
//                    composition,
//                    iterations = 500,
//                    clipSpec = LottieClipSpec.Progress(0.3f, 1f)
//                )
//
//
//                ElevatedCard(
//                    modifier
//                        .fillMaxHeight(0.9f)
//                        .shadow(elevation = 5.dp, shape = RectangleShape, spotColor = Color.Black)
//                        .fillMaxWidth(0.28f),
//                    shape = RectangleShape,
//                    colors = CardDefaults.cardColors(containerColor = Color.White)
//                ) {
////                    Box() {
////                        Column(modifier.fillMaxSize()) {
////                            LottieAnimation(
////                                composition = composition,
////                                progress = { progress },
////                                modifier
////                                    .fillMaxWidth()
////                                    .fillMaxHeight()
////                                    .alpha(0.3f)
////
////                            )
////                        }
//
//                    Column(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .fillMaxWidth()
//                        //  .background(Color(255, 26, 29, 50))
//                        ,
//                        verticalArrangement = Arrangement.SpaceEvenly,
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        Icon(
//                            painter = painterResource(id = R.drawable.growth_svgrepo_com),
//                            contentDescription = "", tint = Color.Red,
//                            modifier = Modifier
//                                .fillMaxHeight(0.4f)
//                                .fillMaxWidth(0.6f)
//                        )
//
//
//                        Text(
//                            text = "PROVISION OF SEED FUND",
//                            fontSize = MaterialTheme.typography.titleSmall.fontSize,
//                            color = Color.Black,
//                            fontWeight = FontWeight.ExtraLight,
//                            textAlign = TextAlign.Center,
//                            lineHeight = MaterialTheme.typography.bodyMedium.lineHeight,
//                            modifier = Modifier.padding(bottom = 0.dp),
//                            fontFamily = FontFamily.Serif
//                        )
////                                                        Text(modifier = modifier.fillMaxWidth(0.9f) ,text = "We help your startup to create, develop and design by providing an office space, where you and your team can brainstorm and work together as one unit", textAlign = TextAlign.Center, fontSize = MaterialTheme.typography.labelSmall.fontSize, lineHeight = MaterialTheme.typography.bodySmall.lineHeight)
//
//
//                    }
//
//
//                    //}
//
//                }
//
//                val composition2 by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.partner2))
//                val progress2 by animateLottieCompositionAsState(composition2, iterations = 500)
//
//                ElevatedCard(
//                    modifier
//                        .fillMaxHeight(0.9f)
//                        .shadow(elevation = 5.dp, shape = RectangleShape, spotColor = Color.Black)
//
//                        .fillMaxWidth(0.42f),
//                    shape = RectangleShape,
//                    colors = CardDefaults.cardColors(containerColor = Color.White)
//
//                ) {
////                    Box() {
////                        Column(modifier.fillMaxSize()) {
////                            LottieAnimation(
////                                composition = composition2,
////                                progress = { progress2 },
////                                modifier
////                                    .fillMaxWidth()
////                                    .fillMaxHeight()
////                                    .alpha(0.2f)
////
////                            )
////                        }
//                    Column(
//                        modifier = Modifier
//                            .fillMaxSize()
//                        //    .background(Color(255, 26, 29, 50))
//                        ,
//                        verticalArrangement = Arrangement.SpaceEvenly,
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        Icon(
//                            painter = painterResource(id = R.drawable.hands_helping_solid_svgrepo_com),
//                            contentDescription = "", tint = Color.Red,
//                            modifier = Modifier
//                                .fillMaxHeight(0.4f)
//                                .fillMaxWidth(0.6f)
//                        )
//
//
//                        Text(
//                            text = "PROVISION OF SUBSIDIZED INCUBATION",
//                            fontSize = MaterialTheme.typography.titleSmall.fontSize,
//                            color = Color.Black,
//                            fontWeight = FontWeight.ExtraLight,
//                            textAlign = TextAlign.Center,
//                            lineHeight = MaterialTheme.typography.bodyMedium.lineHeight,
//                            modifier = Modifier.padding(bottom = 0.dp),
//                            fontFamily = FontFamily.Serif
//                        )
////                            Text(modifier = modifier.fillMaxWidth(0.9f), text = "The idea is to encourage innovation and creativity there is no time and age to build an idea, so you can start early.", textAlign = TextAlign.Center, fontSize = MaterialTheme.typography.labelSmall.fontSize, lineHeight = MaterialTheme.typography.bodySmall.lineHeight)
//
//
//                        //}
//                    }
//                }
//
//                ElevatedCard(
//                    modifier
//                        .fillMaxHeight(0.9f)
//                        .shadow(elevation = 5.dp, shape = RectangleShape, spotColor = Color.Black)
//
//                        .fillMaxWidth(0.7f),
//                    shape = RectangleShape,
//                    colors = CardDefaults.cardColors(containerColor = Color.White)
//                ) {
////                    Box() {
////                        Column(modifier.fillMaxSize()) {
////                            LottieAnimation(
////                                composition = composition2,
////                                progress = { progress2 },
////                                modifier
////                                    .fillMaxWidth()
////                                    .fillMaxHeight()
////                                    .alpha(0.2f)
////
////                            )
////                        }
//                    Column(
//                        modifier = Modifier
//                            .fillMaxSize()
//                        //    .background(Color(255, 26, 29, 50))
//                        ,
//                        verticalArrangement = Arrangement.SpaceEvenly,
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        Icon(
//                            painter = painterResource(id = R.drawable.studentid_svgrepo_com),
//                            contentDescription = "", tint = Color.Red,
//                            modifier = Modifier
//                                .fillMaxHeight(0.4f)
//                                .fillMaxWidth(0.6f)
//                        )
//
//
//                        Text(
//                            text = "FOCUS ON STUDENT STARTUPS",
//                            fontSize = MaterialTheme.typography.titleSmall.fontSize,
//                            color = Color.Black,
//                            fontWeight = FontWeight.ExtraLight,
//                            textAlign = TextAlign.Center,
//                            lineHeight = MaterialTheme.typography.bodyMedium.lineHeight,
//                            modifier = Modifier.padding(bottom = 0.dp),
//                            fontFamily = FontFamily.Serif
//                        )
////                            Text(modifier = modifier.fillMaxWidth(0.9f), text = "The idea is to encourage innovation and creativity there is no time and age to build an idea, so you can start early.", textAlign = TextAlign.Center, fontSize = MaterialTheme.typography.labelSmall.fontSize, lineHeight = MaterialTheme.typography.bodySmall.lineHeight)
//
//
//                        //}
//                    }
//                }
//            }
//
//        }



//        Spacer(modifier = modifier.height(200.dp))
//PartnerPage()


//            val composition2 by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.work))
//                val progress2 by animateLottieCompositionAsState(composition2, iterations = 500)
//
//            Card(
//                modifier
//                    .padding(top = 20.dp, bottom = 20.dp)
//                    .wrapContentHeight()
//
//                    .fillMaxWidth(0.9f), colors = CardDefaults.cardColors(containerColor = Color.White)
//                , shape = RectangleShape
//            ) {
//
//
//                Column(
//                    modifier
//                        .fillMaxWidth()
//                        .wrapContentHeight()) {
//                  Row (modifier.fillMaxWidth()){
//                     Box(modifier = modifier) {
//LottieAnimation(composition = composition2, progress = { progress2 },modifier.fillMaxWidth(0.5f))
//
//                         Column(
//                             modifier
//                                 .fillMaxWidth(0.5f)
//                                 .wrapContentHeight()
//                         ) {
//
//
//                             Text(
//                                 text = "STRUCTURED MENTORING PROGRAM",
//                                 textAlign = TextAlign.Center,
//                                 fontSize = MaterialTheme.typography.titleMedium.fontSize,
//                                 fontWeight = FontWeight.SemiBold
//                             )
//                             Text(
//                                 text = "Mentoring increases companies odds of success. We understand the journey and advise accordingly.",
//                                 textAlign = TextAlign.Center,
//                                 fontSize = MaterialTheme.typography.labelSmall.fontSize,
//                                 lineHeight = MaterialTheme.typography.bodySmall.lineHeight
//                             )
//                         }
//                     }
//                         Column(
//                             modifier
//                                 .fillMaxWidth()
//                                 .wrapContentHeight()
//                         ) {
//                             Text(
//                                 text = "FOCUS ON STUDENT STARTUPS",
//                                 textAlign = TextAlign.Center,
//                                 fontSize = MaterialTheme.typography.titleMedium.fontSize,
//                                 fontWeight = FontWeight.SemiBold
//                             )
//                             Text(
//                                 text = "The idea is to encourage innovation and creativity there is no time and age to build an idea, so you can start early.",
//                                 textAlign = TextAlign.Center,
//                                 fontSize = MaterialTheme.typography.labelSmall.fontSize,
//                                 lineHeight = MaterialTheme.typography.bodySmall.lineHeight
//                             )
//
//                         }
//
//                  }
//                    Row (
//                        modifier
//                            .fillMaxWidth()
//                            .padding(top = 10.dp)){
//                        Column (
//                            modifier
//                                .fillMaxWidth(0.5f)
//                                .wrapContentHeight()
//                               ){
//                            Text(text = "FOCUS ON INDUSTRY NETWORK", textAlign = TextAlign.Center, fontSize = MaterialTheme.typography.titleMedium.fontSize, fontWeight = FontWeight.SemiBold)
//                            Text(text = "The notion is to foster continuous and sustained engagement between large corporates and innovative technology ventures.", textAlign = TextAlign.Center, fontSize = MaterialTheme.typography.labelSmall.fontSize, lineHeight = MaterialTheme.typography.bodySmall.lineHeight)
//                        }
//                        Column(
//                            modifier
//                                .fillMaxWidth()
//                                .wrapContentHeight()
//                              ) {
//                            Text(text = "PROVISION OF SEED FUND", textAlign = TextAlign.Center, fontSize = MaterialTheme.typography.titleMedium.fontSize, fontWeight = FontWeight.SemiBold)
//                            Text(text = "Incubator provides seed funding to help startups in developing their business idea, create a prototype, conduct market research, and cover initial expenses.", textAlign = TextAlign.Center, fontSize = MaterialTheme.typography.labelSmall.fontSize, lineHeight = MaterialTheme.typography.bodySmall.lineHeight)
//
//                        }
//                    }
//                    Row (
//                        modifier
//                            .fillMaxWidth()
//                            .padding(top = 10.dp)){
//                        Column (
//                            modifier
//                                .fillMaxWidth(0.5f)
//                                .wrapContentHeight()
//                               ){
//                            Text(text = "PROVISION OF SUBSIDIZED INCUBATION SPACE", textAlign = TextAlign.Center,  fontSize = MaterialTheme.typography.titleMedium.fontSize, fontWeight = FontWeight.SemiBold)
//                            Text(text = "We help your startup to create, develop and design by providing an office space, where you and your team can brainstorm and work together as one unit", textAlign = TextAlign.Center, fontSize = MaterialTheme.typography.labelSmall.fontSize, lineHeight = MaterialTheme.typography.bodySmall.lineHeight)
//                        }
//                        Column(
//                            modifier
//                                .fillMaxWidth()
//                                .wrapContentHeight()
//                               ) {
//                            Text(text = "FOCUSED ACCELERATION PROGRAM", textAlign = TextAlign.Center, fontSize = MaterialTheme.typography.titleMedium.fontSize, fontWeight = FontWeight.SemiBold)
//                            Text(text = "We help the growth and development of entrepreneurial talent in youth. Acceleration program equips young startups with the tools and skills.", textAlign = TextAlign.Center, fontSize = MaterialTheme.typography.labelSmall.fontSize, lineHeight = MaterialTheme.typography.bodySmall.lineHeight)
//
//                        }
//                    }
//                }
//            }
            var schroll2 = rememberScrollState()
var schroll3 = rememberScrollState()
            Column(
                modifier
                    .fillMaxWidth(0.91f)
                    .wrapContentHeight()) {
                Text(modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                    text = "Strategy",
                    textAlign = TextAlign.Start,
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                    fontWeight = FontWeight.SemiBold)
                Row (
                    modifier
                        .wrapContentHeight()
                        .fillMaxWidth(1f)
                        .horizontalScroll(schroll2)){
                    programList.forEachIndexed { index, pair ->
                        NewHomePage(title = pair.first, description =pair.second )
                    }

                }
            }
            Column(
                modifier
                    .fillMaxWidth(0.9f)
                    .wrapContentHeight()) {
                Text(modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                    text = "Services",
                    textAlign = TextAlign.Start,
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                    fontWeight = FontWeight.SemiBold)
                Row (
                    modifier
                        .wrapContentHeight()
                        .fillMaxWidth(1f)
                        .horizontalScroll(
                            schroll3
                        )){
                   items.forEachIndexed { index, item ->
                       ServicesElement(tittle = item)
                    }

                }
            }



        }


    }




}

//@Preview
//@Composable
//private fun HomeSp() {
//    HomePage()
//}





//1000