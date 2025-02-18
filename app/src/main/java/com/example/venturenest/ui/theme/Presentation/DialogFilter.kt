package com.example.venturenest.ui.theme.Presentation

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import java.security.KeyStore.TrustedCertificateEntry

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun DialogFilter(
    modifier: Modifier = Modifier,
    show: MutableState<Boolean>
) {

    AnimatedVisibility(visible = show.value) {
        var checkbox1 by remember {
            mutableStateOf(false)
        }
        var select by remember {
            mutableStateOf(0)
        }
        var checkbox2 by remember {
            mutableStateOf(false)
        }

        var checkbox3 by remember {
            mutableStateOf(false)
        }

        var checkbox4 by remember {
            mutableStateOf(false)
        }
        LaunchedEffect(key1 = checkbox1) {
            checkbox2 = false
            checkbox3= false
            checkbox4 = false
        }


        var schroll = rememberScrollState()

        var showBotom = show
        ModalBottomSheet(onDismissRequest = { show.value = false }, scrimColor = Color.Transparent,
            shape = RoundedCornerShape(topEnd = 100f, topStart = 100f),
            containerColor = Color.White, modifier = Modifier.offset(y = 20.dp)) {
            Column(
                modifier
                    .fillMaxWidth()
                    .wrapContentHeight(), verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Choose your filter",
                    modifier
                        .padding(top = 10.dp, bottom = 20.dp)
                        .fillMaxWidth(0.9f),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize
                )

                Row (
                    modifier
                        .padding(bottom = 20.dp)
                        .fillMaxWidth()
                        .height(50.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly){
OutlinedButton(onClick = { select = 0 },
    shape = RoundedCornerShape(25f)
, colors = ButtonDefaults.outlinedButtonColors(containerColor =  if (select==0) Color.Gray else Color.White)) {
    Text(text = "Events" ,color =if (select==0) Color.White else Color.Black)
}
                    OutlinedButton(onClick = { select = 1 },
                        shape = RoundedCornerShape(25f)
                        , colors = ButtonDefaults.outlinedButtonColors(containerColor =  if (select==1) Color.Gray else Color.White)) {
                        Text(text = "News", color = if (select==1) Color.White else Color.Black)
                    }
                }
                Card(
                    modifier
                        .padding(bottom = 20.dp)
                        .fillMaxWidth(0.9f)
                        .wrapContentHeight()
                ) {
                    Row(modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIos,
                            contentDescription = "",
                            modifier.padding(start = 10.dp)
                        )
                        Text(text = "All", modifier.padding(start = 10.dp))
                        Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                            Checkbox(checked = checkbox1, onCheckedChange = { checkbox1 = it })
                        }
                    }
                }

                Card(
                    modifier
                        .padding(bottom = 20.dp)
                        .fillMaxWidth(0.9f)
                        .wrapContentHeight()
                ) {
                    Row(modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIos,
                            contentDescription = "",
                            modifier.padding(start = 10.dp)
                        )
                        Text(text = "Past", modifier.padding(start = 10.dp))
                        Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                            Checkbox(checked = checkbox2, onCheckedChange = { checkbox2 = it },enabled = if (checkbox1)false else true)
                        }
                    }
                }

                Card(
                    modifier
                        .padding(bottom = 20.dp)
                        .fillMaxWidth(0.9f)
                        .wrapContentHeight()
                ) {
                    Row(modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIos,
                            contentDescription = "",
                            modifier.padding(start = 10.dp)
                        )
                        Text(text = "Current", modifier.padding(start = 10.dp))
                        Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                            Checkbox(checked = checkbox3, onCheckedChange = { checkbox3 = it },enabled = if (checkbox1)false else true)
                        }
                    }
                }
                Card(
                    modifier
                        .padding(bottom = 30.dp)
                        .fillMaxWidth(0.9f)
                        .wrapContentHeight()
                ) {
                    Row(modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIos,
                            contentDescription = "",
                            modifier.padding(start = 10.dp)
                        )
                        Text(text = "Upcoming", modifier.padding(start = 10.dp))
                        Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                            Checkbox(checked = checkbox4, onCheckedChange = { checkbox4 = it }, enabled = if (checkbox1)false else true)
                        }
                    }
                }


            }
        }
    }
}

var sho = mutableStateOf(true)
@Preview
@Composable
private fun PreviewDialogFilter() {
    DialogFilter(show = sho)
}