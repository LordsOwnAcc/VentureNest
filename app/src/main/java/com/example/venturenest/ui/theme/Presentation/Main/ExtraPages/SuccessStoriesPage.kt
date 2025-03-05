package com.example.venturenest.ui.theme.Presentation.Main.ExtraPages

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.ArrowRightAlt
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

@Composable
fun SuccessStoriesPage(
    modifier: Modifier = Modifier
    ,navHostController: NavHostController
    ,windowInsets: WindowInsets
) {

//    var rotated by remember { mutableStateOf(false) }
//
//    val rotation by animateFloatAsState(
//        targetValue = if (rotated) 180f else 0f,
//        animationSpec = tween(500), label = ""
//    )
//
//    val animateFront by animateFloatAsState(
//        targetValue = if (!rotated) 1f else 0f,
//        animationSpec = tween(500), label = ""
//    )
//
//    val animateBack by animateFloatAsState(
//        targetValue = if (rotated) 1f else 0f,
//        animationSpec = tween(500), label = ""
//    )
//    val animateColor by animateColorAsState(
//        targetValue = if (rotated) Color.Red else Color.Blue,
//        animationSpec = tween(500), label = ""
//    )
//    //smnfj
//
//    val couritine = rememberCoroutineScope()
//
//    var pagerState = rememberPagerState(initialPage = 0, 0.0f, { peoplePngLinks.size + 1 })
//
//    Column(
//        modifier
//            .windowInsetsPadding(windowInsets)
//            .fillMaxSize()
//            .background(Color.White), horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Top
//    ) {
//
//        Box(
//            modifier
//                .fillMaxWidth()
//                .fillMaxHeight(0.1f), contentAlignment = Alignment.CenterStart
//        ) {
//            Icon(
//                imageVector = Icons.Default.ArrowBack,
//                contentDescription = "",
//                modifier
//                    .padding(start = 10.dp)
//                    .size(40.dp)
//                    .background(Color.White)
//                    .clickable { navHostController.popBackStack() }
//                    .border(2.dp, Color.Black, RectangleShape)
//                    .padding(8.dp),
//                tint = Color.Black
//            )
//            Row(
//                modifier.fillMaxSize(),
//                horizontalArrangement = Arrangement.End,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Text(
//                    text = "Success Stories",
//                    fontSize = MaterialTheme.typography.displaySmall.fontSize,
//                    fontWeight = FontWeight.Black,
//                    letterSpacing = MaterialTheme.typography.titleLarge.letterSpacing,
//                    modifier = modifier.padding(end = 20.dp)
//                )
//            }
//
//        }
//
//        HorizontalPager(
//            state = pagerState, modifier = modifier
//                .fillMaxWidth()
//                .fillMaxHeight(), pageSpacing = -55.dp
//        ) { page ->
//            val offset = pagerState.getOffsetDistanceInPages(page)
//            var realoffset = if (offset > 0) offset else -offset
//            var isDescriptionClicked by rememberSaveable {
//                mutableStateOf(false)
//            }
//
//            Column(
//                modifier
//                    .padding(if (page == peoplePngLinks.size) 80.dp else 0.dp)
//                    .fillMaxWidth()
//                    .fillMaxHeight(0.6f)
//                ,
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center
//            ) {
//
//
//                if (page == peoplePngLinks.size) {
//Row (
//    modifier
//        .fillMaxSize()
//       , horizontalArrangement = Arrangement.Start,
//    verticalAlignment = Alignment.CenterVertically ) {
//
//
//    Icon(
//        imageVector = Icons.Default.ArrowBack,
//        contentDescription = "",
//        modifier.clickable {
//            couritine.launch {
//                pagerState.animateScrollToPage(0)
//            }
//
//
//
//        })
//    Text(text = "Back",modifier.padding(start = 10.dp).clickable {
//        couritine.launch {
//            pagerState.animateScrollToPage(0)
//        }
//    })
//}
//                } else {
//                    Box(
//                        modifier = Modifier
//                            .clip(RoundedCornerShape(50f))
//                            .fillMaxHeight(0.9f - (realoffset / 5))
//                            .fillMaxWidth(0.8f - (realoffset) / 100)
//                            ,
//                        contentAlignment = Alignment.TopEnd
//                    ) {
//
//                        Column(
//                            modifier = modifier
//                            .fillMaxWidth()
//                                .fillMaxHeight()
//                                .border(2.dp, Color.Black, RoundedCornerShape(50f))
//,                            verticalArrangement = Arrangement.Top,
//                            horizontalAlignment = Alignment.CenterHorizontally
//                        ) {
//
//
//                            AsyncImage(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .fillMaxHeight(0.8f)
////                                .clip(RoundedCornerShape(50f))
////                                .fillMaxHeight(0.8f - (realoffset / 5))
////                                .fillMaxWidth(0.8f - (realoffset) / 100)
////                                .background(Color.Black)
//                                ,
//                                model = peoplePngLinks[page].link, contentDescription = "",
//                                contentScale = ContentScale.Crop
//                            )
//
//                            Text(
//                                text = peoplePngLinks[page].title,
//                                maxLines = 1,
//                                fontSize = MaterialTheme.typography.titleLarge.fontSize,
//                                fontWeight = FontWeight.ExtraLight,
//                                lineHeight = MaterialTheme.typography.titleLarge.lineHeight,
//                                letterSpacing = MaterialTheme.typography.titleLarge.letterSpacing
//                            )
//                            Box(
//                                modifier = modifier.fillMaxWidth(),
//                                contentAlignment = Alignment.Center
//                            ) {
//
//
//                                Text(
//                                    text = "Founded by Elon Musk",
//                                    maxLines = 1,
//                                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
//                                    fontWeight = FontWeight.SemiBold,
//                                    lineHeight = MaterialTheme.typography.titleSmall.lineHeight,
//                                    letterSpacing = MaterialTheme.typography.titleSmall.letterSpacing
//                                )
//
//
//                            }
//
//
//
//                        }
//                        Icon(
//                            imageVector = Icons.Default.Bookmark, contentDescription = "", modifier
//                                .padding(end = 10.dp)
//                                .size(40.dp)
//                                .background(Color.White)
//                                .border(2.dp, Color.Black, RectangleShape)
//                                .padding(8.dp),
//                            tint = Color.Black
//                        )
//                    }
//
//                }
//            }
//
//
//        }
//
//
//    }

}
//
//@Preview
//@Composable
//private fun PreviewSuccessStoriesPage() {
//  SuccessStoriesPage()
//}

