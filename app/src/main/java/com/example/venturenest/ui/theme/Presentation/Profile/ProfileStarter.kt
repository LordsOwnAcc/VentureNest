package com.example.venturenest.ui.theme.Presentation.Profile

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.GridOn
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.venturenest.R
import com.example.venturenest.ui.theme.DataBase.DataViewModel
import com.example.venturenest.ui.theme.DataBase.Users
import com.example.venturenest.ui.theme.Navigation.HomePage
import kotlin.math.truncate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileStarter(modifier: Modifier = Modifier
,navController: NavController) {
    var animatedVisible by remember {
        mutableStateOf(false)
    }
    val dataviewModel:DataViewModel= hiltViewModel()
    val list = dataviewModel._getallUser.collectAsState(initial = emptyList())
    val isanyPofileSelected = list.value.any { it.isSelected ==true }



    Column(modifier.fillMaxSize(), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.1f)
            ,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(text = "Profiles", fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.titleLarge.fontSize )

        }

if (list.value.isNullOrEmpty()){

    Column(modifier.fillMaxSize()
    , verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
     Image(painter = painterResource(id = R.drawable.nothingfound), contentDescription = "",
         modifier
             .padding(bottom = 0.dp)
             .size(250.dp)
     , contentScale = ContentScale.FillBounds)
        Text(text = "Oops! no profile found "+"\n"+ "Create your profile to continue to Venturenest",
            modifier
                .padding(bottom = 10.dp)
                .fillMaxWidth(0.8f)
        , maxLines = 3, overflow = TextOverflow.Ellipsis, textAlign = TextAlign.Center)
        Button(onClick = {
            animatedVisible=true
            Log.d("Button","Clicked")
        }, shape = RoundedCornerShape(25f), colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = Color.Black)) {
Text(text = "Create Profile ", color = Color.White)        }
    }

}else if (isanyPofileSelected==true){
   LaunchedEffect(key1 = Unit) {
       navController.navigate(HomePage)
   }

}else {
    list.value.forEach {

        Row (modifier = modifier
            .padding(top = 10.dp, bottom = 10.dp)
            .fillMaxWidth(0.9f)
            .border(1.dp, Color.LightGray, RoundedCornerShape(25f))
            .height(80.dp), verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start){
            Image(painter = painterResource(id = if (it.isBoySelected) R.drawable.boy else R.drawable.girl), contentDescription =""
                ,
                modifier
                    .padding(start = 10.dp, end = 10.dp)
                    .size(50.dp)
                    .clip(CircleShape))
            Column(
                modifier
                    .fillMaxHeight()
                    .wrapContentWidth(),
                verticalArrangement = Arrangement.Center) {
                Text(text = it.name, fontWeight = FontWeight.SemiBold)
                Text(text = it.memberShip
                    , fontSize = MaterialTheme.typography.labelSmall.fontSize)
            }
            Row(
                modifier
                    .fillMaxWidth()
                    .fillMaxHeight(), verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End) {


                Icon(
                    imageVector = Icons.Default.ArrowForwardIos,
                    contentDescription = "",
                    modifier.padding(end = 10.dp)
                    , tint = Color.LightGray
                )
            }

        }
    }



}

        AnimatedVisibility(visible = animatedVisible) {
            ModalBottomSheet(onDismissRequest = {
                animatedVisible=false
            }, shape = RectangleShape, containerColor = Color.Transparent) {

                ProfileScreen(users = Users(0,"","","","","",false,true), exists = false)
            }
        }




    }
}