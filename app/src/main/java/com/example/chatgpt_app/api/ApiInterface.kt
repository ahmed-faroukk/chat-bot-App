package com.example.chatgpt_app.api

import com.example.chatgpt_app.model.ChatGptResponse
import com.example.chatgpt_app.model.CompletionRequest
import com.example.chatgpt_app.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiInterface {

    @POST("v1/completions")
    @Headers("Authorization: Bearer $API_KEY")
    suspend fun getCompletion(@Body request: CompletionRequest): Response<ChatGptResponse>

}