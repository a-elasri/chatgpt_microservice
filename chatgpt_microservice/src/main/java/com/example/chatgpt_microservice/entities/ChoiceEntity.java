package com.example.chatgpt_microservice.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter @Setter @NoArgsConstructor
public class ChoiceEntity {

    private String text;
    private Integer index;
    private Integer logprobs;
    private String finish_reason;
}
