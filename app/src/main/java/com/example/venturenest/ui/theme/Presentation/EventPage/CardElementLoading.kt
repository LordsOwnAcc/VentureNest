package com.example.venturenest.ui.theme.Presentation.EventPage

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.venturenest.ui.theme.DaggerHilt.Events
import com.example.venturenest.ui.theme.Presentation.helper.ShimmerEffect

@Composable
fun CardElementLoading(
    modifier: Modifier = Modifier


) {



    Column(
        modifier
            .padding(bottom = 20.dp)
            .clip(RoundedCornerShape(15f))
            .border(1.dp, ShimmerEffect(), RoundedCornerShape(15f))
            .fillMaxWidth(0.9f)
            .wrapContentHeight()
            .background(Color.White)
            .clickable { }
        , verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Column(
            modifier
                .padding(1.dp)
                .fillMaxHeight(0.9f)
                .fillMaxWidth(), verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {
            Box (modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd ) {

                Column(
                    modifier
                        .padding(bottom = 10.dp)
                        .fillMaxWidth()
                        .height(180.dp)
                        .background(ShimmerEffect()),

                ){}
            }

            Column(
                modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .background(ShimmerEffect())) {

            }



        }
    }

}

