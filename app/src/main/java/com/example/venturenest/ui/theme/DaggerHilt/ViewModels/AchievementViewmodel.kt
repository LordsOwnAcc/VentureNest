package com.example.venturenest.ui.theme.DaggerHilt.ViewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.venturenest.ui.theme.DaggerHilt.AchievementResult
import com.example.venturenest.ui.theme.DaggerHilt.Repoitoory.RecipeRepository
import com.example.venturenest.ui.theme.DaggerHilt.States.Achievementstate
import com.example.venturenest.ui.theme.DaggerHilt.States.AchievementstateCompanion
import com.example.venturenest.ui.theme.DaggerHilt.States.HomePageCompanion
import com.example.venturenest.ui.theme.DaggerHilt.States.HomePageResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AchievementViewModel @Inject constructor(
    val repository: RecipeRepository
): ViewModel() {
    val _state = MutableStateFlow(Achievementstate())
    val state = _state.asStateFlow()

    init {
        fetchSuccess()
    }
    fun fetchSuccess(){
        _state.value=_state.value.copy(
            state = AchievementstateCompanion.Loading
        )
        viewModelScope.launch {
            try {
                val results = repository.getCouncilmembe()
                val result2 = repository.getPartners()
                val result3 = repository.getStartups()
                val result4 = repository.getPatents()
                if (results.isSuccessful && results.body()!!
                        .isNotEmpty() && result2.isSuccessful && result2.body()!!.isNotEmpty()
                ) {
                    _state.value = _state.value.copy(
                        result = AchievementResult(
                            patentsList = result4.body()!!,
                            partnersList = result2.body()!!,
                            startUp = result3.body()!!,
                            councilmembers = results.body()!!
                        ),
                        state = AchievementstateCompanion.Result

                    )
                } else {
                    _state.value = _state.value.copy(
                        state = AchievementstateCompanion.Result,
                        error = "Error fetcing from server"
                    )
                }

            } catch (e: Exception) {

                _state.value = _state.value.copy(
                    state = AchievementstateCompanion.Error, error = e.localizedMessage
                )

            }
        }
    }

}