package com.example.venturenest.ui.theme.Presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.traceEventEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimatable
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.venturenest.R

@Composable
fun Check(modifier: Modifier = Modifier) {
    Column(modifier.fillMaxSize()) {
//        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.work))
//        val progress by animateLottieCompositionAsState(composition, iterations = 500)
//
//        LottieAnimation(composition = composition, progress ={progress},modifier.size(200.dp))
    AsyncImage(model = "https://upload.wikimedia.org/wikipedia/commons/4/4a/Logo_2013_Google.png", contentDescription = "",modifier.fillMaxWidth())
    }
}