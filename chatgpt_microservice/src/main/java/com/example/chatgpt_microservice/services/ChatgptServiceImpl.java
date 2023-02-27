package com.example.chatgpt_microservice.services;

import com.example.chatgpt_microservice.dtos.QADto;
import com.example.chatgpt_microservice.entities.AnswerEntity;
import com.example.chatgpt_microservice.entities.CallEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

import static org.apache.commons.lang3.ArrayUtils.toStringArray;


@Slf4j
//@Service
@Component
public class ChatgptServiceImpl implements ChatgptService {
    @Value("${openai.apikey}")
    private String openaiApiKey;
    private ObjectMapper jsonMapper;
    @Value("${url}")
    private String URL;
    @Value("${openai.model}")
    private String model;
    @Value("${openai.maxTokens}")
    private Integer max_tokens;
    @Value("${openai.temperature}")
    private Double temperature;
    private final HttpClient client = HttpClient.newHttpClient();
    public ChatgptServiceImpl(ObjectMapper jsonMapper){
        this.jsonMapper=jsonMapper;
    }

    @Override
    public String sendChatgptRequest(String body) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + openaiApiKey)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }

    @Override
    public void writeToCSV(String filePath, QADto QADto) {
        File file = new File(filePath);
        try {
            boolean fileExists = file.exists();
            StringBuilder sb = new StringBuilder();

            FileWriter fileWriter = new FileWriter(file, true);
            CSVWriter writer = new CSVWriter(fileWriter, ';', CSVWriter.DEFAULT_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

            // Write header if file doesn't exist
            if (!fileExists) {
                sb.append("Question").append(";").append("Answer").append("\n");
            }
            sb.append(QADto.getQuestion()).append(";").append(QADto.getAnswer()).append("\n");
            String[] data = {sb.toString()};

            writer.writeNext(data);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public QADto sendPrompt(String filePath, String prompt) throws Exception {
        CallEntity callEntity = new CallEntity(model,prompt,max_tokens,temperature);
        String responseBody = sendChatgptRequest(jsonMapper.writeValueAsString(callEntity));
        AnswerEntity answerEntity = jsonMapper.readValue(responseBody, AnswerEntity.class);
        String text= Optional.of(answerEntity.getChoiceEntities().get(0).getText()).orElseThrow();
        String myString = text.replace("\n", "").replace("\r", "");
        QADto QADto = new QADto(prompt,myString);
        writeToCSV(filePath, QADto);
        return QADto;
    }

}