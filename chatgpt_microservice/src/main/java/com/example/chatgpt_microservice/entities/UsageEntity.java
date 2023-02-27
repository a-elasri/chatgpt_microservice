package com.example.chatgpt_microservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UsageEntity {
    Integer prompt_token;
    Integer completion_tokens;
    Integer total_tokens;
}