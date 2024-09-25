package com.example.venturenest.ui.theme.Presentation.Main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.border
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy
import com.example.venturenest.ui.theme.Presentation.Main.ExtraPages.SuccessStoriesPage
import com.example.venturenest.ui.theme.Presentation.PartnerPage
import com.example.venturenest.ui.theme.Presentation.Success

@Composable
fun Achievement(
    modifier: Modifier = Modifier
    ,windowInsets: WindowInsets
) {

    var successStoriesVisible by remember {

        mutableStateOf(false)
    }
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
                .padding(top = 20.dp, bottom = 20.dp)
                .wrapContentHeight()
                .shadow(
                    elevation = 5.dp,
                    spotColor = Color.Black,
                    shape = RoundedCornerShape(50f)
                )

                .fillMaxWidth(0.9f)
                   // .border(0.1.dp, Color.Black, shape = RoundedCornerShape(50f))
                        ,
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier
                        .wrapContentHeight()
                        .padding(top = 0.dp, bottom = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {

                    Box(
                        modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp),

                    ) {
                        Text(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp),
                            text = "Statistics",
                            textAlign = TextAlign.Center,
                            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                            fontWeight = FontWeight.SemiBold
                        )
                        Row (modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
                            Icon(imageVector = Icons.Default.ArrowOutward, contentDescription = "",
                                modifier
                                    .padding(end = 10.dp, top = 10.dp)
                                    .clickable { }
                                    .size(20.dp), tint = Color.Gray)
                        }
                    }

                    Row(
                        modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp)
                            .wrapContentHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Column(
                            modifier
                                .fillMaxWidth()
                                .weight(1f), horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Startups Incubated",
                                fontSize = MaterialTheme.typography.labelSmall.fontSize
                            )
                            Text(
                                text = "150+",
                                textAlign = TextAlign.Center,
                                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                                fontWeight = FontWeight.SemiBold
                                , color = Color.Red
                            )
                        }
                        Column(
                            modifier
                                .fillMaxWidth()
                                .weight(1f), horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Startups Mentored",
                                fontSize = MaterialTheme.typography.labelSmall.fontSize
                            )
                            Text(
                                text = "500+",
                                textAlign = TextAlign.Center,
                                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                                fontWeight = FontWeight.SemiBold
                                , color = Color.Red
                            )
                        }

                    }
                    Row(
                        modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp)
                            .wrapContentHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Column(
                            modifier
                                .fillMaxWidth()
                                .weight(1f), horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Key Mentors",
                                fontSize = MaterialTheme.typography.labelSmall.fontSize
                            )
                            Text(
                                text = "100+",
                                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                                fontWeight = FontWeight.SemiBold
                                , color = Color.Red
                            )
                        }
                        Column(
                            modifier
                                .fillMaxWidth()
                                .weight(1f), horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Patents Filed",
                                fontSize = MaterialTheme.typography.labelSmall.fontSize
                            )
                            Text(
                                text = "400+",
                                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                                fontWeight = FontWeight.SemiBold
                                , color = Color.Red
                            )
                        }

                    }

                    Row(
                        modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp)
                            .wrapContentHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {

                        Column(
                            modifier
                                .fillMaxWidth()
                                .weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Funding Raised By Startups",
                                fontSize = MaterialTheme.typography.labelSmall.fontSize
                            )
                            Text(
                                text = "1400 Lakh",
                                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                                fontWeight = FontWeight.SemiBold
                                , color = Color.Red
                            )
                        }
                        Column(
                            modifier
                                .fillMaxWidth()
                                .weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Investment By Incubator",
                                fontSize = MaterialTheme.typography.labelSmall.fontSize
                            )
                            Text(
                                text = "140 Lakhs",
                                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                                fontWeight = FontWeight.SemiBold
                                , color = Color.Red
                            )
                        }

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
                PartnerPage()
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
                                  successStoriesVisible = !successStoriesVisible
                              }
                              .size(20.dp), tint = Color.Gray)


                    }
                }
                Success()
            }
            }

AnimatedVisibility(visible = successStoriesVisible) {
    Dialog(onDismissRequest = { successStoriesVisible = false },
        properties = DialogProperties(usePlatformDefaultWidth = false, decorFitsSystemWindows = false, securePolicy = SecureFlagPolicy.SecureOff)) {
        Column(modifier.fillMaxSize()) {
            SuccessStoriesPage()
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