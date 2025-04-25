package com.example.venturenest.ui.theme.DaggerHilt.States

import com.example.venturenest.ui.theme.DaggerHilt.Events

data class EventsState(
    val result:List<Events> = emptyList(),
    val error: String?=null,
    val state : EventsStateCompanion = EventsStateCompanion.Loading,
    val searchState : EventsStateSearching= EventsStateSearching.Starred
)

sealed class EventsStateCompanion {
    object Loading : EventsStateCompanion()
    object Result : EventsStateCompanion()
    object Error: EventsStateCompanion()
}

sealed class EventsStateSearching{
    object Starred : EventsStateSearching()
    object Nonstarred: EventsStateSearching()
    object Latest: EventsStateSearching()
    object AllEvents: EventsStateSearching()
}
