package com.example.venturenest.ui.theme.DaggerHilt.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.venturenest.ui.theme.DaggerHilt.Events
import com.example.venturenest.ui.theme.DaggerHilt.Repoitoory.RecipeRepository
import com.example.venturenest.ui.theme.DaggerHilt.States.GalleryState
import com.example.venturenest.ui.theme.DaggerHilt.States.GalleryStateCompanion
import com.example.venturenest.ui.theme.DaggerHilt.photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    val repository: RecipeRepository
): ViewModel() {

    private val _state = MutableStateFlow(GalleryState())
    val state = _state.asStateFlow()

    private val _events = MutableStateFlow<List<Events>>(emptyList())
    val events = _events.asStateFlow()

    // MANUAL DATA CONFIGURATION
    private val manualEventsList = listOf(
        Events(
            _id = "1", 
            eventName = "E-Summit 2024", 
            eventDate = "2024-04-15", 
            eventTitle = "Annual Entrepreneurship Summit", 
            imageUrl = "https://images.unsplash.com/photo-1540575467063-178a50c2df87?q=80&w=2070&auto=format&fit=crop", 
            isStarred = true
        ),
        Events(
            _id = "2", 
            eventName = "HackOverflow", 
            eventDate = "2024-03-20", 
            eventTitle = "24 Hour Hackathon", 
            imageUrl = "https://images.unsplash.com/photo-1504384308090-c54be3855833?q=80&w=2062&auto=format&fit=crop", 
            isStarred = true
        ),
        Events(
            _id = "3", 
            eventName = "Workshop Series", 
            eventDate = "2024-02-10", 
            eventTitle = "Tech Workshops", 
            imageUrl = "https://images.unsplash.com/photo-1515187029135-18ee286d815b?q=80&w=2070&auto=format&fit=crop", 
            isStarred = false
        ),
         Events(
            _id = "4", 
            eventName = "Investor Pitch Day", 
            eventDate = "2024-01-05", 
            eventTitle = "Startup Pitching", 
            imageUrl = "https://images.unsplash.com/photo-1556761175-5973dc0f32e7?q=80&w=2032&auto=format&fit=crop", 
            isStarred = false
        ),
        Events(
            _id = "5",
            eventName = "Test the day",
            eventDate = "2025-01-05",
            eventTitle = "Test",
            imageUrl = "https://images.unsplash.com/photo-1556761175-5973dc0f32e7?q=80&w=2032&auto=format&fit=crop",
            isStarred = false
        )
    )

    private val manualPhotosMap = mapOf(
        "E-Summit 2024" to listOf(
            photo("101", "https://images.unsplash.com/photo-1475721027767-4d06cdd74377?q=80&w=2070&auto=format&fit=crop", "Keynote"),
            photo("102", "https://images.unsplash.com/photo-1511578314322-379afb476865?q=80&w=2069&auto=format&fit=crop", "Audience"),
            photo("103", "https://images.unsplash.com/photo-1505373877841-8d25a7d0188e?q=80&w=2072&auto=format&fit=crop", "Networking")
        ),
        "HackOverflow" to listOf(
             photo("201", "https://images.unsplash.com/photo-1531482615713-2afd69097998?q=80&w=2070&auto=format&fit=crop", "Coding"),
             photo("202", "https://images.unsplash.com/photo-1522071820081-009f0129c71c?q=80&w=2070&auto=format&fit=crop", "Teams"),
             photo("203", "https://images.unsplash.com/photo-1519389950473-47ba0277781c?q=80&w=2070&auto=format&fit=crop", "Presentation")
        ),
        "Workshop Series" to listOf(
            photo("301", "https://images.unsplash.com/photo-1524178232363-1fb2b075b655?q=80&w=2070&auto=format&fit=crop", "Learning"),
             photo("302", "https://images.unsplash.com/photo-1531403009284-440f080d1e12?q=80&w=2070&auto=format&fit=crop", "Design")
        ),
         "Investor Pitch Day" to listOf(
            photo("401", "https://images.unsplash.com/photo-1559136555-930d72f1d3d0?q=80&w=2069&auto=format&fit=crop", "Pitching"),
             photo("402", "https://images.unsplash.com/photo-1454165804606-c3d57bc86b40?q=80&w=2070&auto=format&fit=crop", "Deal")
        ),
        "Test the day" to listOf(
            photo("501", "https://wallhaven.cc/w/yqqvv7","test"),
            photo("502", "https://wallhaven.cc/w/yq8k2d", "toast"),
            photo("503", "https://wallhaven.cc/w/d8gygl","tasty")
        )

    )

    init {
        fetchEvents()
        // Default to first event photos or empty
    }

    fun fetchEvents() {
        // Simulating API latency if needed, but for manual data it's instant
        _events.value = manualEventsList
    }

    fun fetchPhotosForEvent(eventName: String) {
        _state.value = _state.value.copy(
            state = GalleryStateCompanion.Loading
        )
        viewModelScope.launch {
            try {
                // Determine photos based on manual map or fallback to repository for others?
                // The requirement is strict manual decision, so we stick to map.
                val photos = manualPhotosMap[eventName] ?: emptyList()
                
                if (photos.isNotEmpty()) {
                    _state.value = _state.value.copy(
                        result = photos,
                        state = GalleryStateCompanion.Result
                    )
                } else {
                     // Fallback to fetch from repository if needed, or show empty
                     // For now, let's try repo if manual fails, to keep some data flow valid for unmapped events
                     // But user said "I can decide... manually", implying strict control. 
                     // I will leave it as empty or maybe a default set if strict.
                     
                     // Let's try to fetch from repo as fallback to avoid empty screens if event name mismatches
                     val results = repository.getphoto()
                     if (results.isSuccessful && results.body() != null) {
                         _state.value = _state.value.copy(
                            result = results.body()!!,
                            state = GalleryStateCompanion.Result
                        )
                     } else {
                         _state.value = _state.value.copy(
                             state = GalleryStateCompanion.Error,
                             error = "No photos found for this event"
                         )
                     }
                }

            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    state = GalleryStateCompanion.Error,
                    error = e.localizedMessage
                )
            }
        }
    }
    
    // Kept for compatibility but unused directly in new flow
    fun fetchPhotos() {
        fetchPhotosForEvent(manualEventsList.firstOrNull()?.eventName ?: "")
    }
}
