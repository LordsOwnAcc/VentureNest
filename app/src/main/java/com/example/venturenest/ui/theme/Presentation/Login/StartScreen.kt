package com.example.venturenest.ui.theme.Presentation.Login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRightAlt

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.venturenest.R
import com.example.venturenest.ui.theme.Navigation.LoginPage
import com.example.venturenest.ui.theme.Navigation.SignUpPage
import com.example.venturenest.ui.theme.Presentation.helper.ChangeStatusBarColorEdgeToEdge
import com.example.venturenest.ui.theme.Presentation.helper.HideSystemBars
import kotlinx.coroutines.launch

@Composable
fun StartScreen(windowInsets: WindowInsets,navController: NavController,modifier: Modifier = Modifier) {
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

navController.navigate(LoginPage)


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
navController.navigate(SignUpPage)

                }, shape = RoundedCornerShape(25f)
                , colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black)
            ) {
                Row(
                    modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowRightAlt,
                        contentDescription = "", modifier = modifier.size(40.dp)
                        , tint = Color.White
                    )
                }
            }
        }

    }
        }
}

