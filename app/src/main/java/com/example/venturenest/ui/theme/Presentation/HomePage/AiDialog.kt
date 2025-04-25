package com.example.venturenest.ui.theme.Presentation.HomePage



import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.RotateRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.venturenest.R
import com.example.venturenest.ui.theme.DaggerHilt.Events
import com.example.venturenest.ui.theme.DaggerHilt.States.AiStatesCompanion
import com.example.venturenest.ui.theme.DaggerHilt.ViewModels.AiViewModel
import com.example.venturenest.ui.theme.Presentation.Setting.SettingElement
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AiDialog(show: MutableState<Boolean>, modifier: Modifier) {
    AnimatedVisibility(visible = show.value) {
        val aipageViewModel: AiViewModel = hiltViewModel()
        val aiState by aipageViewModel.state.collectAsState()
        val scrollState = rememberScrollState()

        // Force full expansion of the bottom sheet
        val bottomSheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = true
        )

        ModalBottomSheet(
            onDismissRequest = { show.value = false
                aipageViewModel._state.value = aipageViewModel.state.value.copy(
                    state = AiStatesCompanion.Idle
                )
                               },
            shape = RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp),
          containerColor = Color(0xffEAE8C0),
//            containerColor = Color.Transparent,
            sheetState = bottomSheetState,
            modifier = Modifier.fillMaxHeight()
        ) {
            ElevatedCard(
                modifier = Modifier.fillMaxSize(),
                shape = RectangleShape
                , colors = CardDefaults.elevatedCardColors(
                    containerColor = Color(0xffEAE8C0)

                )

            ) {
                var prompt by remember { mutableStateOf("") }

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .imePadding(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
//                    Row (modifier.fillMaxWidth()
//                        .height(60.dp)
//                        .clip(RectangleShape)
//                        .border(1.dp,Color.Gray)
//                        , verticalAlignment = Alignment.CenterVertically
//                        , horizontalArrangement = Arrangement.SpaceBetween
//                    ){
//                    Text("Chat",modifier.padding(start = 10.dp))
//                    Icon(imageVector = Icons.Default.RotateRight,
//                        contentDescription = null
//                    ,modifier.padding(end = 10.dp))
//
//
//                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.85f)
                            ,
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        when (aiState.state) {
                            is AiStatesCompanion.Error -> {
                                Column(
                                    modifier = Modifier.fillMaxHeight(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Top
                                ) {
                                    ChatBubbleWithArrow(
                                        message = aiState.promt,
                                        isUser = true
                                    )

                                   ChatBubbleWithArrow(aiState.error.toString(),isUser = false)

                                    Box(modifier.fillMaxSize()
                                    , contentAlignment = Alignment.BottomCenter) {

                                        Button( onClick = {

                                            aipageViewModel.generateResponse(prompt=aiState.promt)
                                        }
                                            , colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                                        ) {
                                            Text("Regenerate", color = Color.White)
                                        }

                                    }


                                }
                            }

                            is AiStatesCompanion.Idle -> {
                                Column(modifier.fillMaxSize()
                                , verticalArrangement = Arrangement.Top,
                                    horizontalAlignment = Alignment.CenterHorizontally) {


                                    Column(modifier.fillMaxWidth(0.9f)
                                        .fillMaxHeight(0.8f)
                                        .verticalScroll(rememberScrollState()),
                                        verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.Start){
                                        ChatBubbleWithArrow(
                                            message = "Hello! \uD83D\uDC4B I'm VentureBot \uD83E\uDD16, here to assist you .\n" +
                                                    "I can help answer questions, provide suggestions, and support you in your journey with VentureNet.\n" +
                                                    "\n" +
                                                    "Please note:\n" +
                                                    "\n" +
                                                    "I do my best to provide accurate and helpful information, but I may sometimes get things wrong. Always double-check anything important.\n" +
                                                    "\n" +
                                                    "I might occasionally use language or provide content that could be unexpected or inappropriate. This is never intentional.\n" +
                                                    "\n" +
                                                    "VentureNet is not responsible for any incorrect information or inappropriate content generated by me.\n" +
                                                    "\n" +
                                                    "By continuing to use this service, you agree that VentureNet is not liable for any outcomes resulting from interactions with VentureBot.",
                                            isUser = false
                                        )
                                    }
                                    Column(modifier.fillMaxSize()
                                    , verticalArrangement = Arrangement.Bottom,
                                        horizontalAlignment = Alignment.Start) {


                                        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.aibot))
                                        val progress by animateLottieCompositionAsState(composition, iterations = 500)
                                        LottieAnimation(
                                            composition = composition,
                                            progress = { progress },
                                            modifier = Modifier.size(150.dp).padding(bottom = 10.dp)
                                        )
                                    }








                                }



                            }

                            is AiStatesCompanion.Result -> {

                                Column(
                                    modifier = Modifier.fillMaxSize(1f)
                                        .verticalScroll(rememberScrollState()),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Top
                                ) {
                                    ChatBubbleWithArrow(
                                        message = aiState.promt,
                                        isUser = true
                                    )
                                    ChatBubbleWithArrow(
                                        message = aiState.result,
                                        isUser = false
                                    )

                                }
                            }

                            is AiStatesCompanion.Loading -> {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
                                    ChatBubbleWithArrow(
                                        message = aiState.promt,
                                        isUser = true
                                    )

                                    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.ai))
                                    val progress by animateLottieCompositionAsState(composition, iterations = 500)
                                    LottieAnimation(
                                        composition = composition,
                                        progress = { progress },
                                        modifier = Modifier.size(150.dp).padding(bottom = 10.dp)
                                    )
                                }
                            }
                        }
                    }

                    // Input Field at the bottom
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(start = 0.dp, bottom = 0.dp, top = 10.dp, end = 0.dp)
                            .clip(RoundedCornerShape(topEnd = 30f, topStart = 30f))
                            .background(Color.White)
                          ,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextField(
                            value = prompt,
                            onValueChange = { prompt = it },
                            modifier = Modifier.weight(1f),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent

                            ),
                            maxLines = 3
                            , placeholder = {
                                Text( "Chat with ventureBot")
                            }
                            , keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search
                            , keyboardType = KeyboardType.Text)
                            , keyboardActions = KeyboardActions(onSearch = {
                                aipageViewModel.generateResponse(aiState.promt)
                            })
                            , enabled = aiState.state != AiStatesCompanion.Loading
                        )

                        Icon(
                            imageVector = Icons.Default.Send ,
                            contentDescription = "Send",
                            modifier = Modifier
                                .padding(20.dp)
                                .size(24.dp)
                                .scale(1.2f)
                                .clickable { aipageViewModel.generateResponse(prompt)

                                prompt = ""}
                            , tint = if (aiState.state != AiStatesCompanion.Loading) Color.Blue else Color.Gray
                        )
                    }
                }
            }
        }
    }
}
