package com.example.venturenest.ui.theme.Presentation.HomePage

import android.widget.Toast
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.venturenest.ui.theme.DaggerHilt.Partners
import com.example.venturenest.ui.theme.DaggerHilt.SuccessStories

@Composable
fun PartnerPages(
    modifier: Modifier = Modifier,
    schroll : ScrollState
    , list :List<Partners>
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
                .horizontalScroll(schroll)
                .basicMarquee(
                    animationMode = MarqueeAnimationMode.Immediately, velocity = 25.dp
                )
//                .basicMarquee(
//                    500, MarqueeAnimationMode.Immediately, velocity = 10.dp,
//                    repeatDelayMillis = 0, spacing = MarqueeSpacing(0.dp)
//                )
            , horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically



        ) {
            Spacer(modifier = modifier.width(16.dp))
            val context = LocalContext.current
            list.forEach { iconUrl ->
                ElevatedCard( modifier = Modifier
                    .padding(start = 10.dp, top = 5.dp, bottom = 5.dp
                        , end = 5.dp)
                    // .border(0.1.dp, Color.Black, shape = RoundedCornerShape(25f))
                    ,
                    colors = CardDefaults.cardColors(containerColor = Color.White)) {


                    AsyncImage(
                        model = iconUrl.imgpath,
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .clip(RoundedCornerShape(20f))
                            .width(150.dp)
                            .height(180.dp)


                    )


                }
            }
            Spacer(modifier = modifier.width(16.dp))
        }
    }
}
