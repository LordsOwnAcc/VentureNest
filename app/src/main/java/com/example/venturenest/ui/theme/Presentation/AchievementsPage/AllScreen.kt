package com.example.venturenest.ui.theme.Presentation.AchievementsPage

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Text
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.venturenest.ui.theme.DaggerHilt.ViewModels.LoadingStateViewmodel
import com.example.venturenest.ui.theme.Presentation.helper.ChangeStatusBarColorEdgeToEdge
import com.example.venturenest.ui.theme.background

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun startupsScreen(
    windowInsets: WindowInsets, modifier: Modifier,
    search2: String?,
    type: String, contain: String
) {

    ChangeStatusBarColorEdgeToEdge(background)
    var search by remember {
        mutableStateOf("")
    }
    LaunchedEffect(Unit) {
        search = search2.toString()
    }
    val viewModel: LoadingStateViewmodel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    Column(
        modifier
            .fillMaxSize()
            .windowInsetsPadding(windowInsets)

            .background(background)
            .padding(top = 10.dp), verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier
                .padding(bottom = 10.dp)
                .fillMaxWidth(0.9f)
                .height(55.dp), horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier
                    .clip(RoundedCornerShape(15f))
                    .fillMaxWidth(0.95f)
                    .fillMaxHeight()
                    .border(
                        1.dp,
                        Color.LightGray,
                        RoundedCornerShape(15f)
                    ),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                TextField(
                    value = search,
                    onValueChange = { search = it },
                    modifier
                        .clip(RoundedCornerShape(15f))
                        .fillMaxSize(),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White,
textColor = Color.Gray,

                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    ),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    placeholder = { Text(text = "Search in Startups",modifier
                    , color = Color.Gray) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "",modifier.scale(0.8f)
                        )
                    },
                    textStyle = TextStyle(fontSize = MaterialTheme.typography.body1.fontSize),
                    maxLines = 1,
                    trailingIcon = {
                        if (search != "") {

                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "",
                                modifier.clickable {
                                    search = ""

                                }.scale(0.8f),
                                tint = Color.Gray
                            )
                        }
                    })
            }
        }

        val filteredStartups = state.Data.startUp.filter { startup ->
            val matchesSearch = listOf(
                startup.StartupName,
                startup.ProductName,
                startup.FounderName
            ).any { it.contains(search, ignoreCase = true) }

            when (type) {
                "reg" -> matchesSearch && startup.RegistrationStatus.contains(
                    contain,
                    ignoreCase = true
                )

                "type" -> matchesSearch && startup.StartupType.contains(contain, ignoreCase = true)
                else -> false
            }
        }
        FlowRow(
            modifier
                .fillMaxHeight(1f)
                .fillMaxWidth(0.85f)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top, horizontalArrangement = Arrangement.Center
        ) {
// Now show cards
            filteredStartups.forEach { startup ->

                StartUpCard(startUp = startup)
            }


        }

    }


}