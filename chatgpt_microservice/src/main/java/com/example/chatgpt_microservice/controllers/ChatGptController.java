package com.example.chatgpt_microservice.controllers;

import com.example.chatgpt_microservice.dtos.PromptDto;
import com.example.chatgpt_microservice.dtos.QADto;
import com.example.chatgpt_microservice.services.ChatgptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/")
public class ChatGptController {
    @Autowired
    private ChatgptService service;

    @PostMapping("/response")
    public QADto response(@RequestBody PromptDto prompt) throws Exception {
        return service.sendPrompt("chatgpt",prompt.getPrompt());
    }

}
