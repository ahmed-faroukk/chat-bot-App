package com.example.chatgpt_app.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatgpt_app.model.ChatGptResponse
import com.example.chatgpt_app.model.CompletionRequest
import com.example.chatgpt_app.repo.repository
import kotlinx.coroutines.launch

class ChattingViewModel(
    val repository : repository
): ViewModel() {

    val ChatGptAnswer =  MutableLiveData<ChatGptResponse>()

    fun sendUserQuestion (completionRequest: CompletionRequest)
    = viewModelScope.launch {
        try {
            val response = repository.getCompletion(completionRequest)
        if(response.isSuccessful) {
                ChatGptAnswer.postValue(response.body())
            }
        } catch (t : Throwable){
            Log.d("responseError" , t.message.toString())
        }
    }
}