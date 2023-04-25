package com.example.chatgpt_app.repo

import com.example.chatgpt_app.api.ApiInterface
import com.example.chatgpt_app.api.RetrofitInstance
import com.example.chatgpt_app.model.CompletionRequest

class repository {
    suspend fun getCompletion(completionRequest: CompletionRequest)
    = RetrofitInstance().api.getCompletion(completionRequest)
}