package com.example.venturenest.ui.theme.Presentation.HomePage


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.venturenest.R
import com.example.venturenest.ui.theme.Presentation.CollageClass
import com.example.venturenest.ui.theme.bg
import kotlinx.coroutines.launch


@Composable
fun AboutEcell(
    modifier: Modifier = Modifier
    ,navController: NavController,
windowInsets: WindowInsets


) {
    val collageClass = listOf(
        CollageClass(
            title = "About E-Cell",
            description = "VentureNest is the first entrepreneurship incubation centre at Chandigarh Group of Colleges, Jhanjeri; founded to promote innovation, business development and entrepreneurship. Located at Block 3 of the campus, VentureNest is essentially one of the most vibrant hubs for entrepreneurship development with the necessary facilities for startup success. We incubate and mentor creativity and innovation enabling idea developers to turn their visions into business successes."   ),
        CollageClass(
            title = "How E-Cell Work",
            description = "VentureNest is the first entrepreneurship incubation centre at Chandigarh Group of Colleges, Jhanjeri; founded to promote innovation, business development and entrepreneurship. Located at Block 3 of the campus, VentureNest is essentially one of the most vibrant hubs for entrepreneurship development with the necessary facilities for startup success. We incubate and mentor creativity and innovation enabling idea developers to turn their visions into business successes"  ),
        CollageClass(
            title = "Mission",
            description = "VentureNest is the first entrepreneurship incubation centre at Chandigarh Group of Colleges, Jhanjeri; founded to promote innovation, business development and entrepreneurship. Located at Block 3 of the campus, VentureNest is essentially one of the most vibrant hubs for entrepreneurship development with the necessary facilities for startup success. We incubate and mentor creativity and innovation enabling idea developers to turn their visions into business successes."  ),
        CollageClass(
            title = "Vision",
            description = "VentureNest is the first entrepreneurship incubation centre at Chandigarh Group of Colleges, Jhanjeri; founded to promote innovation, business development and entrepreneurship. Located at Block 3 of the campus, VentureNest is essentially one of the most vibrant hubs for entrepreneurship development with the necessary facilities for startup success. We incubate and mentor creativity and innovation enabling idea developers to turn their visions into business successes."  )

    )


    var rotated by remember { mutableStateOf(false) }



    val couritine = rememberCoroutineScope()

    var pagerState = rememberPagerState(initialPage = 0, 0.0f, { collageClass.size + 1 })

    Column(
        modifier
            .windowInsetsPadding(windowInsets)
            .fillMaxSize()
            .background(Color.White), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Box(
            modifier
                .fillMaxWidth()
                .fillMaxHeight(0.1f), contentAlignment = Alignment.CenterStart
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "",
                modifier
                    .padding(start = 10.dp)
                    .size(40.dp)
                    .background(Color.White)
                    .clickable {
                        navController.popBackStack()
                    }
                    .border(2.dp, bg, RectangleShape)
                    .padding(8.dp),
                tint = bg
            )
            Row(
                modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
//                Text(
//                    text = "VentureNest",
//                    fontSize = MaterialTheme.typography.bodyLarge.fontSize.times(1.3),
//                    fontWeight = FontWeight.Black,
//                    letterSpacing = MaterialTheme.typography.titleLarge.letterSpacing,
//                    modifier = modifier.padding(end = 20.dp)
//                )
            }

        }

        HorizontalPager(
            state = pagerState, modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(), pageSpacing = -55.dp
        ) { page ->
            val offset = pagerState.getOffsetDistanceInPages(page)
            var realoffset = if (offset > 0) offset else -offset


            Column(
                modifier
                    .padding(if (page == collageClass.size) 80.dp else 0.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
                ,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {


                if (page == collageClass.size) {
                    Row (
                        modifier
                            .fillMaxSize()
                        , horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically ) {
                        Button(onClick = {
                            couritine.launch {
                                pagerState.animateScrollToPage(0)
                            }
                        }, shape = RoundedCornerShape(25f),
                            colors = ButtonDefaults.buttonColors(containerColor = bg)
                        ) {
                            Text(text = "Back")
                        }
                    }
                } else {

                    Box(modifier = modifier
                        .padding(top = 10.dp, bottom = 10.dp)
                        .fillMaxHeight(0.9f - (realoffset / 5))
                        .fillMaxWidth(0.8f - (realoffset) / 100)
                        .clip(RoundedCornerShape(25f))
                    ){
                        Image(painter = painterResource(id = R.drawable.whatsapp),
                            contentDescription = "",
                            modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            contentScale = ContentScale.FillBounds)


                        Column(
                            modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                            ,
                            verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp, bottom = 5.dp),
                                text = collageClass[page].title,
                                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                                , color = Color.White
                            )


                            Text(
                                modifier = Modifier.fillMaxWidth(0.9f),
                                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                textAlign = TextAlign.Center,
                                color = Color.White,

                                text = collageClass[page].description)}
                    }


                }
            }


        }


    }

}
