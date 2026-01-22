package com.example.venturenest.ui.theme.Presentation.EventPage

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import com.example.venturenest.R

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.venturenest.ui.theme.DaggerHilt.Events

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dialog(
   show:MutableState<Boolean> ,
   event:Events
,modifier: Modifier
,color: Long) {
    AnimatedVisibility(visible = show.value) {




        ModalBottomSheet(
            onDismissRequest = {
                show.value = false
                               },

            shape = RectangleShape,
            containerColor = Color.White, modifier = Modifier,
        ) {


                Column(modifier = Modifier.fillMaxSize()
                    .background(Color.White), verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally) {
Column(modifier = Modifier.fillMaxWidth(0.9f).wrapContentHeight()
    .background(Color.LightGray)
    .clip(RoundedCornerShape(25f)), verticalArrangement = Arrangement.Top,
    horizontalAlignment = Alignment.CenterHorizontally) {
                    AsyncImage(
                        model = event.imageUrl, contentDescription = "", modifier = Modifier
                            .padding(top = 20.dp)
                            .clip(RoundedCornerShape(25f))
                            .fillMaxWidth(0.9f)
                            .height(200.dp)

                            .shadow(
                                elevation = 5.dp,
                                shape = RectangleShape,
                                ambientColor = Color.Black,
                                spotColor = Color.Black
                            ), contentScale = ContentScale.FillBounds
                    )

                    Text(
                        text = event.eventName, fontWeight = FontWeight.SemiBold,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        maxLines = 3, textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .padding(top = 20.dp, bottom = 20.dp)
                        , color = Color.White
                    )


                    Row(
                        Modifier
                            .padding(bottom = 40.dp)
                            .fillMaxWidth(0.9f)
                            .height(60.dp)
                            .clip(
                                RoundedCornerShape(10.dp)
                            )
                            .background(Color(0xffFDFAF6)),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Column(
                            Modifier
                                .fillMaxHeight()
                                .fillMaxWidth()
                                .weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceAround
                        ) {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = "",
                                Modifier
                                    .offset(y = 5.dp)
                                    .clickable {

                                    }, tint = Color.Black
                            )
                            Text(
                                text = event.eventDate.substring(0, 9).reversed(),
                                modifier = Modifier.offset(y = -5.dp),
                                fontSize = MaterialTheme.typography.labelSmall.fontSize,
                                letterSpacing = MaterialTheme.typography.labelSmall.letterSpacing,
                                lineHeight = MaterialTheme.typography.labelSmall.lineHeight,
                                fontFamily = FontFamily.Serif,
                                overflow = TextOverflow.Ellipsis, color = Color.Black
                            )


                        }
                        Spacer(
                            modifier = Modifier.width(1.dp).fillMaxHeight(0.6f)
                                .background(Color.LightGray)
                        )
                        Column(
                            Modifier
                                .fillMaxHeight()
                                .fillMaxWidth()
                                .weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceAround
                        ) {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = "",
                                Modifier.offset(y = 5.dp), tint = Color.Black
                            )
                            Text(
                                text = "CGC Mohali",
                                modifier = Modifier.offset(y = -5.dp),
                                fontSize = MaterialTheme.typography.labelSmall.fontSize,
                                letterSpacing = MaterialTheme.typography.labelSmall.letterSpacing,
                                lineHeight = MaterialTheme.typography.labelSmall.lineHeight,
                                fontFamily = FontFamily.Serif,
                                color = Color.Black,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        Spacer(
                            modifier = Modifier.width(1.dp).fillMaxHeight(0.6f)
                                .background(Color.LightGray)
                        )


                        Column(
                            Modifier
                                .fillMaxHeight()
                                .fillMaxWidth()
                                .weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceAround
                        ) {
                            Icon(
                                imageVector = if (event.isStarred) Icons.Default.Star else Icons.Default.StarBorder,
                                contentDescription = "",
                                Modifier.offset(y = 5.dp), tint = Color.Black
                            )
                            Text(
                                text = if (event.isStarred) "Starred" else "Not Starred",
                                modifier = Modifier.offset(y = -5.dp),
                                fontSize = MaterialTheme.typography.labelSmall.fontSize,
                                letterSpacing = MaterialTheme.typography.labelSmall.letterSpacing,
                                lineHeight = MaterialTheme.typography.labelSmall.lineHeight,
                                fontFamily = FontFamily.Serif,
                                overflow = TextOverflow.Ellipsis, color = Color.Black
                            )
                        }
                    }
Row (Modifier.fillMaxWidth().scale(1.1f)
    .wrapContentHeight(), horizontalArrangement = Arrangement.SpaceBetween){
    Spacer(Modifier.size(50.dp)
        .clip(CircleShape)
        .background(Color.White)
        .offset(x=-25.dp)

        )
    Spacer(Modifier.size(50.dp)
        .clip(CircleShape)
        .background(Color.White)
        .offset(x = 50.dp))

}
                    Text(
                        text = event.eventTitle, fontWeight = FontWeight.Normal,
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        maxLines = 3, textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .padding(top = 15.dp, bottom = 10.dp)
                        , color = Color.White
                    )

    Image(painter = painterResource(R.drawable.barcode)
    , contentDescription = null,Modifier.fillMaxWidth(0.92f)

            .padding(bottom = 30.dp, top = 20.dp)
    , contentScale = ContentScale.FillWidth)

}
                }


            }
        }
    }


