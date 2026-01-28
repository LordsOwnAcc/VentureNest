package com.example.venturenest.ui.theme.Presentation.Gallery

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import coil.compose.AsyncImage
import com.example.venturenest.ui.theme.DaggerHilt.Events
import com.example.venturenest.ui.theme.DaggerHilt.States.GalleryStateCompanion
import com.example.venturenest.ui.theme.DaggerHilt.ViewModels.GalleryViewModel
import com.example.venturenest.ui.theme.DaggerHilt.photo
import com.example.venturenest.ui.theme.Presentation.helper.ChangeStatusBarColorEdgeToEdge
import com.example.venturenest.ui.theme.Presentation.helper.HideSystemBars
import kotlinx.serialization.Serializable

// Define routes for internal Gallery navigation
@Serializable
object GalleryListRoute

@Serializable
data class EventImagesRoute(val eventName: String)

@Composable
fun GalleryScreen(
    window: WindowInsets,
    modifier: Modifier = Modifier
) {
    HideSystemBars()
    ChangeStatusBarColorEdgeToEdge(Color.White)
    
    val galleryViewModel: GalleryViewModel = hiltViewModel()
    val events by galleryViewModel.events.collectAsState()
    val galleryState by galleryViewModel.state.collectAsState()
    
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = GalleryListRoute) {
        composable<GalleryListRoute> {
            GalleryEventList(
                events = events,
                onEventClick = { event ->
                    navController.navigate(EventImagesRoute(event.eventName))
                },
                modifier = modifier,
                window = window
            )
        }
        
        composable<EventImagesRoute> { backStackEntry ->
            val args = backStackEntry.toRoute<EventImagesRoute>()
            
            // Trigger fetch for selected event
            androidx.compose.runtime.LaunchedEffect(args.eventName) {
                galleryViewModel.fetchPhotosForEvent(args.eventName)
            }

            EventImagesScreen(
                eventName = args.eventName,
                photos = galleryState.result, 
                isLoading = galleryState.state is GalleryStateCompanion.Loading,
                error = galleryState.error,
                onBackClick = { navController.popBackStack() },
                window = window
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryEventList(
    events: List<Events>,
    onEventClick: (Events) -> Unit,
    modifier: Modifier,
    window: WindowInsets
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "Gallery", 
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    ) 
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                ),
                windowInsets = window
            )
        },
        containerColor = Color.White
    ) { paddingValues ->
        if (events.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                items(events) { event ->
                    EventThumbnailCard(event = event, onClick = { onEventClick(event) })
                }
            }
        }
    }
}

@Composable
fun EventThumbnailCard(
    event: Events,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.85f)
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = event.imageUrl,
                contentDescription = event.eventName,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.8f)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            )
            
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.2f)
                    .padding(horizontal = 8.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = event.eventName,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Black
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventImagesScreen(
    eventName: String,
    photos: List<photo>,
    isLoading: Boolean,
    error: String?,
    onBackClick: () -> Unit,
    window: WindowInsets
) {
    var selectedPhotoIndex by remember { mutableStateOf<Int?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        eventName, 
                        fontWeight = FontWeight.Medium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black,
                    navigationIconContentColor = Color.Black
                ),
                windowInsets = window
            )
        },
        containerColor = Color.White
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.primary
                )
            } else if (error != null) {
                Text(
                    text = error,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    contentPadding = PaddingValues(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(photos.size) { index ->
                        AsyncImage(
                            model = photos[index].imageUrl,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .aspectRatio(1f)
                                .clip(RoundedCornerShape(4.dp))
                                .clickable { selectedPhotoIndex = index }
                                .background(Color.LightGray)
                        )
                    }
                }
            }
        }
    }

    // Full Screen Image Dialog
    if (selectedPhotoIndex != null) {
        FullScreenImageDialog(
            photos = photos,
            initialPage = selectedPhotoIndex!!,
            onDismiss = { selectedPhotoIndex = null }
        )
    }
}

@Composable
fun FullScreenImageDialog(
    photos: List<photo>,
    initialPage: Int,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            decorFitsSystemWindows = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            val pagerState = rememberPagerState(
                initialPage = initialPage,
                pageCount = { photos.size }
            )

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                ZoomableImage(imageUrl = photos[page].imageUrl)
            }

            // Close button
            IconButton(
                onClick = onDismiss,
                modifier = Modifier
                    .padding(16.dp)
                    .statusBarsPadding()
                    .align(Alignment.TopStart)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Close",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun ZoomableImage(imageUrl: String) {
    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(androidx.compose.ui.geometry.Offset.Zero) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    scale = (scale * zoom).coerceIn(1f, 3f)
                    val newScale = (scale * zoom).coerceIn(1f, 3f)
                    
                    // Reset offset if zoomed out
                    if (newScale == 1f) {
                        offset = androidx.compose.ui.geometry.Offset.Zero
                    } else {
                         offset += pan
                    }
                }
            },
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offset.x,
                    translationY = offset.y
                )
        )
    }
}
