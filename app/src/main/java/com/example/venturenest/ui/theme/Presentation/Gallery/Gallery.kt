package com.example.venturenest.ui.theme.Presentation.Gallery

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.ViewList
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.example.venturenest.ui.theme.DaggerHilt.States.GalleryStateCompanion
import com.example.venturenest.ui.theme.DaggerHilt.ViewModels.GalleryViewModel
import com.example.venturenest.ui.theme.DaggerHilt.photo
import com.example.venturenest.ui.theme.Presentation.helper.ChangeStatusBarColorEdgeToEdge
import com.example.venturenest.ui.theme.Presentation.helper.HideSystemBars
import androidx.compose.ui.geometry.Offset


// Color Palette
private val PrimaryDark = Color(0xFF1A1A2E)
private val SecondaryDark = Color(0xFF16213E)
private val AccentPurple = Color.Black      //Color(0xFF7B68EE)
private val AccentBlue = Color(0xFF4A90D9)
private val SurfaceLight = Color(0xFFF8F9FA)
private val TextPrimary = Color(0xFF1A1A2E)
private val TextSecondary = Color(0xFF6C757D)
private val CardBackground = Color.White
private val GradientStart = Color(0xFF667eea)
private val GradientEnd = Color(0xFF764ba2)

@Composable
fun GalleryScreen(
    window: WindowInsets,
    modifier: Modifier = Modifier
) {
    HideSystemBars()
    ChangeStatusBarColorEdgeToEdge(SurfaceLight)
    
    val galleryViewModel: GalleryViewModel = hiltViewModel()
    val galleryState by galleryViewModel.state.collectAsState()
    
    var selectedPhotoIndex by remember { mutableStateOf<Int?>(null) }
    var isGridView by remember { mutableStateOf(true) }

    // Fetch all photos directly on screen load
    LaunchedEffect(Unit) {
        galleryViewModel.fetchAllPhotos()
    }

    Scaffold(
        topBar = {
            GalleryTopAppBar(
                isGridView = isGridView,
                onToggleView = { isGridView = !isGridView },
                window = window
            )
        },
        containerColor = SurfaceLight
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                galleryState.state is GalleryStateCompanion.Loading -> {
                    LoadingView()
                }
                galleryState.error != null -> {
                    ErrorView(error = galleryState.error!!)
                }
                galleryState.result.isEmpty() -> {
                    EmptyGalleryView()
                }
                else -> {
                    if (isGridView) {
                        PhotoGridView(
                            photos = galleryState.result,
                            onPhotoClick = { index -> selectedPhotoIndex = index }
                        )
                    } else {
                        PhotoListView(
                            photos = galleryState.result,
                            onPhotoClick = { index -> selectedPhotoIndex = index }
                        )
                    }
                }
            }
        }
    }

    // Full Screen Image Dialog
    if (selectedPhotoIndex != null) {
        FullScreenImageDialog(
            photos = galleryState.result,
            initialPage = selectedPhotoIndex!!,
            onDismiss = { selectedPhotoIndex = null }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GalleryTopAppBar(
    isGridView: Boolean,
    onToggleView: () -> Unit,
    window: WindowInsets
) {
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Outlined.Image,
                    contentDescription = null,
                    tint = AccentPurple,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    "Gallery",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color =TextPrimary
                )
            }
        },
        actions = {
            // View Toggle Button
            IconButton(
                onClick = onToggleView,
                modifier = Modifier
                    .padding(end = 8.dp)


            ) {
                Icon(
                    imageVector = if (isGridView) Icons.Default.ViewList else Icons.Default.GridView,
                    contentDescription = if (isGridView) "Switch to List View" else "Switch to Grid View",
                    tint = Color.Black
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = SurfaceLight,
            titleContentColor = TextPrimary
        ),
        windowInsets = window
    )
}

