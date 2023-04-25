package com.example.chatgpt_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chatgpt_app.repo.repository
import com.example.chatgpt_app.ui.ChattingViewModel

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: ChattingViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val repository = repository()
        viewModel = ChattingViewModel(repository)

    }
}