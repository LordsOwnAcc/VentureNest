package com.example.venturenest.ui.theme.DaggerHilt.States

import com.example.venturenest.ui.theme.DaggerHilt.AchievementResult
import java.lang.Error

data class Achievementstate(
    val error: String?=null,
    val state :AchievementstateCompanion=AchievementstateCompanion.Loading,
    val result: AchievementResult = AchievementResult(emptyList(), emptyList(), emptyList(),
        emptyList()
    ),
    val statiticsselect: statiticsselected= statiticsselected.startup1
)



sealed class AchievementstateCompanion{
    object Loading :AchievementstateCompanion()
    object Result:AchievementstateCompanion()
    object Error:AchievementstateCompanion()


}

sealed class statiticsselected{
    object startup1: statiticsselected()
    object startup2: statiticsselected()
    object councilMembers: statiticsselected()
    object partnerstat : statiticsselected()
}