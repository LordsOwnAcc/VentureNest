package com.example.venturenest.ui.theme.Presentation.Gallery

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.material.icons.filled.GridOn
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.venturenest.R
import com.example.venturenest.ui.theme.DaggerHilt.States.GalleryStateCompanion
import com.example.venturenest.ui.theme.DaggerHilt.ViewModels.GallaryViewModel
import com.example.venturenest.ui.theme.DaggerHilt.ViewModels.LoadingStateViewmodel
import com.example.venturenest.ui.theme.Presentation.helper.ChangeStatusBarColorEdgeToEdge
import com.example.venturenest.ui.theme.Presentation.helper.HideSystemBars
import com.example.venturenest.ui.theme.Presentation.helper.ShimmerEffect
import com.example.venturenest.ui.theme.background
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun GalaryScreen(
    modifier: Modifier = Modifier
    ,window:WindowInsets
) {
    HideSystemBars()
    ChangeStatusBarColorEdgeToEdge(background)
    val galleryViewModel : LoadingStateViewmodel= hiltViewModel()
    val state by galleryViewModel.state.collectAsState()
    val galleryViewModel2 : GallaryViewModel=hiltViewModel()
    val state2 by galleryViewModel2.state.collectAsState()


    var show by remember {
        mutableStateOf(false)
    }
    var showingContent by remember {
        mutableStateOf(0)
    }
    var coritine = rememberCoroutineScope()




    Box(
        modifier
            .fillMaxSize()
            .windowInsetsPadding(window)
            .background(
                background

            ), contentAlignment = Alignment.BottomCenter
    ) {






        Column(
            modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(background),
            verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {




    Column(
        modifier
            .padding(bottom = 44.dp)
            .fillMaxWidth()
            .fillMaxHeight(1f)
        , verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally
            ) {


        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.1f)
            ,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(text = "Galary", fontWeight = FontWeight.Medium,
                fontSize =MaterialTheme.typography.titleLarge.fontSize.times(1.3), color = Color.Black )
            Row(modifier.fillMaxWidth()  ,
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { galleryViewModel2.changeGrid()},modifier.offset(x=10.dp)) {
                    Icon(imageVector = if (!state2.isGridSelected) Icons.Default.GridView else Icons.Default.GridOn, contentDescription = "", tint = Color(0xffdd1212))
                }
            }
        }

        ElevatedCard(
            modifier
                .fillMaxWidth(0.95f)
                .padding(bottom = 20.dp)
                .wrapContentHeight()
                ,shape = RectangleShape
            , elevation = CardDefaults.elevatedCardElevation(0.dp)
        , colors = CardDefaults.cardColors(containerColor = background)) {




                    var pagerState = rememberPagerState(initialPage = 1, pageCount = { state.Data.photo.size })
                    LazyVerticalStaggeredGrid(
                        columns = StaggeredGridCells.Adaptive(if (!state2.isGridSelected)105.dp else 150.dp),
                        verticalItemSpacing = 8.dp,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        content = {
                            items(state.Data.photo) { photo ->
                                AsyncImage(
                                    model = photo.imageUrl,
                                    contentScale = ContentScale.Fit,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth()

                                        .wrapContentHeight()
//                                .border(5.dp, Color.Black, RectangleShape)
                                        .clickable {
                                            showingContent = state.Data.photo.indexOf(photo)
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

                                ) {



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
                                                Column(modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
                                                    horizontalAlignment = Alignment.CenterHorizontally) {


                                                    AsyncImage(
                                                        model = state.Data.photo[page].imageUrl,
                                                        contentDescription = "",

                                                        modifier = Modifier


                                                            .clickable { }
                                                            .pointerInput(Unit) {
                                                                detectTransformGestures { _, pan, zoom, _ ->
                                                                    scale =
                                                                        (scale * zoom).coerceIn(
                                                                            1f,
                                                                            5f
                                                                        )
                                                                    offsetX += pan.x
                                                                    offsetY += pan.y

                                                                }
                                                            }
                                                            .graphicsLayer {
                                                                scaleX = scale
                                                                scaleY = scale
                                                            }
                                                            .padding(start = 10.dp, end = 10.dp)
                                                            .clip(RoundedCornerShape(50f))
                                                            .fillMaxWidth(),
                                                        clipToBounds = true,
                                                        contentScale = ContentScale.Fit
                                                    )
                                                    Text(
                                                        text = state.Data.photo[page].photoName,
                                                        color = Color.White,
                                                        maxLines = 1,
                                                        modifier = modifier.padding(top = 20.dp)
                                                    )
                                                }
                                            }
                                        }


                                    }
                                }
                            }
                        }
                    }
                }
            }





        }



    }


        }





