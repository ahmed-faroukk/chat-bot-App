package com.example.chatgpt_app.model

data class Choice(
    val finish_reason: String,
    val index: Int,
    val logprobs: Any,
    val text: String
)