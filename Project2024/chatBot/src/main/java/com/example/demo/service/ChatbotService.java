package com.example.demo.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatbotService {

    @Autowired
    private ChatModel chatModel;

    public String generateResponse(String inputType) {

        return chatModel.call(inputType);

    }
}
