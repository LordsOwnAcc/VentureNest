package com.example.venturenest.ui.theme.Presentation.Login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.venturenest.R
import com.example.venturenest.ui.theme.DaggerHilt.ViewModels.AuthViewModel
import com.example.venturenest.ui.theme.Navigation.HomePage
import com.example.venturenest.ui.theme.Navigation.LoginPage
import com.example.venturenest.ui.theme.Navigation.SignUpPage
import com.example.venturenest.ui.theme.Navigation.Start
import com.example.venturenest.ui.theme.Presentation.helper.ChangeStatusBarColorEdgeToEdge
import com.example.venturenest.ui.theme.Presentation.helper.HideSystemBars
import com.example.venturenest.ui.theme.auth.AuthStateCompanion
import com.example.venturenest.ui.theme.auth.isValidEmail
import com.example.venturenest.ui.theme.auth.isValidPassword
import com.example.venturenest.ui.theme.auth.rememberFirebaseAuthLauncher
import com.google.android.gms.auth.api.signin.GoogleSignIn
import kotlinx.coroutines.launch

@Composable
fun LoginPage(windowInsets: WindowInsets,navController: NavController,modifier: Modifier = Modifier) {
    HideSystemBars()
    ChangeStatusBarColorEdgeToEdge(color = Color.White)
    Column(
        modifier
            .windowInsetsPadding(windowInsets)
            .fillMaxSize()
            .background(Color.Companion.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val context = LocalContext.current

        val authViewModel : AuthViewModel = hiltViewModel()
        val state = authViewModel.authState.collectAsState()
        val launcher = rememberFirebaseAuthLauncher(
            onSuccess = {

                if (authViewModel.repository.firebaseAuth.currentUser?.displayName != null) {

                    authViewModel.onGoogleSuccess()
                }
            },
            onError = {
                    error->
                authViewModel.onGoogleFailure(error.localizedMessage)
            },
            onCancelles = {
                authViewModel.onGoogleFailure("Cancelled")

            }


        )


        LaunchedEffect(key1 = state.value.state == AuthStateCompanion.Error) {
            if (state.value.state == AuthStateCompanion.Error) {
                Toast.makeText(context, state.value.error, Toast.LENGTH_SHORT).show()
            }
        }
        LaunchedEffect(key1 = state.value.state == AuthStateCompanion.CreatedUser) {
            if (state.value.state == AuthStateCompanion.CreatedUser) {
                Toast.makeText(context,"Account created successfully", Toast.LENGTH_SHORT).show()
            }

        }
        LaunchedEffect(key1 = state.value.state == AuthStateCompanion.UserExist) {
            if ( state.value.state == AuthStateCompanion.UserExist) {
                Toast.makeText(context,"Logged in Successfully", Toast.LENGTH_SHORT).show()
                navController.navigate(Start){
                    popUpTo(Start){
                        inclusive=true
                    }
                }

            }
        }

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
                .fillMaxHeight(0.5f),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.login))
            val progress by animateLottieCompositionAsState(composition, iterations = 500)
            LottieAnimation(
                composition = composition,
                progress = { progress },
                modifier = Modifier.fillMaxWidth().padding(bottom = 0.dp, top = 30.dp)
            )

        }
        Column(
            modifier
                .fillMaxWidth(0.9f)
                .wrapContentHeight(), verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Login to connect with us and get latest updates",
                modifier = modifier.fillMaxWidth(0.8f).padding(top = 30.dp),
                textAlign = TextAlign.Center,
                fontSize = MaterialTheme.typography.displaySmall.fontSize.div(1.5),
                fontWeight = FontWeight.Bold,
                lineHeight = MaterialTheme.typography.titleLarge.lineHeight
            )
            Text(
                text = "Note that by logging in you are accepting to our terms and condition and privacy policy",
                modifier = modifier.fillMaxWidth(0.8f).padding(top = 20.dp),
                textAlign = TextAlign.Center,
                fontSize = MaterialTheme.typography.labelMedium.fontSize,
                fontWeight = FontWeight.Bold,
                lineHeight = MaterialTheme.typography.labelLarge.lineHeight
                , color = Color.Gray
            )

        }
        Column(
            modifier
                .fillMaxHeight()
                .fillMaxWidth(0.8f),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            TextField(
//                modifier = modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 10.dp),
//                value = userName,
//                onValueChange = { userName = it },
//                colors = androidx.compose.material.TextFieldDefaults.textFieldColors(
//                    backgroundColor = Color.White,
//                    unfocusedIndicatorColor = Color.LightGray,
//                    focusedIndicatorColor = Color.Gray
//                ),
//                placeholder = {
//                    Text(text = "Email")
//                },
//                leadingIcon = {
//                    Icon(
//                        imageVector = Icons.Default.Email, contentDescription = "",
//                        modifier.offset(x = -10.dp)
//                    )
//                },
//                trailingIcon = {
//                    if (userName != "") {
//
//
//                        Icon(imageVector = Icons.Default.Clear, contentDescription = "",
//                            modifier
//                                .size(20.dp)
//                                .clickable {
//                                    userName = ""
//                                }
//                        )
//                    }
//                },
//                singleLine = true,
//                maxLines = 1
//, enabled = state.value.state !=AuthStateCompanion.Loading
//            )
//
//            TextField(
//                modifier = modifier.fillMaxWidth(),
//                visualTransformation = if (!showPassword) PasswordVisualTransformation() else VisualTransformation.None,
//                value = Password,
//                onValueChange = { Password = it },
//                colors = androidx.compose.material.TextFieldDefaults.textFieldColors(
//                    backgroundColor = Color.White,
//                    unfocusedIndicatorColor = Color.LightGray,
//                    focusedIndicatorColor = Color.Gray
//                ),
//                placeholder = {
//                    Text(text = "Password")
//                },
//                leadingIcon = {
//                    Icon(
//                        imageVector = Icons.Default.Password, contentDescription = "",
//                        modifier.offset(x = -10.dp)
//                    )
//                },
//                trailingIcon = {
//
//
//                    Icon(imageVector = if (showPassword) Icons.Default.Visibility else Icons.Default.VisibilityOff,
//                        contentDescription = "",
//                        modifier
//                            .size(20.dp)
//                            .clickable {
//                                showPassword = !showPassword
//                            }, tint = if (Password == "") Color.LightGray else Color.Gray
//                    )
//
//                },
//                singleLine = true,
//                maxLines = 1, enabled = state.value.state !=AuthStateCompanion.Loading
//
//            )

            Column(
                modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.9f),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

//
//                Column(
//                    modifier
//                        .fillMaxWidth()
//                        .fillMaxHeight(0.6f),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.SpaceEvenly
//                ) {
//                    Button(
//                        onClick = {
//                            if (!isValidEmail(email = userName)){
//                                Toast.makeText(context,"Enter Valid Email",Toast.LENGTH_SHORT).show()
//                                return@Button
//                            }
//                            if (!isValidPassword(Password)){
//                                Toast.makeText(context,"Password must be 6+ chars, include a number & special char",Toast.LENGTH_SHORT).show()
//                                return@Button
//                            }
//
//                            //authViewModel.createUserWithEmailPassword(email, password)
//                            authViewModel.logInWithEmailPassword(userName, password = Password)
//
//
//
//                        }, enabled = state.value.state !=AuthStateCompanion.Loading , modifier =
//                        modifier.fillMaxWidth(),
//                        shape = RoundedCornerShape(25f)
//                        , colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black)
//                    ) {
//                        Text("Login", color = Color.White)
//                    }
//                    Row(
//                        modifier.fillMaxWidth(0.7f),
//                        horizontalArrangement = Arrangement.Center,
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Spacer(
//                            modifier = modifier
//                                .fillMaxWidth(0.39f)
//                                .height(2.dp)
//                                .background(Color.DarkGray)
//                        )
//                        Text(text = "or", modifier.padding(start = 10.dp, end = 10.dp))
//                        Spacer(
//                            modifier = modifier
//                                .fillMaxWidth(0.9f)
//                                .height(2.dp)
//                                .background(Color.DarkGray)
//                        )
//
//                    }

//                    Button(
//                        onClick = {
//                            authViewModel.makeLoading()
//
//                            val googleSignInClient = try {
//                                GoogleSignIn.getClient(context, authViewModel.gso())
//                            } catch (e: Exception) {
//                                Toast.makeText(context, "Sign-in configuration failed: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
//                                authViewModel.onGoogleFailure("Error : Google Verification Not Available")
//                                return@Button
//                            }
//
//                            try {
//                                launcher.launch(googleSignInClient.signInIntent)
//                            } catch (e: Exception) {
//                                Toast.makeText(context, "Failed to start Google Sign-In: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
//                            }
//
//
//
//
//
//
//
//
//                        }, enabled = state.value.state !=AuthStateCompanion.Loading, modifier =
//                        modifier.fillMaxWidth(0.9f),
//                        shape = RoundedCornerShape(25f),
//                        colors = androidx.compose.material.ButtonDefaults
//                            .buttonColors(backgroundColor = Color.LightGray)
//                    ) {
//                        Row(
//                            modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
//                            horizontalArrangement = Arrangement.Absolute.SpaceEvenly                        ) {
//                            Image(
//                                painter = painterResource(id = R.drawable.google_icon_icons_com_62736),
//                                contentDescription = "", modifier.size(20.dp)
//                            )
//
//                            Text("Login with Google")
//                        }
//                    }



                ModernGoogleSignInButton(onClick = {
                    authViewModel.makeLoading()

                    val googleSignInClient = try {
                        GoogleSignIn.getClient(context, authViewModel.gso())
                    } catch (e: Exception) {
                        Toast.makeText(context, "Sign-in configuration failed: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
                        authViewModel.onGoogleFailure("Error : Google Verification Not Available")
                        return@ModernGoogleSignInButton
                    }

                    try {
                        launcher.launch(googleSignInClient.signInIntent)
                    } catch (e: Exception) {
                        Toast.makeText(context, "Failed to start Google Sign-In: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
                    }

                }, Modifier.fillMaxWidth(), enabled =state.value.state !=AuthStateCompanion.Loading, )
                }




            }


        }
    }


