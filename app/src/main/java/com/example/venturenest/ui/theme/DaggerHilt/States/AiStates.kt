package com.example.venturenest.ui.theme.DaggerHilt.States

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

data class Ai(
    val promt: String= "",
    val error: String? = "",
    val state: AiStatesCompanion= AiStatesCompanion.Idle,
    val result: String = ""

)

sealed class AiStatesCompanion{
    object Idle: AiStatesCompanion()
    object Loading : AiStatesCompanion()
    object Result : AiStatesCompanion()
    object Error: AiStatesCompanion()
}
