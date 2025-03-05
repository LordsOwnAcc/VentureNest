package com.example.venturenest.ui.theme.Presentation.Main.ExtraPages

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.venturenest.R


@Composable
fun Achievement(
    modifier: Modifier = Modifier
    ,windowInsets: WindowInsets
    ,navController: NavHostController
) {

    Column(
        modifier
            .windowInsetsPadding(windowInsets)
            .padding(bottom = 60.dp)
            .fillMaxSize(1f)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top) {

        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.1f)
            ,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(text = "Achievements",
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.Bold)
            Row(modifier.fillMaxWidth()  ,
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Top) {
                IconButton(onClick = {} , modifier.offset(x=10.dp)) {
                    Icon(imageVector = Icons.Default.Tune, contentDescription = "", tint =Color.Black)
                }
            }
        }




                    ElevatedCard(
                        modifier
                            .fillMaxWidth(0.9f)
                            .shadow(
                                elevation = 5.dp,
                                shape = RoundedCornerShape(50f),
                                spotColor = Color.Black
                            )
                            .wrapContentHeight()
                   // .border(0.1.dp, Color.Black, shape = RoundedCornerShape(50f))
                        , colors = CardDefaults.cardColors(containerColor = Color.White)) {
                Column(
                    modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally) {



              Box (modifier.fillMaxWidth()){

                  Image(painter = painterResource(id = R.drawable.whatsapp), contentDescription = "",modifier.fillMaxWidth(), contentScale = ContentScale.FillBounds)
                  Text(
                      modifier = Modifier
                          .padding(bottom = 10.dp, top = 10.dp)
                          .fillMaxWidth(),
                      text = "Partners",
                      textAlign = TextAlign.Center,
                      fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                      fontWeight = FontWeight.SemiBold
                  )
                  Row(modifier.fillMaxWidth(0.95f), horizontalArrangement = Arrangement.End) {
                      Icon(imageVector = Icons.Default.ArrowOutward, contentDescription = "",
                          modifier
                              .padding(top = 10.dp)
                              .clickable { }
                              .size(20.dp), tint = Color.Gray)

                  }



              }

            }
            }

            ElevatedCard(
                modifier
                    .padding(top = 20.dp, bottom = 20.dp)
                    .fillMaxWidth(0.9f)
                    .shadow(
                        elevation = 5.dp,
                        shape = RoundedCornerShape(50f),
                        spotColor = Color.Black
                    )

                    .wrapContentHeight()
                 //   .border(0.1.dp, Color.Black, shape = RoundedCornerShape(50f))
                , colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                Column(
                    modifier.fillMaxWidth(), verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {





                Box(modifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier
                            .padding(start = 0.dp, top = 10.dp)
                            .fillMaxWidth(),
                        text = "Success Stories",
                        textAlign = TextAlign.Center,
                        fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                        fontWeight = FontWeight.SemiBold
                    )

                    Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                      Icon(imageVector = Icons.Default.ArrowOutward, contentDescription = "",
                          modifier
                              .padding(top = 10.dp, end = 10.dp)
                              .clickable {
                                  navController.navigate("SuccessStories")
                              }
                              .size(20.dp), tint = Color.Gray)


                    }
                }

            }
            }





    }
}

//@Preview
//@Composable
//private fun PreviewNewsPage() {
//   Achievement()
//}