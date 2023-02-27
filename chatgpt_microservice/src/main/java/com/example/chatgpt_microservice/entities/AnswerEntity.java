package com.example.chatgpt_microservice.entities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class AnswerEntity {
    String id;
    String object;
    LocalDate created;
    String model;

    List<ChoiceEntity> choiceEntities;
    UsageEntity usageEntity;
}
