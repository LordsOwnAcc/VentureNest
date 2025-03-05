package com.example.venturenest.ui.theme.DaggerHilt.States

import com.example.venturenest.ui.theme.DaggerHilt.Events

data class EventsState(
    val result:List<Events> = emptyList(),
    val error: String?=null,
    val state : EventsStateCompanion = EventsStateCompanion.Loading
)

sealed class EventsStateCompanion {
    object Loading : EventsStateCompanion()
    object Result : EventsStateCompanion()
    object Error: EventsStateCompanion()
}