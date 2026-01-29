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

    init {
        fetchEvents()
    }

    // Fetch events from repository
    fun fetchEvents() {
        viewModelScope.launch {
            try {
                val results = repository.upcominEvents()
                if (results.isSuccessful && results.body() != null) {
                    _events.value = results.body()!!
                }
            } catch (e: Exception) {
                // Handle error silently or log
            }
        }
    }

    // Fetch all photos from repository
    fun fetchAllPhotos() {
        _state.value = _state.value.copy(
            state = GalleryStateCompanion.Loading
        )
        viewModelScope.launch {
            try {
                android.util.Log.d("GalleryViewModel", "Fetching photos from API...")
                val results = repository.getphoto()
                android.util.Log.d("GalleryViewModel", "Response code: ${results.code()}")
                android.util.Log.d("GalleryViewModel", "Response body: ${results.body()}")
                
                if (results.isSuccessful && results.body() != null) {
                    val photos = results.body()!!
                    android.util.Log.d("GalleryViewModel", "Photos count: ${photos.size}")
                    if (photos.isNotEmpty()) {
                        _state.value = _state.value.copy(
                            result = photos,
                            state = GalleryStateCompanion.Result
                        )
                    } else {
                        _state.value = _state.value.copy(
                            state = GalleryStateCompanion.Error,
                            error = "No photos found in database"
                        )
                    }
                } else {
                    val errorBody = results.errorBody()?.string() ?: "Unknown error"
                    android.util.Log.e("GalleryViewModel", "Error: $errorBody")
                    _state.value = _state.value.copy(
                        state = GalleryStateCompanion.Error,
                        error = "Failed to load photos (Code: ${results.code()})"
                    )
                }
            } catch (e: Exception) {
                android.util.Log.e("GalleryViewModel", "Exception: ${e.message}", e)
                _state.value = _state.value.copy(
                    state = GalleryStateCompanion.Error,
                    error = e.localizedMessage ?: "An error occurred"
                )
            }
        }
    }

    // Fetch photos for a specific event (if needed)
    fun fetchPhotosForEvent(eventName: String) {
        _state.value = _state.value.copy(
            state = GalleryStateCompanion.Loading
        )
        viewModelScope.launch {
            try {
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
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    state = GalleryStateCompanion.Error,
                    error = e.localizedMessage ?: "An error occurred"
                )
            }
        }
    }
    
    // Kept for compatibility
    fun fetchPhotos() {
        fetchAllPhotos()
    }
}
