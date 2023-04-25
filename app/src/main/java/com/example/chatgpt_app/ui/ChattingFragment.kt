package com.example.chatgpt_app.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatgpt_app.MainActivity
import com.example.chatgpt_app.adapter.chatadapter
import com.example.chatgpt_app.databinding.FragmentChattingBinding
import com.example.chatgpt_app.model.CompletionRequest


class ChattingFragment : Fragment() {
    lateinit var binding: FragmentChattingBinding
    lateinit var chatadapter: chatadapter
    lateinit var viewModel: ChattingViewModel
    val messageList = mutableListOf<MessageHandler>()
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentChattingBinding.inflate(inflater,  container, false)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerview()
        chatadapter = chatadapter(messageList)


        viewModel.ChatGptAnswer.observe(viewLifecycleOwner, Observer {
            if (it.choices.isNotEmpty())
            addToChat(it.choices.get(0).text , MessageHandler.SENT_BY_BOT)
        })

        binding.sendBtn.setOnClickListener {
           val question =  binding.messageEditText.text.toString()
            addToChat(question , MessageHandler.SENT_BY_ME)
            sendToChatGpt(binding.messageEditText.text.toString())
            binding.messageEditText.text.clear()

        }


        // Inflate the layout for this fragment
        return binding.root
    }
    fun addToChat(message: String, sentBy: String) {
        requireActivity().runOnUiThread {
            messageList.add(MessageHandler(message, sentBy))
            // to auto scroll for last message added
            binding.rv.smoothScrollToPosition(chatadapter.itemCount)
            chatadapter.notifyDataSetChanged()
        }
    }

    fun setupRecyclerview(){

        //setup recycler view
        chatadapter = chatadapter(messageList)
        binding.rv.setAdapter(chatadapter)
        val llm = LinearLayoutManager(requireContext())
        llm.stackFromEnd = true
        binding.rv.setLayoutManager(llm)
    }

    fun sendToChatGpt(question : String){
        val request = CompletionRequest(
            model = "text-davinci-003",
            prompt = question,
            max_tokens = 4000,
            temperature = 0f
        )
        viewModel.sendUserQuestion(request)
    }

}