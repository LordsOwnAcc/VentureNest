package com.example.venturenest.ui.theme.Presentation.Main

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.venturenest.ui.theme.Presentation.CardElement2
import com.example.venturenest.ui.theme.Presentation.Dialog
import com.example.venturenest.ui.theme.Presentation.DialogFilter
import com.example.venturenest.ui.theme.Presentation.backgroundColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsPage(
  modifier: Modifier = Modifier
  ,
  window :WindowInsets

) {
    var filterSelected = remember {
        mutableStateOf(false)
    }

    var selected1 = remember {
        mutableStateOf(false)
    }

    Column(
        modifier
                   .windowInsetsPadding(window)
            .padding(bottom = 65.dp)
            .fillMaxSize()
            .background(backgroundColor)
            , verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally) {
//        Row(
//            modifier
//                .fillMaxWidth(1f)
//                .fillMaxHeight(0.05f)
//                .background(Color(0xffdd1212))
//            , verticalAlignment = Alignment.CenterVertically
//            , horizontalArrangement = Arrangement.Center
//        ) {
//
//
//        Row (
//            modifier
//                .fillMaxWidth(0.9f)
//                .fillMaxHeight(1f)
//
//
//            , verticalAlignment = Alignment.CenterVertically){
//            AsyncImage(model = "https://www.cgc.ac.in/public/course/assets/images/header-footer/cgc-jhanjeri-logo-white.png", contentDescription = "",
//                modifier
//                    .fillMaxHeight()
//                    .fillMaxWidth(0.3f))
//            Row (modifier.fillMaxWidth(0.95f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End){
//                Text(text = "Events", color = Color.White, fontSize = MaterialTheme.typography.titleLarge.fontSize, fontWeight = FontWeight.ExtraBold)
//            }
//
//        }
//
//
//        }


        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.1f)
            ,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(text = "Events",
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.Bold)
            Row(modifier.fillMaxWidth()  ,
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Top) {
                IconButton(onClick = { filterSelected.value = !filterSelected.value },modifier.offset(x=10.dp)) {
                    Icon(imageVector = Icons.Default.Tune, contentDescription = "", tint =Color.Black)
                }
            }
        }

        var search by remember {
            mutableStateOf("")
        }
        Row (
            modifier
                .padding(bottom = 20.dp)
                .fillMaxWidth()
                .height(50.dp)
        , horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
Row (
    modifier
        .clip(RoundedCornerShape(50f))
        .fillMaxWidth(0.9f)
        .fillMaxHeight()
        .border(1.dp, Color.Black, RoundedCornerShape(50f)), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.Bottom){
TextField(value = search, onValueChange ={search = it},
    modifier
        .clip(RoundedCornerShape(50f))
        .fillMaxSize(),
   colors = TextFieldDefaults.textFieldColors(
       containerColor = Color.White ,


        unfocusedIndicatorColor = Color.Transparent, focusedIndicatorColor = Color.Transparent)
, keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search ,),
    placeholder = { Text(text = "Search")}, leadingIcon = { Icon(
        imageVector = Icons.Default.Search,
        contentDescription = ""
    )}, maxLines = 1, trailingIcon = {if (search != ""){

        Icon(imageVector =Icons.Default.Clear , contentDescription ="",modifier.clickable {
            search = ""

        } )
    }})
}
        }

//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth(0.9f)
//                .height(35.dp),
//            horizontalArrangement = Arrangement.SpaceAround,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
////            OutlinedButton(
////                onClick = { selected = 0 },
////                modifier =
////                Modifier
////                    .fillMaxWidth(0.29f)
////                    .fillMaxHeight()
////                    ,
////                shape = RoundedCornerShape(25),
////                colors = ButtonDefaults.outlinedButtonColors(containerColor = if (selected == 0) Color(0xffdd1212) else Color.White)
////            ) {
////                Row(modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
////                    horizontalArrangement = Arrangement.SpaceEvenly) {
////
////
////                    Text(
////                        text = "Past",
////                        color = if (selected == 0) Color.White else Color.Black,
////                        textAlign = TextAlign.Left
////                    )
////
////                    AnimatedVisibility(visible = selected == 0) {
////                        Icon(
////                            imageVector = Icons.Default.Clear,
////                            contentDescription = "",
////                            tint = Color.Black,
////                            modifier = Modifier
////                                .size(18.dp)
////                                .clickable {
////                                    selected = -1
////                                }
////                        )
////                    }
////                }
////            }
////            OutlinedButton(
////                onClick = { selected = 1 },
////                modifier = Modifier
////                    .fillMaxWidth(0.45f)
////                    .fillMaxHeight()
//////                    .padding(start = 10.dp, end = 10.dp)
////                    ,
////                shape = RoundedCornerShape(25),
////                colors = ButtonDefaults.outlinedButtonColors(containerColor = if (selected == 1) Color(0xffdd1212) else Color.White)
////            ) {
////                Row(modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
////                    horizontalArrangement = Arrangement.SpaceBetween) {
////
////
////                    Text(
////                        text = "Current",
////                        color = if (selected == 1) Color.White else Color.Black,
////                        textAlign = TextAlign.Left
////                    )
////
////                    AnimatedVisibility(visible = selected == 1) {
////                        Icon(
////                            imageVector = Icons.Default.Clear,
////                            contentDescription = "",
////                            tint = Color.Black,
////                            modifier = Modifier
////                                .size(18.dp)
////                                .clickable {
////                                    selected = -1
////                                }
////                        )
////                    }
////                }
////            }
////            OutlinedButton(
////                onClick = { selected = 2 },
////                modifier = Modifier
////                    .fillMaxWidth(0.7f)
////                    .fillMaxHeight(),
////                shape = RoundedCornerShape(25),
////                colors = ButtonDefaults.outlinedButtonColors(containerColor = if (selected == 2) Color(0xffdd1212) else Color.White)
////            ) {
////                Row(modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
////                    horizontalArrangement = Arrangement.SpaceBetween) {
////
////
////                    Text(
////                        text = "Upcoming",
////                        color = if (selected == 2) Color.White else Color.Black,
////                        textAlign = TextAlign.Left
////                    )
////
////                    AnimatedVisibility(visible = selected == 2) {
////                        Icon(
////                            imageVector = Icons.Default.Clear,
////                            contentDescription = "",
////                            tint = Color.Black,
////                            modifier = Modifier
////                                .size(18.dp)
////                                .clickable {
////                                    selected = -1
////                                }
////                        )
////                    }
////                }
////            }
//
//        }
        var schroll = rememberScrollState()
        Column(
            modifier
                .padding(top = 0.dp)
                .clip(RoundedCornerShape(topEnd = 80f, topStart = 80f))
                .fillMaxWidth(1f)
                .fillMaxHeight()
                .verticalScroll(schroll)
                .background(
                    backgroundColor
                ), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {

            //Search Logic Here

            CardElement2(onClick = {selected1.value=true})
            CardElement2(onClick = {selected1.value=true})
            CardElement2(onClick = {selected1.value=true})




        }





DialogFilter(show =filterSelected )
        Dialog(show = selected1)


    }



}

//@Preview
//@Composable
//private fun PreviewEventsPage() {
//    EventsPage()
//}