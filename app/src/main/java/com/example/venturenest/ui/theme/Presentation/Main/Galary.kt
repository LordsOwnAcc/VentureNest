package com.example.venturenest.ui.theme.Presentation.Main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.GridOn
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import kotlinx.coroutines.launch

@Composable
fun GalaryScreen(
    modifier: Modifier = Modifier
    ,window:WindowInsets
) {
    var show by remember {
        mutableStateOf(false)
    }
    var showingContent by remember {
        mutableStateOf(0)
    }
    var coritine = rememberCoroutineScope()

    var gridOFf by remember {
        mutableStateOf(true)
    }

    val peoplePngLinks = listOf(

        "https://img.freepik.com/premium-photo/professional-indian-female-headshots-business-corporate-women_203363-204.jpg",
        "https://img.freepik.com/premium-photo/professional-indian-female-headshots-business-corporate-women_203363-204.jpg",
        "https://img.freepik.com/premium-photo/professional-indian-female-headshots-business-corporate-women_203363-204.jpg",
          "https://www.chitkara.edu.in/ciif/images/slider/seed-fund-banner-new.jpg",
        "https://img.freepik.com/premium-photo/professional-indian-female-headshots-business-corporate-women_203363-204.jpg"
,  "https://img.freepik.com/premium-photo/professional-indian-female-headshots-business-corporate-women_203363-204.jpg",
        "https://img.freepik.com/premium-photo/professional-indian-female-headshots-business-corporate-women_203363-204.jpg",
        "https://img.freepik.com/premium-photo/professional-indian-female-headshots-business-corporate-women_203363-204.jpg",
        "https://img.freepik.com/premium-photo/professional-indian-female-headshots-business-corporate-women_203363-204.jpg"
,  "https://img.freepik.com/premium-photo/professional-indian-female-headshots-business-corporate-women_203363-204.jpg",
        "https://img.freepik.com/premium-photo/professional-indian-female-headshots-business-corporate-women_203363-204.jpg",
        "https://img.freepik.com/premium-photo/professional-indian-female-headshots-business-corporate-women_203363-204.jpg",
        "https://img.freepik.com/premium-photo/professional-indian-female-headshots-business-corporate-women_203363-204.jpg"

    )
    var pagerState = rememberPagerState(initialPage = 1, pageCount = { peoplePngLinks.size })

    Box(
        modifier
            .fillMaxSize()
            .windowInsetsPadding(window)
            .background(
//                Color(0xffffefea)
                Color.White
            ), contentAlignment = Alignment.BottomCenter
    ) {

        Column(
            modifier
                .fillMaxHeight()
                .fillMaxWidth(), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {

//            Row(
//                modifier
//                    .fillMaxWidth(1f)
//                    .fillMaxHeight(0.05f)
//                    .background(Color(0xffdd1212))
//                , verticalAlignment = Alignment.CenterVertically
//                , horizontalArrangement = Arrangement.Center
//            ) {
//
//
//                Row (
//                    modifier
//                        .fillMaxWidth(0.9f)
//                        .fillMaxHeight(1f)
//
//
//                    , verticalAlignment = Alignment.CenterVertically){
//                    AsyncImage(model = "https://www.cgc.ac.in/public/course/assets/images/header-footer/cgc-jhanjeri-logo-white.png", contentDescription = "",
//                        modifier
//                            .fillMaxHeight()
//                            .fillMaxWidth(0.3f))
//                    Row (modifier.fillMaxWidth(0.95f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End){
//                        Text(text = "Galary", color = Color.White, fontSize = MaterialTheme.typography.titleLarge.fontSize, fontWeight = FontWeight.ExtraBold)
//                    }
//
//                }
//
//
//            }

            val schroll = rememberScrollState()
    Column(
        modifier
            .padding(bottom = 44.dp)
            .fillMaxWidth()
            .fillMaxHeight(1f)
        , verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally
            ) {

//
//        ElevatedCard(
//            modifier
//                .fillMaxWidth()
//                .padding(top = 20.dp, bottom = 20.dp, start = 10.dp)
//                .height(200.dp - schroll.value.div(2).dp)
//                .shadow(
//                    elevation = 5.dp,
//                    spotColor = Color.Black,
//                    shape = RectangleShape
//                ), shape = RectangleShape){
//            Row (modifier.fillMaxWidth()){
//Text(modifier = Modifier.padding(start = 10.dp, top = 10.dp, bottom = 10.dp), text = "Featured Photos",
//    textAlign = TextAlign.Start,
//    fontSize = MaterialTheme.typography.headlineSmall.fontSize,
//    fontWeight = FontWeight.SemiBold)
//
//Row (modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.CenterVertically){
//    Text(modifier = Modifier
//        .padding(end = 10.dp, top = 10.dp, bottom = 10.dp)
//        .clickable { }, text = "see all",
//        textAlign = TextAlign.Start,
//        fontSize = MaterialTheme.typography.labelLarge.fontSize,
//        fontWeight = FontWeight.SemiBold, color = Color.Blue)
//
//}
//
//
//            }
//            Success()
//                }
        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.1f)
            ,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(text = "Galary", fontWeight = FontWeight.Bold,
                fontSize =MaterialTheme.typography.titleLarge.fontSize )
            Row(modifier.fillMaxWidth()  ,
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { gridOFf =!gridOFf },modifier.offset(x=10.dp)) {
                    Icon(imageVector = if (!gridOFf) Icons.Default.GridView else Icons.Default.GridOn, contentDescription = "", tint = Color(0xffdd1212))
                }
            }
        }
        ElevatedCard(
            modifier
                .fillMaxWidth(0.95f)
                .padding(bottom = 25.dp)
                .wrapContentHeight()

//                . shadow(
//                    elevation = 5.dp,
//                    spotColor = Color.Black,
//                    shape = RectangleShape
//                ),
                ,shape = RectangleShape
        , colors = CardDefaults.cardColors(containerColor = Color.White)) {

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Adaptive(if (!gridOFf)105.dp else 150.dp),
                verticalItemSpacing = 8.dp,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                content = {
                    items(peoplePngLinks) { photo ->
                        AsyncImage(
                            model = photo,
                            contentScale = ContentScale.Fit,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()

                                .wrapContentHeight()
//                                .border(5.dp, Color.Black, RectangleShape)
                                .clickable {
                                    showingContent = peoplePngLinks.indexOf(photo)

                                    show = true
                                    coritine.launch {
                                        pagerState.scrollToPage(showingContent)
                                    }
                                }
//                                .padding(7.dp)


                        )
                    }
                },
                modifier = Modifier.fillMaxSize()
            )


        }



    }


        }


    }

    AnimatedVisibility(visible = show) {
        androidx.compose.ui.window.Dialog(onDismissRequest = { show = false}, properties = DialogProperties(decorFitsSystemWindows = true, usePlatformDefaultWidth = false)) {
            Column(
                modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color(0, 0, 0, 0))
                    .clickable { show = false }
            , verticalArrangement = Arrangement.Top) {


            Box(
                modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.94f)
                    .background(Color(0, 0, 0, 255))
            ) {
                Column(modifier.fillMaxSize(), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.Start) {
                    IconButton(onClick = { show = false }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "", tint = Color.White)
                    }

                }


                HorizontalPager(
                    flingBehavior = PagerDefaults.flingBehavior(
                        pagerState
                    ),
                    state = pagerState,
                    modifier = modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    pageSpacing = 20.dp, beyondViewportPageCount = 1
                ) { page ->
                    Column(
                        modifier
                            .fillMaxWidth(1f)
                            .fillMaxHeight(0.86f)

                            .clip(RoundedCornerShape(50f))
                            .background(Color.Transparent)
                        , horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.
                        Center
                    ) {
                        var scale by remember { mutableStateOf(1f) }
                        var offsetX by remember { mutableStateOf(0f) }
                        var offsetY by remember { mutableStateOf(0f) }
                        Box(modifier = Modifier.fillMaxSize()

                            , contentAlignment = Alignment.Center){
Text(text = page.toString(), color = Color.White)
                        AsyncImage(
                            model = peoplePngLinks[page],
                            contentDescription = "",

                            modifier = Modifier


                                .clickable {  }
                                .pointerInput(Unit) {
                                    detectTransformGestures { _, pan, zoom, _ ->
                                        scale = (scale * zoom).coerceIn(1f, 5f)
                                        offsetX += pan.x
                                        offsetY += pan.y

                                    }
                                }
                                .graphicsLayer {
                                    scaleX = scale
                                    scaleY = scale
//                                    translationX = offsetX
//                                    translationY = offsetY
                                }
                                .padding(start = 10.dp, end = 10.dp)
                                .clip(RoundedCornerShape(50f))
                                .fillMaxWidth(),
                            clipToBounds = true,
                            contentScale = ContentScale.Fit
                        )
                    }
                    }


                }
            }
        }
        }
    }

}



//@Preview
//@Composable
//private fun PreviewGalaryScreen() {
//    GalaryScreen()
//}