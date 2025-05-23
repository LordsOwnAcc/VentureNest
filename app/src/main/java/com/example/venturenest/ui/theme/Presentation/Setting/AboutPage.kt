package com.example.venturenest.ui.theme.Presentation.Setting

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.venturenest.R
import com.example.venturenest.ui.theme.DaggerHilt.ViewModels.AuthViewModel
import com.example.venturenest.ui.theme.DataBase.DataViewModel
import com.example.venturenest.ui.theme.Navigation.AboutECell
import com.example.venturenest.ui.theme.Navigation.AboutVentureNest
import com.example.venturenest.ui.theme.Navigation.StartScreen
import com.example.venturenest.ui.theme.Presentation.helper.ChangeStatusBarColorEdgeToEdge
import com.example.venturenest.ui.theme.Presentation.helper.HideSystemBars
import com.example.venturenest.ui.theme.auth.AuthStateCompanion
import com.example.venturenest.ui.theme.background
import com.example.venturenest.ui.theme.bg
import com.example.venturenest.ui.theme.secondary
import com.example.venturenest.ui.theme.textColor

@SuppressLint("SuspiciousIndentation")
@Composable
fun AboutPage(
    modifier: Modifier = Modifier,
   window:WindowInsets
    ,navController: NavController
) {
  val authViewModel :AuthViewModel= hiltViewModel()
    var selected = remember { mutableStateOf(0) }
    var show = remember { mutableStateOf(false) }
    HideSystemBars()
    ChangeStatusBarColorEdgeToEdge(background)

    Box(
        modifier
            .fillMaxSize()
            .background(background)
            .windowInsetsPadding(window)
           , contentAlignment = Alignment.BottomCenter
        ) {
        Column(modifier.fillMaxSize(), verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Setting",
                    fontSize = MaterialTheme.typography.titleLarge.fontSize.times(1.3),
                    lineHeight = MaterialTheme.typography.titleLarge.lineHeight,
                    letterSpacing = MaterialTheme.typography.titleLarge.letterSpacing,
                    fontWeight = FontWeight.Medium,
                    color = textColor
                )
                Row(modifier.fillMaxWidth()  ,
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.Top) {

                }
            }


            Column(
                modifier
                    .padding(bottom = 66.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {

//                Text(text = "Profile", modifier.fillMaxWidth(0.89f)
//                , textAlign = TextAlign.Start,
//                    fontSize = MaterialTheme.typography.bodyMedium.fontSize
//                , lineHeight = MaterialTheme.typography.bodyMedium.lineHeight,
//                    letterSpacing = MaterialTheme.typography.bodyMedium
//                        .letterSpacing)

//
//                Row (modifier = modifier
//                    .padding(top = 10.dp, bottom = 10.dp)
//                    .fillMaxWidth(0.9f)
//                    .border(1.dp, Color.LightGray, RoundedCornerShape(25f))
//                    .height(80.dp), verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.Start){
//                Image(painter = painterResource(id = if (selected.isBoySelected)R.drawable.boy else R.drawable.girl), contentDescription =""
//                ,
//                    modifier
//                        .padding(start = 10.dp, end = 10.dp)
//                        .size(50.dp)
//                        .clip(CircleShape))
//                    Column(
//                        modifier
//                            .fillMaxHeight()
//                            .wrapContentWidth(),
//                        verticalArrangement = Arrangement.Center) {
//                        Text(text = selected.name, fontWeight = FontWeight.SemiBold)
//                        Text(text = selected.memberShip
//                        , fontSize = MaterialTheme.typography.labelSmall.fontSize)
//                    }
//                    Row(
//                        modifier
//                            .fillMaxWidth()
//                            .fillMaxHeight(), verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.End) {
//
//
//                        Icon(
//                            imageVector = Icons.Default.ArrowForwardIos,
//                            contentDescription = "",
//                            modifier.padding(end = 10.dp)
//                            , tint = Color.LightGray
//                        )
//                    }
//
//                }

//                Row (
//                    modifier
//                        .padding(top = 10.dp, bottom = 10.dp)
//                        .fillMaxWidth(0.9f)
//                        .height(150.dp)
//                        .clip(RoundedCornerShape(25f))
//                        .background(Color.White)
//                        .border(1.dp, Color.LightGray, RoundedCornerShape(25f)),
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.SpaceEvenly){
//
//                    Image(painter = painterResource(id = R.drawable.joinus), contentDescription = "",
//                        modifier = modifier
//                            .fillMaxWidth(0.4f)
//                            .fillMaxHeight(0.9f)
//                            .clip(RoundedCornerShape(25f))
//                    , contentScale = ContentScale.FillBounds)
//
//                    Column(
//                        modifier
//                            .fillMaxHeight(0.9f)
//                            .fillMaxWidth(0.9f), verticalArrangement = Arrangement.SpaceEvenly,
//                        horizontalAlignment = Alignment.CenterHorizontally) {
//                    Text(text = "Incubate Idea",
//                        fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Start,
//                        modifier = modifier.fillMaxWidth())
//                        Text(text = "Lora epsum lasu andthats y u ne" +
//                                "eded in v nest etc blah blah blah",
//                            textAlign = TextAlign.Start,
//                            fontSize = MaterialTheme.typography.labelSmall.fontSize,
//                            maxLines = 2, overflow = TextOverflow.Ellipsis)
//
//Button(
//    onClick = { /*TODO*/ },
//    modifier = modifier.fillMaxWidth(),
//    shape = RoundedCornerShape(25f),
//    colors = ButtonDefaults.buttonColors(bg.copy(alpha = 0.8f))
//) {
//   Text(text = "Incubate", color = Color.White)
//}
//                    }
//
//                }

                Text(text = "Profile",
                    modifier
                        .padding(top = 0.dp, bottom = 0.dp)
                        .fillMaxWidth(0.89f)
                    , textAlign = TextAlign.Start,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize
                    , lineHeight = MaterialTheme.typography.bodyMedium.lineHeight,
                    letterSpacing = MaterialTheme.typography.bodyMedium
                        .letterSpacing, color = secondary
                )
SettingElement(modifier = modifier, text ="Profile", onClick = {

})

                Text(text = "About",
                    modifier
                        .padding(top = 10.dp, bottom = 0.dp)
                        .fillMaxWidth(0.89f)
                    , textAlign = TextAlign.Start,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize
                    , lineHeight = MaterialTheme.typography.bodyMedium.lineHeight,
                    letterSpacing = MaterialTheme.typography.bodyMedium
                        .letterSpacing, color = secondary
                )
                SettingElement(modifier = modifier, text ="About E-Cell", onClick = {
navController.navigate(AboutECell)
                })
                SettingElement(modifier = modifier, text ="About Venturenest", onClick = {
navController.navigate(AboutVentureNest)
                })

                Text(text = "Privacy and Contact",
                    modifier
                        .padding(top = 10.dp, bottom = 0.dp)
                        .fillMaxWidth(0.89f)
                    , textAlign = TextAlign.Start,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize
                    , lineHeight = MaterialTheme.typography.bodyMedium.lineHeight,
                    letterSpacing = MaterialTheme.typography.bodyMedium
                        .letterSpacing, color = secondary
                )
                SettingElement(modifier = modifier, text ="Privacy and Policy", onClick = {
                    selected.value =0
                    show.value=true
                } )
SettingElement(modifier = modifier, text = "Terms and Condition" ) {
         selected.value =1
    show.value=true

}
                SettingElement(modifier = modifier, text ="Contact Us" ) {
selected.value=2
show.value=true

                }


                Text(text = "Danger Zone",
                    modifier
                        .padding(top = 10.dp, bottom = 10.dp)
                        .fillMaxWidth(0.89f)
                    , textAlign = TextAlign.Start,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize
                    , lineHeight = MaterialTheme.typography.bodyMedium.lineHeight,
                    letterSpacing = MaterialTheme.typography.bodyMedium
                        .letterSpacing, color = Color.Red)

                SettingElement(modifier = modifier, text ="Logout" ) {
                        authViewModel.SignOut()
                    authViewModel._authState.value=authViewModel._authState.value.copy(
                        state = AuthStateCompanion.NoUser
                    )
                            navController.navigate(StartScreen){
                                popUpTo(StartScreen){
                                    inclusive = true
                                }
                            }

                }



        }}
        Dialog2(show = show,selected=selected.value,modifier)
    }

}
