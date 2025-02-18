package com.example.venturenest.ui.theme.Presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CoulageElement(
    modifier: Modifier = Modifier
) {
    val originalItems = listOf(
        "https://www.chitkara.edu.in/ciif/images/slider/seed-fund-banner-new.jpg",
        "https://www.chitkara.edu.in/ciif/images/slider/tech-startups-banner.jpg"
    )
    val items = listOf(

        *originalItems.toTypedArray(), // Original items
    )
    var checkPress by remember {
        mutableStateOf(0)
    }
    var checkPressCheck by remember {
        mutableStateOf(0)
    }
    var isReversed by remember { mutableStateOf(false) }
    // Control auto-scrolling
    var isUserInteracting by remember { mutableStateOf(false) }

    val pagerState = rememberPagerState(1, 0f, { items.size })
    LaunchedEffect(Unit) {
        checkPressCheck = checkPress
        delay(2000)
        if (checkPressCheck == checkPress) {
            isUserInteracting = false
            checkPress = 0
            checkPressCheck = 0
        }

    }


    LaunchedEffect(Unit) {
        while (true) {
            if (!isUserInteracting) {
                while (true) {

                    delay(1000) // Delay between scroll

                    val currentPage = pagerState.currentPage
                    if (currentPage != items.size - 2) {


                        val nextPage = (currentPage + 1) % items.size
                        pagerState.animateScrollToPage(nextPage)
                    } else {
                        val nextPage = 1
                        pagerState.animateScrollToPage(nextPage)

                    }


                }
            } else {
                // If user interaction occurs, pause scrolling
                delay(100) // Check every 100ms if user interaction is done
            }
        }
    }

    Box(
        modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clip(RoundedCornerShape(50f))
            .background(Color.White)
            .border(3.dp, Color(0xffA30D33), RoundedCornerShape(50f))


    ) {

        HorizontalPager(
            flingBehavior = PagerDefaults.flingBehavior(
                pagerState,
                pagerSnapDistance = PagerSnapDistance.atMost(2)
            ),
            state = pagerState,
            modifier =   modifier
                .fillMaxWidth()
                   .fillMaxHeight(),
            pageSpacing = 0.dp, beyondViewportPageCount = 1
        ) { page ->
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp),
            ) {
                Box(
                    modifier = modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = items[page],
                        contentDescription = "",
                        modifier = modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )
                }
            }
        }

//Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter){
//    Row (
//        modifier
//            .fillMaxWidth(0.5f)
//            .offset(y = -25.dp)
//            .height(15.dp)
//            .background(Color.Cyan)
//
//    , verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly){
// items.forEach {
//
//
// }
//
//    }
//
//
//}

        Box(modifier = modifier.fillMaxWidth().fillMaxHeight(0.1f), contentAlignment = Alignment.BottomCenter) {
            Row(
                modifier
                    .padding(bottom = 25.dp)
                    .padding(top = 170.dp)
                    .fillMaxWidth(0.8f)
                    .height(0.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                items.forEachIndexed { index, s ->
                    Row(
                        modifier
                            .padding(start = 5.dp, end = 5.dp)
                            .clip(CircleShape)
                            .width(10.dp)
                            .height(0.dp)
                            .background(
                                if (pagerState.currentPage == index) Color.Red else Color(0xffffefea)
                            )

                    ) {

                    }
                }
            }
        }


    }

    // Handle user interaction to pause auto-scrolling
    modifier.pointerInput(Unit) {
        detectTapGestures(
            onPress = {
                isUserInteracting = true
                while (true) {
                    delay(1000)
                    checkPress++
                }
            }, onTap = {

                isUserInteracting = false

            }
        )
    }
}


@Preview
@Composable
private fun PreviewCoulageElement() {
    CoulageElement()
}