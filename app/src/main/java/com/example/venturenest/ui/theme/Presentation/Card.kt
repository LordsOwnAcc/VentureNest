package com.example.venturenest.ui.theme.Presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.sharp.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun CardElement(
    modifier: Modifier = Modifier, onClick: () -> Unit
) {

    var isIconSelected by remember {
        mutableStateOf(false)
    }
    Row(
        modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(50f))
            .fillMaxWidth()
            .height(150.dp)
            .background(Color.White)
            .clickable { onClick.invoke() }
    ) {
        AsyncImage(
            model = "https://www.cgc.ac.in/public/tiny-uploads/_DSC_2835.JPG",
            contentDescription = "",
            modifier
                .padding(7.dp)
                .clip(
                    RoundedCornerShape(30f)
                )
                .fillMaxHeight()
                .fillMaxWidth(0.4f), contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize()
        ) {

            Text(
                text = "Chandigarh Engineering College, CGC Jhanjeri, Hosts Successful Internal Smart India Hackathon 2024",
                lineHeight = MaterialTheme.typography.bodySmall.lineHeight,
                letterSpacing = MaterialTheme.typography.bodySmall.letterSpacing,
                fontWeight = FontWeight.Bold,
                maxLines = 4,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize
            )
            Row(
                modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "",
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(15.dp)
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
            Row(modifier = Modifier.fillMaxWidth()) {
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
                            .size(15.dp)
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
                    modifier.fillMaxWidth(1f),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "",
                        tint = Color.Gray,
                        modifier = Modifier
                            .size(15.dp)
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
            }
//            Row (
//                modifier
//                    .fillMaxSize()
//                   , horizontalArrangement = Arrangement.End){
//
//               ElevatedButton(onClick = { /*TODO*/ }, shape = RectangleShape, modifier = Modifier) {
//                    Text(text = "Register")
//                }
//
//            }
            Row(
                modifier
                    .fillMaxWidth(0.949f)
                    .fillMaxHeight(1f),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = if (!isIconSelected) Icons.Default.BookmarkBorder else Icons.Default.Bookmark,
                    contentDescription = "",
                    modifier.clickable { isIconSelected = !isIconSelected },
                    tint = if (isIconSelected) Color(0xffdd1212) else Color.Black
                )
            }

        }

    }


}

