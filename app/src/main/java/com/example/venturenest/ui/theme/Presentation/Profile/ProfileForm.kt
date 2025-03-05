package com.example.venturenest.ui.theme.Presentation.Profile


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem

import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ChangeCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults

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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction

import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.venturenest.R
import com.example.venturenest.ui.theme.DataBase.DataViewModel
import com.example.venturenest.ui.theme.DataBase.Users
import com.example.venturenest.ui.theme.bg


@Composable
fun ProfileScreen(modifier: Modifier = Modifier,users: Users
,exists:Boolean) {

val dataViewModel : DataViewModel= hiltViewModel()
    var name by remember { mutableStateOf(users.name) }
    var mobileNumber by remember { mutableStateOf(users.mobileNo) }
    var email by remember { mutableStateOf(users.email) }
    var rollNumber by remember { mutableStateOf(users.rollNo) }

    val membershipOptions = listOf("Leads","E-Cell", "VentureNest", "Not A Member",)
    var membershipExpanded by remember { mutableStateOf(false) }
    var selectedMembership by remember { mutableStateOf(membershipOptions[0]) }
    Column(
        modifier
            .fillMaxSize()
            .background(Color.White), verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally) {

        var boyChoosed by remember {
            mutableStateOf(true)
        }
        Row (
            modifier
                .fillMaxHeight(0.05f)
                .fillMaxWidth(0.95f), verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start){



            Text(text = "Profile",
                modifier
                    .fillMaxWidth()
                    ,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,

                textAlign = TextAlign.Center)

        }
Row (
    modifier
        .fillMaxHeight(0.3f)
        .fillMaxWidth()
        .background(Color.White), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
    Box(modifier = modifier.size(150.dp), contentAlignment = Alignment.BottomCenter) {
        Image(
            painter = painterResource(id = if (boyChoosed) R.drawable.boy else R.drawable.girl), contentDescription = "",
            modifier
                .size(150.dp)
                .clip(CircleShape)
                .border(1.dp, Color.Black, CircleShape),
            contentScale = ContentScale.FillHeight
        )


    Icon(imageVector = Icons.Default.ChangeCircle,
        contentDescription = "", tint = Color.White, modifier = modifier
            .scale(1.8f)
            .clickable { boyChoosed = !boyChoosed })
   }

}




        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
                , maxLines = 1
                , trailingIcon = {
                    if(name !=""){
                        Icon(
                            imageVector = Icons.Default.Clear
                            , contentDescription = "",
                            modifier.clickable {
                                name =""
                            }
                        )
                    }
                }
                , keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )

            OutlinedTextField(
                value = mobileNumber,
                onValueChange = { mobileNumber = it },
                label = { Text("Mobile Number") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1
                , trailingIcon = {
                    if(mobileNumber !=""){
                        Icon(
                            imageVector = Icons.Default.Clear
                            , contentDescription = "",
                            modifier.clickable {
                                mobileNumber =""
                            }
                        )
                    }
                }
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth()
                ,
                maxLines = 1
                , trailingIcon = {
                    if(email !=""){
                        Icon(
                            imageVector = Icons.Default.Clear
                            , contentDescription = "",
                            modifier.clickable {
                               email =""
                            }
                        )
                    }
                }
            )

            Box(modifier = Modifier.fillMaxWidth()) {
                Column {
                    Text(
                        text = "Membership",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .clickable { membershipExpanded = true }
                            .border(1.dp, Color.LightGray)
                            .padding(15.dp)

                    ) {
                        Text(text = selectedMembership, fontSize = 16.sp, color = Color.Black)
                    }
                    DropdownMenu(
                        expanded = membershipExpanded,
                        onDismissRequest = { membershipExpanded = false },
                        modifier = Modifier.fillMaxWidth(0.9f)) {
                        membershipOptions.forEach { option ->
                            DropdownMenuItem(onClick = {
                                selectedMembership = option
                                membershipExpanded = false
                            }) {
                                Text(option)
                            }
                        }
                    }
                }
            }

            OutlinedTextField(
                value = rollNumber,
                onValueChange = { rollNumber = it },
                label = { Text("Roll Number") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1
                , trailingIcon = {
                    if(rollNumber !=""){
                        Icon(
                            imageVector = Icons.Default.Clear
                            , contentDescription = "",
                            modifier.clickable {
                                rollNumber =""
                            }
                        )
                    }
                }
                , keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Text)
            )

            Button(
                onClick = {
                    if (exists){
                        dataViewModel.update(users = Users(id = users.id,name=name, mobileNo = mobileNumber,
                            email = email, memberShip = selectedMembership, rollNo = rollNumber, isSelected = true, isBoySelected = boyChoosed))
                    }else{

dataViewModel.insert(users = Users(name=name, mobileNo = mobileNumber,
    email = email, memberShip = selectedMembership, rollNo = rollNumber, isSelected = true, isBoySelected = boyChoosed))


                    }




                },
                modifier = Modifier.fillMaxWidth()
                , shape = RoundedCornerShape(25f)
                , colors = ButtonDefaults.buttonColors(containerColor = bg)
            ) {
                Text("Submit")
            }
        }



    }



}

//@Preview
//@Composable
//fun ProfPrev(modifier: Modifier = Modifier) {
//ProfileScreen()
//}