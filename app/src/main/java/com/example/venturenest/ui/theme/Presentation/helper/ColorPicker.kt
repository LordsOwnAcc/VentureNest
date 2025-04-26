package com.example.venturenest.ui.theme.Presentation.helper

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlin.random.Random


fun ColorPicker(): Long {
val colorlist = listOf(
    0xFF22A699,
    0xFFF29727,
    0xFFF24C3D,
    0xFFB349D2,
    0xFFF2BE22
)
    val random = Random.nextInt(0,4)
    return colorlist[random]
}