@Composable
private fun LoadingView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(
                color = AccentPurple,
                strokeWidth = 3.dp,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Loading photos...",
                color = TextSecondary,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
private fun ErrorView(error: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(32.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                tint = Color.Red.copy(alpha = 0.7f),
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = error,
                color = TextSecondary,
                textAlign = TextAlign.Center,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
private fun EmptyGalleryView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(32.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Photo,
                contentDescription = null,
                tint = TextSecondary.copy(alpha = 0.5f),
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "No photos yet",
                color = TextPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Photos from your events will appear here",
                color = TextSecondary,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun PhotoGridView(
    photos: List<photo>,
    onPhotoClick: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(photos.size) { index ->
            PhotoGridCard(
                photo = photos[index],
                onClick = { onPhotoClick(index) }
            )
        }
    }
}

@Composable
private fun PhotoGridCard(
    photo: photo,
    onClick: () -> Unit
) {
    var isLoaded by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isLoaded) 1f else 0.95f,
        animationSpec = tween(300),
        label = "scale"
    )

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
            pressedElevation = 8.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.8f)
            .scale(scale)
            .clickable { onClick() }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Image
            SubcomposeAsyncImage(
                model = photo.imageUrl,
                contentDescription = photo.photoName,
                contentScale = ContentScale.Crop,
                loading = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.LightGray.copy(alpha = 0.3f)),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = AccentPurple,
                            strokeWidth = 2.dp,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                onSuccess = { isLoaded = true },
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp))
            )

            // Gradient Overlay at bottom
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .align(Alignment.BottomCenter)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.7f)
                            )
                        )
                    )
            )

            // Photo Info
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(12.dp)
            ) {
                Text(
                    text = photo.photoName,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                if (photo.date.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Outlined.CalendarMonth,
                            contentDescription = null,
                            tint = Color.White.copy(alpha = 0.8f),
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = photo.date,
                            color = Color.White.copy(alpha = 0.8f),
                            fontSize = 11.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun PhotoListView(
    photos: List<photo>,
    onPhotoClick: (Int) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(photos.size) { index ->
            PhotoListCard(
                photo = photos[index],
                onClick = { onPhotoClick(index) }
            )
        }
    }
}

@Composable
private fun PhotoListCard(
    photo: photo,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Thumbnail
            SubcomposeAsyncImage(
                model = photo.imageUrl,
                contentDescription = photo.photoName,
                contentScale = ContentScale.Crop,
                loading = {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .background(
                                Color.LightGray.copy(alpha = 0.3f),
                                RoundedCornerShape(12.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = AccentPurple,
                            strokeWidth = 2.dp,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                },
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Photo Details
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = photo.photoName,
                    color = TextPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                
                if (photo.date.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(
                                AccentPurple.copy(alpha = 0.1f),
                                RoundedCornerShape(8.dp)
                            )
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.CalendarMonth,
                            contentDescription = null,
                            tint = AccentPurple,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = photo.date,
                            color = AccentPurple,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
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
            
            // Track if current page is zoomed
            var isZoomed by remember { mutableStateOf(false) }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize(),
                pageSpacing = 16.dp,
                beyondViewportPageCount = 1,
                userScrollEnabled = !isZoomed // Disable swipe when zoomed
            ) { page ->
                // Calculate page offset for animation
                val pageOffset = (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
                
                // Animate scale and alpha based on page offset
                val scale by animateFloatAsState(
                    targetValue = 1f - (kotlin.math.abs(pageOffset) * 0.15f).coerceIn(0f, 0.15f),
                    animationSpec = tween(durationMillis = 150),
                    label = "scale"
                )
                val alpha by animateFloatAsState(
                    targetValue = 1f - (kotlin.math.abs(pageOffset) * 0.3f).coerceIn(0f, 0.3f),
                    animationSpec = tween(durationMillis = 150),
                    label = "alpha"
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                            this.alpha = alpha
                        },
                    contentAlignment = Alignment.Center
                ) {
                    ZoomableImage(
                        imageUrl = photos[page].imageUrl,
                        onZoomChange = { zoomed ->
                            // Only update if this is the current page
                            if (page == pagerState.currentPage) {
                                isZoomed = zoomed
                            }
                        }
                    )
                }
            }

            // Top Bar with close button and photo info
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.7f),
                                Color.Transparent
                            )
                        )
                    )
                    .statusBarsPadding()
                    .padding(16.dp)
                    .align(Alignment.TopCenter)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Close button
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.2f))
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Close",
                            tint = Color.White
                        )
                    }

                    // Page indicator
                    Text(
                        text = "${pagerState.currentPage + 1} / ${photos.size}",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.width(40.dp))
                }
            }

            // Bottom info bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.8f)
                            )
                        )
                    )
                    .padding(24.dp)
                    .align(Alignment.BottomCenter)
            ) {
                Column {
                    Text(
                        text = photos[pagerState.currentPage].photoName,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    if (photos[pagerState.currentPage].date.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Outlined.CalendarMonth,
                                contentDescription = null,
                                tint = Color.White.copy(alpha = 0.7f),
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = photos[pagerState.currentPage].date,
                                color = Color.White.copy(alpha = 0.7f),
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ZoomableImage(
    imageUrl: String,
    onZoomChange: (Boolean) -> Unit = {}
) {
    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    
    // Animated values for smooth transitions
    val animatedScale by animateFloatAsState(
        targetValue = scale,
        animationSpec = tween(durationMillis = 200),
        label = "zoom"
    )
    
    val animatedOffsetX by animateFloatAsState(
        targetValue = offset.x,
        animationSpec = tween(durationMillis = 200),
        label = "offsetX"
    )
    
    val animatedOffsetY by animateFloatAsState(
        targetValue = offset.y,
        animationSpec = tween(durationMillis = 200),
        label = "offsetY"
    )

    // Notify parent about zoom state
    LaunchedEffect(scale) {
        onZoomChange(scale > 1.1f)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = { tapOffset ->
                        if (scale > 1.1f) {
                            // Zoom out with animation
                            scale = 1f
                            offset = Offset.Zero
                        } else {
                            // Zoom in to 2.5x centered on tap position
                            val newScale = 2.5f
                            scale = newScale
                            
                            // Calculate offset to center zoom on tap point
                            val centerX = size.width / 2f
                            val centerY = size.height / 2f
                            
                            // The tap point should stay in place after zoom
                            val newOffsetX = (centerX - tapOffset.x) * (newScale - 1) / newScale
                            val newOffsetY = (centerY - tapOffset.y) * (newScale - 1) / newScale
                            
                            // Calculate bounds
                            val maxX = size.width * (newScale - 1) / (2 * newScale)
                            val maxY = size.height * (newScale - 1) / (2 * newScale)
                            
                            offset = Offset(
                                x = newOffsetX.coerceIn(-maxX, maxX),
                                y = newOffsetY.coerceIn(-maxY, maxY)
                            )
                        }
                    }
                )
            }
            .pointerInput(scale) {
                // Only enable drag when zoomed in
                if (scale > 1.1f) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        
                        // Calculate bounds for panning
                        val maxX = size.width * (scale - 1) / (2 * scale)
                        val maxY = size.height * (scale - 1) / (2 * scale)
                        
                        // Update offset with drag, respecting bounds
                        val newOffset = Offset(
                            x = (offset.x + dragAmount.x / scale).coerceIn(-maxX, maxX),
                            y = (offset.y + dragAmount.y / scale).coerceIn(-maxY, maxY)
                        )
                        offset = newOffset
                    }
                }
            },
        contentAlignment = Alignment.Center
    ) {
        SubcomposeAsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            loading = {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = Color.White,
                        strokeWidth = 2.dp,
                        modifier = Modifier.size(40.dp)
                    )
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    scaleX = animatedScale
                    scaleY = animatedScale
                    translationX = animatedOffsetX * animatedScale
                    translationY = animatedOffsetY * animatedScale
                }
        )
    }
}