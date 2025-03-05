package com.example.venturenest.ui.theme.Presentation.EventPage

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.venturenest.ui.theme.DaggerHilt.Events

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dialog(
   show:MutableState<Boolean> ,
   event:Events) {
    AnimatedVisibility(visible = show.value) {


        var schroll = rememberScrollState()


        ModalBottomSheet(
            onDismissRequest = { show.value = false },

            shape = RectangleShape,
            containerColor = Color.Transparent, modifier = Modifier,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                contentAlignment = Alignment.TopCenter
            ) {

                AsyncImage(model = event.imageUrl, contentDescription = "", modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(250.dp)
                    .offset(y = -100.dp)
                    .shadow(
                        elevation = 5.dp,
                        shape = RectangleShape,
                        ambientColor = Color.Black,
                        spotColor = Color.Black
                    )

                , contentScale = ContentScale.FillBounds)
                Column(modifier = Modifier.fillMaxSize().padding(top = 150.dp), verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally){


                    Text(
                        text = event.eventName, fontWeight = FontWeight.SemiBold,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        maxLines = 3, textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .padding(top = 10.dp, bottom = 10.dp)
                    )
                    Text(
                        text = event.eventTitle, fontWeight = FontWeight.Normal,
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        maxLines = 3, textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .padding(top = 0.dp, bottom = 10.dp)
                    )

                }


            }
        }
    }
}

