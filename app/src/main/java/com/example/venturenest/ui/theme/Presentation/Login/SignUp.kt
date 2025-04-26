package com.example.venturenest.ui.theme.Presentation.Login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRightAlt
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
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
import com.example.venturenest.R
import com.example.venturenest.ui.theme.DaggerHilt.ViewModels.AuthViewModel
import com.example.venturenest.ui.theme.Navigation.Forum
import com.example.venturenest.ui.theme.Navigation.HomePage
import com.example.venturenest.ui.theme.Navigation.LoginPage
import com.example.venturenest.ui.theme.Navigation.StartScreen
import com.example.venturenest.ui.theme.Presentation.helper.ChangeStatusBarColorEdgeToEdge
import com.example.venturenest.ui.theme.Presentation.helper.HideSystemBars
import com.example.venturenest.ui.theme.auth.AuthStateCompanion
import com.example.venturenest.ui.theme.auth.isValidEmail
import com.example.venturenest.ui.theme.auth.isValidPassword

@Composable
fun SignUpPage(windowInsets: WindowInsets,navController: NavController,modifier: Modifier = Modifier) {
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
                navController.navigate(HomePage){
                    popUpTo(HomePage){
                        inclusive=true
                    }
                }

            }
        }

        var verifyPassword by remember {
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
        var showPassword2 by remember {
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
                text = "Signup",
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
                modifier = modifier.fillMaxWidth(),
                visualTransformation = if(!showPassword2) PasswordVisualTransformation() else VisualTransformation.None,
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
                        imageVector = Icons.Default.Password, contentDescription = "",
                        modifier.offset(x = -10.dp)
                    )
                },
                trailingIcon = {



                    Icon(imageVector = if(showPassword2) Icons.Default.Visibility else Icons.Default.VisibilityOff, contentDescription = "",
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

            TextField(
                modifier = modifier.padding(top = 10.dp).fillMaxWidth(),
                visualTransformation = if(!showPassword) PasswordVisualTransformation() else VisualTransformation.None,
                value = verifyPassword,
                onValueChange = { verifyPassword = it },
                colors = androidx.compose.material.TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    unfocusedIndicatorColor = Color.LightGray,
                    focusedIndicatorColor = Color.Gray
                ),
                placeholder = {
                    Text(text = "Verify Password",modifier.offset(x=-3.dp))
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Password, contentDescription = "",
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
                        , tint = if(verifyPassword == "") Color.LightGray else Color.Gray
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
                    .fillMaxWidth(), verticalArrangement = Arrangement.Bottom) {




                Row(
                    modifier
                        .padding(bottom = 20.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Start
                ) {

                    TextButton(onClick = {
navController.navigate(StartScreen){
    popUpTo(StartScreen){
        inclusive=true
    }
}
//

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
                            modifier = modifier.fillMaxWidth(),
                           shape = RoundedCornerShape(25f)
                            ,onClick = {
                                if (!isValidEmail(email)){
                                    Toast.makeText(context,"Enter Valid Email",Toast.LENGTH_SHORT).show()
                                    return@Button
                                }
                                if (!isValidPassword(password)){
                                    Toast.makeText(context,"Password must be 6+ chars, include a number & special char",Toast.LENGTH_SHORT).show()
                                    return@Button
                                }

                                authViewModel.createUserWithEmailPassword(email, password)
                                authViewModel.logInWithEmailPassword(email,password)



                            }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black)
                        ) {
                            Row(
                                modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = "Continue",
                                    color = Color.White)
                                Icon(
                                    imageVector = Icons.Default.ArrowRightAlt,
                                    contentDescription = "",
                                    modifier = modifier.size(30.dp)
                                    , tint = Color.White
                                )
                            }
                        }
                    }

                }
                Row(
                    modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Already have an account ? ")
                    TextButton(onClick = {
navController.navigate(LoginPage)
{
    popUpTo(LoginPage){
        inclusive=true
    }
}
                    }) {
                        Text(text = "Log In")
                    }
                }

            }


        }


    }


}
