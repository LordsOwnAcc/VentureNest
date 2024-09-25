package com.example.venturenest.ui.theme.Presentation.Main

import androidx.annotation.RawRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowRightAlt
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformSpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.venturenest.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnBoarding(
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(0, 0f) { 3 }
    var coritine = rememberCoroutineScope()

    Column(
        modifier
            .fillMaxSize()
            .background(Color.Companion.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        HorizontalPager(state = pagerState) { page ->

            Column(
                modifier
                    .fillMaxSize()
                    .background(Color.Companion.White),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                if (page == 0) {


                    Column(
                        modifier
                            .fillMaxWidth(0.9f)
                            .fillMaxHeight(0.5f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img),
                            contentDescription = "",
                            modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Column(
                        modifier
                            .fillMaxWidth(0.9f)
                            .wrapContentHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Text(
                            text = "Welcome To",
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            fontWeight = FontWeight.Bold,
                            lineHeight = MaterialTheme.typography.titleLarge.lineHeight
                        )
                        Text(
                            text = "VentureNest",
                            fontSize = MaterialTheme.typography.displayMedium.fontSize,
                            fontWeight = FontWeight.Bold,
                            lineHeight = MaterialTheme.typography.displayMedium.lineHeight

                        )
                        Text(text = "Fostering Innovation | Connecting Entrepreneurs")
                    }

                    Row(
                        modifier
                            .padding(bottom = 20.dp)
                            .fillMaxWidth(0.9f)
                            .fillMaxHeight(),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.Start
                    ) {

                        TextButton(onClick = {
                            coritine.launch { pagerState.animateScrollToPage(2) }
                        }) {
                            androidx.compose.material3.Text(
                                text = "Skip",
                                modifier.padding(end = 20.dp)
                            )
                        }
                        Row(
                            modifier.fillMaxWidth(), horizontalArrangement =
                            Arrangement.End
                        ) {
                            Button(
                                modifier = modifier.fillMaxWidth(), onClick = {
                                    coritine.launch {
                                        pagerState.animateScrollToPage(1)
                                    }

                                }, shape = RoundedCornerShape(50f)
                            ) {
                                Row(
                                    modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowRightAlt,
                                        contentDescription = "", modifier = modifier.size(40.dp)
                                    )
                                }
                            }
                        }

                    }


                } else if (page==1) {
                    var userName by remember {
                        mutableStateOf("")
                    }
                    var password by remember {
                        mutableStateOf("")
                    }
                    var email by remember {
                        mutableStateOf("")
                    }

                    var showPassword by remember {
                        mutableStateOf(false)
                    }

                    Column(
                        modifier
                            .fillMaxWidth(0.9f)
                            .fillMaxHeight(0.4f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.sign_up_bro),
                            contentDescription = "",
                            modifier.fillMaxSize(0.8f),
                            contentScale = ContentScale.Crop
                        )

                    }
                    Column(
                        modifier
                            .fillMaxWidth(0.9f)
                            .wrapContentHeight(), verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "SignUp",
                            modifier = modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontSize = MaterialTheme.typography.displayLarge.fontSize.div(2),
                            fontWeight = FontWeight.Bold,
                            lineHeight = MaterialTheme.typography.displayLarge.lineHeight
                        )

                    }
                    Column(
                        modifier
                            .fillMaxHeight()
                            .fillMaxWidth(0.8f),
                         verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TextField(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(bottom = 10.dp),
                            value = email,
                            onValueChange = { email = it },
                            colors = androidx.compose.material.TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.White,
                                unfocusedIndicatorColor = Color.LightGray,
                                focusedIndicatorColor = Color.Gray
                            ),
                            placeholder = {
                                Text(text = "Email")
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Email, contentDescription = "",
                                    modifier.offset(x = -10.dp)
                                )
                            },
                            trailingIcon = {
                                if (email != "") {


                                    Icon(imageVector = Icons.Default.Clear, contentDescription = "",
                                        modifier
                                            .size(20.dp)
                                            .clickable {
                                                email = ""
                                            }
                                    )
                                }
                            },
                            singleLine = true,
                            maxLines = 1

                        )
                        TextField(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(bottom = 10.dp),
                            value = userName,
                            onValueChange = { userName = it },
                            colors = androidx.compose.material.TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.White,
                                unfocusedIndicatorColor = Color.LightGray,
                                focusedIndicatorColor = Color.Gray
                            ),
                            placeholder = {
                                Text(text = "Full Name")
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Person, contentDescription = "",
                                    modifier.offset(x = -10.dp)
                                )
                            },
                            trailingIcon = {
                                if (userName != "") {


                                    Icon(imageVector = Icons.Default.Clear, contentDescription = "",
                                        modifier
                                            .size(20.dp)
                                            .clickable {
                                                userName = ""
                                            }
                                    )
                                }
                            },
                            singleLine = true,
                            maxLines = 1

                        )
                        TextField(
                            modifier = modifier.fillMaxWidth(),
                            visualTransformation = if(!showPassword) PasswordVisualTransformation() else VisualTransformation.None,
                            value = password,
                            onValueChange = { password = it },
                            colors = androidx.compose.material.TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.White,
                                unfocusedIndicatorColor = Color.LightGray,
                                focusedIndicatorColor = Color.Gray
                            ),
                            placeholder = {
                                Text(text = "Password")
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Email, contentDescription = "",
                                    modifier.offset(x = -10.dp)
                                )
                            },
                            trailingIcon = {



                                    Icon(imageVector = if(showPassword) Icons.Default.Visibility else Icons.Default.VisibilityOff, contentDescription = "",
                                        modifier
                                            .size(20.dp)
                                            .clickable {
                                                showPassword = !showPassword
                                            }
                                        , tint = if(password == "") Color.LightGray else Color.Gray
                                    )

                            },
                            singleLine = true,
                            maxLines = 1

                        )
                        Text(
                            text = "By signing up, you are agreeing to our Terms and Condition and Privacy Policy",
                            modifier.padding(top = 20.dp),
                            fontSize = MaterialTheme.typography.labelSmall.fontSize
                        )
                        Column(
                            modifier
                                .fillMaxHeight()
                                .fillMaxWidth(0.9f), verticalArrangement = Arrangement.Bottom) {




                            Row(
                                modifier
                                    .padding(bottom = 20.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.Bottom,
                                horizontalArrangement = Arrangement.Start
                            ) {

                                TextButton(onClick = {
                                    coritine.launch {
                                        pagerState.animateScrollToPage(0)
                                    }

                                }) {
                                    androidx.compose.material3.Text(
                                        text = "Back",
                                        modifier.padding(end = 20.dp)
                                    )
                                }
                                Row(
                                    modifier.fillMaxWidth(), horizontalArrangement =
                                    Arrangement.End
                                ) {
                                    Button(
                                        modifier = modifier.fillMaxWidth(), onClick = {
                                            coritine.launch {
                                                pagerState.animateScrollToPage(1)
                                            }

                                        }, shape = RoundedCornerShape(50f)
                                    ) {
                                        Row(
                                            modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.End,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(text = "Continue")
                                            Icon(
                                                imageVector = Icons.Default.ArrowRightAlt,
                                                contentDescription = "",
                                                modifier = modifier.size(30.dp)
                                            )
                                        }
                                    }
                                }

                            }
                            Row(
                                modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = "Joined Us Before ? ")
                                TextButton(onClick = { coritine.launch {
                                    pagerState.animateScrollToPage(2)
                                }}) {
                                    Text(text = "Log In")
                                }
                            }

                    }


                }


                }else{
                    var userName by remember {
                        mutableStateOf("")
                    }
                    var Password by remember {
                        mutableStateOf("")
                    }
                    var showPassword by remember {
                        mutableStateOf(false)
                    }

                    Column(
                        modifier
                            .fillMaxWidth(0.9f)
                            .fillMaxHeight(0.4f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.login_pana),
                            contentDescription = "",
                            modifier.fillMaxSize(0.8f),
                            contentScale = ContentScale.Crop
                        )

                    }
                    Column(
                        modifier
                            .fillMaxWidth(0.9f)
                            .wrapContentHeight(), verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "Login",
                            modifier = modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontSize = MaterialTheme.typography.displayLarge.fontSize.div(2),
                            fontWeight = FontWeight.Bold,
                            lineHeight = MaterialTheme.typography.displayLarge.lineHeight
                        )

                    }
                    Column(
                        modifier
                            .fillMaxHeight()
                            .fillMaxWidth(0.8f),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TextField(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(bottom = 10.dp),
                            value = userName,
                            onValueChange = { userName = it },
                            colors = androidx.compose.material.TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.White,
                                unfocusedIndicatorColor = Color.LightGray,
                                focusedIndicatorColor = Color.Gray
                            ),
                            placeholder = {
                                Text(text = "Email")
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Email, contentDescription = "",
                                    modifier.offset(x = -10.dp)
                                )
                            },
                            trailingIcon = {
                                if (userName != "") {


                                    Icon(imageVector = Icons.Default.Clear, contentDescription = "",
                                        modifier
                                            .size(20.dp)
                                            .clickable {
                                                userName = ""
                                            }
                                    )
                                }
                            },
                            singleLine = true,
                            maxLines = 1

                        )

                        TextField(
                            modifier = modifier.fillMaxWidth(),
                            visualTransformation = if(!showPassword) PasswordVisualTransformation() else VisualTransformation.None,
                            value = Password,
                            onValueChange = { Password = it },
                            colors = androidx.compose.material.TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.White,
                                unfocusedIndicatorColor = Color.LightGray,
                                focusedIndicatorColor = Color.Gray
                            ),
                            placeholder = {
                                Text(text = "Password")
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Email, contentDescription = "",
                                    modifier.offset(x = -10.dp)
                                )
                            },
                            trailingIcon = {



                                Icon(imageVector = if(showPassword) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                    contentDescription = "",
                                    modifier
                                        .size(20.dp)
                                        .clickable {
                                            showPassword = !showPassword
                                        }
                                    , tint = if(Password == "") Color.LightGray else Color.Gray
                                )

                            },
                            singleLine = true,
                            maxLines = 1

                        )

                        Column(
                            modifier
                                .fillMaxHeight()
                                .fillMaxWidth(0.9f), verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {


                        Column (
                            modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.6f),
                            horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly){
                            Button(onClick = { /*TODO*/ },modifier.fillMaxWidth(0.8f), shape = RoundedCornerShape(25f)) {
                                Text("Login")
                            }
                            Row (modifier.fillMaxWidth(0.7f), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
Spacer(modifier = modifier
    .fillMaxWidth(0.39f)
    .height(2.dp)
    .background(Color.DarkGray))
Text(text = "or",modifier.padding(start = 10.dp, end = 10.dp))
                                Spacer(modifier = modifier
                                    .fillMaxWidth(0.9f)
                                    .height(2.dp)
                                    .background(Color.DarkGray))

                            }

                            Button(onClick = {  },
                                modifier.fillMaxWidth(0.8f),
                                shape = RoundedCornerShape(25f),
                                colors = androidx.compose.material.ButtonDefaults
                                    .buttonColors(backgroundColor = Color.LightGray)) {
                              Row(modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
                                  horizontalArrangement = Arrangement.Absolute.SpaceEvenly) {
Image(
    painter = painterResource(id = R.drawable.google_icon_icons_com_62736),
    contentDescription ="" ,modifier.size(20.dp))

                                  Text("Login with Google")
                              }
                              }
                        }


                            Row(
                                modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = "New to VentureNest ?")
                                TextButton(onClick = {coritine.launch {
                                    pagerState.animateScrollToPage(1)
                                } }) {
                                    Text(text = "SignUp")
                                }
                            }

                        }


                    }

                }

            }


        }

    }


}

@Preview
@Composable
private fun PreviewOnBoarding() {
    OnBoarding()
}