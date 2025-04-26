package com.example.venturenest.ui.theme.Presentation.HomePage

import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.venturenest.ui.theme.DaggerHilt.SuccessStories
import com.example.venturenest.ui.theme.DaggerHilt.councilmembers

@Composable
fun PartnerPage(
    modifier: Modifier = Modifier,
    schroll :ScrollState
    ,list :List<councilmembers>
) {






    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp, bottom = 0.dp)
            .wrapContentHeight()

            .clip(androidx.compose.foundation.shape.RoundedCornerShape(8.dp))
            .background(androidx.compose.ui.graphics.Color.Transparent)
        , contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .padding()
                .fillMaxWidth()
                //.horizontalScroll(schroll)
                .basicMarquee(
                    500, MarqueeAnimationMode.Immediately, velocity = 25.dp,
                    repeatDelayMillis = 0, spacing = MarqueeSpacing(0.dp)
                )
                , horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically



        ) {
            
            val context = LocalContext.current
            list.forEach { iconUrl ->
                ElevatedCard (modifier.padding(10.dp).height(200.dp).width(140.dp)
                    , shape = RoundedCornerShape(topStart = 15f, topEnd = 15f)) {
                    Column(modifier.height(200.dp).width(140.dp)) {
                        AsyncImage(
                            model = iconUrl.imgpath,
                            contentDescription = iconUrl.imgName,
                            modifier.fillMaxWidth()
                                .height(160.dp)
                                .background(Color.White), contentScale = ContentScale.Crop
                        )

                        Row(
                            modifier.fillMaxWidth(1f).fillMaxWidth().fillMaxHeight()
                                .background(Color(0xFFF29727))
                                .horizontalScroll(rememberScrollState()),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                iconUrl.name,
                                maxLines = 1,
                                modifier = modifier.padding(start = 10.dp, end = 10.dp),
                                color = Color.White
                            )
                        }
                    }
                }
            }

        }
    }
}


//@Preview
//@Composable
//private fun PreviewPartnerPage() {
//    PartnerPage()
//}