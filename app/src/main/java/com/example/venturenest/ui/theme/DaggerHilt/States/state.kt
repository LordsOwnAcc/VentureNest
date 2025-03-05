package com.example.venturenest.ui.theme.DaggerHilt.States

import com.example.venturenest.ui.theme.DaggerHilt.heroSection
import com.example.venturenest.ui.theme.DaggerHilt.laptopSection
import java.lang.Error

data class CoulageState(
    val result:List<laptopSection> = emptyList(),
    val error: String?=null,
    val state : CoulageStateCompanion=CoulageStateCompanion.Loading


    )

sealed class CoulageStateCompanion {
    object Loading : CoulageStateCompanion()
object Result : CoulageStateCompanion()
    object Error: CoulageStateCompanion()
}