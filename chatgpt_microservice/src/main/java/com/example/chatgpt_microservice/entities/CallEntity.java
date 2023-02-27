package com.example.chatgpt_microservice.entities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @AllArgsConstructor@NoArgsConstructor
public class CallEntity {
    private String model;
    private String prompt;
    private Integer max_tokens;
    private Double temperature;
}
