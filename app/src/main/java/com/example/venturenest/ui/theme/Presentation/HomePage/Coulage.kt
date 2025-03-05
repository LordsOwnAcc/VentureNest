package com.example.venturenest.ui.theme.Presentation.HomePage

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.venturenest.ui.theme.DaggerHilt.States.CoulageStateCompanion
import com.example.venturenest.ui.theme.DaggerHilt.ViewModels.CoulageVieModel
import com.example.venturenest.ui.theme.Presentation.helper.ShimmerEffect
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CoulageElement(
    modifier: Modifier = Modifier
) {
    val viewModel :CoulageVieModel= hiltViewModel()

    val state by viewModel.state.collectAsState()


    Box(
        modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clip(RoundedCornerShape(50f))
            .background(Color.White)
            .border(3.dp, Color(0xffA30D33), RoundedCornerShape(50f))


    ) {

        when(state.state){
            is CoulageStateCompanion.Loading->{
                Column(
                    modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .clip(RoundedCornerShape(25f))
                        .background(ShimmerEffect()), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    

                }

            }
            is CoulageStateCompanion.Result->{
                val originalItems = state.result
                val items = listOf(

                    *originalItems.toTypedArray(),
                )
                var checkPress by remember {
                    mutableStateOf(0)
                }
                var checkPressCheck by remember {
                    mutableStateOf(0)
                }
                var isReversed by remember { mutableStateOf(false) }

                var isUserInteracting by remember { mutableStateOf(false) }

                val pagerState = rememberPagerState(0, 0f, { items.size })


                LaunchedEffect(Unit) {
                    checkPressCheck = checkPress
                    delay(4000)
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

                                delay(1000)

                                val currentPage = pagerState.currentPage
                                if (currentPage != items.size - 1) {

                                    val nextPage = (currentPage + 1) % items.size
                                    pagerState.animateScrollToPage(nextPage)
                                } else {
                                    val nextPage = 0
                                    pagerState.animateScrollToPage(nextPage)

                                }


                            }
                        } else {
                            // If user interaction occurs, pause scrolling
                            delay(100) // Check every 100ms if user interaction is done
                        }
                    }
                }


                HorizontalPager(
                    flingBehavior = PagerDefaults.flingBehavior(
                        pagerState,
                        pagerSnapDistance = PagerSnapDistance.atMost(2)
                    ),
                    state = pagerState,
                    modifier = modifier
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
                                model = items[page].path,
                                contentDescription = "",
                                modifier = modifier.fillMaxSize(),
                                contentScale = ContentScale.FillBounds
                            )
                        }
                    }
                }

                Box(modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.1f), contentAlignment = Alignment.BottomCenter) {
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
                                        if (pagerState.currentPage == index) Color.Red else Color(
                                            0xffffefea
                                        )
                                    )

                            ) {

                            }
                        }
                    }
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
            }
            is CoulageStateCompanion.Error->{
                LaunchedEffect(key1 = Unit) {
                    while (true) {


                        delay(5000)
                        viewModel.fetchHeroState()
                    }}

                Column(
                    modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .clip(RoundedCornerShape(25f))
                        .background(Color.LightGray), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

                    Text(text = "Unknown Error Occurred")
                    Button(onClick = {viewModel.fetchHeroState() }, shape = RoundedCornerShape(25f), colors =
                    ButtonDefaults.buttonColors(containerColor = Color.White),
                    border = BorderStroke(1.dp, Color.White)
                    ) {
                        Text(text = "Reload", color = Color.Black)
                    }
                }

            }

        }





    }



}


@Preview
@Composable
private fun PreviewCoulageElement() {
    CoulageElement()
}