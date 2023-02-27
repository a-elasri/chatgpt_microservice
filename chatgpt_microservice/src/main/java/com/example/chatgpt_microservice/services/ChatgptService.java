package com.example.chatgpt_microservice.services;
import com.example.chatgpt_microservice.dtos.QADto;
import java.io.IOException;

public interface ChatgptService {
    String sendChatgptRequest(String body) throws IOException, InterruptedException;
    void writeToCSV(String filePath, QADto QADto);
    QADto sendPrompt(String filePath, String prompt) throws Exception;
}
