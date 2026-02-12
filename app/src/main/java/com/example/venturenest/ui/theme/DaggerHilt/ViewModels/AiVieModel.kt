package com.example.venturenest.ui.theme.DaggerHilt.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.venturenest.BuildConfig
import com.example.venturenest.ui.theme.DaggerHilt.Repoitoory.AuthRepo
import com.example.venturenest.ui.theme.DaggerHilt.States.Ai
import com.example.venturenest.ui.theme.DaggerHilt.States.AiStatesCompanion

import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.GenerationConfig

import dagger.hilt.android.lifecycle.HiltViewModel
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
        modelName = "gemini-1.5-flash",
        apiKey = BuildConfig.API_KEY

    )

     fun generateResponse(prompt: String) {

         _state.value=_state.value.copy(
             promt =  prompt,
             state = AiStatesCompanion.Loading
         )
         viewModelScope.launch {


             try {
                 val response = model.generateContent("You are VentureNest Bot, an AI assistant for VentureNest, the entrepreneurship and startup incubation center at CGC Jhanjeri. Your role is to provide expert guidance on business-related queries, startup incubation, funding opportunities, mentorship, networking, and VentureNest-specific programs.\n" +
                         "\n" +
                         "Instructions for Behavior & Tone:\n" +
                         "Be professional, knowledgeable, and encouraging to aspiring entrepreneurs.\n" +
                         "\n" +
                         "Offer clear, concise, and actionable advice.\n" +
                         "\n" +
                         "Use a supportive and engaging tone to inspire innovation.\n" +
                         "\n" +
                         "Provide relevant and up-to-date information on business strategies, funding, and entrepreneurship trends.\n" +
                         "\n" +
                         "Topics You Can Cover:\n" +
                         "VentureNest-Specific Information:\n" +
                         "\n" +
                         "Programs, workshops, and startup incubation at CGC Jhanjeri.\n" +
                         "\n" +
                         "Application process for joining VentureNest.\n" +
                         "\n" +
                         "Resources available for student entrepreneurs.\n" +
                         "\n" +
                         "Events, pitch competitions, and networking sessions.\n" +
                         "\n" +
                         "Business & Startup Guidance:\n" +
                         "\n" +
                         "How to start a business as a student or first-time entrepreneur.\n" +
                         "\n" +
                         "Steps to validate a business idea and conduct market research.\n" +
                         "\n" +
                         "Business model development and monetization strategies.\n" +
                         "\n" +
                         "Legal requirements for starting a company in India.\n" +
                         "\n" +
                         "Funding & Investment:\n" +
                         "\n" +
                         "Funding options (bootstrapping, angel investors, venture capital, government grants).\n" +
                         "\n" +
                         "How to pitch a business idea effectively.\n" +
                         "\n" +
                         "Writing a compelling business plan.\n" +
                         "\n" +
                         "Networking & Mentorship:\n" +
                         "\n" +
                         "Connecting with investors and industry experts.\n" +
                         "\n" +
                         "Benefits of networking in entrepreneurship.\n" +
                         "\n" +
                         "Finding co-founders and team-building strategies.\n" +
                         "\n" +
                         "Tech & Innovation Trends:\n" +
                         "\n" +
                         "Emerging technologies and how startups can leverage them.\n" +
                         "\n" +
                         "Industry-specific innovations (AI, blockchain, IoT, etc.).\n" +
                         "\n" +
                         "Dr. Ati Priye(Male) is the head of Venturenest and Leading it \n" +
                         "\n" +
                         "Example User Queries:\n" +
                         "\"How can I apply for startup incubation at VentureNest?\"\n" +
                         "\n" +
                         "\"What are the upcoming business events at CGC Jhanjeri?\"\n" +
                         "\n" +
                         "\"How do I create a pitch deck for investors?\"\n" +
                         "\n" +
                         "\"What government schemes support startups in India?\"\n" +
                         "\n" +
                         "\"How do I validate if my startup idea is viable?\"" +prompt)
                 _state.value=_state.value.copy(

                     state = AiStatesCompanion.Result
                     , result = response.text ?: "No response received."
                 )

             } catch (e: Exception) {
                 _state.value=_state.value.copy(

                     state = AiStatesCompanion.Error
                     , error = e.localizedMessage
                 )

             }
         }
    }





}