package com.example.chatgpt_microservice;

import com.example.chatgpt_microservice.entities.AnswerEntity;
import com.example.chatgpt_microservice.entities.CallEntity;
import com.example.chatgpt_microservice.entities.ChoiceEntity;
import com.example.chatgpt_microservice.services.ChatgptServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.chatgpt_microservice.dtos.QADto;
import org.mockito.ArgumentCaptor;

import java.io.*;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import com.example.chatgpt_microservice.dtos.QADto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ChatgptMicroserviceApplicationTests {

    @Mock
    private ObjectMapper objectMapper;

    private ChatgptServiceImpl chatgptService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        chatgptService = new ChatgptServiceImpl(objectMapper);
    }

    @Test
    public void testSendPrompt() throws Exception {
        String filePath = "test.csv";
        String prompt = "What is the meaning of life?";
        String expectedAnswer = "The meaning of life is subjective and varies from person to person.";

        AnswerEntity answerEntity = new AnswerEntity();
        ChoiceEntity choiceEntity = new ChoiceEntity();
        choiceEntity.setText(expectedAnswer);
        answerEntity.setChoiceEntities(Collections.singletonList(choiceEntity));

        when(objectMapper.readValue(any(String.class), any(Class.class))).thenReturn(answerEntity);

        QADto actualQADto = chatgptService.sendPrompt(filePath, prompt);

        assertEquals(prompt, actualQADto.getQuestion());
        assertEquals(expectedAnswer, actualQADto.getAnswer());

        File file = new File(filePath);
        assertTrue(file.exists());

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String header = reader.readLine();
        String[] headerColumns = header.split(";");
        assertEquals("Question", headerColumns[0]);
        assertEquals("Answer", headerColumns[1]);

        String line = reader.readLine();
        String[] dataColumns = line.split(";");
        assertEquals(prompt, dataColumns[0]);
        assertEquals(expectedAnswer, dataColumns[1]);

        reader.close();
        file.delete();
    }

    @Test
    public void testWriteToCSV() throws IOException {
        String filePath = "test.csv";
        QADto qaDto = new QADto("What is the meaning of life?", "The meaning of life is subjective and varies from person to person.");

        chatgptService.writeToCSV(filePath, qaDto);

        File file = new File(filePath);
        assertTrue(file.exists());

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String header = reader.readLine();
        String[] headerColumns = header.split(";");
        assertEquals("\"Question", headerColumns[0]);
        assertEquals("Answer", headerColumns[1]);

        String line = reader.readLine();
        String[] dataColumns = line.split(";");
        assertEquals(qaDto.getQuestion(), dataColumns[0]);
        assertEquals(qaDto.getAnswer(), dataColumns[1]);

        reader.close();
        file.delete();
    }
}

