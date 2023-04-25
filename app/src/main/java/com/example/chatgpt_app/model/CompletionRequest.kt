package com.example.chatgpt_app.model

data class CompletionRequest(
    val model: String,
    val prompt: String,
    val max_tokens: Int,
    val temperature: Float,
)
