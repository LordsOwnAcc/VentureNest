package com.example.venturenest.ui.theme.Presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter

@Composable
fun Success(
    modifier: Modifier = Modifier
) {

    val peoplePngLinks = listOf(
        SuccessStoriesClass(title = "Name", description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", link = "https://img.freepik.com/premium-photo/professional-indian-female-headshots-business-corporate-women_203363-204.jpg")
,        SuccessStoriesClass(title = "Name", description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", link = "https://img.freepik.com/premium-photo/professional-indian-female-headshots-business-corporate-women_203363-204.jpg")
,        SuccessStoriesClass(title = "Name", description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", link = "https://img.freepik.com/premium-photo/professional-indian-female-headshots-business-corporate-women_203363-204.jpg")
,        SuccessStoriesClass(title = "Name", description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", link = "https://img.freepik.com/premium-photo/professional-indian-female-headshots-business-corporate-women_203363-204.jpg")

    )
    var schroll = rememberScrollState()
    Row (
        Modifier
            .padding(top = 10.dp, bottom = 0.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth()
            .horizontalScroll(schroll)
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically) {
        peoplePngLinks.forEachIndexed { index, peoplePngLink ->
            if (index == peoplePngLinks.size-1) {
                Box(
                    modifier
                        .clip(
                            RoundedCornerShape(50f)
                        )
                        .fillMaxHeight()
                        .width(150.dp)
                        .height(200.dp)
                        .padding(start = 5.dp, end = 5.dp)
                        ) {


                    AsyncImage(
                        model = peoplePngLink.link, contentDescription = "",
                        modifier

                            .clip(
                                RoundedCornerShape(50f)
                            )
                            .width(150.dp)
                            .height(150.dp)
                            .fillMaxHeight()
                            .alpha(0.2f)

                        , contentScale = ContentScale.Crop
                    )
                    Column(
                        modifier

                            .clip(
                                RoundedCornerShape(50f)
                            )
                            .size(150.dp)
                            .clickable {  }

                        , horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
                        ) {
                        Icon(imageVector = Icons.Filled.ArrowForward, contentDescription = "",modifier.padding(top = 0.dp))

                            Text(text = "See More")

                    }

                }
            }else {
                Column(
                    modifier
                        .height(200.dp)
                        .width(150.dp)) {


                    AsyncImage(
                        model = peoplePngLink.link, contentDescription = "",
                        modifier
                            .height(150.dp)
                            .width(150.dp)
                            .padding(start = 5.dp, end = 5.dp)
                            .clip(
                                RoundedCornerShape(50f)
                            )
                        , contentScale = ContentScale.Crop
                    )
                    Column(modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            modifier = modifier.fillMaxWidth(),
                            text = peoplePngLink.title
, maxLines = 1
                            , textAlign = TextAlign.Center,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            fontWeight = FontWeight.SemiBold)



                    }
                }
            }

        }
    }
}

@Preview
@Composable
private fun PreviewSuccess() {
    Success()
}