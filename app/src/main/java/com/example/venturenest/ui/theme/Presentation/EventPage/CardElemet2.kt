package com.example.venturenest.ui.theme.Presentation.EventPage

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.venturenest.ui.theme.DaggerHilt.Events
import com.example.venturenest.ui.theme.background
import com.example.venturenest.ui.theme.foreground
import com.example.venturenest.ui.theme.textColor

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun CardElement2(
    modifier: Modifier = Modifier
    , onClick: () -> Unit
    , events: Events
    ,color: Long
) {


//   val cardOffset = remember {
//       Animatable(40f)
//   }

//    LaunchedEffect(Unit) {
//        cardOffset.animateTo(
//            targetValue = 0f,
//            animationSpec = tween(durationMillis = 200, easing = FastOutSlowInEasing)
//        )
//
//    }


    ElevatedCard(modifier.fillMaxWidth()
        .padding(top = 10.dp, bottom = 10.dp)
        .height(150.dp)
//        .graphicsLayer{
//            translationY=cardOffset.value
//        }
        .clickable{
            onClick.invoke()
        },
         shape = RoundedCornerShape(25f)
    , colors = CardDefaults.elevatedCardColors(containerColor = Color.White)) {

        Box(modifier.fillMaxSize()) {


            Row(
                modifier.fillMaxWidth().fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                AsyncImage(
                    model = events.imageUrl,
                    contentDescription = null, modifier.padding(start = 10.dp).fillMaxWidth(0.45f)
                        .fillMaxHeight(0.8f)
//                        .graphicsLayer{
//                            scaleX=imageOffset.value
//                            scaleY=imageOffset.value
//                        }
                        .clip(RoundedCornerShape(15f)), contentScale = ContentScale.FillBounds
                )
                Column(
                    Modifier.fillMaxWidth(0.95f)
                        .fillMaxHeight(0.8f), verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    var date = events.eventDate.subSequence(0, 9).reversed().toString()
                    Text(
                        events.eventTitle,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        maxLines = 2,
                        textAlign = TextAlign.Start,
                        modifier = modifier.fillMaxWidth().padding(start = 10.dp),
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.SemiBold
                        , color = Color.Black.copy(alpha = 0.65f)
                    )
                    Row(
                        modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = null,
                            modifier.scale(0.8f)
                                .padding(start = 5.dp), tint = Color.LightGray
                        )
                        Text(
                            text = date,
                            maxLines = 2,
                            textAlign = TextAlign.Start,
                            modifier = modifier.fillMaxWidth().padding(start = 5.dp),
                            overflow = TextOverflow.Ellipsis,
                            color = Color.Black,
                            fontSize = MaterialTheme.typography.bodySmall.fontSize
                        )
                    }
                    Row(
                        modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            modifier.scale(0.8f)
                                .padding(start = 5.dp), tint = Color.LightGray
                        )
                        Text(
                            text = "CGCU Mohali",
                            maxLines = 2,
                            textAlign = TextAlign.Start,
                            modifier = modifier.fillMaxWidth().padding(start = 5.dp),
                            overflow = TextOverflow.Ellipsis,
                            color = Color.Black,
                            fontSize = MaterialTheme.typography.bodySmall.fontSize
                        )
                    }


                }


            }

            Column (modifier.fillMaxSize().padding(end = 10.dp)
            , verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.End
            ){
                if (events.isStarred) {


                    Box(
                        modifier.height(25.dp).width(25.dp)
                            .background(Color.White.copy(alpha = 0.5f)),
                         //   .border(1.dp, Color.Black, RectangleShape),
                        contentAlignment = Alignment.TopStart
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null, modifier = modifier.scale(0.5f)
                            , tint = Color.Black.copy(alpha = 0.5f)
                        )
                    }
                }
            }
        }


    }

//    Column(
//        modifier
//            .wrapContentHeight().
//                wrapContentWidth()
//            .clickable { onClick.invoke() }
//            , verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally) {
//        Column(
//            modifier
//                .padding(1.dp)
//                .wrapContentHeight()
//                .wrapContentHeight()
//               , verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {
//            BoxWithConstraints(modifier.wrapContentWidth(), contentAlignment = Alignment.TopEnd ) {
//
//                AsyncImage(
//                    model = events.imageUrl,
//                    contentDescription = "",
//                    modifier
//                        .padding( 5.dp, bottom = 10.dp)
//                        .clip(RoundedCornerShape(5.dp))
//                        .wrapContentWidth()
//                        .height(220.dp)
//
//                        .clip(RoundedCornerShape(5.dp)),
//                    contentScale = ContentScale.FillBounds
//                )
//                      }
////        Text(
////            text =events.eventName,
////            modifier.fillMaxWidth()
////                .padding(bottom = 10.dp),
////            maxLines = 3,
////            textAlign = TextAlign.Center,
////            fontSize = MaterialTheme.typography.titleMedium.fontSize,
////            fontWeight = FontWeight.SemiBold
////            , overflow = TextOverflow.Ellipsis
////        )
//
//
//
//    }
//    }

}

