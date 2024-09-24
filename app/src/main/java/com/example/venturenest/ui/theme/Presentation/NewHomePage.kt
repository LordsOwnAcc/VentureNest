package com.example.venturenest.ui.theme.Presentation

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun NewHomePage(
    modifier: Modifier = Modifier
    ,title: String,
    description:String
) {
    ElevatedCard( modifier
        .padding(
            10.dp
        )) {


        Column(
            modifier
                .wrapContentHeight()
                .width(170.dp)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            AsyncImage(
                model = "https://img.freepik.com/premium-photo/professional-indian-female-headshots-business-corporate-women_203363-204.jpg",
                contentDescription = "",
                modifier
                    .clip(RoundedCornerShape(25f))
                    .background(Color.Black)
                    .fillMaxWidth()
                    .height(215.dp)
                , contentScale = ContentScale.Crop
            )

            Column(
                modifier
                    .wrapContentHeight()
                    .fillMaxWidth(0.9f)
            ) {
                Text(
                    text = title,
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                    textAlign = TextAlign.Center
                    , fontWeight = FontWeight.SemiBold
                )
                Text(
                    modifier = modifier.padding(bottom = 10.dp),
                    text = description,
                    fontSize = MaterialTheme.typography.labelSmall.fontSize,
                    textAlign = TextAlign.Start
                    , lineHeight = MaterialTheme.typography.labelSmall.lineHeight,
                    letterSpacing = MaterialTheme.typography.labelSmall.letterSpacing
                )

            }
        }
    }
}

//@Preview
//@Composable
//private fun PreviewNewHomePage() {
//    NewHomePage()
//}