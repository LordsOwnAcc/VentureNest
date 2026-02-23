package com.example.venturenest.ui.theme.Presentation.HomePage

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.venturenest.R
import com.example.venturenest.ui.theme.DaggerHilt.States.AiStatesCompanion
import com.example.venturenest.ui.theme.DaggerHilt.ViewModels.AiViewModel
import androidx.compose.ui.text.style.TextAlign
import kotlinx.coroutines.delay
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AiDialog(show: MutableState<Boolean>, modifier: Modifier) {
    if (show.value) {
        val aipageViewModel: AiViewModel = hiltViewModel()
        val aiState by aipageViewModel.state.collectAsState()
        
        val bottomSheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = true
        )

        ModalBottomSheet(
            onDismissRequest = { 
                show.value = false
                aipageViewModel._state.value = aipageViewModel.state.value.copy(
                    state = AiStatesCompanion.Idle
                )
            },
            shape = RoundedCornerShape(topEnd = 0.dp, topStart = 0.dp), // Full screen feel
            containerColor = Color.White,
            sheetState = bottomSheetState,
            dragHandle = null,
            modifier = Modifier.fillMaxHeight()
        ) {
            var showIntro by remember { mutableStateOf(true) }

            // Reset to Intro when dialog opens
            LaunchedEffect(show.value) {
                if (show.value) {
                    showIntro = true
                }
            }

            if (showIntro) {
                // INTRO SCREEN
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        
                        // Robot Animation
                        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.aibot))
                        val progress by animateLottieCompositionAsState(composition, iterations = 500)
                        LottieAnimation(
                            composition = composition,
                            progress = { progress },
                            modifier = Modifier.size(280.dp)
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        // Text Content
                        Text(
                            text = "Meet VentureBot!",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                        
                        Text(
                            text = "Your AI Assistant",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFA30D33),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "All your questions will be answered\nby an AI assistant. Ask here, please!",
                            fontSize = 16.sp,
                            color = Color.Gray,
                            textAlign = TextAlign.Center,
                            lineHeight = 24.sp
                        )
                        
                        Spacer(modifier = Modifier.weight(1f))
                    }

                    // Bottom Button
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 50.dp)
                    ) {
                        // Ripple effect or shadow ring could go here
                        Box(
                            modifier = Modifier
                                .size(70.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFA30D33))
                                .clickable { showIntro = false },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack, // Using ArrowBack rotated
                                contentDescription = "Start",
                                tint = Color.White,
                                modifier = Modifier
                                    .size(32.dp)
                                    .rotate(135f) // Rotates ArrowBack to point Top-Right/Up (approx) or purely Up depending on icon. 
                                    // Actually the image shows an arrow pointing Top-Right (North East).
                                    // ArrowBack points Left. 180 flips to Right. 135 is Down-Right? 
                                    // Let's use a simpler arrow or Rotate properly.
                                    // ArrowBack points West (Left). 
                                    // Rotate 180 -> East (Right).
                                    // Rotate 135 (CW) -> North-West?
                                    // Let's use a different icon or rotation.
                                    // ArrowForward points East. -45 -> North East.
                            )
                        }
                    }
                }
            } else {
                // CHAT SCREEN
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .imePadding()
                        .background(Color.White)
                ) {
                    // Header
                    val user = com.google.firebase.ktx.Firebase.auth.currentUser
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp, bottom = 10.dp, start = 16.dp, end = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {
                                    // If we want back to close the dialog:
                                    // show.value = false 
                                    // OR if we want back to go to Intro:
                                    // showIntro = true
                                    // Let's assume Back closes the dialog as per standard pattern, or maybe back to intro?
                                    // The user experience "Back" usually means "Back". 
                                    // Previous code closed the dialog. Let's keep that or maybe go back to intro?
                                    // Let's try closing dialog for now, or go back to intro if users prefer.
                                    // I will set it to close dialog to be safe.
                                    show.value = false
                                },
                            tint = Color.Black
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        
                        if (user?.photoUrl != null) {
                            coil.compose.AsyncImage(
                                model = user.photoUrl,
                                contentDescription = "Profile",
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(Color.LightGray),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Image(
                                painter = painterResource(R.drawable.img),
                                contentDescription = "Profile",
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(Color.LightGray),
                                contentScale = ContentScale.Crop
                            )
                        }
                        
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            val currentHour = java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY)
                            val greeting = when (currentHour) {
                                in 0..11 -> "Good Morning"
                                in 12..16 -> "Good Afternoon"
                                else -> "Good Evening"
                            }
                            Text(
                                text = greeting,
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                            Text(
                                text = user?.displayName ?: "Venture Explorer",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }
                    }

                    Divider(color = Color(0xFFF0F0F0), thickness = 1.dp)

                    // Chat Content
                    val listState = rememberLazyListState()
                    LaunchedEffect(aiState.chatHistory.size, aiState.state) {
                        if (aiState.chatHistory.isNotEmpty()) {
                            listState.animateScrollToItem(
                                if (aiState.state == AiStatesCompanion.Loading) aiState.chatHistory.size else aiState.chatHistory.size - 1
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    ) {
                        if (aiState.chatHistory.isEmpty() && aiState.state == AiStatesCompanion.Idle) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.aibot))
                                val progress by animateLottieCompositionAsState(composition, iterations = 500)
                                LottieAnimation(
                                    composition = composition,
                                    progress = { progress },
                                    modifier = Modifier.size(200.dp)
                                )
                                Text(
                                    "How can I help you today?",
                                    color = Color.Gray,
                                    fontSize = 16.sp,
                                    modifier = Modifier.padding(top = 16.dp)
                                )
                            }
                        } else {
                            LazyColumn(
                                state = listState,
                                modifier = Modifier.fillMaxSize(),
                                contentPadding = PaddingValues(bottom = 16.dp)
                            ) {
                                itemsIndexed(
                                    items = aiState.chatHistory,
                                    key = { index, message -> "${message.isUser}_${index}_${message.message.hashCode()}" }
                                ) { index, message ->
                                    AiChatBubble(
                                        message = message.message,
                                        isUser = message.isUser,
                                        isLast = index == aiState.chatHistory.size - 1
                                    )
                                }

                                if (aiState.state == AiStatesCompanion.Loading) {
                                    item {
                                        TypingIndicatorBubble()
                                    }
                                }

                                if (aiState.state == AiStatesCompanion.Error) {
                                    item {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(16.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text(
                                                text = "Error: ${aiState.error}",
                                                color = Color.Red,
                                                fontSize = 14.sp,
                                                textAlign = TextAlign.Center
                                            )
                                            Spacer(modifier = Modifier.height(8.dp))
                                            Button(
                                                onClick = { aipageViewModel.generateResponse(aiState.promt) },
                                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA30D33))
                                            ) {
                                                Text("Retry", color = Color.White)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // Input Area
                    Divider(color = Color(0xFFF0F0F0), thickness = 1.dp)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                            .background(Color.White),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        var prompt by remember { mutableStateOf("") }

//                        Icon(
//                            imageVector = Icons.Default.Add,
//                            contentDescription = "Add",
//                            tint = Color.Black,
//                            modifier = Modifier
//                                .size(32.dp)
//                                .padding(4.dp)
//                                .clickable { }
//                        )
                        
                        Spacer(modifier = Modifier.width(8.dp))

                        Row(
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp)
                                .clip(RoundedCornerShape(25.dp))
                                .background(Color(0xFFF5F5F5))
                                .padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            TextField(
                                value = prompt,
                                onValueChange = { prompt = it },
                                modifier = Modifier
                                    .weight(1f)
                                    .offset(y = (-2).dp), // Minor alignment tweak
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = Color.Transparent,
                                    unfocusedContainerColor = Color.Transparent,
                                    disabledContainerColor = Color.Transparent,
                                    errorContainerColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    disabledIndicatorColor = Color.Transparent,
                                    cursorColor = Color.Black,
                                    focusedTextColor = Color.Black,
                                    unfocusedTextColor = Color.Black
                                ),
                                placeholder = {
                                    Text("Ask VentureBot", color = Color.Gray, fontSize = 14.sp)
                                },
                                singleLine = true,
                                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send, keyboardType = KeyboardType.Text),
                                keyboardActions = KeyboardActions(onSend = {
                                    if (prompt.isNotBlank()) {
                                        aipageViewModel.generateResponse(prompt)
                                        prompt = ""
                                    }
                                }),
                                enabled = aiState.state != AiStatesCompanion.Loading
                            )

                            Icon(
                                imageVector = Icons.Default.Mic,
                                contentDescription = "Mic",
                                tint = Color.Gray,
                                modifier = Modifier.size(20.dp).clickable { }
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFA30D33))
                                .clickable {
                                    if (aiState.state == AiStatesCompanion.Loading) {
                                        aipageViewModel.stopResponse()
                                    } else if (prompt.isNotBlank()) {
                                        aipageViewModel.generateResponse(prompt)
                                        prompt = ""
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            if (aiState.state == AiStatesCompanion.Loading) {
                                Icon(
                                    imageVector = Icons.Default.Stop,
                                    contentDescription = "Stop",
                                    tint = Color.White,
                                    modifier = Modifier.size(24.dp)
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Default.Send,
                                    contentDescription = "Send",
                                    tint = Color.White,
                                    modifier = Modifier
                                        .size(20.dp)
                                        .offset(x = 2.dp)
                                        .rotate(-45f)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AiChatBubble(message: String, isUser: Boolean, isLast: Boolean = false) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 16.dp),
        horizontalAlignment = if (isUser) Alignment.End else Alignment.Start
    ) {
        // Label Row
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start,
            modifier = Modifier.padding(bottom = 6.dp)
        ) {
            if (!isUser) {
                // Bot Header
                Icon(
                    painter = painterResource(R.drawable.aing), // Using generic icon or logo
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = Color(0xFFA30D33)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "VentureBot",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            } else {
                // User Header
                val user = com.google.firebase.ktx.Firebase.auth.currentUser
                Text(
                    text = user?.displayName ?: "You",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.width(6.dp))
                if (user?.photoUrl != null) {
                    coil.compose.AsyncImage(
                        model = user.photoUrl,
                        contentDescription = "Profile",
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(Color.LightGray),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Image(
                        painter = painterResource(R.drawable.img),
                        contentDescription = "Profile",
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(Color.LightGray),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }

        // Message Card
        Card(
            shape = if (isUser) RoundedCornerShape(20.dp, 4.dp, 20.dp, 20.dp) else RoundedCornerShape(4.dp, 20.dp, 20.dp, 20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, if (!isUser) Color(0xFFA30D33) else Color(0xFFE0E0E0)),
            elevation = CardDefaults.cardElevation(2.dp),
            modifier = Modifier.widthIn(max = 280.dp)
        ) {
            var displayedText by rememberSaveable { mutableStateOf("") }
            
            // Typewriter effect only for the LATEST bot message
            LaunchedEffect(message, isLast) {
                if (!isUser && isLast && displayedText.length < message.length) {
                    displayedText = ""
                    message.forEachIndexed { index, _ ->
                        displayedText = message.substring(0, index + 1)
                        delay(5)
                    }
                } else {
                    displayedText = message
                }
            }
            
            Text(
                text = if (!isUser && message.length > displayedText.length) displayedText else message,
                modifier = Modifier.padding(14.dp),
                color = Color.Black,
                fontSize = 15.sp,
                lineHeight = 22.sp
            )
        }
    }
}

@Composable
fun TypingIndicatorBubble() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        // Bot Header
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(bottom = 6.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.aing),
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = Color(0xFFA30D33)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "VentureBot",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        // Message Card with Typing Indicator
        Card(
            shape = RoundedCornerShape(4.dp, 20.dp, 20.dp, 20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color(0xFFA30D33)),
            elevation = CardDefaults.cardElevation(2.dp),
            modifier = Modifier.widthIn(max = 280.dp)
        ) {
            TypingIndicatorAnimated()
        }
    }
}

@Composable
fun TypingIndicatorAnimated() {
    val infiniteTransition = rememberInfiniteTransition()

    val dot1Offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -6f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val dot2Offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -6f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500, delayMillis = 150, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val dot3Offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -6f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500, delayMillis = 300, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Row(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
//        Text(
//            text = "(",
//            color = Color.Black,
//            fontSize = 18.sp,
//            fontWeight = FontWeight.Bold
//        )

        Spacer(modifier = Modifier.width(4.dp))

        // Dot 1: Black
        Box(
            modifier = Modifier
                .size(6.dp)
                .offset(y = dot1Offset.dp)
                .clip(CircleShape)
                .background(Color.Black)
        )

        Spacer(modifier = Modifier.width(4.dp))

        // Dot 2: White
        Box(
            modifier = Modifier
                .size(6.dp)
                .offset(y = dot2Offset.dp)
                .clip(CircleShape)
                .background(Color.White)
                .border(1.dp, Color.LightGray, CircleShape)
        )

        Spacer(modifier = Modifier.width(4.dp))

        // Dot 3: Red
        Box(
            modifier = Modifier
                .size(6.dp)
                .offset(y = dot3Offset.dp)
                .clip(CircleShape)
                .background(Color(0xFFA30D33))
        )

        Spacer(modifier = Modifier.width(4.dp))

//        Text(
//            text = ")",
//            color = Color.Black,
//            fontSize = 18.sp,
//            fontWeight = FontWeight.Bold
//        )
    }
}
