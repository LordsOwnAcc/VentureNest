package com.example.venturenest.ui.theme.Presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.material.icons.filled.Brightness1
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun CardElement2(
    modifier: Modifier = Modifier
    , onClick: () -> Unit
) {



    Column(
        modifier
            .padding(bottom = 20.dp)
            .clip(RoundedCornerShape(50f))
            .border(3.dp, Color(0xffA30D33), RoundedCornerShape(50f))
            .fillMaxWidth(0.9f)
            .height(320.dp)
            .background(Color.White)
            .clickable { onClick.invoke() }
            , verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Column(
            modifier
                .fillMaxHeight(0.9f)
                .fillMaxWidth(0.9f)) {
            Box (modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd ) {

                AsyncImage(
                    model = "https://www.cgc.ac.in/public/tiny-uploads/_DSC_2835.JPG",
                    contentDescription = "",
                    modifier
                        .clip(RoundedCornerShape(50f))
                        .fillMaxWidth()
                        .height(180.dp),
                    contentScale = ContentScale.Crop
                )
                      }
        Text(
            text = "Chandigarh Engineering College, CGC Jhanjeri, Hosts Successful Internal Smart India Hackathon 2024",
            modifier.fillMaxWidth(),
            maxLines = 3,
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = FontWeight.SemiBold
        )
        Row(
            modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "",
                tint = Color.Red,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .size(18.dp)
                    .padding(end = 5.dp)
            )
            Text(
                text = "CGC J",
                maxLines = 1,
                textAlign = TextAlign.Center,
                fontSize = MaterialTheme.typography.labelSmall.fontSize,
                lineHeight = MaterialTheme.typography.labelSmall.lineHeight
            )
        }

        Row(modifier = Modifier.fillMaxWidth(0.9f)) {
            Row(
                modifier.fillMaxWidth(0.5f),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "",
                    tint = Color.Gray,
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .size(18.dp)
                        .padding(end = 5.dp)
                )
                Text(
                    text = "2024/02/24",
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                    fontSize = MaterialTheme.typography.labelSmall.fontSize,
                    lineHeight = MaterialTheme.typography.labelSmall.lineHeight
                )
            }
            Row(
                modifier.fillMaxWidth(0.9f),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "",
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(18.dp)
                        .padding(end = 5.dp)
                )
                Text(
                    text = "2pm-5pm",
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                    fontSize = MaterialTheme.typography.labelSmall.fontSize,
                    lineHeight = MaterialTheme.typography.labelSmall.lineHeight
                )
            }
            Icon(
                imageVector = Icons.Default.ArrowDropUp,
                contentDescription = "",
                Modifier.clickable { onClick.invoke() }
                    .scale(2f,2f))



        }
    }
    }

}

@Preview
@Composable
private fun PreviewCardElement() {
    CardElement2(onClick = {})
}