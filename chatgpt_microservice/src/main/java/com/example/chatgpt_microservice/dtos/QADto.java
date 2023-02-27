package com.example.chatgpt_microservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class QADto {
    private String question;
    private String answer;
}
