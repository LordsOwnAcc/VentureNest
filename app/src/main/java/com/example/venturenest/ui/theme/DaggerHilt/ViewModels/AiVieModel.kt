package com.example.venturenest.ui.theme.DaggerHilt.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.venturenest.BuildConfig
import com.example.venturenest.ui.theme.DaggerHilt.Repoitoory.AuthRepo
import com.example.venturenest.ui.theme.DaggerHilt.States.Ai
import com.example.venturenest.ui.theme.DaggerHilt.States.AiStatesCompanion
import com.example.venturenest.ui.theme.DaggerHilt.States.ChatMessage

import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AiViewModel @Inject constructor(
    val repository: AuthRepo
):ViewModel(){

    val _state = MutableStateFlow(Ai())
    val state = _state.asStateFlow()

    private val model = GenerativeModel(
        modelName = "gemini-flash-latest",
        apiKey = BuildConfig.API_KEY,
        systemInstruction = content {
            text("You are VentureNest Bot, an AI assistant for VentureNest, the entrepreneurship and startup incubation center at CGC Jhanjeri. " +
                    "Your role is to provide expert guidance on business-related queries, startup incubation, funding opportunities, mentorship, networking, and VentureNest-specific programs. " +
                    "IMPORTANT: Keep your responses very concise, professional, and brief. Limit your answers to 2-3 sentences unless explicitly asked for a detailed explanation. " +
                    "Always be encouraging but direct.")
        }
    )

    private var chatSession = model.startChat()
    private var generationJob: Job? = null

    fun stopResponse() {
        generationJob?.cancel()
        _state.value = _state.value.copy(
            state = AiStatesCompanion.Idle
        )
    }

     fun generateResponse(prompt: String) {
         val currentHistory = _state.value.chatHistory.toMutableList()
         currentHistory.add(ChatMessage(message = prompt, isUser = true))

         _state.value = _state.value.copy(
             promt = prompt,
             chatHistory = currentHistory,
             state = AiStatesCompanion.Loading
         )

         generationJob = viewModelScope.launch {
             var retryCount = 0
             val maxRetries = 2
             var success = false

             while (retryCount <= maxRetries && !success) {
                 try {
                     val response = chatSession.sendMessage(prompt)
                     val botResponse = response.text ?: "No response received."

                     val updatedHistory = _state.value.chatHistory.toMutableList()
                     updatedHistory.add(ChatMessage(message = botResponse, isUser = false))

                     _state.value = _state.value.copy(
                         state = AiStatesCompanion.Result,
                         result = botResponse,
                         chatHistory = updatedHistory
                     )
                     success = true

                 } catch (e: Exception) {
                     val errorMessage = e.localizedMessage ?: "Unknown error"
                     val isTransient = errorMessage.contains("overloaded", ignoreCase = true) || 
                                     errorMessage.contains("usage", ignoreCase = true) ||
                                     errorMessage.contains("unavailable", ignoreCase = true)

                     if (isTransient && retryCount < maxRetries) {
                         retryCount++
                         delay(1000L * retryCount) // Incremental delay: 1s, 2s
                     } else {
                         _state.value = _state.value.copy(
                             state = AiStatesCompanion.Error,
                             error = errorMessage
                         )
                         break
                     }
                 }
             }
         }
     }





}