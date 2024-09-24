package com.example.venturenest.ui.theme.Presentation

import android.widget.Toast
import androidx.compose.animation.core.EaseInBack
import androidx.compose.animation.core.EaseInBounce
import androidx.compose.animation.core.EaseInCirc
import androidx.compose.animation.core.EaseInOutBounce
import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.systemGestureExclusion
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter

@Composable
fun PartnerPage(
    modifier: Modifier = Modifier
) {
    val companyIcons = listOf(
        "https://upload.wikimedia.org/wikipedia/commons/4/4a/Logo_2013_Google.png", // Google
"https://tse4.mm.bing.net/th?id=OIP.pqS4Ck5LICvYiYrZPkBC1wHaJB&pid=Api&P=0&h=180",
        "https://tse2.mm.bing.net/th?id=OIP.BHsDLmb5Z-pq2W2DwOaZCAHaGF&pid=Api&P=0&h=180",
        "https://upload.wikimedia.org/wikipedia/commons/4/4a/Logo_2013_Google.png", // Google
        "https://tse4.mm.bing.net/th?id=OIP.pqS4Ck5LICvYiYrZPkBC1wHaJB&pid=Api&P=0&h=180",
        "https://tse2.mm.bing.net/th?id=OIP.BHsDLmb5Z-pq2W2DwOaZCAHaGF&pid=Api&P=0&h=180",
        "https://upload.wikimedia.org/wikipedia/commons/4/4a/Logo_2013_Google.png", // Google
        "https://tse4.mm.bing.net/th?id=OIP.pqS4Ck5LICvYiYrZPkBC1wHaJB&pid=Api&P=0&h=180",
        "https://tse2.mm.bing.net/th?id=OIP.BHsDLmb5Z-pq2W2DwOaZCAHaGF&pid=Api&P=0&h=180"
    )
    // Create a list of repeated icons to ensure the marquee effect
    val extendedIcons = remember { companyIcons + companyIcons }

    // Infinite transition for scrolling effect
    val infiniteTransition = rememberInfiniteTransition()
    val scrollX = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -100f, // Adjust this value based on the total width of your content
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .padding(top = 5.dp, bottom = 15.dp)
            .wrapContentHeight()

            .clip(androidx.compose.foundation.shape.RoundedCornerShape(8.dp))
            .background(androidx.compose.ui.graphics.Color.Transparent)
        , contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .padding()
                .fillMaxWidth()
                .basicMarquee(
                    500, MarqueeAnimationMode.Immediately, velocity = 30.dp,
                    repeatDelayMillis = 0, spacing = MarqueeSpacing(0.dp)
                )
                , horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically


            // .horizontalScroll(rememberScrollState())
//                Ensure the scrolling works
        ) {
            val context = LocalContext.current
            companyIcons.forEach { iconUrl ->
                ElevatedCard(onClick = { /*TODO*/ }, modifier = Modifier
                    .padding(start = 10.dp, top = 5.dp, bottom = 5.dp)
                    .border(0.1.dp, Color.Black, shape = RoundedCornerShape(50f))
                    ,
                    colors = CardDefaults.cardColors(containerColor = Color.White)) {


                    AsyncImage(
                        model = iconUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Inside,
                        modifier = Modifier
                            .padding(8.dp)
                            .clip(RoundedCornerShape(40f))
                            .size(50.dp)

                            .clickable {
                                Toast
                                    .makeText(
                                        context,
                                        companyIcons
                                            .indexOf(iconUrl)
                                            .toString(),
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            }

                            //  .offset(scrollX.value.dp)

                            .background(Color.White)
                    )

                }
            }
        }
    }
}


@Preview
@Composable
private fun PreviewPartnerPage() {
    PartnerPage()
}