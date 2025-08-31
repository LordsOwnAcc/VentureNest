package com.example.venturenest.ui.theme.Presentation.AchievementsPage


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.venturenest.ui.theme.DaggerHilt.ViewModels.LoadingStateViewmodel
import com.example.venturenest.ui.theme.Presentation.helper.ChangeStatusBarColorEdgeToEdge
import com.example.venturenest.ui.theme.Presentation.helper.HideSystemBars
import com.example.venturenest.ui.theme.background

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CouncilScreen(windowInsets: WindowInsets,modifier: Modifier,
                   search2: String?
                  ,color: Long
                   ,category:String) {


    ChangeStatusBarColorEdgeToEdge(background)
    var d = mapOf(
        "advisory" to "Advisory",
        "techinnov" to "Technology and Innovation",
        "mentorship" to "Mentorship",
        "legalcompl" to "Legal Compliance"
    )
    val animationState = remember { MutableTransitionState(false) }
    val image = remember { mutableStateOf("") }
    val name = remember { mutableStateOf("") }
    var isVisible = remember {
        mutableStateOf(false)
    }
    var search by remember {
        mutableStateOf("")
    }
    LaunchedEffect(Unit) {
        search = search2.toString()
    }
    val viewModel: LoadingStateViewmodel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    Box(Modifier.fillMaxSize()) {


    Column(
        modifier.fillMaxSize()
            .windowInsetsPadding(windowInsets)

            .background(background)
            .padding(top = 10.dp), verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier
                .padding(bottom = 10.dp)
                .fillMaxWidth(0.9f)
                .height(55.dp), horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier
                    .clip(RoundedCornerShape(15f))
                    .fillMaxWidth(0.95f)
                    .fillMaxHeight()
                    .border(
                        1.dp, Color.LightGray,
                        RoundedCornerShape(15f)
                    ),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = search,
                    onValueChange = { search = it },
                    modifier
                        .clip(RoundedCornerShape(15f))
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White,

textColor = Color.Gray,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    ),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done,),
                    placeholder = {
                        Text(
                            text = "Search in Council-Members",
                            modifier.offset(y = 0.dp),
                            color = Color.Gray
                        )
                    },
                    textStyle = TextStyle(fontSize = MaterialTheme.typography.body1.fontSize),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "", modifier.scale(1f)
                        )
                    },
                    maxLines = 1,
                    trailingIcon = {
                        if (search != "") {

                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "",
                                modifier.clickable {
                                    search = ""

                                }.scale(0.8f),
                                tint = Color.Gray
                            )
                        }
                    })
            }

        }



        FlowRow(
            modifier.fillMaxHeight(1f)
                .fillMaxWidth(0.9f)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top, horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            state.Data.councilmembers.filter { it ->
                it.category.contains(category, true) && it.name.contains(
                    search.toString(), true
                )
            }.forEach { council ->
                ElevatedCard(
                    modifier.padding(10.dp).fillMaxWidth()
                        .height(200.dp), shape = RoundedCornerShape(topStart = 15f, topEnd = 15f)
                ) {
                    Row(
                        modifier.fillMaxWidth().height(200.dp)
                            .border(
                                0.1.dp,
                                Color(color),
                                RoundedCornerShape(topStart = 15f, topEnd = 15f)
                            ).clickable{
                                image.value=council.imgpath
                                name.value=council.name
                                isVisible.value=true
                            }
                    ) {
                        AsyncImage(
                            model = council.imgpath,
                            contentDescription = council.imgName,
                            modifier.fillMaxHeight()
                                .fillMaxWidth(0.45f)
                                .background(Color.White), contentScale = ContentScale.Crop
                        )

                        Column(
                            modifier.fillMaxWidth(1f).fillMaxHeight()
                                .background(Color(color)),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                council.name,
                                maxLines = 1,
                                modifier = modifier.padding(start = 10.dp, end = 10.dp),
                                color = Color.White,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = MaterialTheme.typography.h6.fontSize

                                , overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                d.get(council.category) + " Committee".toString(),
                                modifier = modifier.fillMaxWidth()
                                    .padding(start = 10.dp, end = 10.dp),
                                fontWeight = FontWeight.W700,
                                color = Color.Black,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                fontSize = MaterialTheme.typography.body1.fontSize
                            )
                            Text(
                                council.company,
                                modifier = modifier.padding(start = 10.dp, end = 10.dp),
                                color = Color.DarkGray
                                , maxLines = 2
                                ,overflow = TextOverflow.Ellipsis,
                            )

                        }
                    }
                }


            }


        }


    }
        LaunchedEffect(isVisible.value) {
            animationState.targetState = isVisible.value
        }

        // Only show the Dialog when the animation state is visible or was visible
        if ( isVisible.value==true) {
            Dialog(
                onDismissRequest = { isVisible.value=false

                },
                properties = DialogProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true,
                    usePlatformDefaultWidth = true
                )
            ) {
                // Background scrim with fade animation
                AnimatedVisibility(
                    visibleState = animationState,
                    enter = fadeIn(tween(300)),
                    exit = fadeOut(tween(300))
                ) {
                    Box(
                        modifier = Modifier
                            .wrapContentHeight()
                            .wrapContentWidth()
                        ,
                        contentAlignment = Alignment.Center
                    ) {
                        // Dialog content with scale animation
                        AnimatedVisibility(
                            visibleState = animationState,
                            enter = scaleIn(tween(300)) + fadeIn(tween(300)),
                            exit = scaleOut(tween(300)) + fadeOut(tween(300))
                        ) {
                            Card(
                                modifier = Modifier

                                    .fillMaxWidth(0.85f),
                                shape = RoundedCornerShape(24.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White
                                ),
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 8.dp
                                )
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(24.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    AsyncImage(
                                        model = image.value,
                                        contentDescription = null,modifier.height(300.dp)
                                            .fillMaxWidth()
                                        , contentScale = ContentScale.FillWidth
                                    )
                                    if (name.value!=null){
                                        Text(name.value
                                        ,color= Color.Black
                                        , fontWeight = FontWeight.Bold,
                                            fontSize = MaterialTheme.typography.h6.fontSize
                                        , modifier = modifier.fillMaxWidth()
                                        , textAlign = TextAlign.Center)
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